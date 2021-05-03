package SQLqueries;

import audioModels.Album;
import audioModels.Chanson;
import audioModels.Genre;
import userModels.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SelectAlbumQueries implements QueriesInterface {
    private String message;

    public static void main(String[] args) {
        List<Album> albums = new ArrayList<>();
        Client client = new Client();
        client.setId(6);
        SelectAlbumQueries.executeQueries(client,albums);
    }
    public static void executeQueries(Client client, List<Album> albums) {
        Connection conn;
        PreparedStatement psmt;
        Chanson chanson;
        Album album;
        List<Chanson> chansons = new ArrayList<>();

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
                chanson.setAlbumId(rs.getString("albumID"));
                chansons.add(chanson);
            }
            sql = "Select * from album where clientID=?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, client.getId());
            rs = psmt.executeQuery();
            while(rs.next()){
                album = new Album();
                album.setId(rs.getString("album.id"));
                album.setTitre(rs.getString("titre"));
                album.setId(rs.getString("titre"));
                //System.out.println(element.getElementsByTagName("id").item(0).getTextContent());
                album.setArtiste(rs.getString("artiste"));
                album.setDuree(rs.getInt("duree"));
                album.setDateDeSortie(LocalDate.parse(rs.getString("dateDeSortie")));
                String id = album.getId();
                List<Chanson> filtChanson = new ArrayList<>();
                for(Chanson c : chansons){
                    if(rs.getString("album.id").equals(c.getAlbumId())){
                        filtChanson.add(c);
                        System.out.println(" chanson added ");
                    }
                }
                album.setChansons(filtChanson);
                albums.add(album);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMessage() {
        return null;
    }
}
