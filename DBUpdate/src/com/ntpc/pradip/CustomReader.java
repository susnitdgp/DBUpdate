package com.ntpc.pradip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;


public class CustomReader {
	
	public Data readFile() throws ClassNotFoundException, SQLException{
		
		
		Data data=new Data();
		  try {
			  
			  File file = new File("E:\\cert\\new_file.csv"); 
			  
			  BufferedReader br = new BufferedReader(new FileReader(file)); 
			  DBMigrate migrate=new DBMigrate();
			  String st;
			  
			  int i=0;
			  int j=0;
			  while ((st = br.readLine()) != null) {
				  
				 
				String[] arr=st.split(",");
				

				DecimalFormat df = new DecimalFormat("000000");
				String emp=df.format(Double.parseDouble(arr[1]));
				
			    System.out.println("EMP NO:" + emp + " PROJECT: " + arr[16] + " REGION: " + arr[84]);
			    i=i+1;
			    data.setEmp_num(emp);
			    data.setProject(arr[16]);
			    data.setRegion(arr[84]);
			    
			    				
				boolean result=migrate.migrateData(data.getEmp_num(), data.getProject(), data.getRegion());
			    if(result)
			    	j++;
				
			    System.out.println("Total Emp Count:" + i + " Success " + j);
				
			    
			  }
		
			  System.out.println("Total Emp Count:" + i);
			  System.out.println("Total Success Count:" + j);
			  
			  
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
			  
			return data;    
		
	}
	
}
