package admin;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class addbookcontrol implements Initializable {
	@FXML
	TextField isbnprom;
	@FXML
	TextField nameprom;
	@FXML
	TextField authprom;
	@FXML
	TextField qtprom;
	@FXML
	ChoiceBox <String> catprom;
	String[]choices= {"Fiction","Horror","Romance","Fantasy","Biography"};
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		catprom.getItems().addAll(choices);
		
	}
	public void doadd(ActionEvent e) throws Exception{
		db_book_connect.addbook(isbnprom.getText(), nameprom.getText(), catprom.getValue(),authprom.getText(), qtprom.getText());
		Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
		stage.close();
	}
	
	
	
}
