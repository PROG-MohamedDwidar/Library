package admin;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class book {
	private String isbn="N/A",totq="N/A",tak="N/A";
	private String nam="N/A",cat="N/A",auth="N/A";

	private Button bb;
	book(String isbn,String nam,String cat,String auth,String totq,String tak){
		this.isbn=isbn;
		this.nam=nam;
		this.cat=cat;
		this.auth=auth;
		this.totq=totq;
		this.tak=tak;
		bb=new Button("edit");
		bb.setOnAction((event)->{
			Stage edito=new Stage();
			try {

				FXMLLoader loader=new FXMLLoader(getClass().getResource("edito_UI.fxml"));
				Parent root=loader.load();
				editBcontrol editc=loader.getController();

				editc.initialize(isbn);
				//System.out.println("---------11"+editc.isbnsearch);
				Scene ss=new Scene(root);
				edito.setScene(ss);
				edito.show();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});
	}
	public String getIsbn(){return isbn;}
	public String getNam(){return nam;}
	public String getCat(){return cat;}
	public String getAuth(){return auth;}
	public String getTotq(){return totq;}
	public String getTak(){return tak;}
	public Button getBb() {return bb;}
}
