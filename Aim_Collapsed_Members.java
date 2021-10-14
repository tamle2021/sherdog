
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;



public class Aim_Collapsed_Members {

	
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
		      
		      
		        
		    
		        BufferedReader read_aim = new BufferedReader(new FileReader("C:\\Users\\e42914\\Desktop\\programming\\java\\csv_files\\AIM_Collapsed_Members.csv"));
		        //BufferedReader find_total_words = new BufferedReader(new FileReader("C:\\Users\\e42914\\Desktop\\programming\\java\\text\\wor.txt"));
		        
		        File aim_output = new File("C:\\Users\\e42914\\Desktop\\programming\\java\\text\\aim_output.txt");
		       
		        BufferedWriter write_aim = new BufferedWriter(new FileWriter(aim_output));
		         
		        PreparedStatement drop_table = con.prepareStatement("IF OBJECT_ID('aim_collapsed_members') IS NOT NULL DROP TABLE aim_collapsed_members");
		        PreparedStatement create_table = con.prepareStatement("CREATE TABLE [dbo].[aim_collapsed_members]("
		        													 + "[pk] [bigint] NOT NULL,"
		        													 + "[group_id] [varchar] (25) NOT NULL,"
		        													 + "[sbsb_id] [varchar] (25) NOT NULL,"
		        													 + "[lname] [varchar](30),"
		        													 + "[mid_initial_fname] [varchar](45),"
		        													 + "[id1_street] [varchar](125),"
		        													 + "[city] [varchar] (45),"
		        													 + "[state][varchar] (2),"
		        													 + "[id2] [varchar] (45),"
		        													 + "[id3] [varchar](30),"
		        													 + "[id4] [varchar] (15),"
		        													 + "CONSTRAINT [PK_aim] PRIMARY KEY CLUSTERED" 
		        													 + "("
		        													 + 		"[pk] ASC,[sbsb_id],[id3],[id1_street]"
		        												     + ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]"
		        												     + ") ON [PRIMARY]");
		        												     
		        drop_table.execute();
		        create_table.execute();
		        
		        PreparedStatement insert_statement = con.prepareStatement("INSERT INTO aim_collapsed_members (pk,group_id,sbsb_id,lname,mid_initial_fname,"
		        														+ "id1_street,city,state,id2,id3,id4)"
		        														+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		       
		        StringBuilder sb = new StringBuilder();
		        
		        
		        String v0,v1,v2,v3,v4,v5,v6,v7,v8,v9;
		        long v_pk;
		        
		        // random number generator
		        Random rand = new Random();
		        
		        int pk_generator; 
		    
		        
		        
		        
		        
		        //List<String> list_test1 = Arrays.asList(split);
	            List<String> list_test1 = new ArrayList<String>();
	            Iterator<String> iterate = list_test1.iterator();
	            List<String> list_test2 = new ArrayList<String>();
	            
		        	        
		        // read line by line from numbers file
		        while ((read = read_aim.readLine()) != null) 
		        {
		        	// put the line in file delimited by vertical bar into array
		            String[] split = read.split("\\|");
		           
		            // -------------------------------------------------------------------------
		            // convert from string array to list
		           
		            list_test1 = Arrays.asList(split);
		            
		            // read contains line of data and add into list
		            //list_test2.add(read);
		            
		            //System.out.println(list_test1);
		            
		            //System.out.println("List write:  " + list_test2);
		            
		          
		            /*while (iterate.hasNext())
		            {
		            	System.out.println(iterate.next());
		            }*/
		            
		            /*for (String item : list_test)
		            {
		            	System.out.println(item);
		            }*/
		            
		           // ---------------------------------------------------------------------------
			           
		           // at index 6, we read from 6 to the end
		           // save into string builder
		           for (i = 0; i < split.length; i++)
		           {
		        		if (i > 5)
		        		{
		        			sb.append(split[i] + "    ");
		        		}
		        		
		           }
		           
		           System.out.println("Namo Amitabha	Namo Avalokitesvara    Namo Aksobhya    Namo Sakyamunaye");
		           System.out.println("Namo Sampuspita Salendra Rajaya    Namo Ratna Kusuma Ketu Rajaya");
		           System.out.println("Namo Drdha Sura Sena Pra Harana Rajaya    Namo Bhaisajya Guru Vaidurya Prabha Rajaya");
		           System.out.println(" ---- " + sb.toString());
		           
		           // write to file
		           // System.out.println(String.format("My number:  %-9s    Count:  %-9s",number,count));
		           write_aim.write(String.format("Om Mani Padme Hong:  %-150s  %s", sb.toString(),System.lineSeparator()));
				   
		           // reset the string builder
				   sb.setLength(0);
				   
				   // get random integer from 1 - 1000000
		           pk_generator = rand.nextInt(999998)+ 1; 
		        		
		            // save all column values into variables
		           v_pk = pk_generator;  // generates values for primary key from 1 till ........
		           v0 = split[0];     
		           v1 = split[1];
		           v2 = split[2];
		           v3 = split[3];
		           v4 = split[4];
		           v5 = split[5];  
		           v6 = split[6];       
		           v7 = split[7];  
        		   v8 = split[8];
		           v9 = split[9];
		       
		        			
		        					
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
		           	System.out.println("String array length:  " + split.length);
		        
		        		
			        //System.out.println(String.format("%-55s",hold_v));
		        	//System.out.println(hold_v);
			    
			        if (split.length == 10)
			        {
			        // set the values for insert statement
			        	insert_statement.setLong(1,v_pk);
			        	insert_statement.setString(2,v0);
			        	insert_statement.setString(3,v1);
			        	insert_statement.setString(4,v2);
			        	insert_statement.setString(5,v3);
			        	insert_statement.setString(6,v4);
			        	insert_statement.setString(7,v5);
			        	insert_statement.setString(8,v6);
			        	insert_statement.setString(9,v7);
			        	insert_statement.setString(10,v8);
			        	insert_statement.setString(11,v9);
			       
			        	insert_statement.executeUpdate();
			        }
				        
				
			      
			  
				        
		        }
		            
		        	
		      
		        read_aim.close();
		        write_aim.close();
		        
		        
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
