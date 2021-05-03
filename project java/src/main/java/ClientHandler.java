import audioModels.*;
import userModels.Client;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.Socket;


public class ClientHandler  implements Runnable {
    BufferedReader in;
    PrintWriter out;
    Socket socket;
    RequestManager reqManager;
    Client client = new Client();
    Album album = new Album();
    PlayList playlist = new PlayList();
    Chanson chanson = new Chanson();
    LivreAudio livreaudio = new LivreAudio();

    ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        reqManager = new RequestManager(client,album,playlist,chanson,livreaudio);

    }

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {

            try {
                while (true) {

                    out.println("1) connectez vous");
                    out.println("2) s'inscrire");
                    out.println("");
                    String req = in.readLine();
                    switch (req) {

                        case "1":  // si le client choisit 1 il se connecte
                            reqManager.signIn(socket);
                            break;

                        case "2": //  si le client choisi 2 il s'inscri
                            reqManager.signUp(socket);
                            break;

                        default: // sinonn son choix est invalide
                            out.println("entrée invalide");
                            break;
                }

                }
            } catch (IOException | ParserConfigurationException | ClassNotFoundException | TransformerException e) {
                e.printStackTrace();
            }
            try {
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        // closing resources

    }


}
