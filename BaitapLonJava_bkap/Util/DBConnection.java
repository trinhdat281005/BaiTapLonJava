package BaitapLonJava_bkap.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
public class DBConnection {

        // Thông tin kết nối
       static String url="jdbc:sqlserver://localhost:1433;databaseName=BookManagement;encrypt=false";
        static String user="sa";
        static String pass="12345";

        public static Connection getConnect()
        {
            Connection con=null;
            try {
                con=DriverManager.getConnection(url,user,pass);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return con;
        }


}


