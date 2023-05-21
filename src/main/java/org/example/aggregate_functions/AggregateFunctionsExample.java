package org.example.aggregate_functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

public class AggregateFunctionsExample {

    public static void insertData(Connection connection) throws SQLException {
        Random rand = new Random();

        PreparedStatement stmtPrepared = connection.prepareStatement(
                "INSERT INTO employee (name, salary) VALUES (?, ?)");

        int i = 1;
        while (i < 1001) {
            int salaryRand = rand.nextInt(5001);
            stmtPrepared.setString(1, "Andrew");
            stmtPrepared.setInt(2, salaryRand);
            stmtPrepared.executeUpdate();
            i++;
        }
    }
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:postgresql://localhost:5432/JDBC";
        String user = "alinaverkholomova";
        String pass = "password";


        try {
            System.out.println("Connecting to database" + jdbcUrl);

            Connection connection = DriverManager.getConnection(jdbcUrl, user, pass);

            insertData(connection);

            Statement stmt = connection.createStatement();

            long startTime1 = currentTimeMillis();

            ResultSet rs = stmt.executeQuery("Select avg(salary) from employee");
            if (rs.next()) {
                int avgSalary = rs.getInt(1);
                System.out.println("Average salary: " + avgSalary);
            }
            long stopTime1 = currentTimeMillis();
            long time1 = stopTime1 - startTime1;
            System.out.println("Time to get average salary is: " + time1);

            long startTime2 = currentTimeMillis();

            rs = stmt.executeQuery("Select max(salary) from employee");
            if (rs.next()) {
                int maxSalary = rs.getInt(1);
                System.out.println("\nMaximum salary: " + maxSalary);
            }
            long stopTime2 = currentTimeMillis();
            long time2 = stopTime2 - startTime2;
            System.out.println("Time to get max salary is: " + time2);

            long startTime3 = currentTimeMillis();

            rs = stmt.executeQuery("Select min(salary) from employee");
            if (rs.next()) {
                int minSalary = rs.getInt(1);
                System.out.println("\nMinimum salary: " + minSalary);
            }
            long stopTime3 = currentTimeMillis();
            long time3 = stopTime3 - startTime3;
            System.out.println("Time to get min salary is: " + time3);

            long startTime4 = currentTimeMillis();

            rs = stmt.executeQuery("Select sum(salary) from employee");
            if (rs.next()) {
                int sumSalary = rs.getInt(1);
                System.out.println("\nSummary salary: " + sumSalary);
            }
            long stopTime4 = currentTimeMillis();
            long time4 = stopTime4 - startTime4;
            System.out.println("Time to get sum of salaries is: " + time4);

            long startTime5 = currentTimeMillis();

            rs = stmt.executeQuery("Select count(*) from employee");
            if (rs.next()) {
                int countEmployee = rs.getInt(1);
                System.out.println("\nAmount of employees: " + countEmployee);
            }

            long stopTime5 = currentTimeMillis();
            long time5 = stopTime5 - startTime5;
            System.out.println("Time to get min salary is: " + time5);


            System.out.println("Connection successful!");

        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
