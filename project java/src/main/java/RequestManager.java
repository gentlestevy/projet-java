import SQLqueries.*;
import XMLmanager.XMLmanager;
import audioModels.*;
import fileTransfert.FileTransaction;
import org.w3c.dom.Document;
import userModels.Client;
import userModels.Genders;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestManager {
    private boolean signInValid = false;


    private Client client = new Client();
    private static List<Album> albums = new ArrayList<>();
    private static List<PlayList> playlists = new ArrayList<>();
    private static List<Chanson> chansons = new ArrayList<>();
    private static List<Resource> resources = new ArrayList<>();
    private static List<LivreAudio> livreaudios = new ArrayList<>();
    private Document docAlbum;
    private Document docPlaylist;
    private Document docResources;

    public static void main(String[] args) throws ParserConfigurationException, ClassNotFoundException, TransformerException {

    }

    RequestManager(Client client, Album a, PlayList p, Chanson c, LivreAudio l) {
        this.client = client;
    }

    public void signIn(Socket socket) throws IOException, ParserConfigurationException, ClassNotFoundException, TransformerException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        while (!signInValid) {
            out.println("entrez votre email");
            out.println(""); // fin de la reponse
            String request = in.readLine();
            client.setEmail(request);
            out.println("entrez votre mot de passe");
            out.println("");// fin de la reponse
            request = in.readLine();
            client.setPassword(request); // assigne un nouveau mot de passe au client
            SignInQueries.executeQueries(client);
            signInValid = SignInQueries.getValidSignin();
            out.println(SignInQueries.getMessage());
        }
        if (signInValid) { // si les identifiants du client son valides
            out.println("<connected>");
            // il a accès a la page aux options d'acceuille
            this.home(socket);
        }
    }

    public void signUp(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        boolean validEmail = false;
        boolean validSignup = false;

        String req;

        // mettre les information pour son inscrition
        while (!validSignup) {
            while (!validEmail) {
                out.println("entrez  votre adresse email");
                out.println("");
                req = in.readLine();
                validEmail = Validator.valEmail(req); // verifie si le mail est valide
                client.setEmail(req);
                if (!validEmail) {
                    out.println("email invalide");
                }
            }
            out.println("entrez votre prenom");
            out.println("");
            req = in.readLine();
            client.setFirstname(req);

            out.println("entrez votre nom");
            out.println("");
            req = in.readLine();
            client.setLastname(req);

            out.println("entrez votre date adresse domicile");
            out.println("");
            req = in.readLine();
            client.setAddress(req);

            while (!(req.equals("1") || req.equals("2"))) {
                out.println("Vous êtes un : ");
                out.println("1) Homme");
                out.println("2) Femme");
                out.println("");
                req = in.readLine();
                if (req.equals("1"))
                    client.setGender(Genders.HOMME);
                else if (req.equals("2"))
                    client.setGender(Genders.FEMME);
            }
            out.println("entrez votre mot de pass");
            out.println("");
            req = in.readLine();
            client.setPassword(req);
            client.setDateCreation(LocalDate.now());
            SignUpQueries.executeQueries(client);
            validSignup = SignUpQueries.getValidSignup();
            out.println(SignUpQueries.getMessage());
            validEmail = false;
        }
    }

    void home(Socket socket) throws IOException, ParserConfigurationException, ClassNotFoundException, TransformerException {
        updateUserFile(socket);
        command(socket);

    }

    private void updateUserFile(Socket socket) throws ClassNotFoundException, ParserConfigurationException, TransformerException, IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        //album
        SelectAlbumQueries.executeQueries(this.client, albums);
        Document doc = XMLmanager.writeAlbum(albums);
        FileTransaction.sendDoc(socket, doc);
        //playlist
        SelectPlaylistQueries.executeQueries(this.client, playlists);
        Document doc2 = XMLmanager.writePlaylist(playlists);
        FileTransaction.sendDoc(socket, doc2);
        //livreaudio
        SelectLivreAudioQueries.executeQueries(this.client, livreaudios);
        Document doc3 = XMLmanager.writeLivreAudio(livreaudios);
        // FileTransaction.sendDoc(socket,doc3);
        //chanson
        SelectChansonQueries.executeQueries(this.client, chansons);
        Document doc4 = XMLmanager.writeChanson(chansons);
        //  FileTransaction.sendDoc(socket,doc4);
        //resources
        Document doc5 = XMLmanager.writeResource(chansons, livreaudios);
        FileTransaction.sendDoc(socket, doc5);


    }

    private void command(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        try {


            if (in.readLine().equals("<send2>")) {

                docAlbum = FileTransaction.receiveDoc(socket);
                docPlaylist = FileTransaction.receiveDoc(socket);
                docResources = FileTransaction.receiveDoc(socket);
            albums  = XMLmanager.extractAlbum(docAlbum);
            playlists=    XMLmanager.extractPlaylist(docPlaylist);
            chansons =   XMLmanager.extractChansons(docResources);
            livreaudios=   XMLmanager.extractLivreAudio(docResources);

            InsertAlbumQueries.executeQueries(client,albums);
                InsertPlaylistQueries.executeQueries(client,playlists);
                InsertChansonQueries.executeQueries(client,chansons);
                InsertLivreAudioQueries.executeQueries(client,livreaudios);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
