import audioModels.Album;
import audioModels.Chanson;
import audioModels.LivreAudio;
import audioModels.PlayList;
import soundAPI.AudioServer;
import userModels.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestManager {
        private boolean signInValid = false;
        private boolean signUpValid = false;

        private Client client = new Client();
        private Album album = new Album();
        private PlayList playlist = new PlayList();
        private Chanson chanson = new Chanson();
        private LivreAudio livreaudio = new LivreAudio();

        RequestManager(Client client, Album a, PlayList p, Chanson c, LivreAudio l) {
            this.client = client;
            album = a;
            playlist = p;
            chanson = c;
            livreaudio = l;

        }
    public void signIn(Socket socket) throws IOException {
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
        }
        if(signInValid){ // si les identifiants du client son valides

        }
    }
    public void signUp(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        boolean validEmail = false;
        String req;
        // mettre les information pour son inscrition
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
        client.setFirstname(req);
        out.println("entrez votre date adresse domicile");
        out.println("");
        req = in.readLine();
        client.setFirstname(req);
        while (!(req.equals("1") || req.equals("2"))) {
            out.println("Vous êtes un : ");
            out.println("1) Homme");
            out.println("2) Femme");
            out.println("");
            req = in.readLine();
            if (req.equals("1"))
                client.setGender("Homme");
            else if (req.equals("2"))
                client.setGender("Femme");
        }
    }

}
