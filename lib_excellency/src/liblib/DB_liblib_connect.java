package liblib;

import java.sql.*;

public abstract class DB_liblib_connect {
    static String url="jdbc:mysql://projectlibprog.mysql.database.azure.com:3306/proj";
    static String n="project@projectlibprog",pass="rootrt*1";


    //IsRegred looks for reader number in the database
    //it returns true if found false if not
    //tn is counter for the number of tries
    public static boolean IsRegRed(String rednum,int tn){
        try {
            Connection c= DriverManager.getConnection(url,n,pass);
            Statement ss=c.createStatement();
            ResultSet ans= ss.executeQuery("SELECT * FROM reader where Rnumber='"+rednum+"';");
            if(ans.next())return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            //******IMPORTANT*******//
            //if we try to confirm a reader and there was a network error the program might ask the for the reader to be added again
            //trying to add the same reader again will result in an error
            //this block tries to confirm the reader again in network error cases
            if(tn<2)return IsRegRed(rednum,tn+1);
            else return false;
            //**********************//
        }
    }
    //regred adds new reader to the database
    public static void regred(String pnum,String nam,String email,String addr) throws SQLException {
        Connection c= DriverManager.getConnection(url,n,pass);
        PreparedStatement ss= c.prepareStatement("INSERT INTO `proj`.`reader`(`Rnumber`,`Rname`,`Remail`,`Raddress`)VALUES(?,?,?,?);");
        ss.setNString(1,pnum);
        ss.setNString(2,nam);
        ss.setNString(3,email);
        ss.setNString(4,addr);
        ss.executeUpdate();
        c.close();
    }
}
