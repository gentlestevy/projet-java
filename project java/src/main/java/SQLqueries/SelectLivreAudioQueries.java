package SQLqueries;

import audioModels.*;
import userModels.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class SelectLivreAudioQueries implements  QueriesInterface{
    private static  String message;
    public static void executeQueries(Client client, List<LivreAudio> livreAudios) throws ClassNotFoundException {
        Connection conn;
        PreparedStatement pstmt;
        LivreAudio livreAudio =  new LivreAudio();

        try{
            Class.forName(ConnectionInfo.DRIVER);

            conn = DriverManager.getConnection(ConnectionInfo.DB_URL,ConnectionInfo.USER,ConnectionInfo.PASSWORD);

            String sql = "Select * from livreaudio where clientID=?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,client.getId());

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){

                livreAudio.setId(rs.getString("id"));
                livreAudio.setTitre(rs.getString("titre"));
                livreAudio.setDuree(rs.getInt("duree"));
                livreAudio.setAuteur(rs.getString("auteur"));

                switch (rs.getString("categorie")) {
                    case "Jeunesse":
                        livreAudio.setCategorie(Categorie.JEUNESSE);
                        break;
                    case "Roman":
                        livreAudio.setCategorie(Categorie.ROMAN);
                        break;
                    case "Théâtre":
                        livreAudio.setCategorie(Categorie.THEATRE);
                        break;
                    case "Discours":
                        livreAudio.setCategorie(Categorie.DISCOURS);
                        break;
                    case "Documentaire":
                        livreAudio.setCategorie(Categorie.DOCUMENTAIRE);
                        break;
                }

                switch (rs.getString("langue")) {
                    case "Français":
                        livreAudio.setLangue(Langues.FRANCAIS);
                        break;
                    case "Anglais":
                        livreAudio.setLangue(Langues.ANGLAIS);
                        break;
                    case "Italien":
                        livreAudio.setLangue(Langues.ITALIEN);
                        break;
                    case "Espagnol":
                        livreAudio.setLangue(Langues.ESPAGNOL);
                        break;
                    case "Allemand":
                        livreAudio.setLangue(Langues.ALLEMAND);
                        break;
                }
                livreAudio.setChemin(rs.getString("chemin"));
                livreAudios.add(livreAudio);
            }

            message = "la livreAudio a été sauvegardé";
        }
        catch(Exception e){
            e.printStackTrace();

        }
    }
    public static String getMessage() {
        return message;
    }
}
