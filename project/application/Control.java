package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Control {
	@FXML
	TextField id;
	@FXML
	TextField pass;
	@FXML
	Label warnings;
	@FXML
	Label out;
	@FXML
	ImageView angryface;
	
	
	public void login(ActionEvent e) throws Exception {
		
		String serverresponse=emps.checkpass(id.getText(), pass.getText());
		if(serverresponse.equals("1")) {
			Parent root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
			Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
			Scene scene=new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		}
		else if(serverresponse.equals("2")) {
			
		}
		else {
			out.setText(serverresponse);
			try {
				InputStream inim=new FileInputStream("D:\\WorkSpace\\project\\Resources\\Angry.jpeg");
				Image im=new Image(inim);
				angryface.setImage(im);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
}
