package admin;

import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import liblib.libmain_control;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

public abstract class db_book_connect {
	static String url="jdbc:mysql://projectlibprog.mysql.database.azure.com:3306/proj";
	static String n="project@projectlibprog",pass="rootrt*1";
	static private Image defim(byte[]imam){
		Image im;
		if(imam!=null){
			im=new Image(new ByteArrayInputStream(imam));
		}
		else{
			im=new Image("https://images.assetsdelivery.com/compings_v2/yehorlisnyi/yehorlisnyi2104/yehorlisnyi210400016.jpg");
		}
		return im;
	}

	static public ObservableList<book>fillt(ResultSet insert){
		ObservableList<book>oo=FXCollections.observableArrayList();
		try {
			while(insert.next()) {
				//System.out.println(insert.getString(1));
				oo.add(new book(insert.getString(1),insert.getString(2),insert.getString(3),insert.getString(4),insert.getString(5),insert.getString(6),defim(insert.getBytes(7))));
			}
		} catch (SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}

		return oo;
	}
	static public ObservableList<book>fillt(ResultSet insert, TextField i, TextField n, TextField c, TextField a, ImageView v, libmain_control controller){
		ObservableList<book>oo=FXCollections.observableArrayList();
		try {
			while(insert.next()) {
				//System.out.println(insert.getString(1));
				oo.add(new book(insert.getString(1),insert.getString(2),insert.getString(3),insert.getString(4),insert.getString(5),insert.getString(6),defim(insert.getBytes(7)),i,n,c,a,v,controller));
			}
		} catch (SQLException e) {
			    //TODO Auto-generated catch block
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
	public static ObservableList<book> search(String key, TextField i, TextField na, TextField ca, TextField a,ImageView v,libmain_control controller) throws SQLException {
		Connection c=DriverManager.getConnection(url,n,pass);
		Statement ss=c.createStatement();
		ResultSet ans=ss.executeQuery("select * from books where (Bname like '%"+key+"%') or (Category like '%"+key+"%') or (Author like'%"+key+"%')");
		ObservableList<book>oo=fillt(ans,i,na,ca,a,v,controller);
		c.close();
		return oo;
	}
	public static ObservableList<book> search(String key) throws SQLException {
		Connection c=DriverManager.getConnection(url,n,pass);
		Statement ss=c.createStatement();
		ResultSet ans=ss.executeQuery("select * from books where (Bname like '%"+key+"%') or (Category like '%"+key+"%') or (Author like'%"+key+"%')");
		ObservableList<book>oo=fillt(ans);
		c.close();
		return oo;
	}
	public static book getBook(String isbn) throws SQLException {
		Connection c=DriverManager.getConnection(url,n,pass);
		Statement ss=c.createStatement();
		ResultSet ans=ss.executeQuery("select * from books where isbn ="+isbn);
		ans.next();
		Image im=defim(ans.getBytes(7));
		book ret=new book(ans.getString(1),ans.getString(2),ans.getString(3),ans.getString(4),ans.getString(5),ans.getString(6),im);
		return ret;
	}
	public static void upbook(String isb, String quant, Image im) throws SQLException, IOException {
		File fileim=new File("imbuf");
		ImageIO.write(SwingFXUtils.fromFXImage(im,null),"png",fileim);
		FileInputStream fin=new FileInputStream(fileim);
		Connection c=DriverManager.getConnection(url,n,pass);
		PreparedStatement ss=c.prepareStatement("UPDATE `proj`.`books` SET `TotQT` = ? , cover = ? WHERE `ISBN` = ? ;");
		ss.setNString(1,quant);
		ss.setBinaryStream(2,fin,(int)fileim.length());
		ss.setNString(3,isb);
		ss.executeUpdate();
		c.close();
	}
	public static void delete(String isb) throws SQLException {
		Connection c=DriverManager.getConnection(url,n,pass);
		Statement ss=c.createStatement();
		//ss.execute("UPDATE `proj`.`books` " + "SET" + " `TotQT` = "+quant+ " WHERE `ISBN` = "+isb+";");
		c.close();
	}
}//(ISBN ="+key+") or



