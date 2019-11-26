package com.ntpc.pradip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMigrate {
	
		
	public void migrateData() throws ClassNotFoundException, SQLException{
		
		Connection con1 = null;
		Connection con2=null;
		
		PreparedStatement pstm1 = null;
		PreparedStatement pstm2 = null;
		
		ResultSet rs1=null;
		ResultSet rs2=null;
		int count=1;
	
		
		try {
			
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			con1 = DriverManager.getConnection("jdbc:db2://10.3.0.56:50005/MAXDB76", "ctginst1","NTPC@123");
			
			
			
			pstm1 = con1.prepareStatement("SELECT PERSONID FROM MAXIMO.PERSON where NTPCBUSAREA IS NULL");
			//pstm.setString(1, userid);
			
			rs1 = pstm1.executeQuery();
			//pstm1.close();
			while (rs1.next()) {
				
				String person=rs1.getString(1);
				
				con2 = DriverManager.getConnection("jdbc:db2://10.3.0.94:50000/PCOMMON", "db2inst1","Devecm@321");
				pstm2 = con2.prepareStatement("SELECT EMP_NO,PROJECT,REGION FROM PCOMMON.P_EMPLOYEE_MASTER where  emp_no=?");
				pstm2.setString(1, person);
				
				rs2 = pstm2.executeQuery();
				//pstm2.close();
				
				while(rs2.next()){
					
					
					String emp_num=rs2.getString(1);
					String project=rs2.getString(2);
					String region=rs2.getString(3);
					
					if(!this.isNullOrEmpty(project)&& !this.isNullOrEmpty(region) ) {
						
						count ++;
						System.out.println(count + " " +emp_num + ":" + project + ":"+region);
						
						pstm1 = con1.prepareStatement("UPDATE MAXIMO.PERSON SET NTPCBUSAREA=?, NTPCREGION=? WHERE PERSONID=? ");
						
						System.out.println("Update Done: " + emp_num);
						
						pstm1.setString(1, project);
						pstm1.setString(2, region);
						pstm1.setString(3, emp_num);
						
						pstm1.addBatch();
						
						pstm1.executeBatch();
						pstm1.close();
						//con1.close();
					
											
					}
					
					
				}
				//pstm2.close();
				//con2.close();
			
			}
			
		
		}
		catch( SQLException ex){
			
			System.out.println(ex.getMessage());
		}
		finally {
			
			/*if (con1 != null | pstm1 != null || rs1 != null ) {
				con1.close();
				pstm1.close();
				rs1.close();
				
				con1 = null;
				pstm1 = null;
				rs1 = null;
				
				con2.close();
				pstm2.close();
				rs2.close();
				
				con2 = null;
				pstm2 = null;
				rs2 = null;
				
				}*/
				
			
			
		}
		

	}
	
	private boolean isNullOrEmpty(String str) {
		//StringUtils.is
		
		if (str != null && !str.trim().isEmpty())
			return false;
		return true;
	}
}

