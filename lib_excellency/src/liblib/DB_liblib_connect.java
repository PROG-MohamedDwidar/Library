package liblib;

import java.sql.*;

public abstract class DB_liblib_connect {
    static String url="jdbc:mysql://projectlibprog.mysql.database.azure.com:3306/proj";
    static String n="project@projectlibprog",pass="rootrt*1";

    public static boolean IsRegRed(String rednum,int tn){
        try {
            Connection c= DriverManager.getConnection(url,n,pass);
            Statement ss=c.createStatement();
            ResultSet ans= ss.executeQuery("SELECT * FROM reader where Rnumber='"+rednum+"';");
            if(ans.next())return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            if(tn<2)return IsRegRed(rednum,tn+1);
            else return false;
        }
    }

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
