package com.ntpc.pradip;

import java.sql.SQLException;

public class Start {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		/*
		DBMigrate m=new DBMigrate();
		
		try {
			
			m.migrateData();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block hello
			e.printStackTrace();
		}*/
		
		CustomReader reader=new CustomReader();
		
		Data data=reader.readFile();
		
		
		
		
	}

}
