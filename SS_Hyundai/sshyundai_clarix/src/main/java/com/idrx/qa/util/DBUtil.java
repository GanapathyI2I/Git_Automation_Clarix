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

    public static String enquiriesToBookingE2BGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("EnquiriesToBookingE2B_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Enquiries To Booking E2B DB Value: " + dbValue);
        return dbValue;
    }

    public static String enquiriesToRetailE2RGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("EnquiriesToRetailE2R_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Enquiries To Retail E2R DB Value: " + dbValue);
        return dbValue;
    }

    public static String testDriveToBookingT2BGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("TestDriveToBookingT2B_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Test Drive To Booking T2B DB Value: " + dbValue);
        return dbValue;
    }

    public static String bookingToRetailB2RGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("BookingToRetailB2R_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Booking To Retail B2R DB Value: " + dbValue);
        return dbValue;
    }

    //------------------Sales/Bookings DB values-----------------------
    public static String noOfBookingsYdayGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("No_of_Bookings_Y'day_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("No of Bookings Y'day DB Value: " + dbValue);
        return dbValue;
    }

    public static String noOfBookingsThisMonthGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("No_of_Bookings_This_Month_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("No of Bookings This Month DB Value: " + dbValue);
        return dbValue;
    }

    public static String noOfPendingBookingsGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("No_of_Pending_Bookings_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("No of Pending Bookings DB Value: " + dbValue);
        return dbValue;
    }

    public static String currentMonthGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Current_Month_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Current Month DB Value: " + dbValue);
        return dbValue;
    }

    public static String pastMonthGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Past_Month_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Past Month DB Value: " + dbValue);
        return dbValue;
    }

    //------------------Sales/Retail DB values-----------------------
    public static String vehiclesSoldGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Vehicles_Sold_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Vehicles Sold DB Value: " + dbValue);
        return dbValue;
    }

    public static String avgSalesGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Avg_Sales_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Avg Sales DB Value: " + dbValue);
        return dbValue;
    }

    public static String enquiriesYdayGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Enquiries_Y'day_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Enquiries Y'day DB Value: " + dbValue);
        return dbValue;
    }

    public static String vehiclesBookedYdayGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Vehicles_Booked_Y'day_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Vehicles Booked Y'day DB Value: " + dbValue);
        return dbValue;
    }

    public static String vehiclesInvoicedYdayGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Vehicles_Invoiced_Y'day_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Vehicles Invoiced Y'day DB Value: " + dbValue);
        return dbValue;
    }

    public static String vehiclesInvoicedThisMonthGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Vehicles_Invoiced_This_Month_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Vehicles Invoiced This Month DB Value: " + dbValue);
        return dbValue;
    }

    public static String qtyTrendCurrentMonthGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Qty_Trend_Current_Month_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Qty Trend Current Month DB Value: " + dbValue);
        return dbValue;
    }

    public static String qtyTrendPreviousMonthGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Qty_Trend_Previous_Month_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Qty Trend Previous Month DB Value: " + dbValue);
        return dbValue;
    }

    //------------------Sales/Deliveries DB values-----------------------
    public static String deliveriesYesterdayGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Deliveries_Yesterday_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Deliveries Yesterday DB Value: " + dbValue);
        return dbValue;
    }

    public static String deliveriesThisMonthGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Deliveries_This_Month_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Deliveries This Month DB Value: " + dbValue);
        return dbValue;
    }

    public static String deliveriesPendingGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Deliveries_Pending_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Deliveries Pending DB Value: " + dbValue);
        return dbValue;
    }

    public static String deliveriesPreviousMonthGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Deliveries_Previous_Month_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Deliveries Previous Month DB Value: " + dbValue);
        return dbValue;
    }

    public static String deliveriesCurrentMonthGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Deliveries_Current_Month_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Deliveries Current Month DB Value: " + dbValue);
        return dbValue;
    }

    public static String deliveriesModelWisePendingGetDBValue() throws Exception {
        String sql = TestBase.dbProp.getProperty("Deliveries_Model_Wise_Pending_DB_Value");
        String dbValue = getExpectedValue(sql, "net_qty");
        System.out.println("Deliveries Model Wise Pending DB Value: " + dbValue);
        return dbValue;
    }
    
    
}