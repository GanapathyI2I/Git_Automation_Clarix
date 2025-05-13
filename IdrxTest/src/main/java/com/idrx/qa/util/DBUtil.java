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
        return dbValue;
    }

    public static String vehicleBookedYesterdayTestDBValue() throws Exception {
        //need to change the query to get the correct value
        String sql = "SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date BETWEEN '2025-05-01 00:00:00' AND '2025-05-06 23:59:59' AND qty IN (1)";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }
    
    public static String vehicleInvoicedYesterdayTestDBValue() throws Exception {
        //need to change the query to get the correct value
        String sql = "SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date BETWEEN '2025-05-01 00:00:00' AND '2025-05-06 23:59:59' AND qty IN (1)";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String vehicleInvoicedThisMonthTestDBValue() throws Exception {
        //need to change the query to get the correct value
        String sql = "SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date BETWEEN '2025-05-01 00:00:00' AND '2025-05-06 23:59:59' AND qty IN (1)";
        String dbValue = DBUtil.getExpectedValue(sql, "net_qty");
        return dbValue;
    }

    public static String vehicleInvoicedButNotDeliveredTestDBValue() throws Exception {
        //need to change the query to get the correct value
        String sql = "SELECT SUM(qty) FROM commondatamodel.vehicles_sales WHERE invoice_date BETWEEN '2025-05-01 00:00:00' AND '2025-05-06 23:59:59' AND qty IN (1)";
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