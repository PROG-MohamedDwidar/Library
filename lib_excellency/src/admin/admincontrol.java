package admin;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class admincontrol implements Initializable  {
	@FXML
	TextField searchable;
	//Book Table
	//----------------------------------
	@FXML
	TableView<book>tb;
	@FXML
	TableColumn<book,String>isbn;
	@FXML
	TableColumn<book,String>nam;
	@FXML
	TableColumn<book,String>cat;
	@FXML
	TableColumn<book,String>auth;
	@FXML
	TableColumn<book,String>qt;
	@FXML
	TableColumn<book,String>out;
	@FXML
	TableColumn<book, Button>edi;
	
	//Employee Table
	//----------------------------------
	@FXML
	TableView <Emp> tabl;
	@FXML
	TableColumn <Emp,String> id;
	@FXML
	TableColumn <Emp,String> name;
	@FXML
	TableColumn <Emp,String> job;
	@FXML
	TableColumn <Emp,String> email;
	@FXML
	TableColumn <Emp,String> phone;
	//----------------------------------
	//ADD book
	//----------------------------------
	public void addbook(ActionEvent e) throws Exception{
		Parent rt=FXMLLoader.load(getClass().getResource("addbook.fxml"));
		Stage ss=new Stage();
		Scene sc=new Scene(rt);
		ss.setScene(sc);
		ss.show();
		
	}
	
	
	
	//Refresh Employee
	public void refresh(ActionEvent e) {

		try {
			tabl.setItems(db_user_connect.getAll());
		} catch (SQLException e1) {

			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL url,ResourceBundle resourceBundle) {
		//setting the employee
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		job.setCellValueFactory(new PropertyValueFactory<>("job"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		phone.setCellValueFactory(new PropertyValueFactory<>("pnum"));
		//---------------------------------------------
		refresh(new ActionEvent());
		//-----------------------------------------------
		//setting the books table columns
		isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		nam.setCellValueFactory(new PropertyValueFactory<>("nam"));
		cat.setCellValueFactory(new PropertyValueFactory<>("cat"));
		auth.setCellValueFactory(new PropertyValueFactory<>("auth"));
		qt.setCellValueFactory(new PropertyValueFactory<>("totq"));
		out.setCellValueFactory(new PropertyValueFactory<>("tak"));
		edi.setCellValueFactory(new PropertyValueFactory<>("bb"));
		//-----------------------------------------------
		refbook();
		
		
	}
	public void refbook() {
		try {
			tb.setItems(db_book_connect.getAll());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void seabook(ActionEvent e) throws SQLException {
		tb.setItems(db_book_connect.search(searchable.getText()));
	}
	
	
}
