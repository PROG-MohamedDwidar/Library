package admin;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import liblib.libmain_control;

import java.io.IOException;

public class book {
	private String isbn="N/A",totq="N/A",tak="N/A";
	private String nam="N/A",cat="N/A",auth="N/A";
	private Image cov;
	private Button bb,bba,bbv;

	private TextField i,n,c,a;
	private ImageView co;
	private libmain_control controller;
	book(String isbn, String nam, String cat, String auth, String totq, String tak, Image cov){
		this.isbn=isbn;
		this.nam=nam;
		this.cat=cat;
		this.auth=auth;
		this.totq=totq;
		this.tak=tak;
		this.cov=cov;
		//---------------------------------------------------
		//admin button to update books
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
		//----------------------------------------------------
		//librarian buttons
		bbv=new Button();
		bbv.setText("View");
		bbv.setOnAction((event)->{

		});
		bba=new Button();
		bba.setText("add");
		bba.setOnAction(e->{
			Alert aa=new Alert(Alert.AlertType.CONFIRMATION);
			aa.setContentText("clicked");
			aa.showAndWait();
		});
		//----------------------------------------------------
	}
	book(String isbn, String nam, String cat, String auth, String totq, String tak, Image cov, TextField i, TextField n, TextField c, TextField a, ImageView v,libmain_control controller){
		this.isbn=isbn;
		this.nam=nam;
		this.cat=cat;
		this.auth=auth;
		this.totq=totq;
		this.tak=tak;
		this.cov=cov;
		this.i=i;
		this.n=n;
		this.c=c;
		this.a=a;
		this.co=v;
		this.controller=controller;
		//---------------------------------------------------
		//admin button to update books

		//----------------------------------------------------
		//librarian buttons
		bbv=new Button();
		bbv.setText("View");
		bbv.setOnAction((event)->{
			i.setText(isbn);
			n.setText(nam);
			c.setText(cat);
			a.setText(auth);
			v.setImage(cov);
			controller.addbas.setDisable(false);
			controller.addbas.setOnAction(e->{
				Alert ar=new Alert(Alert.AlertType.CONFIRMATION);
				ar.setContentText("clicker");
				ar.showAndWait();

			});
		});
		bba=new Button();
		bba.setText("add");
		bba.setOnAction(e->{
			Alert aa=new Alert(Alert.AlertType.CONFIRMATION);
			aa.setContentText("clicked");
			aa.showAndWait();
		});
		//----------------------------------------------------
	}
	public String getIsbn(){return isbn;}
	public String getNam(){return nam;}
	public String getCat(){return cat;}
	public String getAuth(){return auth;}
	public String getTotq(){return totq;}
	public String getTak(){return tak;}
	public Button getBb() {return bb;}
	public Image getCov() {return cov;}
	public Button getBba() {return bba;}
	public Button getBbv() {return bbv;}
}
