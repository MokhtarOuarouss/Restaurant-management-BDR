package bdr.bdr;

import java.sql.*;

public class Employee {
    private static final String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
    private static final String USERNAME = "BDR";
    private static final String PASSWORD = "BDR";
    public void AddEmployee(String resName, String fullName,String Pos,String Phone ,float sal) throws SQLException {

        CallableStatement callableStatement;
        PreparedStatement statement;
        int res_id = 0;
        String res ;

        try {
            Connection connection;

            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            callableStatement = connection.prepareCall("{call insert_employee(?,?, ?,?,?)}");
            statement = connection.prepareStatement("SELECT RESTAURANT_ID FROM restaurant WHERE NAME = ?");

            if(resName.equals("RESTAURANT1"))  res ="Bella Vita";
            else res ="Your Second House";

            statement.setString(1, res);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    res_id = resultSet.getInt("RESTAURANT_ID");
                }
            }


            System.out.println(res_id + " | "+fullName+ " | " +Pos +  " | "+Phone+" | "+sal);



            callableStatement.setInt(1, res_id);
            callableStatement.setString(2, fullName);
            callableStatement.setString(3, Pos);
            callableStatement.setString(4, Phone);
            callableStatement.setFloat(5, sal);

            callableStatement.execute();

            System.out.println("Employee created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void UpdateEmployee( int id,String fullName,String Pos,String Phone ,float sal) throws SQLException {

        CallableStatement callableStatement;


        try {
            Connection connection;

            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            callableStatement = connection.prepareCall("{call update_employee(?,?,?,?,?)}");




            System.out.println("Employee "+id + " updated with values :  "+fullName+ " | " +Pos +  " | "+Phone+" | "+sal);



            callableStatement.setInt(1, id);
            callableStatement.setString(2, fullName);
            callableStatement.setString(3, Pos);
            callableStatement.setString(4, Phone);
            callableStatement.setFloat(5, sal);

            callableStatement.execute();

            System.out.println("Employee updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteEmployee(int id) throws SQLException {

        CallableStatement callableStatement;

        try {
            Connection connection;

            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            callableStatement = connection.prepareCall("{call delete_employee(?)}");


            System.out.println("Employee id = "+id+  " was deleted");



            callableStatement.setInt(1, id);


            callableStatement.execute();

            System.out.println("Employee deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
