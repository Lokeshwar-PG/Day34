package org.example;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class JDBCCRUD {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String userName = "root";
        String password = "1234";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver loaded");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("cannot find the driver in the classpath");
        }
        
        listDrivers();

        try {
            System.out.println("Connecting to "+ url);
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is successful "+connection);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while(driverList.hasMoreElements()) {
            Driver driverClass = driverList.nextElement();
            System.out.println(driverClass.getClass().getName());
        }
    }
}
