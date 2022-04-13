package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	public void login(ActionEvent e) {
		String serverresponse=emps.checkpass(id.getText(), pass.getText());
		if(serverresponse.equals("1")) {
			System.out.println("success");
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
