package com.Client.auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 Client clt = new Client();
         Connection conn = null;
         try {
             // db parameters
             String url       = "jdbc:mysql://localhost:3306/gl";
             String user      = "root";
             String password  = "";
             
             // create a connection to the database
             conn = DriverManager.getConnection(url, user, password);
             System.out.println("Connected");
             System.out.println(clt.authenticate("1","1234", conn));

             
             
         } catch(SQLException e) {
            System.out.println(e.getMessage());
         } finally {
             try{
                    if(conn != null)
                      conn.close();
             }catch(SQLException ex){
                    System.out.println(ex.getMessage());
             }
         }
         
         for (int val : RandomTable.randomtab(10000)) {
			System.out.println(val);
		}
    }
}
