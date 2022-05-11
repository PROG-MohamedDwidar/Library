package liblib;

import admin.book;
import admin.db_book_connect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import liblib.add_reader.addred_control;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class libmain_control implements Initializable {

    //-----------------------------------------------------------
    //readers hub
    @FXML
    TextField renum;
    public void regred() throws IOException {
        String rednum=renum.getText();
        if(DB_liblib_connect.IsRegRed(rednum,0)){

        }
        else{
            Stage adred=new Stage();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("add_reader/addred.fxml"));
            Parent root= loader.load();
            addred_control cc1=loader.getController();
            cc1.takeP(renum.getText(),this);
            Scene adre=new Scene(root);
            adred.setScene(adre);
            adred.show();
        }


    }
    //-----------------------------------------------------------
    //return hub

    //-----------------------------------------------------------
    //borrow hub
    @FXML
    public Button addbas;
    @FXML
    TextField sebox;
    @FXML
    TableView<book>bees;
    @FXML
    TableColumn<book,String>bis;
    @FXML
    TableColumn<book,String>bnam;
    @FXML
    TableColumn<book,Button>bbv;
    @FXML
    TableColumn<book, Button>bba;
    @FXML
    TextField is;
    @FXML
    TextField na;
    @FXML
    TextField ca;
    @FXML
    TextField au;
    @FXML
    ImageView vco;
    public void seda(){
        bis.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        bnam.setCellValueFactory(new PropertyValueFactory<>("nam"));
        bbv.setCellValueFactory(new PropertyValueFactory<>("bbv"));
        bba.setCellValueFactory(new PropertyValueFactory<>("bba"));
        try {
            bees.setItems(db_book_connect.search(sebox.getText(),is,na,ca,au,vco,this));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //-----------------------------------------------------------
    //basket hub

    //-----------------------------------------------------------


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addbas.setDisable(true);
    }
}
