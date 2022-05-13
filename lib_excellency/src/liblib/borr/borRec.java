package liblib.borr;

import admin.book;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class borRec {
    public DatePicker rdatp;
    public String isbn,rid,eid;
    LocalDate rdatc;
    public borRec(book b) {
        this.isbn=b.getIsbn();
        //this.rid=;
        //this.eid=;

    }
}
