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
import java.sql.SQLException;

public class book {
	private int flag=0;
	private String isbn="N/A",totq="N/A",tak="N/A";
	private String nam="N/A",cat="N/A",auth="N/A";
	private Image cov;
	private Button bb,bba,bbv,but_rem;

	private TextField i,n,c,a;
	private ImageView co;
	private libmain_control controller;
	book(String isbn, String nam, String cat, String auth, String totq, String tak, Image cov,libmain_control controller){
		this.isbn=isbn;
		this.nam=nam;
		this.cat=cat;
		this.auth=auth;
		this.totq=totq;
		this.tak=tak;
		this.cov=cov;
		this.controller=controller;
		but_rem=new Button("remove");
		but_rem.setOnAction(e->{
			flag=0;
			controller.bbs.remove(this);
			controller.basket_refresh();
		});
		//---------------------------------------------------
		//admin button to update books

		//----------------------------------------------------
		//librarian buttons

		//----------------------------------------------------
	}
	book(String isbn, String nam, String cat, String auth, String totq, String tak, Image cov){
		this.isbn=isbn;
		this.nam=nam;
		this.cat=cat;
		this.auth=auth;
		this.totq=totq;
		this.tak=tak;
		this.cov=cov;
		but_rem=new Button("remove");
		but_rem.setOnAction(e->{
			flag=0;
			controller.bbs.remove(this);
			controller.basket_refresh();
		});
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
		controller.addbas.setOnAction(e->{
			controller.bbs.add(this);
			controller.basket_refresh();
		});
		//---------------------------------------------------
		but_rem=new Button("remove");
		but_rem.setOnAction(e->{
			flag=0;
			controller.bbs.remove(this);
			controller.basket_refresh();
		});

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
				try {
					if(!db_book_connect.isAvailable(isbn)) {
						Alert alert=new Alert(Alert.AlertType.ERROR);
						alert.setContentText("out of stock");
						alert.showAndWait();

					}
					else{
						for(book travers:controller.bbs){
							if(travers.isbn.equals(isbn))flag=1;
						}
						if(flag!=1) {

							controller.bbs.add(this);
							controller.basket_refresh();
							flag=1;
						}
						else{
							Alert alert=new Alert(Alert.AlertType.ERROR);
							alert.setContentText("book already in basket");
							alert.showAndWait();
						}
					}
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				}


			});
		});
		bba=new Button();
		bba.setText("add");
		bba.setOnAction(e->{
			try {
				if(!db_book_connect.isAvailable(isbn)) {
					Alert alert=new Alert(Alert.AlertType.ERROR);
					alert.setContentText("out of stock");
					alert.showAndWait();

				}
				else{
					for(book travers:controller.bbs){
						if(travers.isbn.equals(isbn))flag=1;
					}
					if(flag!=1) {
						flag=1;
						controller.bbs.add(this);
						controller.basket_refresh();
					}
					else{
						Alert alert=new Alert(Alert.AlertType.ERROR);
						alert.setContentText("book already in basket");
						alert.showAndWait();
					}
				}
			} catch (SQLException ex) {
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.setContentText("check connection");
				alert.showAndWait();
				throw new RuntimeException(ex);

			}

		});
		try {
			if(!db_book_connect.isAvailable(isbn)){
				bba.setDisable(true);
				controller.addbas.setDisable(true);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

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
	public Button getBut_rem() {return but_rem;}
}
