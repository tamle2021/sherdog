
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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




public class import_numbers {

	
	 public static void main(String[] args) throws IOException {

		  
		    // declare variables
		    String number;
		    
		    int count_words = 0;
		    int count_total_words = 0;
		    
		    // program start time and calculate time elapsed for program
		    Date start_time = new Date();
		    // this gets a system number time that is big
		    long total_start_time = start_time.getTime();
		    
		    System.out.println(start_time);
		    System.out.println("Get time:  " + total_start_time);
		    System.out.println(Instant.now());
		    
			try
		    {
				
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		        String connection_url = "jdbc:sqlserver://eds-e42914-v04:1433;"+"databaseName=test;user=e42914;password=Sql2121@!";
		        Connection con = DriverManager.getConnection(connection_url);

				
		        String read = null;
		        int i;
		        int count = 1;
		        
		    
		        BufferedReader input_num = new BufferedReader(new FileReader("C:\\Users\\e42914\\Desktop\\programming\\java\\text\\sample_number.txt"));
		        BufferedReader input_word = new BufferedReader(new FileReader("C:\\Users\\e42914\\Desktop\\programming\\java\\text\\words.txt"));
		        BufferedReader find_total_words = new BufferedReader(new FileReader("C:\\Users\\e42914\\Desktop\\programming\\java\\text\\words.txt"));
		        
		        
		        
		        PreparedStatement truncate = con.prepareStatement("Truncate table sample");
		        truncate.execute();
		        
		        PreparedStatement insert_statement = con.prepareStatement("INSERT INTO sample (id) VALUES (?)");
		       
		        // read line by line from numbers file
		        while ((read = input_num.readLine()) != null) 
		        {
		        	// put numbers in file delimited by comma into array
		        	String[] split = read.split(",");
		           
		        
		        	for (i = 1; i < split.length; i++)
		        	{
		        		number = split[i];
			            //System.out.println("My number:  " + number + "  Count:  " + count);
			            
			            System.out.println(String.format("My number:  %-9s    Count:  %-9s",number,count));
			            count = count + 1;
			            
			            
			            insert_statement.setString(1,number);
				        insert_statement.execute();
			        
		
		        	}
		            
		           
		            
		        }
		        
				// select data into result set
		        PreparedStatement select_statement = con.prepareStatement("SELECT * FROM sample");
		        ResultSet rs = select_statement.executeQuery();
		        PreparedStatement update_statement = con.prepareStatement("UPDATE sample SET words = ? WHERE id = ?");
		        
		        
		        
		        
		        // loop through result set
		        while (rs.next())
		        {
		        	String id = rs.getString("id");
		        	System.out.println(String.format("ID:  %-10s", id));
		        	
		        	// get data from word file and execute update statement
		        	if ((read = input_word.readLine()) != null)
		        	{
		        		// split the read by newline
		        		String [] split = read.split("\n");
		        		System.out.println("The read:  " + read);
		        		update_statement.setString(1,split[0]);
		        		update_statement.setString(2,id);
			        	update_statement.execute();
		        	}
		        	
		        	
		        }
		        
		        
		        // find total number of words in text file
		        while ((read = find_total_words.readLine()) != null) 
		        {
		        	// split read by space character and put into array
		        	String[] split = read.split(" ");
		        	count_words = split.length;
		        	count_total_words = count_total_words + count_words;
		        	
		        	System.out.println("Count words:  " + count_words);
		        	System.out.println("Count total words:  " + count_total_words);
		            
		        	
		        }
		        
		        
		        input_num.close();
		        input_word.close();
		        find_total_words.close();
		        
		        // calculate end time and program elapsed time
		        Date end_time = new Date();
		        // this gets a system number time that is big
			    long total_end_time = end_time.getTime();
			 
			    double convert_seconds = (total_end_time - total_start_time) / 1000.00;
			    
			    System.out.println("Get time:  " + total_end_time);
			   
			    System.out.println("Program time elapsed:  " + convert_seconds + " seconds");
			    
		        
		        
		    }
		    catch (Exception e) 
			{
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
