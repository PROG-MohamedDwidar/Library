package liblib.retrun;

import admin.book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class return_control implements Initializable {
    String rednum;
    @FXML
    TableView<book>returned;
    @FXML
    TableColumn<book,String>isbn;
    @FXML
    TableColumn<book,String>name;
    @FXML
    TableColumn<book,String>author;
    @FXML
    TableColumn<book,Long>dlate;
    @FXML
    TableColumn<book,Long>latfine;
    @FXML
    Button confirm;
    @FXML
    Button cancel;
    ObservableList<book>return_list;

    @FXML
    Label stot;
    @FXML
    Label tax;
    @FXML
    Label tot;

    public void addbook(ObservableList<book>relist,String rednum){
        this.rednum=rednum;
        return_list=relist;
        double total=0,sum=0;
        returned.setItems(return_list);
        for(book trav:return_list){
            sum+=trav.getFine();
        }
        stot.setText(String.valueOf(sum));
        total=sum*14/100;
        tax.setText(String.valueOf(total));
        total=total+sum;
        tot.setText(String.valueOf(total));
    }
    public void addbook(book rb,String rednum){
        this.rednum=rednum;
        return_list.add(rb);
        returned.setItems(return_list);
        //returned.setItems(return_list);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        return_list= FXCollections.observableArrayList();
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        name.setCellValueFactory(new PropertyValueFactory<>("nam"));
        author.setCellValueFactory(new PropertyValueFactory<>("nam"));
        dlate.setCellValueFactory(new PropertyValueFactory<>("lateness"));
        latfine.setCellValueFactory(new PropertyValueFactory<>("fine"));
    }
}
