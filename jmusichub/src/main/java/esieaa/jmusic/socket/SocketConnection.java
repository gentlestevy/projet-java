package esieaa.jmusic.socket;

import esieaa.jmusic.FileIO.FileIO;

import java.io.*;
import java.net.Socket;

public class SocketConnection {
    private static Socket socket;
    private static final String SERVER_NAME = "localhost";
    private static final int SERVER_PORT = 9090;
    private static PrintWriter out;
    private BufferedReader keyboard;
    private static BufferedReader in;
    private boolean connected;
    public static String albumXML = "";
    public static String plalistXML = "";
    private String chansonXML = "";
    private String livreAudioXML = "";
    public static String resouceXML = "";
    public SocketConnection() throws IOException {
        connected = false;
        SocketConnection.socket = new Socket(SERVER_NAME, SERVER_PORT);
        this.out = new PrintWriter(SocketConnection.socket.getOutputStream(), true);
        this.keyboard = new BufferedReader(new InputStreamReader(System.in));
        this.in = new BufferedReader(new InputStreamReader(SocketConnection.socket.getInputStream()));
    }

    public void accountConnection() {
        try {

            while (!connected) {
                String line = "";
                StringBuilder serverRes = new StringBuilder();
                do {
                    serverRes.append(line).append("\n");
                    line = in.readLine();
                    if (line.equals(""))
                        break;
                    else if (line.equals("<connected>"))
                        break;

                } while (true);
                if (line.equals("")) {
                    System.out.println(serverRes);
                    System.out.print(">");
                    String command = keyboard.readLine();
                    out.println(command);
                } else if (line.equals("<connected>")) {
                    this.connected = true;
                    updateFile();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateFile() throws IOException {

        System.out.println("mise a jour des documents");

        albumXML = in.readLine();
   //     System.out.println(albumXML);
        FileIO.WriteFile(albumXML,"C:\\jmusichub\\file\\albums.xml");
        plalistXML = in.readLine();
   //     System.out.println(plalistXML);
        FileIO.WriteFile(plalistXML,"C:\\jmusichub\\file\\playlists.xml");


      //  livreAudioXML = in.readLine();
     //   System.out.println(livreAudioXML);


       // chansonXML = in.readLine();
       // System.out.println(chansonXML);

      //  System.out.println(in.readLine());
        resouceXML = in.readLine();
        FileIO.WriteFile(resouceXML,"C:\\jmusichub\\file\\elements.xml");
    }

    public boolean getConnected() {
        return connected;
    }
    public static void  sendXML(){

        out.println("<send>");
        out.println(albumXML);
        out.println(plalistXML);
        out.println(resouceXML);

    }
}
