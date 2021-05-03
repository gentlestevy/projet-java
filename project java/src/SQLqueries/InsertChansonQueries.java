package SQLqueries;

import XMLmanager.XMLmanager;
import audioModels.*;
import org.w3c.dom.Document;
import userModels.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;

public class InsertChansonQueries implements QueriesInterface {
    private static String message;


    public static void executeQueries(Client client, List<Chanson> chansons) throws ClassNotFoundException {
        Connection conn;
        PreparedStatement pstmt;


        try {
            Class.forName(ConnectionInfo.DRIVER);

            conn = DriverManager.getConnection(ConnectionInfo.DB_URL, ConnectionInfo.USER, ConnectionInfo.PASSWORD);

            String sqlInsert = "Insert into chanson(id,titre,duree,artiste,genre,type,clientID,chemin)" +
                    "Select ?,?,?,?,?,?,?,? FROM (Select 1) t" +
                    " WHERE not exists (select * from chanson where id=?); ";
            for (Chanson chanson : chansons) {


                pstmt = conn.prepareStatement(sqlInsert);
                pstmt.setString(1, chanson.getId());
                pstmt.setString(2, chanson.getTitre());
                pstmt.setInt(3, chanson.getDuree());
                pstmt.setString(4, chanson.getArtiste());
                pstmt.setString(5, chanson.getGenre().toString());
                pstmt.setString(6, chanson.getType().toString());
                pstmt.setInt(7, client.getId());
                pstmt.setString(8, chanson.getChemin());
                pstmt.setString(9, chanson.getId());

                pstmt.executeUpdate();
            }
            message = "la chanson a été sauvegardé";
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static String getMessage() {
        return message;
    }
}
