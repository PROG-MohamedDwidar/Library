package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControlAdmin {
	
	
	public void logout(ActionEvent e) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
