package com.idrx.qa.util;

import java.sql.*;

import com.idrx.qa.base.TestBase;

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
                return String.format("%.2fL", value).replaceAll("\\.?0+L$","L");
            } else if (num >= 1000) {
                double value = num / 1000.0;
                return String.format("%.2fk", value).replaceAll("\\.?0+k$", "k");
            } else {
                return String.valueOf(num);
            }
        }
    
        // public static void main(String[] args) {
        //     System.out.println(numberToShortIndianFormat(2034341));    // 3.25L
        //     System.out.println(numberToShortIndianFormat(30000000));  // 3Cr
        //     System.out.println(numberToShortIndianFormat(12345678));  // 1.23Cr
        //     System.out.println(numberToShortIndianFormat(1500));      // 1.5k
        //     System.out.println(numberToShortIndianFormat(999));       // 999
        // }

        //-----------------Sales/Booking Page DB Values-----------------------
        public static String yesterdayBookingsGetDBValue() throws Exception {
            String sql = "SELECT COUNT(id) AS net_qty "+
                        "FROM commondatamodel.booking "+
                        "WHERE category_type != 'Pending Bookings' "+
                        "AND booking_date >= CURRENT_DATE - INTERVAL '1 day' "+
                        "AND booking_date < CURRENT_DATE;";
            String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
            return dbValue;
        }

        public static String thisMonthBookingsGetDBValue() throws Exception {
            String sql = "SELECT COUNT(id) AS net_qty "+
                        "FROM commondatamodel.booking "+
                        "WHERE category_type != 'Pending Bookings' "+
                        "AND booking_date >= date_trunc('month', CURRENT_DATE) "+
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
            String sql = "SELECT COUNT(id) AS net_qty "+
                        "FROM commondatamodel.booking "+
                        "WHERE category_type != 'Pending Bookings' "+
                        "AND booking_date >= date_trunc('month', CURRENT_DATE - INTERVAL '1 month') "+
                        "AND booking_date < date_trunc('month', CURRENT_DATE);";
            String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
            return dbValue;
        }

        public static String thisMonthBookingsTestGetDBValue() throws Exception {
            String sql = "SELECT COUNT(id) AS net_qty "+
                        "FROM commondatamodel.booking "+
                        "WHERE category_type != 'Pending Bookings' "+
                        "AND booking_date >= date_trunc('month', CURRENT_DATE) "+
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
        
        
        
        //------------------------Sales/ Retail Page DB Values--------------------------
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
    
    //-----------------------------Sales/Delivery----------------------------------
    public static String deliveriesYesterdayTestDBValue() throws Exception {
        String sql = "SELECT count (id) AS net_qty "+
                        "FROM commondatamodel.delivery "+
                        "WHERE delivery_date = CURRENT_DATE - INTERVAL '1 day' "+
                        "and category_type = 'Vehicle delivery note';";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String deliveriesThisMonthTestDBValue() throws Exception {
        String sql = "SELECT  count (id) AS net_qty "+
                        "FROM commondatamodel.delivery "+
                        "WHERE delivery_date BETWEEN date_trunc('month', CURRENT_DATE) AND CURRENT_DATE - INTERVAL '1 day' "+
                        "and category_type = 'Vehicle delivery note';";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String pendingDeliveriesTestDBValue() throws Exception {
        String sql = "SELECT COUNT(id) AS net_qty "+
                        "FROM commondatamodel.delivery "+
                        "WHERE category_type != 'Vehicle delivery note' "+
                        "AND created_date = ( "+
                        "SELECT MAX(created_date) "+
                        "FROM commondatamodel.delivery "+
                        "WHERE category_type != 'Vehicle delivery note')";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String deliveriesPreviousMonthTestDBValue() throws Exception {
        String sql = "SELECT count (id) AS net_qty "+
                        "FROM commondatamodel.delivery "+
                        "WHERE delivery_date BETWEEN date_trunc('month', CURRENT_DATE - INTERVAL '1 month') "+
                        "AND date_trunc('month', CURRENT_DATE) - INTERVAL '1 day' "+
                        "AND category_type = 'Vehicle delivery note';";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String deliveriesCurrentMonthTestDBValue() throws Exception {
        String sql = "SELECT  count (id) AS net_qty "+
                        "FROM commondatamodel.delivery "+
                        "WHERE delivery_date BETWEEN date_trunc('month', CURRENT_DATE) AND CURRENT_DATE - INTERVAL '1 day' "+
                        "AND category_type = 'Vehicle delivery note';";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String salesmanWisePendingDeliveriesTestDBValue() throws Exception {
        String sql = "SELECT COUNT(id) AS net_qty "+
                        "FROM commondatamodel.delivery "+
                        "WHERE category_type != 'Vehicle delivery note' "+
                        "AND created_date = ( "+
                        "SELECT MAX(created_date) "+
                        "FROM commondatamodel.delivery "+
                        "WHERE category_type != 'Vehicle delivery note')";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    //-----------------------------Discount Page DB Values-----------------------------
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

    //-----------------------------Finance Page DB Values-----------------------------
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

    //-----------------------------ServiceRevenue Page DB Values-----------------------------

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
    
    //--------------Service/Parts Sales----------------
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

    //----------------------Billing and Purchase------------------------
    public static String vehicleQtyDBValue () throws Exception {
        String sql = "SELECT COUNT(qty) AS net_qty "+
                        "FROM commondatamodel.purchase "+
                        "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) "+
                        "AND invoice_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') "+
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
        String sql = "SELECT COUNT(qty) AS net_qty "+
                        "FROM commondatamodel.purchase "+
                        "WHERE invoice_date >= date_trunc('month', CURRENT_DATE) "+
                        "AND invoice_date < date_trunc('month', CURRENT_DATE + INTERVAL '1 month') "+
                        "AND item_type = 'Vehicles';";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String unitsPastMonthDBValue() throws Exception {
        String sql = "SELECT COUNT(qty) AS net_qty "+
                        "FROM commondatamodel.purchase "+
                        "WHERE invoice_date >= date_trunc('month', CURRENT_DATE - INTERVAL '1 month') "+
                        "AND invoice_date < date_trunc('month', CURRENT_DATE) "+
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
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String topVehicleQtyTotalDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String topVehiclePurchaseValueTotalDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String partsAccessoriesQtyTotalDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String partsAccessoriesPurchaseValueTotalDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    //----------------New Order Vehicles----------------
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

    //-----------------New Order P & A----------------
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

    //------------------Receivables/ Receivables----------------------
    public static String creditOutstandingDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
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
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String receivablesTrentPastMonthDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
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

    //----------------Expenses----------------
    public static String totalExpensesDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
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
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }

    public static String totalExpensesTrendPastMonthDBValue() throws Exception {
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.parts_sales WHERE created_date = CURRENT_DATE";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        String numConversion = TestUtil.numberToShortIndianFormat(num);
        return numConversion;
    }
    
} 