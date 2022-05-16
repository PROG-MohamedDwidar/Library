package liblib.borr;

import admin.book;
import admin.db_book_connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDate;

public class borrow_control {
    @FXML
    DatePicker gen_dat;
    public ObservableList<borRec>shoplist;
    public String rid,eid;
    @FXML
    TableView<borRec> list;
    @FXML
    TableColumn<borRec,String>is;
    @FXML
    TableColumn<borRec,String>nam;
    @FXML
    TableColumn<borRec,String>aut;
    @FXML
    TableColumn<borRec, DatePicker>lodatp;

    public void setneeds(String rid,String eid,ObservableList<book>blist){
        this.rid=rid;
        this.eid=eid;
        this.shoplist= FXCollections.observableArrayList();
        for(book b:blist){
            shoplist.add(new borRec(b,rid,eid));
        }
        is.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        nam.setCellValueFactory(new PropertyValueFactory<>("name"));
        aut.setCellValueFactory(new PropertyValueFactory<>("author"));
        lodatp.setCellValueFactory(new PropertyValueFactory<>("rdatp"));
        list.setItems(shoplist);
    }
    public void execute_transaction() throws SQLException {
        for(borRec rec:shoplist){
            LocalDate da;
            if(rec.rdatp.getValue()==null){
                if(gen_dat.getValue()!=null){
                    da=gen_dat.getValue();
                }
                else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("no date picked");
                    alert.showAndWait();
                    return;
                }
            }
            else{
                da=rec.rdatp.getValue();
            }
            db_book_connect.borrow_trans(rec.eid,rec.rid,rec.isbn,da);
        }
    }





}
