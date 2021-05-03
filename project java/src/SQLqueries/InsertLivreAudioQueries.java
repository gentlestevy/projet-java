package SQLqueries;

import XMLmanager.XMLmanager;
import audioModels.Chanson;
import audioModels.LivreAudio;
import org.w3c.dom.Document;
import userModels.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;


public class InsertLivreAudioQueries implements QueriesInterface {

    private static String message;


    public static void executeQueries(Client client, List<LivreAudio> livreaudios) {

        Connection conn;
        PreparedStatement pstmt;

        try {
            Class.forName(ConnectionInfo.DRIVER);
            conn = DriverManager.getConnection(ConnectionInfo.DB_URL, ConnectionInfo.USER, ConnectionInfo.PASSWORD);

            String sqlInsert = "Insert into livreaudio(id,titre,duree,auteur,langue,categorie,type,clientID,chemin)"+
                    "Select ?,?,?,?,?,?,?,?,? FROM (Select 1) t" +
                    " WHERE not exists (select * from livreaudio where id=?); ";
            for (LivreAudio livreaudio : livreaudios) {
                pstmt = conn.prepareStatement(sqlInsert);

                pstmt.setString(1, livreaudio.getId());
                pstmt.setString(2, livreaudio.getTitre());
                pstmt.setInt(3, livreaudio.getDuree());
                pstmt.setString(4, livreaudio.getAuteur());
                pstmt.setString(5, livreaudio.getLangue().toString());
                pstmt.setString(6, livreaudio.getCategorie().toString());
                pstmt.setString(7, livreaudio.getType().toString());
                pstmt.setInt(8, client.getId());
                pstmt.setString(9, livreaudio.getChemin());
                pstmt.setString(10, livreaudio.getId());

                pstmt.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static String getMessage() {
        return null;
    }
}
