package org.example;

import java.sql.*;
import java.util.Enumeration;

public class JDBCCRUD {
    static String url = "jdbc:mysql://localhost:3306/payroll_service";
    static String userName = "root";
    static String password = "1234";
    public static void main(String[] args) throws jdbcexception {

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

            String updateQuery = String.valueOf(updateSalary("Rajesh", 90000));

        } catch(Exception e) {
            throw new jdbcexception(e);
        }
    }


    public static double updateSalary(String name, double salary) throws SQLException {
        Connection connection = DriverManager.getConnection(url, userName, password);
        String updateQuery = "update employee_payroll set salary = ? where name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setDouble(1, salary);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();

        String selectQuery = "select salary from employee_payroll where name = ?";
        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setString(1, name);
        ResultSet resultSet = selectStatement.executeQuery();
        if(resultSet.next()) {
            double updatedSalary = resultSet.getDouble("salary");
            return updatedSalary;
        }
        return 0;
    }
}
