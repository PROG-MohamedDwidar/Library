package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage s) throws Exception {
		
		Parent root=FXMLLoader.load(getClass().getResource("/login/LoginGUI.fxml"));
	
		
		
		Scene ss=new Scene(root);
		
		s.setScene(ss);
		
		
		
		s.show();
	}
	
	public static void main(String[] args) {
		launch(args);
		//System.out.println(emps.checkpass("1", "1111"));
	}
}