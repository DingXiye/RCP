package com.dy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * ���ݿ����ӹ�����
 * @author dingye
 *
 */
public class DBConnectManager {
	private static Connection con;
	public DBConnectManager() {
		// TODO Auto-generated constructor stub
	}
	public static String driver = "com.mysql.jdbc.Driver";
	public static Connection getDataBase() throws SQLException {
		try{
			Class.forName(driver);
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123456");
			System.out.println("�������ݿ�ɹ�");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return con;
	}

	public static void closeConnection(){
		try{
			con.close();
		}catch(SQLException e){
			
			e.printStackTrace();
		}
		con=null;
	}
}
