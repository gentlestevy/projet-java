package SQLqueries;

import XMLmanager.XMLmanager;
import audioModels.Album;
import audioModels.Chanson;
import audioModels.PlayList;
import org.w3c.dom.Document;
import userModels.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class InsertAlbumQueries implements QueriesInterface {
    public static String message;


    public static void executeQueries(Client client, List<Album> albums) {
        Connection conn;
        PreparedStatement psmt;
        List<Chanson> chansons;
        try {
            Class.forName(ConnectionInfo.DRIVER);
            conn = DriverManager.getConnection(ConnectionInfo.DB_URL, ConnectionInfo.USER, ConnectionInfo.PASSWORD);
            int j = 0;
            int i = 0;
            for (j = 0;j<albums.size();j++) {
                String sqlInsert = "Insert into album(id,clientID,artiste,duree,dateDeSortie,titre) "+
                        " Select ?,?,?,?,?,? FROM (Select 1) t" +
                        " WHERE not exists (select * from album where id=?); ";

                psmt = conn.prepareStatement(sqlInsert);

                psmt.setString(1, albums.get(j).getId());
                System.out.println("album id  : "+albums.get(2).getId());
                psmt.setInt(2, client.getId());
                psmt.setString(3, albums.get(j).getArtiste());
                psmt.setInt(4, albums.get(j).getDuree());
                psmt.setString(5, albums.get(j).getDateDeSortie().toString());
                psmt.setString(6, albums.get(j).getTitre());
                psmt.setString(7, albums.get(j).getId());

                System.out.println("album numero : "+j);

                psmt.executeUpdate();
                

                chansons =  albums.get(j).getChansons();
                System.out.println("taille de la chanson : "+chansons.size());

                for (i = 0; i < chansons.size(); i++)
                {

                    sqlInsert = "Insert into chanson(id,titre,duree,artiste,genre,type,clientID,albumID,chemin)"+
                            " Value(?,?,?,?,?,?,?,?,?) " +
                            " ON DUPLICATE KEY UPDATE " +
                            " albumID=?; ";

                    psmt = conn.prepareStatement(sqlInsert);
                    psmt.setString(1, chansons.get(i).getId());
                    psmt.setString(2, chansons.get(i).getTitre());
                    psmt.setInt(3, chansons.get(i).getDuree());
                    psmt.setString(4, chansons.get(i).getArtiste());
                    psmt.setString(5, chansons.get(i).getGenre().toString());
                    psmt.setString(6, chansons.get(i).getType().toString());
                    psmt.setInt(7, client.getId());
                    psmt.setString(8, albums.get(j).getId());
                    psmt.setString(9, chansons.get(i).getChemin());
                    psmt.setString(10, albums.get(j).getId());
                    psmt.executeUpdate();

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
