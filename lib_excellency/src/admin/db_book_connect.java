package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

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
				//FXCollections.observableArrayList();
		/*while(ans.next()){
			oo.add(new book(ans.getString(1),
							ans.getString(2),
							ans.getString(3),
							ans.getString(4),
							ans.getString(5),
							ans.getString(6)));
		}*/


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
		ResultSet isadd=ss.executeQuery("select * from books where isbn="+isbn);
		Alert alert;
		if(isadd.next()){
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("Saved");
		}
		else{
			alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Error Occured");
		}
		alert.showAndWait();
		c.close();
	}
	public static ObservableList<book> search(String key) throws SQLException {
		Connection c=DriverManager.getConnection(url,n,pass);
		Statement ss=c.createStatement();
		ResultSet ans=ss.executeQuery("select * from books where (Bname like '%"+key+"%') or (Category like '%"+key+"%') or (Author like'%"+key+"%')");
		ObservableList<book>oo=fillt(ans);
		return oo;
	}
	public static book getBook(String isbn) throws SQLException {
		Connection c=DriverManager.getConnection(url,n,pass);
		Statement ss=c.createStatement();
		ResultSet ans=ss.executeQuery("select * from books where isbn ="+isbn);
		ans.next();
		book ret=new book(ans.getString(1),ans.getString(2),ans.getString(3),ans.getString(4),ans.getString(5),ans.getString(6));
		return ret;
	}
	public static void upbook(String isb,String quant) throws SQLException {
		Connection c=DriverManager.getConnection(url,n,pass);
		Statement ss=c.createStatement();
		ss.execute("UPDATE `proj`.`books` " + "SET" + " `TotQT` = "+quant+ " WHERE `ISBN` = "+isb+";");
		c.close();
	}
	public static void delete(String isb) throws SQLException {
		Connection c=DriverManager.getConnection(url,n,pass);
		Statement ss=c.createStatement();
		//ss.execute("UPDATE `proj`.`books` " + "SET" + " `TotQT` = "+quant+ " WHERE `ISBN` = "+isb+";");
		c.close();
	}
}//(ISBN ="+key+") or



