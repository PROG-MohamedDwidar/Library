import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;

public class client_management implements Runnable{
    Socket cs;
    client_management(Socket clientSocket){
        cs=clientSocket;
    }
    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(cs.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(cs.getInputStream());

            String cls=(String)in.readObject();
            String meth=(String)in.readObject();

            Class c = Class.forName("cls");
            Object obj = c.getDeclaredConstructor().newInstance();

            Method method = c.getDeclaredMethod("meth", null);
            method.setAccessible(true);
            method.invoke(obj, out,in);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
