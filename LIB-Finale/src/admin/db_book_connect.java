package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class db_book_connect {
	static String url="jdbc:mysql://projectlibprog.mysql.database.azure.com:3306/proj";
	static String n="project@projectlibprog",pass="rootrt*1";
	
	static public ObservableList<book>fillt(ResultSet insert){
		ObservableList<book>oo=FXCollections.observableArrayList();
		try {
			while(insert.next()) {
				//System.out.println(insert.getString(1));
				oo.add(new book(insert.getString(1),insert.getString(2),insert.getString(3),insert.getString(4),insert.getString(5),insert.getString(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return oo;
	}
	public static ObservableList<book> getAll() throws SQLException {
		Connection c=DriverManager.getConnection(url,n,pass);
		Statement ss=c.createStatement();
		ResultSet ans= ss.executeQuery("SELECT * FROM books");	
		ObservableList<book>oo=fillt(ans);
		c.close();
		return oo;
	}
	public static void addbook(String isbn,String nm,String categ,String auth,String qt) throws Exception{
		Connection c=DriverManager.getConnection(url,n,pass);
		Statement ss=c.createStatement();
		ss.execute("INSERT INTO `proj`.`books`\r\n"
				+ "(`ISBN`,\r\n"
				+ "`Bname`,\r\n"
				+ "`Category`,\r\n"
				+ "`Author`,\r\n"
				+ "`TotQT`)\r\n"
				+ "VALUES\r\n"
				+ "("+isbn+",'"+nm+"','"+categ+"','"+auth+"',"+qt+");");
		c.close();
	}
}
