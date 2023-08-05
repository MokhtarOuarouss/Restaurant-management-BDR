package bdr.bdr;

import java.sql.*;

public class Customer {
    private static final String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
    private static final String USERNAME = "BDR";
    private static final String PASSWORD = "BDR";
    private static final String PROCEDURE_CALL = "{call insert_customer(?, ?, ?, ?)}";

    public void createCustomer(String name, String email, String phoneNumber, String address) {
        try (Connection connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
             CallableStatement callableStatement = connection.prepareCall(PROCEDURE_CALL)) {

            callableStatement.setString(1, name);
            callableStatement.setString(2, email);
            callableStatement.setString(3, phoneNumber);
            callableStatement.setString(4, address);

            callableStatement.execute();

            System.out.println("Customer created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isUserExists(String fullName, String email) {
        try (Connection connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM customers WHERE name = ? AND email = ?")) {

            statement.setString(1, fullName);
            statement.setString(2, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getCustomerId(String fullName, String email) {
        int customerId = -1; // Default value to indicate no customer found
        try (Connection connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT CUSTOMER_ID FROM customers WHERE name = ? AND email = ?")) {

            statement.setString(1, fullName);
            statement.setString(2, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    customerId = resultSet.getInt("CUSTOMER_ID");
                }
            }
        } catch (SQLException e) {
            // Handle the exception appropriately (e.g., log or throw a custom exception)
            e.printStackTrace();
        }
        return customerId;
    }

}
