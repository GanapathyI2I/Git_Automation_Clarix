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

    //------------------Sales/ETBR DB values-----------------------
    public static String enquiryGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Enquiry_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Enquiry DB Value: " + dbValue);
        return dbValue;
    }

    public static String testDriveGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("TestDrive_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Test Drive DB Value: " + dbValue);
        return dbValue;
    }

    public static String bookingGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Booking_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Booking DB Value: " + dbValue);
        return dbValue;
    }

    public static String retailGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Retail_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Retail DB Value: " + dbValue);
        return dbValue;
    }

    public static String enquiriesToTdE2TGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("EnquiriesToTdE2T_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Enquiries To Td E2T DB Value: " + dbValue);
        return dbValue;
    }
    
    
    
}