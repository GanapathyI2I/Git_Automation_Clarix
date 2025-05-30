package com.idrx.qa.util;

import java.sql.*;

import com.idrx.qa.base.TestBase;
import com.idrx.qa.util.TestUtil;

public class DBUtil {
    public static String getExpectedValue(String sql, String column) throws Exception {
        String dbUrl = TestBase.prop.getProperty("db.url");
        String dbUser = TestBase.prop.getProperty("db.user");
        String dbPass = TestBase.prop.getProperty("db.pass");
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getString(column);
            } else {
                throw new Exception("No expected data found in DB for the given condition.");
            }
        }
    }

    public static String numberToShortIndianFormat(long num) {
        if (num >= 10000000) {
            double value = num / 10000000.0;
            return String.format("%.2fCr", value).replaceAll("\\.?0+Cr$", "Cr");
        } else if (num >= 100000) {
            double value = num / 100000.0;
            return String.format("%.2fL", value).replaceAll("\\.?0+L$", "L");
        } else if (num >= 1000) {
            double value = num / 1000.0;
            return String.format("%.2fk", value).replaceAll("\\.?0+k$", "k");
        } else {
            return String.valueOf(num);
        }
    }

    // public static void main(String[] args) {
    // System.out.println(numberToShortIndianFormat(2034341)); // 3.25L
    // System.out.println(numberToShortIndianFormat(30000000)); // 3Cr
    // System.out.println(numberToShortIndianFormat(12345678)); // 1.23Cr
    // System.out.println(numberToShortIndianFormat(1500)); // 1.5k
    // System.out.println(numberToShortIndianFormat(999)); // 999
    // }

    public static String vehicleSoldGetDBValue() throws Exception {
        String sql = "SELECT COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') AND qty IN (-1, 0)), 0) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String vehicleSalesTestDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String vehicleBookedYesterdayTestDBValue() throws Exception {
        // need to change the query to get the correct value
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.booking WHERE booking_date = CURRENT_DATE - INTERVAL '1 day' AND booking_status = 'Booked' AND category_type = 'Booking Register'";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String vehicleInvoicedYesterdayTestDBValue() throws Exception {
        // need to change the query to get the correct value
        String sql = "SELECT COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date BETWEEN (CURRENT_DATE - INTERVAL '1 day') AND (CURRENT_DATE - INTERVAL '1 second') ), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date BETWEEN (CURRENT_DATE - INTERVAL '1 day') AND (CURRENT_DATE - INTERVAL '1 second') ), 0) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String vehicleInvoicedThisMonthTestDBValue() throws Exception {
        // need to change the query to get the correct value
        String sql = "SELECT COALESCE(SUM(qty), 0) AS net_qty FROM (SELECT COALESCE(qty, 0) AS qty FROM commondatamodel.vehicles_sales WHERE invoice_date BETWEEN date_trunc('month', CURRENT_DATE) AND (CURRENT_DATE + INTERVAL '1 day' - INTERVAL '1 second') UNION ALL SELECT COALESCE(qty, 0) AS qty FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date BETWEEN date_trunc('month', CURRENT_DATE) AND (CURRENT_DATE + INTERVAL '1 day' - INTERVAL '1 second') ) AS combined;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String vehicleInvoicedButNotDeliveredTestDBValue() throws Exception {
        // need to change the query to get the correct value
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.delivery where created_date = (SELECT MAX(created_date) FROM commondatamodel.delivery )and category_type ='IBND report'";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String currentMonthTestDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < CURRENT_DATE AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < CURRENT_DATE AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String lastMonthTestDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE - INTERVAL '1 month') AND invoice_date < date_trunc('month', CURRENT_DATE) AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE - INTERVAL '1 month') AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    // Discount Page DB Values
    public static String noOfDiscountUnitsGetDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String totalDiscountValueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String totalDiscountQtyGetDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String totalDiscountAmountGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String currentMonthUnitsGetDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String previousMonthUnitsGetDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String currentMonthValueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String previousMonthValueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    // Finance Page DB Values
    public static String noOfFinanceQtyGetDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String financePenetrationGetDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String cashVsFinanceTotalGetDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String currentMonthFinanceTrendGetDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String previousMonthFinanceTrendGetDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String currentMonthFinancePenetrationTrendGetDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String previousMonthFinancePenetrationTrendGetDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    // ServiceRevenue Page DB Values

    public static String serviceRevenueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String avgRevPerVehicleGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String currentMonthRevenueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String previousMonthRevenueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    // ServiceUnits Page DB Values

    public static String noOfVehicleInflowGetDbValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String noOfVehicleOutflowGetDbValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String noOfVehicleInflowYesterdayGetDbValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String noOfVehicleOutflowYesterdayGetDbValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String outflowCurrentMonthGetDbValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String outflowLastMonthGetDbValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    // VehicleStocks Page DB Values

    public static String vehicleQtyGetDbValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String stockValueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String accessoriesAndPartsGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String greaterThan30DaysStocksUnitsGetDbValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String greaterThan60DaysStocksUnitsGetDbValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String greaterThan30DaysStockValueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String greaterThan60DaysStockValueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String currentMonthStocksUnitsGetDbValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String lastMonthStocksUnitsGetDbValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String currentMonthStockValueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String lastMonthStockValueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    // PartStocks Page DB Values

    public static String partsQtyGetDbValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String totalStockValueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String accessoriesAndPartsValueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String excessStockQtyGetDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String excessStockValueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String currentMonthPartsQtyTrendGetDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String lastMonthPartsQtyTrendGetDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String currentMonthPartsValueTrendGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String lastMonthPartsValueTrendGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    // Payables Page DB Values

    public static String totalPayablesGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String mahindraPayablesGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String salesPayablesGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String sparesPayablesGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String othersPayablesGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String greaterThan60DaysPendingPayablesGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String greaterThan30DaysPendingPayablesGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String servicePayablesGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String currentMonthPayablesTrendGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String lastMonthPayablesTrendGetDBValue() throws Exception {
        String sql = "SELECT " +
                "(COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = 1), 0) - " +
                "COALESCE((SELECT SUM(invoice_amount) " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE) + INTERVAL '15 days' + INTERVAL '23 hours 59 minutes 59 seconds' "
                +
                "AND qty = -1), 0)) AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

}
