package bdr.bdr;

import java.sql.*;
import java.util.ArrayList;

public class Orders {
    private static final String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
    private static final String USERNAME = "BDR";
    private static final String PASSWORD = "BDR";
    private static final String USERNAME_1 = "RESTAURANT1";
    private static final String USERNAME_2 = "RESTAURANT2";
    private static final String PASSWORD_1 = "RESTAURANT1";
    private static final String PASSWORD_2 = "RESTAURANT2";

    public void createOrders(String itemName, int quantity, String resName,int customer_id) throws SQLException {

        CallableStatement callableStatement;
        PreparedStatement statement;
        int price = 0;
        int menu_id = 0;

        try {
            Connection connection;
            if (resName.equals("RESTAURANT1")) {

                connection = DriverManager.getConnection(url, USERNAME_1, PASSWORD_1);
                callableStatement = connection.prepareCall("{call insert_order1(?, ?, ?)}");
                statement = connection.prepareStatement("SELECT MENU_ID,price FROM menu1 WHERE Item_Name = ?");

            } else {

                connection = DriverManager.getConnection(url, USERNAME_2, PASSWORD_2);
                callableStatement = connection.prepareCall("{call insert_order2(?, ?, ?)}");
                statement = connection.prepareStatement("SELECT MENU_ID,price FROM menu2 WHERE Item_Name = ?");
            }

            statement.setString(1, itemName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                     price = resultSet.getInt("price");
                    menu_id = resultSet.getInt("MENU_ID");
                }
            }


            int Total = price * quantity ;




            callableStatement.setInt(1, customer_id);
            callableStatement.setInt(2, Total);
            callableStatement.setInt(3, menu_id);

            callableStatement.execute();

            System.out.println("Order created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> restaurantItems(String resName) throws SQLException {
        ArrayList<String> itemsList = new ArrayList<>();
        PreparedStatement statement;
        Connection  connection = DriverManager.getConnection(url, USERNAME, PASSWORD);;
        try {
            if (resName.equals("RESTAURANT1")) {
                statement = connection.prepareStatement("SELECT ITEM_NAME FROM MENU WHERE RESTAURANT_ID = 1");
            } else {
                statement = connection.prepareStatement("SELECT ITEM_NAME FROM MENU WHERE RESTAURANT_ID = 2");
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String itemName = resultSet.getString(1);
                    itemsList.add(itemName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemsList;
    }

}