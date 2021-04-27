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

}
