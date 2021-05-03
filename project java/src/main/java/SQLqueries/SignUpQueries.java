package SQLqueries;

import userModels.Client;
import userModels.Genders;

import java.sql.*;
import java.time.LocalDate;

public class SignUpQueries implements QueriesInterface {


    private static String message;
    private static boolean validSignup = false;
    
    public static  void  executeQueries(Client client) {
        Connection conn;
        PreparedStatement pstmt;


        try {
            Class.forName(ConnectionInfo.DRIVER);

            System.out.println("connecting to the selected database");
            conn = DriverManager.getConnection(ConnectionInfo.DB_URL, ConnectionInfo.USER, ConnectionInfo.PASSWORD);
            System.out.println("Connected database successfully");

            System.out.println("Inserting records into  the  table");


            // verifie si l'email est unique
            String sqlCheck = "Select * from client where email=?";
            pstmt = conn.prepareStatement(sqlCheck);
            pstmt.setString(1, client.getEmail());

            ResultSet rs = pstmt.executeQuery();

            //verifie si le nom complet est unique
            String sqlCheck2 = "Select * from client where firstname=? and lastname=?";
            pstmt = conn.prepareStatement(sqlCheck2);

            //place les donnée a la place du ? dans la requête sqlCheck2
            pstmt.setString(1, client.getFirstname());
            pstmt.setString(2, client.getLastname());

            ResultSet rs2 = pstmt.executeQuery();

            if (rs2.next() && rs.next()){
                message = "le nom saisi et l'email existent deja";
                System.out.println("l'email est : "+rs.next()+" le nom est :"+rs2.next());

            }
            else if(rs.next() && !rs2.next()){
                message = "l'email existe deja";
                System.out.println("l'email est : "+rs.next()+" le nom est :"+rs2.next());
            }

           else if(!rs.next() && rs2.next()){
                message = "le nom saisi  existe deja";
                System.out.println("l'email est : "+rs.next()+" le nom est :"+rs2.next());
            }
           else if(!rs2.next() && !rs.next()) {
                String sqlInsert = "Insert into client(firstname,lastname,email,dateCreation,password,address,gender) Value(?,?,?,?,?,?,?)";

                pstmt = conn.prepareStatement(sqlInsert);
                pstmt.setString(1, client.getFirstname());
                pstmt.setString(2, client.getLastname());
                pstmt.setString(3, client.getEmail());
                pstmt.setString(4, client.getDateCreation().toString());
                pstmt.setString(5,client.getPassword());
                pstmt.setString(6, client.getAddress());
                pstmt.setString(7, client.getGender().toString());

                pstmt.executeUpdate();

                message = "registration sucess";
                validSignup = true;
                System.out.println("l'email est : "+rs.next()+" le nom est :"+rs2.next());
            }

        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();
            message = "Something is wrong try again";
        }
    }


    public static String getMessage() {
        return message;
    }
    public static boolean getValidSignup(){return validSignup;}
}
