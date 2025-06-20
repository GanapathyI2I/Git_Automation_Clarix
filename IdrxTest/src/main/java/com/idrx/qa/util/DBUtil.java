package com.idrx.qa.util;

import java.sql.*;

import com.idrx.qa.base.TestBase;

public class DBUtil {
    public static String getExpectedValue(String sql, String column) throws Exception {
        String dbUrl = TestBase.prop.getProperty("db.url");
        // String dbUrlTally = TestBase.prop.getProperty("db.url_tally");
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

    public static String getTallyExpectedValue(String sql, String column) throws Exception {
        String dbUrl = TestBase.prop.getProperty("db.url_tally");
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

    // -----------------Sales/Booking Page DB Values-----------------------
    public static String yesterdayBookingsGetDBValue() throws Exception {
        String sql = "SELECT COUNT(id) AS net_qty " +
                "FROM commondatamodel.booking " +
                "WHERE category_type != 'Pending Bookings' " +
                "AND booking_date >= CURRENT_DATE - INTERVAL '1 day' " +
                "AND booking_date < CURRENT_DATE;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String thisMonthBookingsGetDBValue() throws Exception {
        String sql = "SELECT COUNT(id) AS net_qty " +
                "FROM commondatamodel.booking " +
                "WHERE category_type != 'Pending Bookings' " +
                "AND booking_date >= date_trunc('month', CURRENT_DATE) " +
                "AND booking_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month');";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String pendingBookingsGetDBValue() throws Exception {
        String sql = "SELECT COUNT(id) AS net_qty " +
                "FROM commondatamodel.booking " +
                "WHERE category_type = 'Pending Bookings' " +
                "AND created_date = ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.booking " +
                "WHERE category_type = 'Pending Bookings' " +
                ");";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String previousMonthTestGetDBValue() throws Exception {
        String sql = "SELECT COUNT(id) AS net_qty " +
                "FROM commondatamodel.booking " +
                "WHERE category_type != 'Pending Bookings' " +
                "AND booking_date >= date_trunc('month', CURRENT_DATE - INTERVAL '1 month') " +
                "AND booking_date < date_trunc('month', CURRENT_DATE);";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String thisMonthBookingsTestGetDBValue() throws Exception {
        String sql = "SELECT COUNT(id) AS net_qty " +
                "FROM commondatamodel.booking " +
                "WHERE category_type != 'Pending Bookings' " +
                "AND booking_date >= date_trunc('month', CURRENT_DATE) " +
                "AND booking_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month');";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String salesmanWisePendingBookingsTestGetDBValue() throws Exception {
        String sql = "SELECT COUNT(id) AS net_qty " +
                "FROM commondatamodel.booking " +
                "WHERE category_type = 'Pending Bookings' " +
                "AND created_date = ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.booking " +
                "WHERE category_type = 'Pending Bookings' " +
                ");";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    // ------------------------Sales/ Retail Page DB
    // Values--------------------------
    public static String vehicleSoldGetDBValue() throws Exception {
        String sql = "SELECT COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') AND qty = 1), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') AND qty IN (-1, 0)), 0) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String vehicleSalesTestDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(invoice_amount) FROM commondatamodel.vehicles_sales WHERE invoice_date >= date_trunc('month', CURRENT_DATE) AND invoice_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') AND qty = 1), 0) - COALESCE((SELECT SUM(invoice_amount) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date >= date_trunc('month', CURRENT_DATE) AND invoice_cancellation_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') AND qty = -1), 0)) AS net_qty;";
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

    // -----------------------------Sales/Delivery----------------------------------
    public static String deliveriesYesterdayTestDBValue() throws Exception {
        String sql = "SELECT count (id) AS net_qty " +
                "FROM commondatamodel.delivery " +
                "WHERE delivery_date = CURRENT_DATE - INTERVAL '1 day' " +
                "and category_type = 'Vehicle delivery note';";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String deliveriesThisMonthTestDBValue() throws Exception {
        String sql = "SELECT  count (id) AS net_qty " +
                "FROM commondatamodel.delivery " +
                "WHERE delivery_date BETWEEN date_trunc('month', CURRENT_DATE) AND CURRENT_DATE - INTERVAL '1 day' " +
                "and category_type = 'Vehicle delivery note';";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String pendingDeliveriesTestDBValue() throws Exception {
        String sql = "SELECT COUNT(id) AS net_qty " +
                "FROM commondatamodel.delivery " +
                "WHERE category_type != 'Vehicle delivery note' " +
                "AND created_date = ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.delivery " +
                "WHERE category_type != 'Vehicle delivery note')";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String deliveriesPreviousMonthTestDBValue() throws Exception {
        String sql = "SELECT count (id) AS net_qty " +
                "FROM commondatamodel.delivery " +
                "WHERE delivery_date BETWEEN date_trunc('month', CURRENT_DATE - INTERVAL '1 month') " +
                "AND date_trunc('month', CURRENT_DATE) - INTERVAL '1 day' " +
                "AND category_type = 'Vehicle delivery note';";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String deliveriesCurrentMonthTestDBValue() throws Exception {
        String sql = "SELECT  count (id) AS net_qty " +
                "FROM commondatamodel.delivery " +
                "WHERE delivery_date BETWEEN date_trunc('month', CURRENT_DATE) AND CURRENT_DATE - INTERVAL '1 day' " +
                "AND category_type = 'Vehicle delivery note';";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String salesmanWisePendingDeliveriesTestDBValue() throws Exception {
        String sql = "SELECT COUNT(id) AS net_qty " +
                "FROM commondatamodel.delivery " +
                "WHERE category_type != 'Vehicle delivery note' " +
                "AND created_date = ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.delivery " +
                "WHERE category_type != 'Vehicle delivery note')";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    // Discount Page DB Values
    public static String noOfDiscountUnitsGetDBValue() throws Exception {
        String sql = "SELECT " +
                "SUM(qty) AS net_qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date) AND current_date " +
                "AND qty = '1' " +
                "AND GREATEST( " +
                "COALESCE(total_corporate_disc, 0), " +
                "COALESCE(dealer_disc, 0), " +
                "COALESCE(oem_scheme_discount, 0), " +
                "COALESCE(cpc_csd_disc_amount, 0) " +
                ") > 0;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String totalDiscountValueGetDBValue() throws Exception {
        String sql = "SELECT SUM( " +
                "COALESCE(total_corporate_disc, 0) + " +
                "COALESCE(dealer_disc, 0) + " +
                "COALESCE(oem_scheme_discount, 0) + " +
                "COALESCE(cpc_csd_disc_amount, 0) " +
                ") AS net_qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date) AND current_date " +
                "AND qty = '1' " +
                "AND GREATEST( " +
                "COALESCE(total_corporate_disc, 0), " +
                "COALESCE(dealer_disc, 0), " +
                "COALESCE(oem_scheme_discount, 0), " +
                "COALESCE(cpc_csd_disc_amount, 0) " +
                ") > 0;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String totalDiscountQtyGetDBValue() throws Exception {
        String sql = "SELECT " +
                "SUM(qty) AS net_qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date) AND current_date " +
                "AND qty = '1' " +
                "AND GREATEST( " +
                "COALESCE(total_corporate_disc, 0), " +
                "COALESCE(dealer_disc, 0), " +
                "COALESCE(oem_scheme_discount, 0), " +
                "COALESCE(cpc_csd_disc_amount, 0) " +
                ") > 0;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String totalDiscountAmountGetDBValue() throws Exception {
        String sql = "SELECT SUM( " +
                "COALESCE(total_corporate_disc, 0) + " +
                "COALESCE(dealer_disc, 0) + " +
                "COALESCE(oem_scheme_discount, 0) + " +
                "COALESCE(cpc_csd_disc_amount, 0) " +
                ") AS net_qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date) AND current_date " +
                "AND qty = '1' " +
                "AND GREATEST( " +
                "COALESCE(total_corporate_disc, 0), " +
                "COALESCE(dealer_disc, 0), " +
                "COALESCE(oem_scheme_discount, 0), " +
                "COALESCE(cpc_csd_disc_amount, 0) " +
                ") > 0;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String currentMonthUnitsGetDBValue() throws Exception {
        String sql = "SELECT " +
                "SUM(qty) AS net_qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date) AND current_date " +
                "AND qty = '1' " +
                "AND GREATEST( " +
                "COALESCE(total_corporate_disc, 0), " +
                "COALESCE(dealer_disc, 0), " +
                "COALESCE(oem_scheme_discount, 0), " +
                "COALESCE(cpc_csd_disc_amount, 0) " +
                ") > 0;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String previousMonthUnitsGetDBValue() throws Exception {
        String sql = "SELECT " +
                "SUM(qty) AS net_qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date - INTERVAL '1 month') " +
                "AND date_trunc('month', current_date) - INTERVAL '1 day' " +
                "AND qty = '1' " +
                "AND GREATEST( " +
                "COALESCE(total_corporate_disc, 0), " +
                "COALESCE(dealer_disc, 0), " +
                "COALESCE(oem_scheme_discount, 0), " +
                "COALESCE(cpc_csd_disc_amount, 0) " +
                ") > 0;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String currentMonthValueGetDBValue() throws Exception {
        String sql = "SELECT SUM( " +
                "COALESCE(total_corporate_disc, 0) + " +
                "COALESCE(dealer_disc, 0) + " +
                "COALESCE(oem_scheme_discount, 0) + " +
                "COALESCE(cpc_csd_disc_amount, 0) " +
                ") AS net_qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date) AND current_date " +
                "AND qty = '1' " +
                "AND GREATEST( " +
                "COALESCE(total_corporate_disc, 0), " +
                "COALESCE(dealer_disc, 0), " +
                "COALESCE(oem_scheme_discount, 0), " +
                "COALESCE(cpc_csd_disc_amount, 0) " +
                ") > 0;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String previousMonthValueGetDBValue() throws Exception {
        String sql = "SELECT SUM( " +
                "COALESCE(total_corporate_disc, 0) + " +
                "COALESCE(dealer_disc, 0) + " +
                "COALESCE(oem_scheme_discount, 0) + " +
                "COALESCE(cpc_csd_disc_amount, 0) " +
                ") AS net_qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date - INTERVAL '1 month') " +
                "AND date_trunc('month', current_date) - INTERVAL '1 day' " +
                "AND qty = '1' " +
                "AND GREATEST( " +
                "COALESCE(total_corporate_disc, 0), " +
                "COALESCE(dealer_disc, 0), " +
                "COALESCE(oem_scheme_discount, 0), " +
                "COALESCE(cpc_csd_disc_amount, 0) " +
                ") > 0;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    // Finance Page DB Values
    public static String noOfFinanceQtyGetDBValue() throws Exception {
        String sql = "SELECT SUM(qty) AS net_qty " +
                "FROM ( " +
                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date) AND current_date " +
                "AND qty = 1 " +
                "AND financier != 'CASH' " +

                "UNION ALL " +

                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date BETWEEN date_trunc('month', current_date) AND current_date " +
                "AND financier != 'CASH' " +
                ") AS combined;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String financePenetrationGetDBValue() throws Exception {
        String sql = "SELECT " +
                "ROUND(100.0 * filtered.total_qty::NUMERIC / NULLIF(total.total_qty, 0), 2) AS net_qty " +
                "FROM ( " +
                "SELECT SUM(qty) AS total_qty " +
                "FROM ( " +
                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date) AND current_date " +
                "AND qty = 1 " +
                "AND financier != 'CASH' " +

                "UNION ALL " +

                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date BETWEEN date_trunc('month', current_date) AND current_date " +
                "AND financier != 'CASH' " +
                ") AS combined " +
                ") AS filtered, " +
                "( " +
                "SELECT SUM(qty) AS total_qty " +
                "FROM ( " +
                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date) " +
                "AND (date_trunc('month', current_date) + INTERVAL '1 month - 1 second') " +
                "AND qty = 1 " +

                "UNION ALL " +

                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date BETWEEN date_trunc('month', current_date) " +
                "AND (date_trunc('month', current_date) + INTERVAL '1 month - 1 second') " +
                "AND qty = -1 " +
                ") AS combined " +
                ") AS total;";
        ;
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty") + "%";
        return dbValue;
    }

    public static String cashVsFinanceTotalGetDBValue() throws Exception {
        String sql = "SELECT SUM(qty) AS net_qty " +
                "FROM ( " +
                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date) " +
                "AND (date_trunc('month', current_date) + INTERVAL '1 month - 1 second') " +
                "AND qty = 1 " +

                "UNION ALL " +

                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date BETWEEN date_trunc('month', current_date) " +
                "AND (date_trunc('month', current_date) + INTERVAL '1 month - 1 second') " +
                "AND qty = -1 " +
                ") AS combined;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String currentMonthFinanceTrendGetDBValue() throws Exception {
        String sql = "SELECT SUM(qty) AS net_qty " +
                "FROM ( " +
                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date) AND current_date " +
                "AND qty = 1 " +
                "AND financier != 'CASH' " +

                "UNION ALL " +

                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date BETWEEN date_trunc('month', current_date) AND current_date " +
                "AND financier != 'CASH' " +
                ") AS combined;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String previousMonthFinanceTrendGetDBValue() throws Exception {
        String sql = "SELECT SUM(qty) AS net_qty " +
                "FROM ( " +
                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date - INTERVAL '1 month') " +
                "AND date_trunc('month', current_date) - INTERVAL '1 second' " +
                "AND qty = 1 " +
                "AND financier != 'CASH' " +

                "UNION ALL " +

                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "   WHERE invoice_cancellation_date BETWEEN date_trunc('month', current_date - INTERVAL '1 month') " +
                "AND date_trunc('month', current_date) - INTERVAL '1 second' " +
                "AND financier != 'CASH' " +
                ") AS combined;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String currentMonthFinancePenetrationTrendGetDBValue() throws Exception {
        String sql = "SELECT " +
                "ROUND(100.0 * filtered.total_qty::NUMERIC / NULLIF(total.total_qty, 0), 2) AS net_qty " +
                "FROM ( " +
                "SELECT SUM(qty) AS total_qty " +
                "FROM ( " +
                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date) AND current_date " +
                "AND qty = 1 " +
                "AND financier != 'CASH' " +

                "UNION ALL " +

                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date BETWEEN date_trunc('month', current_date) AND current_date " +
                "AND financier != 'CASH' " +
                ") AS combined " +
                ") AS filtered, " +
                "( " +
                "SELECT SUM(qty) AS total_qty " +
                "FROM ( " +
                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date) " +
                "AND (date_trunc('month', current_date) + INTERVAL '1 month - 1 second') " +
                "AND qty = 1 " +

                "UNION ALL " +

                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date BETWEEN date_trunc('month', current_date) " +
                "AND (date_trunc('month', current_date) + INTERVAL '1 month - 1 second') " +
                "AND qty = -1 " +
                ") AS combined " +
                ") AS total;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        int rounded = (int) Math.round(Double.parseDouble(dbValue));
        return String.valueOf(rounded) + "%";
    }

    public static String previousMonthFinancePenetrationTrendGetDBValue() throws Exception {
        String sql = "SELECT " +
                "ROUND(100.0 * filtered.total_qty::NUMERIC / NULLIF(total.total_qty, 0), 0) AS percentage " +
                "FROM ( " +
                "SELECT SUM(qty) AS total_qty " +
                "FROM ( " +
                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date - INTERVAL '1 month') " +
                "AND date_trunc('month', current_date) - INTERVAL '1 second' " +
                "AND qty = 1 " +
                "AND financier != 'CASH' " +

                "UNION ALL " +

                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date BETWEEN date_trunc('month', current_date - INTERVAL '1 month') " +
                "AND date_trunc('month', current_date) - INTERVAL '1 second' " +
                "AND financier != 'CASH' " +
                ") AS combined " +
                ") AS filtered, " +
                "( " +
                "SELECT SUM(qty) AS total_qty " +
                "FROM ( " +
                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_date BETWEEN date_trunc('month', current_date - INTERVAL '1 month') " +
                "AND date_trunc('month', current_date) - INTERVAL '1 second' " +
                "AND qty = 1 " +

                "UNION ALL " +

                "SELECT qty " +
                "FROM commondatamodel.vehicles_sales " +
                "WHERE invoice_cancellation_date BETWEEN date_trunc('month', current_date - INTERVAL '1 month') " +
                "AND date_trunc('month', current_date) - INTERVAL '1 second' " +
                "AND qty = -1 " +
                ") AS combined " +
                ") AS total;";
        String dbValue = DBUtil.getExpectedValue(sql, "percentage");
        int rounded = (int) Math.round(Double.parseDouble(dbValue));
        return String.valueOf(rounded) + "%";
    }

    // -----------------------------ServiceRevenue Page DB
    // Values-----------------------------

    // ServiceRevenue Page DB Values

    public static String serviceRevenueGetDBValue() throws Exception {
        String sql = "SELECT SUM(bill_amount) as net_qty " +
                "FROM commondatamodel.service_inflow_outflow " +
                "WHERE service_category_type = 'Service Outflow' " +
                "AND bill_date BETWEEN date_trunc('month', current_date) " +
                "AND (date_trunc('month', current_date) + INTERVAL '1 month - 1 second');";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String avgRevPerVehicleGetDBValue() throws Exception {
        String sql = "SELECT " +
                "ROUND(total_bill_amount / NULLIF(jobcard_count, 0), 2) AS net_qty " +
                "FROM ( " +
                "SELECT " +
                "SUM(bill_amount) AS total_bill_amount, " +
                "COUNT(DISTINCT jobcard_no) AS jobcard_count " +
                "FROM commondatamodel.service_inflow_outflow " +
                "WHERE service_category_type = 'Service Outflow' " +
                "AND bill_date BETWEEN date_trunc('month', current_date) " +
                "AND (date_trunc('month', current_date) + INTERVAL '1 month - 1 second') " +
                ") AS summary;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormatTwoDecimal(num);
        return numConversion;
    }

    public static String currentMonthRevenueGetDBValue() throws Exception {
        String sql = "SELECT SUM(bill_amount) as net_qty " +
                "FROM commondatamodel.service_inflow_outflow " +
                "WHERE service_category_type = 'Service Outflow' " +
                "AND bill_date BETWEEN date_trunc('month', current_date) " +
                "AND (date_trunc('month', current_date) + INTERVAL '1 month - 1 second');";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String previousMonthRevenueGetDBValue() throws Exception {
        String sql = "SELECT SUM(bill_amount) as net_qty " +
                "FROM commondatamodel.service_inflow_outflow " +
                "WHERE service_category_type = 'Service Outflow' " +
                "AND bill_date BETWEEN date_trunc('month', current_date - INTERVAL '1 month') " +
                "AND date_trunc('month', current_date) - INTERVAL '1 second';";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    // --------------Service/Parts Sales----------------
    public static String noofQtyTestDBValue() throws Exception {
        String sql = "SELECT SUM(lss_qty) AS net_qty " +
                "FROM commondatamodel.parts_sales " +
                "WHERE doc_date >= date_trunc('month', CURRENT_DATE) " +
                "AND doc_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                "AND doc_type IN ( " +
                "'Workshop Issue', 'Stock Transfer Issue', " +
                "'Counter Sale-Credit', 'Counter Sale-Cheque', 'Counter Sale-Cash' " +
                ");";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String partsSalesTestDBValue() throws Exception {
        String sql = "SELECT SUM(total_value) AS net_qty " +
                "FROM commondatamodel.parts_sales " +
                "WHERE doc_date >= date_trunc('month', CURRENT_DATE) " +
                "AND doc_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                "AND doc_type IN ( " +
                "'Workshop Issue', 'Stock Transfer Issue', " +
                "'Counter Sale-Credit', 'Counter Sale-Cheque', 'Counter Sale-Cash' " +
                ");";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormatOneDecimal(num);
        return numConversion;
    }

    public static String thisMonthTestDBValue() throws Exception {
        String sql = "SELECT SUM(total_value) AS net_qty " +
                "FROM commondatamodel.parts_sales " +
                "WHERE doc_date >= date_trunc('month', CURRENT_DATE) " +
                "AND doc_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                "AND doc_type IN ( " +
                "'Workshop Issue', 'Stock Transfer Issue', " +
                "'Counter Sale-Credit', 'Counter Sale-Cheque', 'Counter Sale-Cash' " +
                ");";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormatInCr(num);
        return numConversion;
    }

    public static String previousMonthTestDBValue() throws Exception {
        String sql = "SELECT SUM(total_value) AS net_qty " +
                "FROM commondatamodel.parts_sales " +
                "WHERE doc_date >= date_trunc('month', CURRENT_DATE - INTERVAL '1 month') " +
                "AND doc_date < date_trunc('month', CURRENT_DATE) " +
                "AND doc_type IN ( " +
                "'Workshop Issue', 'Stock Transfer Issue', " +
                "'Counter Sale-Credit', 'Counter Sale-Cheque', 'Counter Sale-Cash' " +
                ");";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormatOneDecimal(num);
        return numConversion;
    }

    // ----------------------Billing and Purchase------------------------
    public static String vehicleQtyDBValue() throws Exception {
        String sql = "SELECT COUNT(qty) AS net_qty " +
                "FROM commondatamodel.purchase " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                "AND item_type = 'Vehicles';";
        String dbvalue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbvalue;
    }

    public static String totalPurchaseValueDBValue() throws Exception {
        String sql = "SELECT " +
                "(" +
                "  (SELECT COALESCE(SUM(total_part_value), 0) " +
                "   FROM commondatamodel.purchase " +
                "   WHERE grn_date >= date_trunc('month', CURRENT_DATE) " +
                "     AND grn_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                "     AND item_type != 'Vehicles') " +
                "  + " +
                "  (SELECT COALESCE(SUM(total_part_value), 0) " +
                "   FROM commondatamodel.purchase " +
                "   WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "     AND invoice_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                "     AND item_type = 'Vehicles') " +
                ") AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormatOneDecimal(num);
        return numConversion;
    }

    public static String unitsCurrentMonthDBValue() throws Exception {
        String sql = "SELECT COUNT(qty) AS net_qty " +
                "FROM commondatamodel.purchase " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                "AND item_type = 'Vehicles';";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String unitsPastMonthDBValue() throws Exception {
        String sql = "SELECT COUNT(qty) AS net_qty " +
                "FROM commondatamodel.purchase " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE - INTERVAL '1 month') " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) " +
                "AND item_type = 'Vehicles';";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String totalValueCurrentMonthDBValue() throws Exception {
        String sql = "SELECT " +
                "(" +
                "  (SELECT COALESCE(SUM(total_part_value), 0) " +
                "   FROM commondatamodel.purchase " +
                "   WHERE grn_date >= date_trunc('month', CURRENT_DATE) " +
                "     AND grn_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                "     AND item_type != 'Vehicles') " +
                "  + " +
                "  (SELECT COALESCE(SUM(total_part_value), 0) " +
                "   FROM commondatamodel.purchase " +
                "   WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "     AND invoice_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                "     AND item_type = 'Vehicles') " +
                ") AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormatOneDecimal(num);
        return numConversion;
    }

    public static String totalValuePastMonthDBValue() throws Exception {
        String sql = "SELECT  " +
                "(" +
                "(SELECT COALESCE(SUM(total_part_value), 0) " +
                "FROM commondatamodel.purchase " +
                "WHERE grn_date >= date_trunc('month', CURRENT_DATE - INTERVAL '1 month') " +
                "AND grn_date < date_trunc('month', CURRENT_DATE) " +
                "AND item_type != 'Vehicles') " +
                "+ " +
                "(SELECT COALESCE(SUM(total_part_value), 0) " +
                "FROM commondatamodel.purchase " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE - INTERVAL '1 month') " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE) " +
                "AND item_type = 'Vehicles') " +
                ") AS net_qty;";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormatThreeDigits(num);
        return numConversion;
    }

    public static String topVehicleQtyTotalDBValue() throws Exception {
        String sql = "SELECT COUNT(qty) AS net_qty " +
                "FROM commondatamodel.purchase " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                "AND item_type = 'Vehicles';";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String topVehiclePurchaseValueTotalDBValue() throws Exception {
        String sql = "SELECT SUM(total_part_value) AS net_qty " +
                "FROM commondatamodel.purchase " +
                "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) " +
                "AND invoice_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                "AND item_type = 'Vehicles';";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String partsAccessoriesQtyTotalDBValue() throws Exception {
        String sql = "SELECT SUM(qty) AS total_qty " +
                "FROM commondatamodel.purchase " +
                "WHERE grn_date >= date_trunc('month', CURRENT_DATE) " +
                "AND grn_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                "AND item_type != 'Vehicles';";
        String dbValue = DBUtil.getExpectedValue(sql, "total_qty");
        return dbValue;
    }

    public static String partsAccessoriesPurchaseValueTotalDBValue() throws Exception {
        String sql = "SELECT SUM(total_part_value) AS net_qty " +
                "FROM commondatamodel.purchase " +
                "WHERE grn_date >= date_trunc('month', CURRENT_DATE) " +
                "AND grn_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') " +
                "AND item_type != 'Vehicles';";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormatInLakh(num);
        return numConversion;
    }

    // ----------------New Order Vehicles----------------
    public static String vehicleNameFirstRowDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String averageMonthlySalesQtyFirstRowDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String currentStockFirstRowDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String vehicleNameSecondRowDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String averageMonthlySalesQtySecondRowDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String currentStockSecondRowDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    // -----------------New Order P & A----------------
    public static String partsNameFirstRowDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String partNoFirstRowDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String newOrderP_AAverageMonthlySalesQtyFirstRowDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String newOrderP_AcurrentStockFirstRowDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String partsNameSecondRowDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String partNoSecondRowDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String newOrderP_AAverageMonthlySalesQtySecondRowDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String newOrderP_ACurrentStockSecondRowDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    // ------------------Receivables/ Receivables----------------------
    public static String creditOutstandingDBValue() throws Exception {
        String sql = "WITH RECURSIVE date_ranges AS ( "
                + "SELECT "
                + "CAST(DATE '2024-04-01' AS DATE) AS start_date, "
                + "CAST(DATE_TRUNC('month', DATE '2024-04-01' + INTERVAL '1 month') - INTERVAL '1 day' AS DATE) AS end_date "
                + "UNION ALL "
                + "SELECT "
                + "CAST(DATE '2024-04-01' AS DATE), "
                + "CAST(DATE_TRUNC('month', end_date + INTERVAL '1 day' + INTERVAL '1 month') - INTERVAL '1 day' AS DATE) "
                + "FROM date_ranges "
                + "WHERE end_date < DATE '2026-03-31' "
                + "), "
                + "null_date_totals AS ( "
                + "SELECT "
                + "0 AS total_null_date_positive, "
                + "0 AS total_null_date_negative "
                + "), "
                + "monthly_totals AS ( "
                + "    SELECT "
                + "        TO_CHAR(end_date, 'Mon YYYY') AS month, "
                + "        ( "
                + "            COALESCE(( "
                + "                SELECT SUM(total_amount) "
                + "                FROM ( "
                + "                    SELECT "
                + "                        ledger, "
                + "                        SUM(amount) AS total_amount "
                + "                    FROM ( "
                + "                        SELECT ledger, amount "
                + "                        FROM common_view "
                + "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') "
                + "                          AND date BETWEEN start_date AND end_date "
                + "                        UNION ALL "
                + "                        SELECT ledger, amount "
                + "                        FROM common_view "
                + "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') "
                + "                          AND date IS NULL "
                + "                    ) AS combined_data "
                + "                    GROUP BY ledger "
                + "                    HAVING SUM(amount) > 0 "
                + "                ) AS final_data "
                + "            ), 0) "
                + "            + "
                + "            (SELECT total_null_date_positive FROM null_date_totals) "
                + "        ) AS total_positive, "
                + "        ( "
                + "            COALESCE(( "
                + "                SELECT "
                + "                    SUM(total_amount) "
                + "                FROM ( "
                + "                    SELECT "
                + "                        ledger, "
                + "                        SUM(amount) AS total_amount "
                + "                    FROM ( "
                + "                        SELECT ledger, amount "
                + "                        FROM common_view "
                + "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') "
                + "                          AND date BETWEEN start_date AND end_date "
                + "                        UNION ALL "
                + "                        SELECT ledger, amount "
                + "                        FROM common_view "
                + "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') "
                + "                          AND date IS NULL "
                + "                    ) AS combined_data "
                + "                    GROUP BY ledger "
                + "                    HAVING SUM(amount) < 0 "
                + "                ) AS final_data "
                + "            ), 0) "
                + "            + (SELECT total_null_date_negative FROM null_date_totals) "
                + "        ) AS total_negative, "
                + "        start_date "
                + "    FROM date_ranges "
                + ") "
                + "SELECT "
                + "    month, "
                + "    total_negative AS net_qty "
                + "FROM monthly_totals "
                + "WHERE month = TO_CHAR(CURRENT_DATE, 'Mon YYYY') "
                + "ORDER BY start_date;";
        String dbValue = DBUtil.getTallyExpectedValue(sql, "net_qty");
        double num = Math.abs(Double.parseDouble(dbValue));
        String numConversion = TestUtil.numberToShortIndianFormatOneDecimal(num);
        return numConversion;
    }

    public static String mahindraDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String salesDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String serviceDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String supplierAdvanceDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String othersDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String receivablesTrentCurrentMonthDBValue() throws Exception {
        String sql = "WITH RECURSIVE date_ranges AS ( "
                + "SELECT "
                + "CAST(DATE '2024-04-01' AS DATE) AS start_date, "
                + "CAST(DATE_TRUNC('month', DATE '2024-04-01' + INTERVAL '1 month') - INTERVAL '1 day' AS DATE) AS end_date "
                + "UNION ALL "
                + "SELECT "
                + "CAST(DATE '2024-04-01' AS DATE), "
                + "CAST(DATE_TRUNC('month', end_date + INTERVAL '1 day' + INTERVAL '1 month') - INTERVAL '1 day' AS DATE) "
                + "FROM date_ranges "
                + "WHERE end_date < DATE '2026-03-31' "
                + "), "
                + "null_date_totals AS ( "
                + "SELECT "
                + "0 AS total_null_date_positive, "
                + "0 AS total_null_date_negative "
                + "), "
                + "monthly_totals AS ( "
                + "    SELECT "
                + "        TO_CHAR(end_date, 'Mon YYYY') AS month, "
                + "        ( "
                + "            COALESCE(( "
                + "                SELECT SUM(total_amount) "
                + "                FROM ( "
                + "                    SELECT "
                + "                        ledger, "
                + "                        SUM(amount) AS total_amount "
                + "                    FROM ( "
                + "                        SELECT ledger, amount "
                + "                        FROM common_view "
                + "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') "
                + "                          AND date BETWEEN start_date AND end_date "
                + "                        UNION ALL "
                + "                        SELECT ledger, amount "
                + "                        FROM common_view "
                + "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') "
                + "                          AND date IS NULL "
                + "                    ) AS combined_data "
                + "                    GROUP BY ledger "
                + "                    HAVING SUM(amount) > 0 "
                + "                ) AS final_data "
                + "            ), 0) "
                + "            + "
                + "            (SELECT total_null_date_positive FROM null_date_totals) "
                + "        ) AS total_positive, "
                + "        ( "
                + "            COALESCE(( "
                + "                SELECT "
                + "                    SUM(total_amount) "
                + "                FROM ( "
                + "                    SELECT "
                + "                        ledger, "
                + "                        SUM(amount) AS total_amount "
                + "                    FROM ( "
                + "                        SELECT ledger, amount "
                + "                        FROM common_view "
                + "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') "
                + "                          AND date BETWEEN start_date AND end_date "
                + "                        UNION ALL "
                + "                        SELECT ledger, amount "
                + "                        FROM common_view "
                + "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') "
                + "                          AND date IS NULL "
                + "                    ) AS combined_data "
                + "                    GROUP BY ledger "
                + "                    HAVING SUM(amount) < 0 "
                + "                ) AS final_data "
                + "            ), 0) "
                + "            + (SELECT total_null_date_negative FROM null_date_totals) "
                + "        ) AS total_negative, "
                + "        start_date "
                + "    FROM date_ranges "
                + ") "
                + "SELECT "
                + "    month, "
                + "    total_negative AS net_qty "
                + "FROM monthly_totals "
                + "WHERE month = TO_CHAR(CURRENT_DATE, 'Mon YYYY') "
                + "ORDER BY start_date;";
        String dbValue = DBUtil.getTallyExpectedValue(sql, "net_qty");
        double num = Math.abs(Double.parseDouble(dbValue));
        String numConversion = TestUtil.numberToShortIndianFormatOneDecimal(num);
        return numConversion;
    }

    public static String receivablesTrentPastMonthDBValue() throws Exception {
        String sql = "WITH RECURSIVE date_ranges AS ( "
                + "SELECT "
                + "CAST(DATE '2024-04-01' AS DATE) AS start_date, "
                + "CAST(DATE_TRUNC('month', DATE '2024-04-01' + INTERVAL '1 month') - INTERVAL '1 day' AS DATE) AS end_date "
                + "UNION ALL "
                + "SELECT "
                + "CAST(DATE '2024-04-01' AS DATE), "
                + "CAST(DATE_TRUNC('month', end_date + INTERVAL '1 day' + INTERVAL '1 month') - INTERVAL '1 day' AS DATE) "
                + "FROM date_ranges "
                + "WHERE end_date < DATE '2026-03-31' "
                + "), "
                + "null_date_totals AS ( "
                + "SELECT "
                + "0 AS total_null_date_positive, "
                + "0 AS total_null_date_negative "
                + "), "
                + "monthly_totals AS ( "
                + "    SELECT "
                + "        TO_CHAR(end_date, 'Mon YYYY') AS month, "
                + "        ( "
                + "            COALESCE(( "
                + "                SELECT SUM(total_amount) "
                + "                FROM ( "
                + "                    SELECT "
                + "                        ledger, "
                + "                        SUM(amount) AS total_amount "
                + "                    FROM ( "
                + "                        SELECT ledger, amount "
                + "                        FROM common_view "
                + "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') "
                + "                          AND date BETWEEN start_date AND end_date "
                + "                        UNION ALL "
                + "                        SELECT ledger, amount "
                + "                        FROM common_view "
                + "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') "
                + "                          AND date IS NULL "
                + "                    ) AS combined_data "
                + "                    GROUP BY ledger "
                + "                    HAVING SUM(amount) > 0 "
                + "                ) AS final_data "
                + "            ), 0) "
                + "            + (SELECT total_null_date_positive FROM null_date_totals) "
                + "        ) AS total_positive, "
                + "        ( "
                + "            COALESCE(( "
                + "                SELECT SUM(total_amount) "
                + "                FROM ( "
                + "                    SELECT "
                + "                        ledger, "
                + "                        SUM(amount) AS total_amount "
                + "                    FROM ( "
                + "                        SELECT ledger, amount "
                + "                        FROM common_view "
                + "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') "
                + "                          AND date BETWEEN start_date AND end_date "
                + "                        UNION ALL "
                + "                        SELECT ledger, amount "
                + "                        FROM common_view "
                + "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') "
                + "                          AND date IS NULL "
                + "                    ) AS combined_data "
                + "                    GROUP BY ledger "
                + "                    HAVING SUM(amount) < 0 "
                + "                ) AS final_data "
                + "            ), 0) "
                + "            + (SELECT total_null_date_negative FROM null_date_totals) "
                + "        ) AS total_negative, "
                + "        start_date "
                + "    FROM date_ranges "
                + ") "
                + "SELECT "
                + "    month, "
                + "    total_negative AS net_qty "
                + "FROM monthly_totals "
                + "WHERE month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 month'), 'Mon YYYY') "
                + "ORDER BY start_date;";
        String dbValue = DBUtil.getTallyExpectedValue(sql, "net_qty");
        double num = Math.abs(Double.parseDouble(dbValue));
        String numConversion = TestUtil.numberToShortIndianFormatOneDecimal(num);
        return numConversion;
    }

    public static String riskyReceivablesGreaterThan60DaysDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String riskyReceivablesGreaterThan30DaysDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    // ----------------Expenses----------------
    public static String totalExpensesDBValue() throws Exception {
        String sql = "SELECT " +
                "SUM(amount) AS net_qty " +
                "FROM public.common_view " +
                "WHERE is_revenue = '1' " +
                "AND is_deemedpositive = '1' " +
                "AND group_ != 'Purchase Accounts' " +
                "AND date >= DATE_TRUNC('month', CURRENT_DATE) " +
                "AND date < DATE_TRUNC('month', CURRENT_DATE + INTERVAL '1 month');";
        String dbValue = DBUtil.getTallyExpectedValue(sql, "net_qty");
        double num = Math.abs(Double.parseDouble(dbValue));
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String employeeExpDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String financeAndChargesDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String discountsDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String repairAndMaintenanceDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String othersExpensesDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String totalExpensesTrendCurrentMonthDBValue() throws Exception {
        String sql = "SELECT " +
                "SUM(amount) AS net_qty " +
                "FROM public.common_view " +
                "WHERE is_revenue = '1' " +
                "AND is_deemedpositive = '1' " +
                "AND group_ != 'Purchase Accounts' " +
                "AND date >= DATE_TRUNC('month', CURRENT_DATE) " +
                "AND date < DATE_TRUNC('month', CURRENT_DATE + INTERVAL '1 month');";
        String dbValue = DBUtil.getTallyExpectedValue(sql, "net_qty");
        double num = Math.abs(Double.parseDouble(dbValue));
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String totalExpensesTrendPastMonthDBValue() throws Exception {
        String sql = "SELECT " +
                "SUM(amount) AS net_qty " +
                "FROM public.common_view " +
                "WHERE is_revenue = '1' " +
                "AND is_deemedpositive = '1' " +
                "AND group_ != 'Purchase Accounts' " +
                "AND date >= DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 month') " +
                "AND date < DATE_TRUNC('month', CURRENT_DATE);";
        String dbValue = DBUtil.getTallyExpectedValue(sql, "net_qty");
        double num = Math.abs(Double.parseDouble(dbValue));
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    // PartStocks Page DB Values

    public static String partsQtyGetDbValue() throws Exception {
        String sql = "SELECT count(qty) AS net_qty " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                "AND created_date = ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                ");";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String totalStockValueGetDBValue() throws Exception {
        String sql = "SELECT SUM(veh_value) AS net_qty " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                "AND created_date in ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                ");";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String accessoriesAndPartsValueGetDBValue() throws Exception {
        String sql = "SELECT SUM(veh_value) AS net_qty " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                "AND created_date in ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                ");";

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
        String sql = "SELECT count(qty) AS net_qty " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                "AND created_date = ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                ");";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String lastMonthPartsQtyTrendGetDBValue() throws Exception {
        String sql = "SELECT count(qty) AS net_qty " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                "AND created_date = ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                "AND created_date BETWEEN DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 month') " +
                "AND DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 second' " +
                ");";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String currentMonthPartsValueTrendGetDBValue() throws Exception {
        String sql = "SELECT SUM(veh_value) AS net_qty " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                "AND created_date in ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                ");";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String lastMonthPartsValueTrendGetDBValue() throws Exception {
        String sql = "SELECT " +
                "( " +
                "COALESCE(SUM(veh_value), 0) + " +
                "COALESCE(SUM(charge_amount), 0) " +
                ") AS net_qty " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                "AND created_date = ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                "AND created_date BETWEEN DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 month') " +
                "AND DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 second' " +
                ");";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    // Payables Page DB Values

    public static String totalPayablesGetDBValue() throws Exception {
        String sql = "WITH RECURSIVE date_ranges AS ( " +
                "    SELECT CAST(DATE '2024-04-01' AS DATE) AS start_date, " +
                "           CAST(DATE_TRUNC('month', DATE '2024-04-01' + INTERVAL '1 month') - INTERVAL '1 day' AS DATE) AS end_date "
                +
                "    UNION ALL " +
                "    SELECT CAST(DATE '2024-04-01' AS DATE), " +
                "           CAST(DATE_TRUNC('month', end_date + INTERVAL '1 day' + INTERVAL '1 month') - INTERVAL '1 day' AS DATE) "
                +
                "    FROM date_ranges " +
                "    WHERE end_date < DATE '2026-03-31' " +
                "), " +
                "null_date_totals AS ( " +
                "    SELECT 0 AS total_null_date_positive, 0 AS total_null_date_negative " +
                "), " +
                "monthly_totals AS ( " +
                "    SELECT TO_CHAR(end_date, 'Mon YYYY') AS month, " +
                "           (COALESCE(( " +
                "                SELECT SUM(total_amount) " +
                "                FROM ( " +
                "                    SELECT ledger, SUM(amount) AS total_amount " +
                "                    FROM ( " +
                "                        SELECT ledger, amount " +
                "                        FROM common_view " +
                "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') " +
                "                          AND date BETWEEN start_date AND end_date " +
                "                        UNION ALL " +
                "                        SELECT ledger, amount " +
                "                        FROM common_view " +
                "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') " +
                "                          AND date IS NULL " +
                "                    ) AS combined_data " +
                "                    GROUP BY ledger " +
                "                    HAVING SUM(amount) > 0 " +
                "                ) AS final_data " +
                "            ), 0) " +
                "            + (SELECT total_null_date_positive FROM null_date_totals) " +
                "           ) AS total_positive, " +
                "           start_date " +
                "    FROM date_ranges " +
                ") " +
                "SELECT total_positive AS net_qty " +
                "FROM monthly_totals " +
                "WHERE month = TO_CHAR(CURRENT_DATE, 'Mon YYYY') " +
                "ORDER BY start_date;";

        String dbValue = DBUtil.getTallyExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormatOneDecimal(num);
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
        String sql = "WITH RECURSIVE date_ranges AS ( " +
                "    SELECT CAST(DATE '2024-04-01' AS DATE) AS start_date, " +
                "           CAST(DATE_TRUNC('month', DATE '2024-04-01' + INTERVAL '1 month') - INTERVAL '1 day' AS DATE) AS end_date "
                +
                "    UNION ALL " +
                "    SELECT CAST(DATE '2024-04-01' AS DATE), " +
                "           CAST(DATE_TRUNC('month', end_date + INTERVAL '1 day' + INTERVAL '1 month') - INTERVAL '1 day' AS DATE) "
                +
                "    FROM date_ranges " +
                "    WHERE end_date < DATE '2026-03-31' " +
                "), " +
                "null_date_totals AS ( " +
                "    SELECT 0 AS total_null_date_positive, 0 AS total_null_date_negative " +
                "), " +
                "monthly_totals AS ( " +
                "    SELECT TO_CHAR(end_date, 'Mon YYYY') AS month, " +
                "           (COALESCE(( " +
                "                SELECT SUM(total_amount) " +
                "                FROM ( " +
                "                    SELECT ledger, SUM(amount) AS total_amount " +
                "                    FROM ( " +
                "                        SELECT ledger, amount " +
                "                        FROM common_view " +
                "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') " +
                "                          AND date BETWEEN start_date AND end_date " +
                "                        UNION ALL " +
                "                        SELECT ledger, amount " +
                "                        FROM common_view " +
                "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') " +
                "                          AND date IS NULL " +
                "                    ) AS combined_data " +
                "                    GROUP BY ledger " +
                "                    HAVING SUM(amount) > 0 " +
                "                ) AS final_data " +
                "            ), 0) " +
                "            + (SELECT total_null_date_positive FROM null_date_totals) " +
                "           ) AS total_positive, " +
                "           start_date " +
                "    FROM date_ranges " +
                ") " +
                "SELECT total_positive AS net_qty " +
                "FROM monthly_totals " +
                "WHERE month = TO_CHAR(CURRENT_DATE, 'Mon YYYY') "
                + "ORDER BY start_date;";
        String dbValue = DBUtil.getTallyExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormatThreeDigits(num);
        return numConversion;
    }

    public static String lastMonthPayablesTrendGetDBValue() throws Exception {
        String sql = "WITH RECURSIVE date_ranges AS ( " +
                "    SELECT CAST(DATE '2024-04-01' AS DATE) AS start_date, " +
                "           CAST(DATE_TRUNC('month', DATE '2024-04-01' + INTERVAL '1 month') - INTERVAL '1 day' AS DATE) AS end_date "
                +
                "    UNION ALL " +
                "    SELECT CAST(DATE '2024-04-01' AS DATE), " +
                "           CAST(DATE_TRUNC('month', end_date + INTERVAL '1 day' + INTERVAL '1 month') - INTERVAL '1 day' AS DATE) "
                +
                "    FROM date_ranges " +
                "    WHERE end_date < DATE '2026-03-31' " +
                "), " +
                "null_date_totals AS ( " +
                "    SELECT 0 AS total_null_date_positive, 0 AS total_null_date_negative " +
                "), " +
                "monthly_totals AS ( " +
                "    SELECT TO_CHAR(end_date, 'Mon YYYY') AS month, " +
                "           (COALESCE(( " +
                "                SELECT SUM(total_amount) " +
                "                FROM ( " +
                "                    SELECT ledger, SUM(amount) AS total_amount " +
                "                    FROM ( " +
                "                        SELECT ledger, amount " +
                "                        FROM common_view " +
                "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') " +
                "                          AND date BETWEEN start_date AND end_date " +
                "                        UNION ALL " +
                "                        SELECT ledger, amount " +
                "                        FROM common_view " +
                "                        WHERE group_ IN ('Sundry Creditors', 'Sundry Debtors') " +
                "                          AND date IS NULL " +
                "                    ) AS combined_data " +
                "                    GROUP BY ledger " +
                "                    HAVING SUM(amount) > 0 " +
                "                ) AS final_data " +
                "            ), 0) " +
                "            + (SELECT total_null_date_positive FROM null_date_totals) " +
                "           ) AS total_positive, " +
                "           start_date " +
                "    FROM date_ranges " +
                ") " +
                "SELECT total_positive AS net_qty " +
                "FROM monthly_totals " +
                "WHERE month = TO_CHAR(DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 month'), 'Mon YYYY') "
                + "ORDER BY start_date;";
        String dbValue = DBUtil.getTallyExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormatThreeDigits(num);
        return numConversion;
    }

    // Profit Page DB Values
    public static String netProfitGetDBValue() throws Exception {
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

    public static String profitPercentageGetDBValue() throws Exception {
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

    public static String currentMonthProfitValueGetDBValue() throws Exception {
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
        String numConversion = TestUtil.numberToShortIndianFormatThreeDigits(num);
        return numConversion;
    }

    public static String lastMonthProfitValueGetDBValue() throws Exception {
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

    public static String currentMonthProfitPercentageGetDBValue() throws Exception {
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

    public static String lastMonthProfitPercentageGetDBValue() throws Exception {
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
        String sql = "SELECT " +
                "(SELECT COUNT(DISTINCT jobcard_no) " +
                "FROM commondatamodel.service_inflow_outflow " +
                "WHERE service_category_type = 'Service Inflow' " +
                "AND EXTRACT(MONTH FROM jobcard_date) = EXTRACT(MONTH FROM CURRENT_DATE) " +
                "AND EXTRACT(YEAR FROM jobcard_date) = EXTRACT(YEAR FROM CURRENT_DATE) " +
                ") AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String noOfVehicleOutflowGetDbValue() throws Exception {
        String sql = "SELECT " +
                "(SELECT COUNT(DISTINCT jobcard_no) " +
                "FROM commondatamodel.service_inflow_outflow " +
                "WHERE service_category_type = 'Service Outflow' " +
                "AND Bill_status = 'Billed' " +
                "AND EXTRACT(MONTH FROM bill_date) = EXTRACT(MONTH FROM CURRENT_DATE) " +
                "AND EXTRACT(YEAR FROM bill_date) = EXTRACT(YEAR FROM CURRENT_DATE) " +
                ") AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String noOfVehicleInflowYesterdayGetDbValue() throws Exception {
        String sql = "SELECT " +
                "(SELECT COUNT(DISTINCT jobcard_no) " +
                "FROM commondatamodel.service_inflow_outflow " +
                "WHERE service_category_type = 'Service Inflow' " +
                "AND DATE(jobcard_date) = CURRENT_DATE - INTERVAL '1 day' " +
                ") AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String noOfVehicleOutflowYesterdayGetDbValue() throws Exception {
        String sql = "SELECT " +
                "(SELECT COUNT(DISTINCT jobcard_no) " +
                "FROM commondatamodel.service_inflow_outflow " +
                "WHERE service_category_type = 'Service Outflow' " +
                "AND Bill_status = 'Billed' " +
                "AND DATE(bill_date) = CURRENT_DATE - INTERVAL '1 day' " +
                ") AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String outflowCurrentMonthGetDbValue() throws Exception {
        String sql = "SELECT " +
                "(SELECT COUNT(DISTINCT jobcard_no) " +
                "FROM commondatamodel.service_inflow_outflow " +
                "WHERE service_category_type = 'Service Outflow' " +
                "AND Bill_status = 'Billed' " +
                "AND EXTRACT(MONTH FROM bill_date) = EXTRACT(MONTH FROM CURRENT_DATE) " +
                "AND EXTRACT(YEAR FROM bill_date) = EXTRACT(YEAR FROM CURRENT_DATE) " +
                ") AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String outflowLastMonthGetDbValue() throws Exception {
        String sql = "SELECT " +
                "(SELECT COUNT(DISTINCT jobcard_no) " +
                "FROM commondatamodel.service_inflow_outflow " +
                "WHERE service_category_type = 'Service Outflow' " +
                "AND Bill_status = 'Billed' " +
                "AND DATE_TRUNC('month', bill_date) = DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 month' " +
                ") AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    // VehicleStocks Page DB Values

    public static String vehicleQtyGetDbValue() throws Exception {
        String sql = "SELECT count(qty) AS net_qty " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Vehicles' " +
                "AND created_date = ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Vehicles' " +
                ");";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String stockValueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "( " +
                "COALESCE(SUM(veh_value), 0) + " +
                "COALESCE(SUM(charge_amount), 0) " +
                ") AS net_qty " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Vehicles' " +
                "AND created_date = ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Vehicles' " +
                ");";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String accessoriesAndPartsGetDBValue() throws Exception {
        String sql = "SELECT SUM(veh_value) AS net_qty " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                "AND created_date in ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Parts and Accessories' " +
                ");";

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
        String sql = "SELECT count(qty) AS net_qty " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Vehicles' " +
                "AND created_date = ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Vehicles' " +
                ");";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String lastMonthStocksUnitsGetDbValue() throws Exception {
        String sql = "SELECT count(qty) AS net_qty " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Vehicles' " +
                "AND created_date = ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Vehicles' " +
                "AND created_date BETWEEN DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 month') " +
                "AND DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 second' " +
                ");";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String currentMonthStockValueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "( " +
                "COALESCE(SUM(veh_value), 0) + " +
                "COALESCE(SUM(charge_amount), 0) " +
                ") AS net_qty " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Vehicles' " +
                "AND created_date = ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Vehicles' " +
                ");";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormatThreeDigits(num);
        return numConversion;
    }

    public static String lastMonthStockValueGetDBValue() throws Exception {
        String sql = "SELECT " +
                "( " +
                "COALESCE(SUM(veh_value), 0) + " +
                "COALESCE(SUM(charge_amount), 0) " +
                ") AS net_qty " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Vehicles' " +
                "AND created_date = ( " +
                "SELECT MAX(created_date) " +
                "FROM commondatamodel.inventory " +
                "WHERE item_type = 'Vehicles' " +
                "AND created_date BETWEEN DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 month') " +
                "AND DATE_TRUNC('month', CURRENT_DATE) - INTERVAL '1 second' " +
                ");";

        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormatThreeDigits(num);
        return numConversion;
    }

}