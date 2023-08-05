package bdr.bdr;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
public final class DBUtil {
    private static boolean isDriverLoaded = false;
    static{
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver OK !");
            isDriverLoaded = true;
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private final static String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";


    public static Connection getConnection(String user, String password) {
        Connection con = null;
        try {
            if (isDriverLoaded) {
                con = DriverManager.getConnection(url, user, password);
                System.out.println("Connection established");
            }
        } catch (SQLException e) {
            System.out.println("Unable to connect to database: Incorrect username or password.");
            con = null;
        }
        return con;
    }

    public  void addCostumer(String user, String password) {


    }



    public static void closeConnection(Connection con) throws SQLException{
        if(con!=null){
            con.close();
            System.out.println("connection closed");
        }
    }
}


