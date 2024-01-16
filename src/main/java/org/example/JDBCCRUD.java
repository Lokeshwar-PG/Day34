package org.example;

import java.sql.*;
import java.util.Enumeration;

public class JDBCCRUD {
    public static void main(String[] args) throws jdbcexception {
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String userName = "root";
        String password = "1234";

        try {
            System.out.println("Connecting to "+ url);
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is successful "+connection);
            Statement statement = connection.createStatement();

            String selectQuery = "select * from employee_payroll";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while(resultSet.next()) {
                for(int i=1; i<=resultSet.getMetaData().getColumnCount(); i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch(Exception e) {
            throw new jdbcexception(e);
        }
    }

}
