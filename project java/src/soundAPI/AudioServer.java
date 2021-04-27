package soundAPI;

import java.io.*;
import java.net.*;

public class AudioServer {

   public static void sendAudio(Socket s,String path) throws IOException {
        try{
        File soundFile = new File(path); // recupere l'audio par le chemin
        System.out.println("Streaming to client : " + soundFile);
        Socket client = s; // recupere les coordonnée de connection du client
        FileInputStream in = new FileInputStream (soundFile);
        OutputStream out = client.getOutputStream();
                byte buffer[] = new byte[9090];
                int count;
                while ((count = in.read(buffer)) != -1)
                    out.write(buffer, 0, count); // envoi le byte au client
    }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
