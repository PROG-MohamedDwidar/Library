package liblib.add_reader;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import liblib.DB_liblib_connect;
import liblib.libmain_control;

import java.io.IOException;
import java.sql.SQLException;

public class addred_control {
    @FXML
    AnchorPane pp;
    libmain_control main;
    @FXML
    TextField pnum;
    @FXML
    TextField name;
    @FXML
    TextField email;
    @FXML
    TextField addr;

    public void takeP(String pnum,libmain_control mm){
        this.pnum.setText(pnum);
        main=mm;
    }
    public void save(){
        try {
            DB_liblib_connect.regred(pnum.getText(),name.getText(),email.getText(),addr.getText());
            main.regred();
            Stage sma=(Stage)pp.getScene().getWindow();
            sma.close();

        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("DID NOT SAVE");
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
