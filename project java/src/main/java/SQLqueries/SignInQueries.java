package SQLqueries;

import userModels.Client;
import userModels.Genders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;

public class SignInQueries implements QueriesInterface {

    private static String message;
    private static boolean validSignin = false;

    public static void executeQueries(Client client){
        Connection conn;
        PreparedStatement pstmt;

        try{
            Class.forName(ConnectionInfo.DRIVER);
            conn = DriverManager.getConnection(ConnectionInfo.DB_URL,ConnectionInfo.USER,ConnectionInfo.PASSWORD);

            String sqlCheck = "Select * from client where email=? and password=?";

            pstmt = conn.prepareStatement(sqlCheck);

            pstmt.setString(1,client.getEmail());
            pstmt.setString(2,client.getPassword());

            ResultSet res = pstmt.executeQuery();

            if(res.next()){

                message =" vous ête connecté";
                validSignin = true;
                
                client.setId(res.getInt("id"));
                client.setAddress(res.getString("address"));
                client.setDateCreation(LocalDate.parse(res.getString("dateCreation")));
                client.setFirstname(res.getString("firstname"));
                client.setLastname(res.getString("lastname"));

                if(res.getString("gender").equals("Homme")){
                    client.setGender(Genders.HOMME);
                }
                else if (res.getString("gender").equals("Femme")) {
                    client.setGender(Genders.FEMME);
                }
            }
            else{
                message="l'email ou le mot de passe est incorrect";

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getMessage() {
        return message;
    }
    public static  boolean getValidSignin(){

        return validSignin;
    }
}
