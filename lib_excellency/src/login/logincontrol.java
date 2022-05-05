package login;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class logincontrol {
	@FXML
	TextField id;
	@FXML
	TextField pass;
	@FXML
	Label outname;
	@FXML
	Label outpass;
	@FXML
	Label outunknown;
	
	
	
	public void login(ActionEvent e) throws Exception {
		
		String serverresponse=db_login_connect.checkpass(id.getText(), pass.getText());
		if(serverresponse.equals("1")) {
			//System.out.println("it worked :)");
			Parent root = FXMLLoader.load(getClass().getResource("/admin/adminUI.fxml"));
			Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
			Scene scene=new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		}
		else if(serverresponse.equals("2")) {
			
		}
		else {
			System.out.println(serverresponse);
			if(serverresponse.equals("password wrong")) {
				outpass.setText(serverresponse);
				outname.setText("");
				outunknown.setText("");
			}
			else if(serverresponse.equals("wrong id")) {
				outpass.setText("");
				outname.setText(serverresponse);
				outunknown.setText("");
			}
			else {
				outpass.setText("");
				outname.setText("");
				outunknown.setText(serverresponse);
			}
		}
		
		
	}
}
	/*Statement st = null;
	String query ="select * from emps";
	Alert alert;
	ResultSet set = st.executeQuery(query);

        if(){
				while(set.next()){
				String name = set.getString("ename");
				System.out.println(name);
				}
				alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setContentText("Saved");
				}
				else {
				alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Error Occured");

				}
				alert.showAndWait();
				}*/