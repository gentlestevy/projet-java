package XMLmanager;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class XMLmanager {

    public static void main(String[] args) {

        final String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<albums>\n" +
                "  <album>\n" +
                "    <titre>album-1</titre>\n" +
                "    <id>18614281-469a-43be-9986-47711a9cab75</id>\n" +
                "    <artiste>artiste-1</artiste>\n" +
                "    <duree>1000</duree>\n" +
                "    <dateDeSortie>20/12/2020</dateDeSortie>\n" +
                "    <chansons/>\n" +
                "  </album>\n" +
                "  <album>\n" +
                "    <titre>nrj12</titre>\n" +
                "    <id>478e242f-75e5-4d40-a72c-31bc4b2b9a0f</id>\n" +
                "    <artiste>shakira</artiste>\n" +
                "    <duree>10000</duree>\n" +
                "    <dateDeSortie>10/10/2016</dateDeSortie>\n" +
                "    <chansons>\n" +
                "      <chanson>\n" +
                "        <titre>halo</titre>\n" +
                "        <id>5a4c45eb-822b-4799-ac25-f9c23d43cea7</id>\n" +
                "        <duree>200</duree>\n" +
                "        <artiste>beyonce</artiste>\n" +
                "        <genre>Pop</genre>\n" +
                "      </chanson>\n" +
                "      <chanson>\n" +
                "        <titre>t</titre>\n" +
                "        <id>40193e61-702e-4ed7-9885-9ec222981987</id>\n" +
                "        <duree>1111</duree>\n" +
                "        <artiste>qsd</artiste>\n" +
                "        <genre>Jazz</genre>\n" +
                "      </chanson>\n" +
                "      <chanson>\n" +
                "        <titre>djadja</titre>\n" +
                "        <id>4ec7b59f-94ae-4b1e-8eb8-3bfceb2d3dfb</id>\n" +
                "        <duree>200</duree>\n" +
                "        <artiste>aya nakamura</artiste>\n" +
                "        <genre>Pop</genre>\n" +
                "      </chanson>\n" +
                "      <chanson>\n" +
                "        <titre>doudou</titre>\n" +
                "        <id>71b1508d-59e9-4456-a029-015433f24726</id>\n" +
                "        <duree>300</duree>\n" +
                "        <artiste>aya nakamura</artiste>\n" +
                "        <genre>Pop</genre>\n" +
                "      </chanson>\n" +
                "      <chanson>\n" +
                "        <titre>akwesigh ami 3zizen</titre>\n" +
                "        <id>d20101f7-8520-45da-af5e-2cbe618afe12</id>\n" +
                "        <duree>300</duree>\n" +
                "        <artiste>nouara</artiste>\n" +
                "        <genre>Classique</genre>\n" +
                "      </chanson>\n" +
                "    </chansons>\n" +
                "  </album>\n" +
                "  <album>\n" +
                "    <titre>pop2020</titre>\n" +
                "    <id>f9cb242b-c4a8-4332-9090-aff88fda899c</id>\n" +
                "    <artiste> vita</artiste>\n" +
                "    <duree>200000</duree>\n" +
                "    <dateDeSortie>10/10/2019</dateDeSortie>\n" +
                "    <chansons>\n" +
                "      <chanson>\n" +
                "        <titre>kalimat</titre>\n" +
                "        <id>6c87e1fa-a688-49ec-a2b3-faf10a80f98d</id>\n" +
                "        <duree>500</duree>\n" +
                "        <artiste>madjida romi</artiste>\n" +
                "        <genre>Classique</genre>\n" +
                "      </chanson>\n" +
                "      <chanson>\n" +
                "        <titre>Ghzali</titre>\n" +
                "        <id>37445745-e91c-4491-b34d-f384c8d325eb</id>\n" +
                "        <duree>500</duree>\n" +
                "        <artiste>madjida romi</artiste>\n" +
                "        <genre>Classique</genre>\n" +
                "      </chanson>\n" +
                "      <chanson>\n" +
                "        <titre>je t'aime</titre>\n" +
                "        <id>474a4189-fea0-4e16-ac8d-6a158527f548</id>\n" +
                "        <duree>300</duree>\n" +
                "        <artiste>lara fabian</artiste>\n" +
                "        <genre>Pop</genre>\n" +
                "      </chanson>\n" +
                "      <chanson>\n" +
                "        <titre>yema tassa</titre>\n" +
                "        <id>397059a4-2f34-468e-9b4b-b5dc1ef9fa46</id>\n" +
                "        <duree>200</duree>\n" +
                "        <artiste>karima</artiste>\n" +
                "        <genre>Pop</genre>\n" +
                "      </chanson>\n" +
                "    </chansons>\n" +
                "  </album>\n" +
                "  <album>\n" +
                "    <titre>slimane</titre>\n" +
                "    <id>f78082b3-ae60-41c3-a0e6-a3d996eea1d0</id>\n" +
                "    <artiste> vita</artiste>\n" +
                "    <duree>30000</duree>\n" +
                "    <dateDeSortie>10/10/2020</dateDeSortie>\n" +
                "    <chansons>\n" +
                "      <chanson>\n" +
                "        <titre>à fleur de toi</titre>\n" +
                "        <id>382d7f26-c1d2-44a5-9f70-75dc0ca4233c</id>\n" +
                "        <duree>200</duree>\n" +
                "        <artiste>slimane &amp; vita</artiste>\n" +
                "        <genre>Pop</genre>\n" +
                "      </chanson>\n" +
                "      <chanson>\n" +
                "        <titre>dis le moi</titre>\n" +
                "        <id>da618ce4-f7ce-4958-836b-d0b7d9955bae</id>\n" +
                "        <duree>200</duree>\n" +
                "        <artiste>slimane &amp; vita</artiste>\n" +
                "        <genre>Pop</genre>\n" +
                "      </chanson>\n" +
                "    </chansons>\n" +
                "  </album>\n" +
                "</albums>";


        //Use method to convert XML string content to XML Document object
        Document doc = convertStringToXMLDocument( xmlStr );

    }
    public static List<Album> extractAlbum(Document doc, Album album) {
        ArrayList<Album> albumArray = new ArrayList<>();
        NodeList albums = doc.getElementsByTagName("album");
        for (int i = 0; i < albums.getLength(); i++) {
            System.out.println("number of album : " + i);
            Node node = albums.item(i);
            List<Chanson> chansons = new ArrayList<Chanson>();
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                album.setTitre(element.getElementsByTagName("titre").item(0).getTextContent());
                album.setId(element.getElementsByTagName("id").item(0).getTextContent());
                album.setArtiste(element.getElementsByTagName("artiste").item(0).getTextContent());
                album.setDuree(Integer.parseInt(element.getElementsByTagName("duree").item(0).getTextContent()));
                Node nodeChanson = element.getElementsByTagName("chansons").item(0);
                System.out.println(nodeChanson.getTextContent());
                Element elementChanson = (Element) nodeChanson;
                NodeList nodeChansons = elementChanson.getElementsByTagName("chanson");
                System.out.println("nombre de chansons :" + nodeChansons.getLength());
                for (int j = 0; j < nodeChansons.getLength(); j++) {
                    Node node1 = nodeChansons.item(j);
                    if (node1.getNodeType() == Node.ELEMENT_NODE) {
                        Element element1 = (Element) node1;
                        Chanson chanson = new Chanson();
                        chanson.setTitre(element1.getElementsByTagName("titre").item(0).getTextContent());
                        chanson.setId(element1.getElementsByTagName("id").item(0).getTextContent());
                        chanson.setDuree(Integer.parseInt(element1.getElementsByTagName("duree").item(0).getTextContent()));
                        chanson.setArtiste(element1.getElementsByTagName("artiste").item(0).getTextContent());
                        String genre = element1.getElementsByTagName("genre").item(0).getTextContent();
                        if (genre == "Pop")
                            chanson.setGenre(Genre.POP);
                        else if (genre == "Jazz")
                            chanson.setGenre(Genre.JAZZ);
                        else if (genre == "classique")
                            chanson.setGenre(Genre.CLASSIQUE);
                        else if (genre == "Hip-Hop")
                            chanson.setGenre(Genre.HIP_HOP);
                        else if (genre == "Rock")
                            chanson.setGenre(Genre.ROCK);
                        else if (genre == "Rap")
                            chanson.setGenre(Genre.RAP);
                        chansons.add(chanson);

                    }
                }
                album.setChansons(chansons);
                albumArray.add(album);
            }
        }
        return albumArray;
    }


    private static Document convertStringToXMLDocument(String xmlString)
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

