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


    public static void main(String[] args) throws ClassNotFoundException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<elements>\n" +
                "  <chanson>\n" +
                "    <titre>commode</titre>\n" +
                "    <id>1d3ea66f-c132-4a65-be25-6b96dbf0a86f</id>\n" +
                "    <duree>122</duree>\n" +
                "    <artiste>comp</artiste>\n" +
                "    <genre>Pop</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>toto</titre>\n" +
                "    <id>ac9db320-3776-4904-b971-81da467e91ce</id>\n" +
                "    <duree>144</duree>\n" +
                "    <artiste>eminem</artiste>\n" +
                "    <genre>Rap</genre>\n" +
                "  </chanson>\n" +
                "  <livreAudio>\n" +
                "    <titre>livre audio</titre>\n" +
                "    <id>edc25aca-c801-483b-bbec-5b642b4e452b</id>\n" +
                "    <duree>122</duree>\n" +
                "    <auteur>luffy</auteur>\n" +
                "    <categorie>Roman</categorie>\n" +
                "    <langue>Anglais</langue>\n" +
                "  </livreAudio>\n" +
                "  <livreAudio>\n" +
                "    <titre>senpai</titre>\n" +
                "    <id>bfe045dd-34ff-4743-add6-315fa04e9c90</id>\n" +
                "    <duree>333</duree>\n" +
                "    <auteur>zoro</auteur>\n" +
                "    <categorie>Discours</categorie>\n" +
                "    <langue>Italien</langue>\n" +
                "  </livreAudio>\n" +
                "  <chanson>\n" +
                "    <titre>t</titre>\n" +
                "    <id>40193e61-702e-4ed7-9885-9ec222981987</id>\n" +
                "    <duree>1111</duree>\n" +
                "    <artiste>qsd</artiste>\n" +
                "    <genre>Jazz</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>c</titre>\n" +
                "    <id>86b894fd-b6a8-4b53-9ced-287abced5cee</id>\n" +
                "    <duree>66</duree>\n" +
                "    <artiste>k</artiste>\n" +
                "    <genre>Jazz</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>ll</titre>\n" +
                "    <id>96aa9bf9-d99b-4ebb-8e57-e021cd53ff56</id>\n" +
                "    <duree>777</duree>\n" +
                "    <artiste>ll</artiste>\n" +
                "    <genre>Jazz</genre>\n" +
                "  </chanson>\n" +
                "  <livreAudio>\n" +
                "    <titre>titre</titre>\n" +
                "    <id>4600da8a-b58a-4cf5-b0b8-4ddf2eb67249</id>\n" +
                "    <duree>122</duree>\n" +
                "    <auteur>auteur-1</auteur>\n" +
                "    <categorie>Roman</categorie>\n" +
                "    <langue>Anglais</langue>\n" +
                "  </livreAudio>\n" +
                "  <chanson>\n" +
                "    <titre>chanson</titre>\n" +
                "    <id>acb0c3a8-042f-4a29-9995-3780bb4e694f</id>\n" +
                "    <duree>122</duree>\n" +
                "    <artiste>artiste-1</artiste>\n" +
                "    <genre>Pop</genre>\n" +
                "  </chanson>\n" +
                "  <livreAudio>\n" +
                "    <titre>ll</titre>\n" +
                "    <id>c7d6b9a6-ce1c-458a-ab9f-47dee27f9f00</id>\n" +
                "    <duree>122</duree>\n" +
                "    <auteur>ll</auteur>\n" +
                "    <categorie>Roman</categorie>\n" +
                "    <langue/>\n" +
                "  </livreAudio>\n" +
                "  <chanson>\n" +
                "    <titre>c</titre>\n" +
                "    <id>f688b388-1fb0-43eb-9d21-79d0f4ddd5e6</id>\n" +
                "    <duree>111</duree>\n" +
                "    <artiste>c</artiste>\n" +
                "    <genre>Jazz</genre>\n" +
                "  </chanson>\n" +
                "  <livreAudio>\n" +
                "    <titre>l</titre>\n" +
                "    <id>66c7e7d3-21fa-4f70-a637-67b03b6dabbf</id>\n" +
                "    <duree>111</duree>\n" +
                "    <auteur>ll</auteur>\n" +
                "    <categorie>Roman</categorie>\n" +
                "    <langue>Français</langue>\n" +
                "  </livreAudio>\n" +
                "  <chanson>\n" +
                "    <titre>c</titre>\n" +
                "    <id>29d0d4fd-cd25-4d97-973a-91d2c0fb7a93</id>\n" +
                "    <duree>111</duree>\n" +
                "    <artiste>c</artiste>\n" +
                "    <genre>Rock</genre>\n" +
                "  </chanson>\n" +
                "  <livreAudio>\n" +
                "    <titre>titre</titre>\n" +
                "    <id>1210bfaf-e403-4c4b-8c0a-707e294b76db</id>\n" +
                "    <duree>122</duree>\n" +
                "    <auteur>auteur-1</auteur>\n" +
                "    <categorie>Théâtre</categorie>\n" +
                "    <langue>Français</langue>\n" +
                "  </livreAudio>\n" +
                "  <chanson>\n" +
                "    <titre>halo</titre>\n" +
                "    <id>5a4c45eb-822b-4799-ac25-f9c23d43cea7</id>\n" +
                "    <duree>200</duree>\n" +
                "    <artiste>beyonce</artiste>\n" +
                "    <genre>Pop</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>i found love </titre>\n" +
                "    <id>fe5239db-c0f6-4312-bcea-0a034e9e98f0</id>\n" +
                "    <duree>200</duree>\n" +
                "    <artiste>edsheeran</artiste>\n" +
                "    <genre>Classique</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>kalimat</titre>\n" +
                "    <id>6c87e1fa-a688-49ec-a2b3-faf10a80f98d</id>\n" +
                "    <duree>500</duree>\n" +
                "    <artiste>madjida romi</artiste>\n" +
                "    <genre>Classique</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>Ghzali</titre>\n" +
                "    <id>37445745-e91c-4491-b34d-f384c8d325eb</id>\n" +
                "    <duree>500</duree>\n" +
                "    <artiste>madjida romi</artiste>\n" +
                "    <genre>Classique</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>le temps des fleurs</titre>\n" +
                "    <id>6946e4ae-dd5e-462d-b71d-1854c25c2b9a</id>\n" +
                "    <duree>200</duree>\n" +
                "    <artiste>dalida</artiste>\n" +
                "    <genre>Pop</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>djidji l'amorose</titre>\n" +
                "    <id>4f57ecc4-d4b0-4c4a-ab6a-13c10c30890d</id>\n" +
                "    <duree>200</duree>\n" +
                "    <artiste>dalida</artiste>\n" +
                "    <genre>Classique</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>j'te promets</titre>\n" +
                "    <id>49e8ae5c-e19b-4364-ae24-ca80035297ea</id>\n" +
                "    <duree>200</duree>\n" +
                "    <artiste>johny hallidy</artiste>\n" +
                "    <genre>Pop</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>doudou</titre>\n" +
                "    <id>71b1508d-59e9-4456-a029-015433f24726</id>\n" +
                "    <duree>300</duree>\n" +
                "    <artiste>aya nakamura</artiste>\n" +
                "    <genre>Pop</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>djadja</titre>\n" +
                "    <id>4ec7b59f-94ae-4b1e-8eb8-3bfceb2d3dfb</id>\n" +
                "    <duree>200</duree>\n" +
                "    <artiste>aya nakamura</artiste>\n" +
                "    <genre>Pop</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>à l'amoniaque</titre>\n" +
                "    <id>c359aee3-28cb-4f77-979b-9fbe0099aaaa</id>\n" +
                "    <duree>300</duree>\n" +
                "    <artiste>pnl</artiste>\n" +
                "    <genre>Rap</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>akwesigh ami 3zizen</titre>\n" +
                "    <id>d20101f7-8520-45da-af5e-2cbe618afe12</id>\n" +
                "    <duree>300</duree>\n" +
                "    <artiste>nouara</artiste>\n" +
                "    <genre>Classique</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>yema tassa</titre>\n" +
                "    <id>397059a4-2f34-468e-9b4b-b5dc1ef9fa46</id>\n" +
                "    <duree>200</duree>\n" +
                "    <artiste>karima</artiste>\n" +
                "    <genre>Pop</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>tahechat pevrid</titre>\n" +
                "    <id>62a01c35-32f3-4279-98a4-8a31edc06fdf</id>\n" +
                "    <duree>500</duree>\n" +
                "    <artiste>Matoub Lounes</artiste>\n" +
                "    <genre>Pop</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>je t'aime</titre>\n" +
                "    <id>474a4189-fea0-4e16-ac8d-6a158527f548</id>\n" +
                "    <duree>300</duree>\n" +
                "    <artiste>lara fabian</artiste>\n" +
                "    <genre>Pop</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>beliji</titre>\n" +
                "    <id>b01b2d38-83f9-4c67-9a3e-5d1c2dfc8d38</id>\n" +
                "    <duree>200</duree>\n" +
                "    <artiste>mickel jackson</artiste>\n" +
                "    <genre>Jazz</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>à fleur de toi</titre>\n" +
                "    <id>382d7f26-c1d2-44a5-9f70-75dc0ca4233c</id>\n" +
                "    <duree>200</duree>\n" +
                "    <artiste>slimane &amp; vita</artiste>\n" +
                "    <genre>Pop</genre>\n" +
                "  </chanson>\n" +
                "  <chanson>\n" +
                "    <titre>dis le moi</titre>\n" +
                "    <id>da618ce4-f7ce-4958-836b-d0b7d9955bae</id>\n" +
                "    <duree>200</duree>\n" +
                "    <artiste>slimane &amp; vita</artiste>\n" +
                "    <genre>Pop</genre>\n" +
                "  </chanson>\n" +
                "</elements>";

        Client client = new Client();
        List<Chanson> chansons;


        Document doc = XMLmanager.convertStringToXMLDocument(xml);
        chansons = XMLmanager.extractChansons(doc);
        System.out.println("nombre de chanson" + chansons.size());
        client.setId(6);
        InsertChansonQueries.executeQueries(client,chansons);

    }

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
