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

            System.out.println("Given Date Range:");
            String selectDateQuery = "select * from employee_payroll where start_date between '2021-01-01' and '2023-12-31'";
            ResultSet dateResultSet = statement.executeQuery(selectDateQuery);
            while(dateResultSet.next()) {
                for(int i=1; i<=dateResultSet.getMetaData().getColumnCount(); i++) {
                    System.out.print(dateResultSet.getString(i)+"\t");
                }
                System.out.println();
            }

            groupbyQueries(statement);

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

    private static void groupbyQueries(Statement statement) throws SQLException {
        try {
            System.out.println("SUM:");
            String sumQuery = "SELECT SUM(salary) FROM employee_payroll WHERE gender = 'F' GROUP BY gender";
            ResultSet resultSet = statement.executeQuery(sumQuery);
            while(resultSet.next()) {
                for(int i=1; i<=resultSet.getMetaData().getColumnCount(); i++) {
                    System.out.print(resultSet.getString(i)+"\t");
                }
            }

            System.out.println("AVERAGE:");
            String avgQuery = "SELECT AVG(salary) FROM employee_payroll WHERE gender = 'F' GROUP BY gender";
            ResultSet avgResultSet = statement.executeQuery(avgQuery);
            while(avgResultSet.next()) {
                for(int i=1; i<=avgResultSet.getMetaData().getColumnCount(); i++) {
                    System.out.print(avgResultSet.getString(i)+"\t");
                }
            }

            System.out.println("MINIMUM:");
            String minQuery = "SELECT MIN(salary) FROM employee_payroll WHERE gender = 'F' GROUP BY gender";
            ResultSet minResultSet = statement.executeQuery(minQuery);
            while(minResultSet.next()) {
                for(int i=1; i<=minResultSet.getMetaData().getColumnCount(); i++) {
                    System.out.print(minResultSet.getString(i)+"\t");
                }
            }


            System.out.println("MAXIMUM:");
            String maxQuery = "SELECT MAX(salary) FROM employee_payroll WHERE gender = 'F' GROUP BY gender";
            ResultSet maxResultSet = statement.executeQuery(maxQuery);
            while(maxResultSet.next()) {
                for(int i=1; i<=maxResultSet.getMetaData().getColumnCount(); i++) {
                    System.out.print(maxResultSet.getString(i)+"\t");
                }
            }

            System.out.println("COUNT:");
            String countQuery = "SELECT COUNT(salary) FROM employee_payroll WHERE gender = 'F' GROUP BY gender";
            ResultSet countResultSet = statement.executeQuery(countQuery);
            while(countResultSet.next()) {
                for(int i=1; i<=countResultSet.getMetaData().getColumnCount(); i++) {
                    System.out.print(countResultSet.getString(i)+"\t");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
