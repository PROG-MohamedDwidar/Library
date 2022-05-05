package nw;

import javafx.stage.FileChooser;

import java.awt.*;
import java.io.*;
import java.nio.file.DirectoryStream;
import java.sql.*;

public class neww {
    public static void main(String[] args) throws IOException, SQLException {
        Connection c= DriverManager.getConnection("jdbc:mysql://projectlibprog.mysql.database.azure.com:3306/proj",
                                                  "project@projectlibprog",
                                                  "rootrt*1");
        /*File fil=new File("C:\\Users\\THE KING IS HERE\\Downloads\\pp1.jpg");
        FileInputStream mm =new FileInputStream(fil);

        PreparedStatement pp=c.prepareStatement("UPDATE `proj`.`emps`\n" +
                "SET\n" +
                "\n" +
                "`photo` = ?\n" +
                "WHERE `eid` = 1;\n");
        pp.setBinaryStream(1,mm,(int)fil.length() );
        pp.executeUpdate();
        */
        Statement ss=c.createStatement();
        ResultSet ans=ss.executeQuery("select photo from emps where eid=1");
        File file = new File("outputtt.png");
        FileOutputStream output = new FileOutputStream(file);
        ans.next();
        InputStream inner=ans.getBinaryStream(1);
        byte[] buffer=new byte[1024];
        while(inner.read(buffer)>0){
            output.write(buffer);
        }

        c.close();



    }
}
