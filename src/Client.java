import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args){
        try(Socket ss = new Socket("localhost",6969)){
            BufferedReader ui = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream out = new DataOutputStream(ss.getOutputStream());
            new Thread(new MsgReceiver(ss)).start();
            String arg = "";
            String pa = "";
            String not = "";
            while(true){
                arg = ui.readLine();
                pa = ui.readLine();
                not = ui.readLine();
                out.writeBytes(arg + ", " + pa + ", " + not + "\n");
            }

        }catch(IOException e){
            System.out.println("Errore di connessione");
        }
    }
}