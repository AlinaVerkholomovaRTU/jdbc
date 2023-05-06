package main.java.org.example.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionExample {
    public static void main(String[] args) throws SQLException {

        String jdbcUrl = "jdbc:postgresql://localhost:5432/JDBC";
        String user = "alinaverkholomova";
        String pass = "password";


        Connection connection = null;
        System.out.println("Connecting to database" + jdbcUrl);
        connection = DriverManager.getConnection(jdbcUrl, user, pass);
        PreparedStatement withdrawStmt = null;
        PreparedStatement depositStmt = null;

        try {

            connection.setAutoCommit(false);

            withdrawStmt = connection.prepareStatement("UPDATE bank_account set balance = balance - ? where id = ?");
            withdrawStmt.setDouble(1, 100.0);
            withdrawStmt.setInt(2, 1);
            withdrawStmt.executeUpdate();

            depositStmt = connection.prepareStatement("UPDATE bank_account set balance = balance + ? where id = ?");
            depositStmt.setDouble(1, 100.0);
            depositStmt.setInt(2, 2);
            depositStmt.executeUpdate();

            connection.commit();

            System.out.println("Connection successful!");

        } catch (SQLException se) {
            connection.rollback();
        }
        finally{

            if (withdrawStmt != null) {
                withdrawStmt.close();
            }
            if (depositStmt != null) {
                depositStmt.close();
            }
            connection.setAutoCommit(true);

            String newQuery2 = "INSERT INTO bank_account (account_number, balance) values('3333', 200.00)";
            connection.createStatement().executeUpdate(newQuery2);
            connection.close();
        }
    }
}
