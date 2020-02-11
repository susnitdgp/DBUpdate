package com.ntpc.pradip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBMigrate {
	
		
	public boolean migrateData(String emp_num,String project, String region) throws ClassNotFoundException, SQLException{
		
		Connection con1 = null;
		PreparedStatement pstm1 = null;
		
		boolean success=false;
				
		try {
			
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			con1 = DriverManager.getConnection("jdbc:db2://10.3.0.56:50005/MAXDB76", "ctginst1","NTPC@123");
									
			pstm1 = con1.prepareStatement("UPDATE MAXIMO.PERSON SET NTPCBUSAREA=?, NTPCREGION=? WHERE PERSONID =? ");
						
								
			pstm1.setString(1, project);
			pstm1.setString(2, region);
			pstm1.setString(3, emp_num);
			
			int result=pstm1.executeUpdate();
			
			if(result>0){
				System.out.println("Update Done: " + emp_num);
				success=true;
			}else{
				
				System.out.println("Emp Not Found: " + emp_num);
			}
			
			
		
		}
		catch( SQLException ex){
			
			System.out.println(ex.getMessage());
		}
		finally {
			
			if (con1 != null | pstm1 != null ) {
				con1.close();
				pstm1.close();
				
				
				con1 = null;
				pstm1 = null;
								
				}
				
			
			
		}
		
		return success;
	}
	
	
}

