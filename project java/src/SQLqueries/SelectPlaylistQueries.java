package SQLqueries;

import audioModels.*;
import userModels.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SelectPlaylistQueries implements QueriesInterface {

    private String message;


    public static void executeQueries(Client client, List<PlayList> playLists) {
        Connection conn;
        PreparedStatement psmt;
        Chanson chanson;
        PlayList playList;
        List<Chanson> chansons = new ArrayList<>();
        List<LivreAudio> livreAudios = new ArrayList<>();


        try {
            Class.forName(ConnectionInfo.DRIVER);
            conn = DriverManager.getConnection(ConnectionInfo.DB_URL, ConnectionInfo.USER, ConnectionInfo.PASSWORD);
            String sql = "Select * from chanson where clientID=?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, client.getId());
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                chanson = new Chanson();
                chanson.setId(rs.getString("id"));
                chanson.setTitre(rs.getString("titre"));
                chanson.setDuree(rs.getInt("duree"));
                chanson.setArtiste(rs.getString("artiste"));
                switch (rs.getString("genre")) {
                    case "Pop":
                        chanson.setGenre(Genre.POP);
                        break;
                    case "Jazz":
                        chanson.setGenre(Genre.JAZZ);
                        break;
                    case "Classique":
                        chanson.setGenre(Genre.CLASSIQUE);
                        break;
                    case "Hip-Hop":
                        chanson.setGenre(Genre.HIP_HOP);
                        break;
                    case "Rock":
                        chanson.setGenre(Genre.ROCK);
                        break;
                    case "Rap":
                        chanson.setGenre(Genre.RAP);
                        break;
                }
                chanson.setChemin(rs.getString("chemin"));
                chanson.setPlaylistID(rs.getString("playListID"));
                chansons.add(chanson);
            }

            sql = "Select * from livreaudio where clientID=?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, client.getId());
            rs = psmt.executeQuery();
            while(rs.next()){
                LivreAudio livreAudio = new LivreAudio();
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
            sql = "Select * from playlist where clientID=?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, client.getId());
            rs = psmt.executeQuery();
            while(rs.next()){

                playList = new PlayList();
                playList.setId(rs.getString("playList.id"));
                //System.out.println(element.getElementsByTagName("id").item(0).getTextContent());
                playList.setNom(rs.getString("nom"));
                String id = playList.getId();

                List<Chanson> filtChanson = new ArrayList<>();
                List<LivreAudio> filtLivreAudio = new ArrayList<>();

                for(Chanson c : chansons){
                    if(rs.getString("playlist.id").equals(c.getAlbumId())){
                        filtChanson.add(c);
                        System.out.println(" chanson added ");
                    }
                }
                for(LivreAudio l : livreAudios){
                    if(rs.getString("playlist.id").equals(l.getPlaylistId())){
                        filtLivreAudio.add(l);
                        System.out.println(" chanson added ");
                    }
                }
                playList.setChansons(filtChanson);
                playList.setLivreAudios(filtLivreAudio);
                playLists.add(playList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMessage() {
        return null;
    }
}
