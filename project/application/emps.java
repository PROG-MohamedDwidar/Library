package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public abstract class emps {
	static String checkpass(String name,String p) {
		String url="jdbc:mysql://127.0.0.1:3306/project";
		String n="root";
		String q="select epass,ejobid from emps where eid="+name;
		Connection c;
		try {
			c = DriverManager.getConnection(url,n,n);
			Statement st=c.createStatement();
			ResultSet ans=st.executeQuery(q);
			if(ans.next()) {
				if(ans.getString(1).equals(p)) {
					return ans.getString(2);
				}
				else {
					//System.out.println(ans.getString(1));
					return "password wrong";
				}
			}
			else {
				return "wrong id";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "unknown error check terminal";
	}
}
