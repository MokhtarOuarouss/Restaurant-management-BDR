package bdr.bdr;

import java.sql.*;

public class Menu {
    private static final String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
    private static final String USERNAME = "BDR";
    private static final String PASSWORD = "BDR";
    public void AddMenu(String resName, String ItemName,Float price,String description) throws SQLException {

        CallableStatement callableStatement;
        PreparedStatement statement;
        int res_id = 0;

        try {
            Connection connection;

            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            callableStatement = connection.prepareCall("{call insert_menu(?,?, ?,?)}");
            if (resName.equals("RESTAURANT1")) res_id=1;
            else res_id=2;


            System.out.println(res_id + " | "+ItemName+ " | " +price +  " | "+description);

            callableStatement.setInt(1, res_id);
            callableStatement.setString(2, ItemName);
            callableStatement.setFloat(3, price);
            callableStatement.setString(4, description);

            callableStatement.execute();

            System.out.println("Menu created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void UpdateMenu(int id , String ItemName,Float price,String description) throws SQLException {

        CallableStatement callableStatement;


        try {
            Connection connection;

            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            callableStatement = connection.prepareCall("{call update_menu(?,?, ?,?)}");


            System.out.println("Employee "+id + " updated with values :  "+ItemName+ " | " +price +  " | "+description);

            callableStatement.setInt(1, id);
            callableStatement.setString(2, ItemName);
            callableStatement.setFloat(3, price);
            callableStatement.setString(4, description);

            callableStatement.execute();

            System.out.println("Menu updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteMenu(int id) throws SQLException {

        CallableStatement callableStatement;


        try {
            Connection connection;

            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            callableStatement = connection.prepareCall("{call delete_menu_item(?)}");

            System.out.println("MENU id = "+id+  " was deleted");

            callableStatement.setInt(1, id);
            callableStatement.execute();

            System.out.println("Menu deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
