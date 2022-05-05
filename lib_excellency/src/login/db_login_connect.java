package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class db_login_connect {
	static String checkpass(String name,String p) {
		String url="jdbc:mysql://projectlibprog.mysql.database.azure.com:3306/proj";
		String n="project@projectlibprog",pass="rootrt*1";
		String q="select epass,ejobid from emps where eid='"+name+"' or ename=\'"+name+"\'";
		Connection c = null;
		String response;
		try {
			c = DriverManager.getConnection(url,n,pass);
			
			Statement st=c.createStatement();
			
			ResultSet ans=st.executeQuery(q);
			if(ans.next()) {
				if(ans.getString(1).equals(p)) {
					response = ans.getString(2);
					c.close();
					return response;
				}
				else {
					//System.out.println(ans.getString(1));
					c.close();
					return "password wrong";
				}
			}
			else {
				c.close();
				return "wrong id";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(c!=null)c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "unknown error check terminal";
	}
}
