package SQLqueries;

import XMLmanager.XMLmanager;
import audioModels.Album;
import audioModels.PlayList;
import audioModels.ResourceType;
import org.w3c.dom.Document;
import userModels.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InsertPlaylistQueries implements QueriesInterface {

    public static String message;

    public static void executeQueries(Client client, List<PlayList> playlists) {
        Connection conn;
        PreparedStatement psmt;
        try {
            Class.forName(ConnectionInfo.DRIVER);
            conn = DriverManager.getConnection(ConnectionInfo.DB_URL, ConnectionInfo.USER, ConnectionInfo.PASSWORD);
        for(PlayList playlist : playlists ) {

            String sqlInsert = "Insert into playlist(id,nom,clientID) "+
                    " Select ?,?,? FROM (Select 1) t" +
                    " WHERE not exists (select * from playlist where id=?); ";

            psmt = conn.prepareStatement(sqlInsert);

            psmt.setString(1, playlist.getId());
            psmt.setString(2, playlist.getNom());
            psmt.setInt(3, client.getId());
            psmt.setString(4, playlist.getId());
            psmt.executeUpdate();

            for (int i = 0; i < playlist.getChansons().size(); i++)
            {
                sqlInsert = "Insert into chanson(id,titre,duree,artiste,genre,type,clientID,playlistID,chemin) "+
                        " Value(?,?,?,?,?,?,?,?,?) " +
                        " ON DUPLICATE KEY UPDATE " +
                        " playlistID=?; ";

                psmt = conn.prepareStatement(sqlInsert);
                psmt.setString(1, playlist.getChansons().get(i).getId());
                psmt.setString(2, playlist.getChansons().get(i).getTitre());
                psmt.setInt(3, playlist.getChansons().get(i).getDuree());
                psmt.setString(4, playlist.getChansons().get(i).getArtiste());
                psmt.setString(5, playlist.getChansons().get(i).getGenre().toString());
                psmt.setString(6, playlist.getChansons().get(i).getType().toString());
                psmt.setInt(7, client.getId());
                psmt.setString(8, playlist.getId());
                psmt.setString(9, playlist.getChansons().get(i).getChemin());
                psmt.setString(10, playlist.getId());
                psmt.executeUpdate();

            }
            for (int i = 0; i < playlist.getLivreAudios().size(); i++)
            {


                sqlInsert = "Insert into livreaudio(id,titre,duree,auteur,langue,categorie,type,clientID,playlistID,chemin) "+
                        " Value(?,?,?,?,?,?,?,?,?) " +
                        " ON DUPLICATE KEY UPDATE " +
                        " playlistID=Value(?); ";

                psmt = conn.prepareStatement(sqlInsert);

                psmt.setString(1, playlist.getLivreAudios().get(i).getId());
                psmt.setString(2, playlist.getLivreAudios().get(i).getTitre());
                psmt.setInt(3, playlist.getLivreAudios().get(i).getDuree());
                psmt.setString(4, playlist.getLivreAudios().get(i).getAuteur());
                psmt.setString(5, playlist.getLivreAudios().get(i).getLangue().toString());
                psmt.setString(6, playlist.getLivreAudios().get(i).getCategorie().toString());
                psmt.setString(7, playlist.getLivreAudios().get(i).getType().toString());
                psmt.setInt(8, client.getId());
                psmt.setString(9, playlist.getId());
                psmt.setString(10, playlist.getLivreAudios().get(i).getChemin());
                psmt.setString(11, playlist.getLivreAudios().get(i).getId());

            }

        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMessage() {
        return null;
    }
}
