package liblib.bor;

import admin.book;

import java.util.Vector;

public class basket {
    Vector<book> v;
    public basket(){
        v=new Vector<>();
    }
    public void inbas(book b){
        v.add(b);
    }
}
