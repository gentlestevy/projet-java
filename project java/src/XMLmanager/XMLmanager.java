package XMLmanager;

import audioModels.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class XMLmanager {

    public static List<Album> extractAlbum(Document doc) {

        ArrayList<Album> albumArray = new ArrayList<>();
        NodeList albums = doc.getElementsByTagName("album");

        for (int i = 0; i < albums.getLength(); i++) {
            Album album = new Album();
            Node node = albums.item(i);
            List<Chanson> chansons = new ArrayList<Chanson>();
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;


                album.setTitre(element.getElementsByTagName("titre").item(0).getTextContent());
                album.setId(element.getElementsByTagName("id").item(0).getTextContent());
                //System.out.println(element.getElementsByTagName("id").item(0).getTextContent());
                album.setArtiste(element.getElementsByTagName("artiste").item(0).getTextContent());
                album.setDuree(Integer.parseInt(element.getElementsByTagName("duree").item(0).getTextContent()));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                album.setDateDeSortie(LocalDate.parse(element.getElementsByTagName("dateDeSortie").item(0).getTextContent(), formatter));

                Node nodeChanson = element.getElementsByTagName("chansons").item(0);


                Element elementChanson = (Element) nodeChanson;

                NodeList nodeChansons = elementChanson.getElementsByTagName("chanson");
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

                        switch (genre) {
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

                        chansons.add(chanson);


                    }


                }
                album.setChansons(chansons);


            }
            albumArray.add(album);
        }
        return albumArray;
    }

    public static List<PlayList> extractPlaylist(Document doc) {

        List<PlayList> playlistArray = new ArrayList<>();
        NodeList playlists = doc.getElementsByTagName("playlist");

        for (int i = 0; i < playlists.getLength(); i++) {
            PlayList playlist = new PlayList();
            System.out.println("number of playlist : " + i);
            Node node = playlists.item(i);
            List<Resource> resources = new ArrayList<>();
            List<Chanson> chansons = new ArrayList<>();
            List<LivreAudio> liveAudios = new ArrayList<>();
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                playlist.setId(element.getElementsByTagName("id").item(0).getTextContent());
                playlist.setNom(element.getElementsByTagName("nom").item(0).getTextContent());


                Node nodeResource = element.getElementsByTagName("resources").item(0);

                System.out.println(nodeResource.getTextContent());
                Element elementResource = (Element) nodeResource;

                NodeList nodeChanson = elementResource.getElementsByTagName("chanson");
                NodeList nodeLivreAudio = elementResource.getElementsByTagName("livreAudio");
                System.out.println("nombre de chansons :" + nodeChanson.getLength());
                for (int j = 0; j < nodeChanson.getLength(); j++) {
                    Node node1 = nodeChanson.item(j);

                    if (node1.getNodeType() == Node.ELEMENT_NODE) {
                        Element element1 = (Element) node1;
                        Chanson chanson = new Chanson();

                        chanson.setTitre(element1.getElementsByTagName("titre").item(0).getTextContent());
                        chanson.setId(element1.getElementsByTagName("id").item(0).getTextContent());
                        chanson.setDuree(Integer.parseInt(element1.getElementsByTagName("duree").item(0).getTextContent()));
                        chanson.setArtiste(element1.getElementsByTagName("artiste").item(0).getTextContent());

                        String genre = element1.getElementsByTagName("genre").item(0).getTextContent();

                        switch (genre) {
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
                        chansons.add(chanson);
                        resources.add(chanson);


                    }
                }
                for (int j = 0; j < nodeLivreAudio.getLength(); j++) {
                    Node node1 = nodeLivreAudio.item(j);

                    if (node1.getNodeType() == Node.ELEMENT_NODE) {
                        Element element1 = (Element) node1;
                        LivreAudio livreAudio = new LivreAudio();

                        livreAudio.setTitre(element1.getElementsByTagName("titre").item(0).getTextContent());
                        livreAudio.setId(element1.getElementsByTagName("id").item(0).getTextContent());
                        livreAudio.setDuree(Integer.parseInt(element1.getElementsByTagName("duree").item(0).getTextContent()));
                        livreAudio.setAuteur(element1.getElementsByTagName("auteur").item(0).getTextContent());

                        String categorie = element1.getElementsByTagName("categorie").item(0).getTextContent();
                        String langue = element1.getElementsByTagName("langue").item(0).getTextContent();

                        switch (categorie) {
                            case "Jeunesse":
                                livreAudio.setCategorie(Categorie.JEUNESSE);
                                break;
                            case "Roman":
                                livreAudio.setCategorie(Categorie.ROMAN);
                                break;
                            case "Théâtre":
                                livreAudio.setCategorie(Categorie.THEATRE);
                                break;
                            case "Discours":
                                livreAudio.setCategorie(Categorie.DISCOURS);
                                break;
                            case "Documentaire":
                                livreAudio.setCategorie(Categorie.DOCUMENTAIRE);
                                break;
                        }

                        switch (langue) {
                            case "Français":
                                livreAudio.setLangue(Langues.FRANCAIS);
                                break;
                            case "Anglais":
                                livreAudio.setLangue(Langues.ANGLAIS);
                                break;
                            case "Italien":
                                livreAudio.setLangue(Langues.ITALIEN);
                                break;
                            case "Espagnol":
                                livreAudio.setLangue(Langues.ESPAGNOL);
                                break;
                            case "Allemand":
                                livreAudio.setLangue(Langues.ALLEMAND);
                                break;
                        }
                        liveAudios.add(livreAudio);
                        resources.add(livreAudio);


                    }
                }
                playlist.setResources(resources);
                playlist.setChansons(chansons);
                playlist.setLivreAudios(liveAudios);
                playlistArray.add(playlist);
            }

        }
        return playlistArray;
    }

    public static List<Resource> extractResource(Document doc) {
        Chanson chanson = new Chanson();
        LivreAudio livreAudio = new LivreAudio();
        List<Resource> resourcesList = new ArrayList<>();

        NodeList chansonList = doc.getElementsByTagName("chanson");
        NodeList livreAudioList = doc.getElementsByTagName("livreAudio");

        for (int i = 0; i < chansonList.getLength(); i++) {
            Node nodeChanson = chansonList.item(i);
            if (nodeChanson.getNodeType() == Node.ELEMENT_NODE) {
                Element elementChanson = (Element) nodeChanson;

                chanson.setTitre(elementChanson.getElementsByTagName("titre").item(0).getTextContent());
                chanson.setId(elementChanson.getElementsByTagName("id").item(0).getTextContent());
                chanson.setDuree(Integer.parseInt(elementChanson.getElementsByTagName("duree").item(0).getTextContent()));
                chanson.setArtiste(elementChanson.getElementsByTagName("artiste").item(0).getTextContent());

                String genre = elementChanson.getElementsByTagName("genre").item(0).getTextContent();

                switch (genre) {
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

                resourcesList.add(chanson);

            }

        }
        for (int i = 0; i < livreAudioList.getLength(); i++) {
            String categorie;
            String langue;
            Node nodelivreAudio = livreAudioList.item(i);
            if (nodelivreAudio.getNodeType() == Node.ELEMENT_NODE) {
                Element elementlivreAudio = (Element) nodelivreAudio;

                livreAudio.setTitre(elementlivreAudio.getElementsByTagName("titre").item(0).getTextContent());
                livreAudio.setId(elementlivreAudio.getElementsByTagName("id").item(0).getTextContent());
                livreAudio.setDuree(Integer.parseInt(elementlivreAudio.getElementsByTagName("duree").item(0).getTextContent()));
                livreAudio.setAuteur(elementlivreAudio.getElementsByTagName("auteur").item(0).getTextContent());

                categorie = elementlivreAudio.getElementsByTagName("categorie").item(0).getTextContent();
                langue = elementlivreAudio.getElementsByTagName("langue").item(0).getTextContent();

                switch (categorie) {
                    case "Jeunesse":
                        livreAudio.setCategorie(Categorie.JEUNESSE);
                        break;
                    case "Roman":
                        livreAudio.setCategorie(Categorie.ROMAN);
                        break;
                    case "Théâtre":
                        livreAudio.setCategorie(Categorie.THEATRE);
                        break;
                    case "Discours":
                        livreAudio.setCategorie(Categorie.DISCOURS);
                        break;
                    case "Documentaire":
                        livreAudio.setCategorie(Categorie.DOCUMENTAIRE);
                        break;
                }

                switch (langue) {
                    case "Français":
                        livreAudio.setLangue(Langues.FRANCAIS);
                        break;
                    case "Anglais":
                        livreAudio.setLangue(Langues.ANGLAIS);
                        break;
                    case "Italien":
                        livreAudio.setLangue(Langues.ITALIEN);
                        break;
                    case "Espagnol":
                        livreAudio.setLangue(Langues.ESPAGNOL);
                        break;
                    case "Allemand":
                        livreAudio.setLangue(Langues.ALLEMAND);
                        break;
                }

                resourcesList.add(livreAudio);
            }

        }

        return resourcesList;
    }

    public static List<Chanson> extractChansons(Document doc) {

        List<Chanson> chansonsList = new ArrayList<>();
        NodeList chansonList = doc.getElementsByTagName("chanson");

        for (int i = 0; i < chansonList.getLength(); i++) {
            Chanson chanson = new Chanson();
            Node nodeChanson = chansonList.item(i);
            if (nodeChanson.getNodeType() == Node.ELEMENT_NODE) {
                Element elementChanson = (Element) nodeChanson;

                chanson.setTitre(elementChanson.getElementsByTagName("titre").item(0).getTextContent());
                chanson.setId(elementChanson.getElementsByTagName("id").item(0).getTextContent());
                chanson.setDuree(Integer.parseInt(elementChanson.getElementsByTagName("duree").item(0).getTextContent()));
                chanson.setArtiste(elementChanson.getElementsByTagName("artiste").item(0).getTextContent());

                String genre = elementChanson.getElementsByTagName("genre").item(0).getTextContent();

                switch (genre) {
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

                chansonsList.add(chanson);

            }

        }

        return chansonsList;
    }

    public static List<LivreAudio> extractLivreAudio(Document doc) {
        List<LivreAudio> livreAudioLists = new ArrayList<>();
        NodeList livreAudioList = doc.getElementsByTagName("livreAudio");
        String langue;
        for (int i = 0; i < livreAudioList.getLength(); i++) {

            LivreAudio livreAudio = new LivreAudio();
            Node nodelivreAudio = livreAudioList.item(i);
            if (nodelivreAudio.getNodeType() == Node.ELEMENT_NODE) {
                Element elementlivreAudio = (Element) nodelivreAudio;

                livreAudio.setTitre(elementlivreAudio.getElementsByTagName("titre").item(0).getTextContent());
                livreAudio.setId(elementlivreAudio.getElementsByTagName("id").item(0).getTextContent());
                livreAudio.setDuree(Integer.parseInt(elementlivreAudio.getElementsByTagName("duree").item(0).getTextContent()));
                livreAudio.setAuteur(elementlivreAudio.getElementsByTagName("auteur").item(0).getTextContent());

                langue = elementlivreAudio.getElementsByTagName("langue").item(0).getTextContent();

                System.out.println("voici les langue : " + langue);
                String categorie = elementlivreAudio.getElementsByTagName("categorie").item(0).getTextContent();

                switch (langue) {
                    case "Français":
                        livreAudio.setLangue(Langues.FRANCAIS);
                        break;
                    case "Anglais":
                        livreAudio.setLangue(Langues.ANGLAIS);
                        break;
                    case "Italien":
                        livreAudio.setLangue(Langues.ITALIEN);
                        break;
                    case "Espagnol":
                        livreAudio.setLangue(Langues.ESPAGNOL);
                        break;
                    case "Allemand":
                        livreAudio.setLangue(Langues.ALLEMAND);
                        break;
                }
                switch (categorie) {
                    case "Jeunesse":
                        livreAudio.setCategorie(Categorie.JEUNESSE);
                        break;
                    case "Roman":
                        livreAudio.setCategorie(Categorie.ROMAN);
                        break;
                    case "Théâtre":
                        livreAudio.setCategorie(Categorie.THEATRE);
                        break;
                    case "Discours":
                        livreAudio.setCategorie(Categorie.DISCOURS);
                        break;
                    case "Documentaire":
                        livreAudio.setCategorie(Categorie.DOCUMENTAIRE);
                        break;
                }


                livreAudioLists.add(livreAudio);
            }

        }

        return livreAudioLists;
    }



}

