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


    public static String vehicleSoldGetDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date BETWEEN '2025-05-01 00:00:00' AND '2025-05-06 23:59:59' AND qty IN (1)), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date BETWEEN '2025-05-01 00:00:00' AND '2025-05-06 23:59:59' AND qty IN (-1, 0)), 0)) AS net_qty;";
        
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String vehicleSalesTestDBValue() throws Exception {
        String sql = "SELECT " +
                  "(COALESCE((SELECT SUM(invoice_amount) " +
                  "FROM commondatamodel.vehicles_sales " +
                  "WHERE invoice_date BETWEEN '2025-05-01 00:00:00' AND '2025-05-06 23:59:59' " +
                  "AND qty IN (1)), 0) - " +
                  "COALESCE((SELECT SUM(invoice_amount) " +
                  "FROM commondatamodel.vehicles_sales " +
                  "WHERE invoice_cancellation_date BETWEEN '2025-05-01 00:00:00' AND '2025-05-06 23:59:59' " +
                  "AND qty IN (-1)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        double num = Double.parseDouble(dbValue);
        if (num >= 10000000) {
            double value = num / 10000000.0;
                String finalValueCr =  String.format("%.2f Cr", value).replaceAll("\\.?0+Cr$", "Cr");
                System.out.println("finalValueCr: " + finalValueCr);
                return finalValueCr;
            } else if (num >= 100000) {
                double value = num / 100000.0;
                String finalValueL = String.format("%.2f L", value).replaceAll("\\.?0+L$","L");
                System.out.println("finalValueL: " + finalValueL);
                return finalValueL;
            } else if (num >= 1000) {
                double value = num / 1000.0;
                String finalValueK = String.format("%.2f k", value).replaceAll("\\.?0+k$", "k");
                System.out.println("finalValueK: " + finalValueK);
                return finalValueK;
            } else {
                return String.valueOf(num);
            }
    }

    public static String vehicleBookedYesterdayTestDBValue() throws Exception {
        //need to change the query to get the correct value
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.booking WHERE booking_date = CURRENT_DATE - INTERVAL '1 day' AND booking_status = 'Booked' AND category_type = 'Booking Register'";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }
    
    public static String vehicleInvoicedYesterdayTestDBValue() throws Exception {
        //need to change the query to get the correct value
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.booking where booking_date = CURRENT_DATE - INTERVAL '1 day' and booking_status = 'Invoiced' and category_type ='Booking Register'";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String vehicleInvoicedThisMonthTestDBValue() throws Exception {
        //need to change the query to get the correct value
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.booking WHERE booking_date BETWEEN date_trunc('month', CURRENT_DATE) AND (CURRENT_DATE + INTERVAL '1 day' - INTERVAL '1 second') AND booking_status = 'Invoiced' AND category_type = 'Booking Register'";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String vehicleInvoicedButNotDeliveredTestDBValue() throws Exception {
        //need to change the query to get the correct value
        String sql = "SELECT COUNT(*) AS net_qty FROM commondatamodel.delivery where created_date = (SELECT MAX(created_date) FROM commondatamodel.delivery )and category_type ='IBND report'";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String currentMonthTestDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date BETWEEN '2025-05-01 00:00:00' AND '2025-05-06 23:59:59' AND qty IN (1)), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date BETWEEN '2025-05-01 00:00:00' AND '2025-05-06 23:59:59' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String lastMonthTestDBValue() throws Exception {
        String sql = "SELECT (COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date BETWEEN '2025-04-01 00:00:00' AND '2025-04-30 23:59:59' AND qty IN (1)), 0) + COALESCE((SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_cancellation_date BETWEEN '2025-04-01 00:00:00' AND '2025-04-30 23:59:59' AND qty IN (-1, 0)), 0)) AS net_qty;";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }
    
    
    
    
    
} 