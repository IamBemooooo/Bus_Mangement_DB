package Database;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUlt {
    public static Connection getConnection()
    {
        Connection con = null;
        try {
            //Register MySqlDriver With DriverManager
            DriverManager.registerDriver(new Driver());

            //config
            String url = "jdbc:mysql://localhost:3306/qlbus";
            String user = "root";
            String password = "";

            //Connect
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static void CloseConnection(Connection con)
    {
       try
       {
           if(con != null)
           {
               con.close();
           }
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
    }
}
