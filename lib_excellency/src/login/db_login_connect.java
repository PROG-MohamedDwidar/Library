package login;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class db_login_connect {
	//connection url//
	static String url="jdbc:mysql://projectlibprog.mysql.database.azure.com:3306/proj";

	//name and password//
	static String n="project@projectlibprog",pass="rootrt*1";


	//checks if password is right and returns the jobid to the controller or returns the error statement//
	static String checkpass(String name,String p) {
		String q="select epass,ejobid from emps where eid='"+name+"' or ename=\'"+name+"\'";
		Connection c = null;
		String response;
		try {
			c = DriverManager.getConnection(url,n,pass);
			
			Statement st=c.createStatement();
			
			ResultSet ans=st.executeQuery(q);
			//if there is an answer it means the name or id was found and it is an employee
			// now we check the password using the data in the resultset
			if(ans.next()) {
				if(ans.getString(1).equals(p)) {
					//if the password is also right return the job id
					// 1 for admin 2 for librarian
					response = ans.getString(2);
					c.close();
					return response;
				}
				else {
					//password turned out to be wrong
					c.close();
					return "password wrong";
				}
			}
			else {
				//having no result set means the id was not found in database
				//in other words employee doesn't exist
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
		//reaching this point means an error happened in the connection process
		//the error was probably caused by network issues
		return "unknown error check terminal";
	}

	//we usually search in the database using the primary key
	//this method converts the username of the employee to his id in order to be sent to a controller to print it in a receipt
	static String getid(String name) throws SQLException {
		try {
			//the user might enter an id or a username
			//we check here if the value entered by user isn't already an id
			Integer.parseInt(name);
			return name;
		}
		catch (Exception e){
			//trying to convert a username to an integer will cause an exception
			//we catch it and get the corresponding id from the database
			Connection c = DriverManager.getConnection(url,n,pass);
			Statement st=c.createStatement();
			ResultSet ans=st.executeQuery("select eid from emps where ename='"+name+"'");
			ans.next();
			String res= ans.getString(1);
			c.close();
			return res;
		}

	}
}
