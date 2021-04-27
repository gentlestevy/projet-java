// Java implementation of  Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Server class
public class Server
{
    private static  ExecutorService pool = Executors.newFixedThreadPool(4);
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        // le serveur ecoute le port 9900
        ServerSocket ss = new ServerSocket(9090);
        // boucle infini attente de la requete d'un client
        while (true)
        {
            Socket s = null; //  vide le socket pour unj nouveau client
            try
            {
                // attend qu'un  client se connecte
                s = ss.accept();
                System.out.println("un nouveau client est connecté : " + s);

                System.out.println("donnne un nouveau thread au client");
                // creation d'un nouveau thread
                ClientHandler clientThread = new ClientHandler(s);
                clients.add(clientThread);
                // execute la methode run contenu dans clienThread
                pool.execute(clientThread);

            }
            catch (Exception e){
                s.close(); // fermetrure du serveu
                e.printStackTrace(); // retrace l'erreur
            }
        }
    }t

}