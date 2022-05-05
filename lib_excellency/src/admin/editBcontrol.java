package admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class editBcontrol {
    public String isbnsearch;
    @FXML
    TextField isbn;
    @FXML
    TextField nam;
    @FXML
    TextField Cat;
    @FXML
    TextField auth;
    @FXML
    TextField quant;


    public void initialize(String isbnn) {
        isbnsearch=isbnn;
        try {
            //System.out.println("---------22"+isbnsearch);
            book boo=db_book_connect.getBook(isbnsearch);
            isbn.setText(boo.getIsbn());
            nam.setText(boo.getNam());
            Cat.setText(boo.getCat());
            auth.setText(boo.getAuth());
            quant.setText(boo.getTotq());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void save() throws SQLException {
        db_book_connect.upbook(isbn.getText(),quant.getText());
    }
}
    /*public void Close(ActionEvent event){
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close window");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to close the window");
        alert.showAndWait();
        stage.close();
    }*/