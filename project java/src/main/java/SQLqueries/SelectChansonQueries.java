package SQLqueries;

import audioModels.Chanson;
import audioModels.Genre;
import userModels.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class SelectChansonQueries implements  QueriesInterface{
    private static  String message;
    public static void executeQueries(Client client, List<Chanson> chansons) throws ClassNotFoundException {
        Connection conn;
        PreparedStatement pstmt;
        Chanson chanson =  new Chanson();

        try{
            Class.forName(ConnectionInfo.DRIVER);

            conn = DriverManager.getConnection(ConnectionInfo.DB_URL,ConnectionInfo.USER,ConnectionInfo.PASSWORD);

            String sql = "Select * from chanson where clientID=?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,client.getId());

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
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
                    case "classique":
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
                chansons.add(chanson);
            }
            pstmt.close();

            message = "la chanson a été sauvegardé";
        }
        catch(Exception e){
            e.printStackTrace();

        }
    }
    public static String getMessage() {
        return message;
    }
}
