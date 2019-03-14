package com.dy.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.dy.util.Doctor;
/**
 * 数据库操作类
 * @author dingye
 *
 */
public class DataBaseOperate {
	public static boolean getLogin(Doctor doctor){
		Connection con=null;
		Statement st;
		ResultSet rs=null;
		String sqlstr;
		String un;
		String pw;
		try{
			con=DBConnectManager.getDataBase();
			st=con.createStatement();
			un=doctor.getName();
			pw=doctor.getPassword();
			System.out.println(un+pw);
			sqlstr="select * from doctor where username='"+un+"' and password='"+pw+"'";
			rs=st.executeQuery(sqlstr);
			while(rs.next()){
				if(rs.getString("username").equals(un)&&rs.getString("password").equals(pw));
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
		
	}
}
