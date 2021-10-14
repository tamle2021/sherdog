
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;

import javax.swing.JLabel;
import java.sql.Driver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;




public class Chouest_ESI {

	
	 public static void main(String[] args) throws IOException {

		    // declare variables
		    String hold_v;
		    
		    // program start time and calculate time elapsed for program
		    Date start_time = new Date();
		    // this gets a system number time that is big
		    long total_start_time = start_time.getTime();
		    
		    System.out.println(start_time);
		    System.out.println("Start time:  " + total_start_time);
		    System.out.println("Instant.now:  " + Instant.now());
		    
			try {
			     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		          String connection_url = "jdbc:sqlserver://eds-e42914-v04:1433;"+"databaseName=test;user=e42914;password=Sql2121@!";
		        Connection con = DriverManager.getConnection(connection_url);

				
		        String read = null;
		        int i;
		      
		      
		        
		    
		        BufferedReader read_chouest = new BufferedReader(new FileReader("C:\\Users\\e42914\\Desktop\\programming\\java\\csv_files\\Chouest_ESI_comma.csv"));
		        //BufferedReader find_total_words = new BufferedReader(new FileReader("C:\\Users\\e42914\\Desktop\\programming\\java\\text\\wor.txt"));
		        
		        File chouest_output = new File("C:\\Users\\e42914\\Desktop\\programming\\java\\text\\chouest_output.txt");
		       
		        BufferedWriter write_chouest = new BufferedWriter(new FileWriter(chouest_output));
		         
		        PreparedStatement drop_table = con.prepareStatement("IF OBJECT_ID('chouest') IS NOT NULL DROP TABLE chouest");
		        PreparedStatement create_table = con.prepareStatement("CREATE TABLE [dbo].[chouest]("
		        													 + "[v_id1] [varchar](25) NULL,"
		        													 + "[three_id] [varchar](10) NULL,"
		        													 + "[pk] [varchar](50) NOT NULL,"
		        													 + "[fname] [varchar](25) NOT NULL,"
		        													 + "[string_id] [varchar](155) NOT NULL,"
		        													 + "[city] [varchar] (55),"
		        													 + "[data2] [varchar] (25),"
		        													 + "[zip][varchar] (35),"
		        													 + "[data3] [varchar] (45),"
		        													 + "[v_id2] [varchar](45) NOT NULL,"
		        													 + "[data1] [varchar] (45),"
		        													 + "CONSTRAINT [PK_chouest] PRIMARY KEY CLUSTERED" 
		        													 + "("
		        													 + 		"[pk] ASC"
		        												     + ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]"
		        												     + ") ON [PRIMARY]");
		        												     
		        drop_table.execute();
		        create_table.execute();
		        
		        PreparedStatement insert_statement = con.prepareStatement("INSERT INTO chouest (v_id1,three_id,pk,"
		        														+ "fname,string_id,city,data2,zip,data3,v_id2,"
		        														+ "data1) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		       
		        StringBuilder sb = new StringBuilder();
		        
		        
		        String v0,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10;
		    
		        
		        // read line by line from numbers file
		        while ((read = read_chouest.readLine()) != null) 
		        {
		        	// put the string in file delimited by comma into array
		            String[] split = read.split(",");
		           
		       
		        		
		           //hold_v = split[i];
			           
		           // at index 6, we read from 6 to the end
		           // save into string builder
		           for (i = 0; i < split.length; i++)
		           {
		        		if (i > 5)
		        		{
		        			sb.append(split[i] + " ");
		        		}
		        		
		           }
		           
		           System.out.println("Om Mani Padme Hum:  " + sb.toString()); 
		           // write to file
		           // System.out.println(String.format("My number:  %-9s    Count:  %-9s",number,count));
		           write_chouest.write(String.format("Om Mani Padme Hum:  %-200s  %s", sb.toString(),System.lineSeparator()));
		        		
		            // save all column/row values into variables
		            v0 = split[0];
		        	v1 = split[1];
		            v2 = split[2];
		        	v3 = split[3];
		            v4 = split[4];
		            v5 = split[5];
		        	// check for empty values	 
		            if (split[6] == " ")
		        	{
		            	v6 = null;
		        	}
		        	else
		        	{
		        		v6 = split[6];
		        	}
		        			  
		            v7 = split[7];
		            
		        	// check for empty values	  
		            if (split[8] == " ")
        			{
		            	v8 = null;
        			}
        			else		
        			{
        				v8 = split[8];
        			}
		        	 v9 = split[9];
		        	 v10 = split[10];
		        			
		        					
		        	
		        	System.out.println("v0:  " + v0);	
		        	System.out.println("v1:  " + v1);	
		        	System.out.println("v2:  " + v2);
		        	System.out.println("v3:  " + v3);
		        	System.out.println("v4:  " + v4);
		           	System.out.println("v5:  " + v5);
		        	System.out.println("v6:  " + v6);
		           	System.out.println("v7:  " + v7);
		        	System.out.println("v8:  " + v8);
		           	System.out.println("v9:  " + v9);
		           	System.out.println("v10:  " + v10);
		        		
		        		
			        //System.out.println(String.format("%-55s",hold_v));
		        	//System.out.println(hold_v);
			    
			            
			        // set the values for insert statement
			        insert_statement.setString(1,v0);
			        insert_statement.setString(2,v1);
			        insert_statement.setString(3,v2);
			        insert_statement.setString(4,v3);
			        insert_statement.setString(5,v4);
			        insert_statement.setString(6,v5);
			        insert_statement.setString(7,v6);
			        insert_statement.setString(8,v7);
			        insert_statement.setString(9,v8);
			        insert_statement.setString(10,v9);
			        insert_statement.setString(11,v10);
			          
				    insert_statement.executeUpdate();
			        
				        
				        
				    System.out.println("");
			        // reset the string builder
			        sb.setLength(0);
			        
				        
		        }
		            
		        	
		      
		        read_chouest.close();
		        write_chouest.close();
		        
		        
		        // calculate end time and program elapsed time
		        Date end_time = new Date();
		        // this gets a system number time that is big
			    long total_end_time = end_time.getTime();
			 
			    // result is in milliseconds, so divide by 1000
			    double convert_seconds = (total_end_time - total_start_time) / 1000.00;
			    
			    System.out.println(convert_seconds);
			    System.out.println("End time:  " + total_end_time);
			    System.out.println("Program time elapsed:  " + convert_seconds + " seconds");
			    
		    }
			
			catch (Exception e) {
		    	     System.out.println("Error:  " + e);
			}
			
		/*    	preparedstatement=connection.prepareStatement("insert into allpatients(name, age, height, weight) values(?,?,?,?)");
		    	preparedstatement.setString(1, name);
		    	preparedstatement.setString(2, age);
		    	preparedstatement.setString(3, height);
		    	preparedstatement.setString(4, weight);
		    	preparedstatement.executeUpdate();*/
}
	
	
}
