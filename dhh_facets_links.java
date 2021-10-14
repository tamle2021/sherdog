
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




public class dhh_facets_links {

	
	 public static void main(String[] args) throws IOException {

		  
		    // declare variables
		   
		    
		    
		    // program start time and calculate time elapsed for program
		    Date start_time = new Date();
		    // this gets a system number time that is big
		    long total_start_time = start_time.getTime();
		    
		    System.out.println(start_time);
		    System.out.println("Start time:  " + total_start_time);
		    System.out.println("Instant.now:  " + Instant.now());
		    
			try
		    {
				
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		        String connection_url = "jdbc:sqlserver://eds-e42914-v04:1433;"+"databaseName=test;user=e42914;password=Sql2121@!";
		        Connection con = DriverManager.getConnection(connection_url);

				
		        String read = null;
		        int i;
		      
		      
		        
		    
		        BufferedReader read_dhh = new BufferedReader(new FileReader("C:\\Users\\e42914\\Desktop\\programming\\java\\text\\DHH_FACETS_LINKS.txt"));
		        //BufferedReader find_total_words = new BufferedReader(new FileReader("C:\\Users\\e42914\\Desktop\\programming\\java\\text\\wor.txt"));
		        
		        File dhh_output = new File("C:\\Users\\e42914\\Desktop\\programming\\java\\text\\dhh_output.txt");
		       
		        BufferedWriter write_dhh = new BufferedWriter(new FileWriter(dhh_output));
		         
		        PreparedStatement drop_table = con.prepareStatement("IF OBJECT_ID('dhh_facets_links') IS NOT NULL DROP TABLE dhh_facets_links");
		        PreparedStatement create_table = con.prepareStatement("CREATE TABLE [dbo].[dhh_facets_links]("
		        													 + "[pk] [bigint] NOT NULL,"
		        													 + "[id1] [varchar] (35) NOT NULL,"
		        													 + "[fname] [varchar](30),"
		        													 + "[lname] [varchar](45),"
		        													 + "[date1] [varchar](35) NOT NULL,"
		        													 + "[street] [varchar] (45),"
		        													 + "[city] [varchar] (45),"
		        													 + "[state][varchar] (2),"
		        													 + "[zip] [integer],"
		        													 + "[gender] [varchar](1) NULL,"
		        													 + "[id2] [varchar] (15),"
		        													 + "[date2] [varchar] (35) NOT NULL,"
		        													 + "[other_name] [varchar] (35),"
		        													 + "CONSTRAINT [PK_dhh] PRIMARY KEY CLUSTERED" 
		        													 + "("
		        													 + 		"[pk] ASC"
		        												     + ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]"
		        												     + ") ON [PRIMARY]");
		        												     
		        drop_table.execute();
		        create_table.execute();
		        
		        PreparedStatement insert_statement = con.prepareStatement("INSERT INTO dhh_facets_links (pk,id1,fname,lname,date1,"
		        														+ "street,city,state,zip,gender,id2,date2,other_name)"
		        														+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		       
		        StringBuilder sb = new StringBuilder();
		        
		        
		        String v0,v1,v2,v3,v4,v5,v6,v8,v9,v10,v11;
		        long v_pk,v7;
		        int pk_generator = 1;
		    
		        
		        // read line by line from numbers file
		        while ((read = read_dhh.readLine()) != null) 
		        {
		        	// put the string in file delimited by vertical bar into array
		            String[] split = read.split("\\|");
		           
		            /*for (String part : split) {
		            	System.out.println(part);
		            }*/
		        		
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
		           write_dhh.write(String.format("Om Mani Padme Hong:  %-150s  %s", sb.toString(),System.lineSeparator()));
				   
		           // reset the string builder
				   sb.setLength(0);
				   
		           
		        		
		            // save all column values into variables
		            v_pk = pk_generator;  // generates values for primary key from 1 till ........
		            v0 = split[0];     
		        	v1 = split[1];
		            v2 = split[2];
		        	v3 = split[3];
		            v4 = split[4];
		            v5 = split[6];  // offset starts right here because of empty values in file --> | | 
		        	v6 = split[7];       
		            v7 = Long.parseLong(split[8]);    // zip code
        			v8 = split[9];
        			
		        	v9 = split[10];
		        	v10 = split[11];
		        	v11 = split[12];
		        			
		        					
		        	System.out.println("v_pk:  " + v_pk);
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
		        	System.out.println("v11:  " + v11);
		        		
			        //System.out.println(String.format("%-55s",hold_v));
		        	//System.out.println(hold_v);
			    
			            
			        // set the values for insert statement
		        	insert_statement.setLong(1,v_pk);
			        insert_statement.setString(2,v0);
			        insert_statement.setString(3,v1);
			        insert_statement.setString(4,v2);
			        insert_statement.setString(5,v3);
			        insert_statement.setString(6,v4);
			        insert_statement.setString(7,v5);
			        insert_statement.setString(8,v6);
			        insert_statement.setLong(9,v7);
			        insert_statement.setString(10,v8);
			        insert_statement.setString(11,v9);
			        insert_statement.setString(12,v10);
			        insert_statement.setString(13,v11);
			          
				    insert_statement.executeUpdate();
			        
				        
				
			      
			        pk_generator = pk_generator + 1;
				        
		        }
		            
		        	
		      
		        read_dhh.close();
		        write_dhh.close();
		        
		        
		        // calculate end time and program elapsed time
		        Date end_time = new Date();
		        // this gets a system number time that is big
			    long total_end_time = end_time.getTime();
			 
			    // result is in milliseconds, so divide by 1000
			    double convert_seconds = (total_end_time - total_start_time) / 1000.00;
			    
			    
			    System.out.println("");
			    System.out.println(end_time);
			    System.out.println(convert_seconds);
			    System.out.println("End time:  " + total_end_time);
			    System.out.println("Program time elapsed:  " + convert_seconds + " seconds");
			      
		        
		    }
			
			catch (Exception e) 
			{
		    	System.out.println("Error:  " + e);
			}
			
			
					

	 }
	
	

}
