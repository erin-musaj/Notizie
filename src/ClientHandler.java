import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private Socket cs;

    public ClientHandler(Socket cs) {
        this.cs = cs;
    }

    @Override
    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            DataOutputStream out = new DataOutputStream(cs.getOutputStream());
            String msg = "";
            while((msg = in.readLine()) != null){
                System.out.println("Messaggio da " + cs + ": " + msg);
                Server.css.add(msg);
                for(int i = 0; i < Server.css.size(); i++){
                        out.writeBytes(Server.css.get(i) + "\n");
                        out.flush();
                }
            }

            in.close();
            Server.css.remove(out);
            out.close();
            System.out.println("Client " + cs + " si è disconnesso.");
            cs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}