package com.ntpc.pradip;

import java.sql.SQLException;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DBMigrate m=new DBMigrate();
		
		try {
			m.migrateData();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
