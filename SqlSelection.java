//package sqlselection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import java.sql.Driver;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
//import java.io.*;

public class SqlSelection {
	 public static void main(String[] args)
     {
         try
         {
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

             
/*             String connectionUrl = "jdbc:sqlserver://localhost:1433;" +  
            		   "databaseName=AdventureWorks;user=MyUserName;password=*****;";  
            		Connection con = DriverManager.getConnection(connectionUrl);*/

             
             
            //  String userName = "e42914";
            // String password = "Sql2121@!";
             String connection_url = "jdbc:sqlserver://eds-e42914-v04:1433;"+"databaseName=test;user=e42914;password=Sql2121@!";
             Connection con = DriverManager.getConnection(connection_url);
             Statement s1 = con.createStatement();
             ResultSet rs = s1.executeQuery("SELECT * from books");
             
             int num_columns = rs.getMetaData().getColumnCount();
         	 System.out.println("Number of columns:  " + num_columns);
         	 
            
         	 // create file and write
         	 File file = new File("C:\\java_test_file\\java_test.txt");
         	 FileWriter writer = new FileWriter(file);
         	
         	 String book_name;
         	 
         	 StringBuilder sb = new StringBuilder();
         	 
                 while (rs.next()){
                	
                	 // we can loop through the columns like this
                    /* for(int i = 1; i <= num_columns ; i++)
                     {
                         System.out.print(String.format("%-25s",rs.getObject(i)));
                     }*/
                	 
                	 // book number
                	 System.out.print(String.format("%-17s",rs.getObject(1)));
                	 
                	 // book name
                	 System.out.print(String.format("%-39s",rs.getObject(2)));
                	 
                	 // quantity
                	 System.out.print(String.format("%-15s",rs.getObject(3)));
                	 
                	 // price
                	 System.out.print(String.format("%-10s",rs.getObject(4)));
                	 
                	 book_name = rs.getObject(2).toString();
                	 
                	 // write the book name to file to java_test
                	 writer.write(String.format("%-28s     |      Book num --> %-10s", book_name,rs.getObject(1).toString()) + "     Today is hot.");
                	 
                	 
                     // build the string using StringBuilder
                	 sb.append(String.format("%-28s     |      Book num --> %-10s", book_name,rs.getObject(1).toString()) + " |    Today is hot." + "\n");
                	 
                	 
                	 // weird; this is needed for new line as opposed to \n
                	 writer.write(System.lineSeparator());
                	 
                	 writer.flush();	 
            
                	 
                	 
                     // go to next line for next record
                     System.out.println("");
                 }
                 
                 writer.close();
                 
                 
                 System.out.println("\n\nString builder example:");
                 System.out.println(sb.toString());
                 
            
                 
                 // run the JLabel 
                 //   SwingControlDemo demo = new SwingControlDemo(sb);
                 //  demo.showLabelDemo();
                 
         } 

         catch (Exception ex)
         {
        	 ex.printStackTrace();
             System.out.println(ex);
         }
     
}
}











/*



package net.codejava.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
 

 * This program demonstrates how to establish database connection to Microsoft
 * SQL Server.
 * @author www.codejava.net
 *

public class JdbcSQLServerConnection {
 
    public static void main(String[] args) {
 
        Connection conn = null;
 
        try {
 
            String dbURL = "jdbc:sqlserver://localhost\\sqlexpress";
            String user = "sa";
            String pass = "secret";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
 
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}


*/










