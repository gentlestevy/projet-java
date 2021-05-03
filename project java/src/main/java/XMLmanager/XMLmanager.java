package XMLmanager;

import audioModels.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class XMLmanager {
public static String  albumPath;
public static String  playlistPath;
public static String  elementPath;


    public static void main(String[] args) {

    }

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


    public static Document writeAlbum(List<Album> albums) throws ParserConfigurationException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element root;

         root = doc.createElement("albums");
         doc.appendChild(root);
        for (Album album : albums) {
            Element albume = doc.createElement("album");
            root.appendChild(albume);
            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(album.getId()));
            albume.appendChild(id);

            Element duree = doc.createElement("duree");
            duree.appendChild(doc.createTextNode(Integer.toString(album.getDuree())));
            albume.appendChild(duree);

            Element artiste = doc.createElement("artiste");
            artiste.appendChild(doc.createTextNode(album.getArtiste()));
            albume.appendChild(artiste);

            Element dateDeSortie = doc.createElement("dateDeSortie");
            dateDeSortie.appendChild(doc.createTextNode(album.getDateDeSortie().toString()));
            albume.appendChild(dateDeSortie);

            Element chansonE = doc.createElement("chansons");
            albume.appendChild(chansonE);
            for (int j = 0; j < album.getChansons().size(); j++) {
                List<Chanson> chansons = album.getChansons();
                Element chanson = doc.createElement("chanson");
                chansonE.appendChild(chanson);


                Element idChans = doc.createElement("id");
                idChans.appendChild(doc.createTextNode(chansons.get(j).getId()));
                chanson.appendChild(idChans);

                Element dureeChans = doc.createElement("duree");
                dureeChans.appendChild(doc.createTextNode(Integer.toString(chansons.get(j).getDuree())));
                chanson.appendChild(dureeChans);

                Element artisteChans = doc.createElement("artiste");
                artisteChans.appendChild(doc.createTextNode(chansons.get(j).getArtiste()));
                chanson.appendChild(artisteChans);

                Element genre = doc.createElement("genre");
                genre.appendChild(doc.createTextNode(chansons.get(j).getGenre().toString()));
                chanson.appendChild(genre);
                System.out.println("chanson added");
            }
            System.out.println("album added");
        }

        return doc;
    }

    public static Document writePlaylist(List<PlayList> playLists) throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element root;

        root = doc.createElement("playlists");
        doc.appendChild(root);


        for (int i = 0; i < playLists.size(); i++) {
            Element playList = doc.createElement("playlist");
            root.appendChild(playList);
            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(playLists.get(i).getId()));
            playList.appendChild(id);

            Element nom = doc.createElement("nom");
            nom.appendChild(doc.createTextNode(playLists.get(i).getNom()));
            playList.appendChild(nom);

            Element resources = doc.createElement("resources");
            playList.appendChild(resources);
            for (int j = 0; j < playLists.get(i).getChansons().size(); j++) {
                List<Chanson> chansons = playLists.get(i).getChansons();
                Element chanson = doc.createElement("chanson");
                resources.appendChild(chanson);

                Element idChans = doc.createElement("");
                idChans.appendChild(doc.createTextNode(chansons.get(j).getId()));
                chanson.appendChild(id);

                Element dureeChans = doc.createElement("duree");
                dureeChans.appendChild(doc.createTextNode(Integer.toString(chansons.get(j).getDuree())));
                chanson.appendChild(dureeChans);

                Element artisteChans = doc.createElement("artiste");
                artisteChans.appendChild(doc.createTextNode(chansons.get(j).getArtiste()));
                chanson.appendChild(artisteChans);

                Element genre = doc.createElement("genre");
                genre.appendChild(doc.createTextNode(chansons.get(j).getGenre().toString()));
                chanson.appendChild(genre);
            }
            for (int j = 0; j < playLists.get(i).getLivreAudios().size(); j++) {
                List<LivreAudio> livreAudios = playLists.get(i).getLivreAudios();

                Element elivreAudio = doc.createElement("livreAudio");
                resources.appendChild(elivreAudio);

                Element idL = doc.createElement("");
                idL.appendChild(doc.createTextNode(livreAudios.get(j).getId()));
                elivreAudio.appendChild(idL);

                Element duree = doc.createElement("duree");
                duree.appendChild(doc.createTextNode(Integer.toString(livreAudios.get(j).getDuree())));
                elivreAudio.appendChild(duree);

                Element auteur = doc.createElement("auteur");
                auteur.appendChild(doc.createTextNode(livreAudios.get(j).getAuteur()));
                elivreAudio.appendChild(auteur);

                Element categorie = doc.createElement("categorie");
                categorie.appendChild(doc.createTextNode(livreAudios.get(j).getCategorie().toString()));
                elivreAudio.appendChild(categorie);


                Element langue = doc.createElement("langue");
                langue.appendChild(doc.createTextNode(livreAudios.get(j).getLangue().toString()));
                elivreAudio.appendChild(langue);

            }


        }
        return doc;
    }

    public static Document writeLivreAudio(List<LivreAudio> livreAudios) throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element elements;

        elements = doc.createElement("elements");
        doc.appendChild(elements);


        for (LivreAudio livreAudio : livreAudios) {
            Element elivreAudio = doc.createElement("livreAudio");
            elements.appendChild(elivreAudio);

            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(livreAudio.getId()));
            elivreAudio.appendChild(id);

            Element duree = doc.createElement("duree");
            duree.appendChild(doc.createTextNode(Integer.toString(livreAudio.getDuree())));
            elivreAudio.appendChild(duree);

            Element auteur = doc.createElement("auteur");
            auteur.appendChild(doc.createTextNode(livreAudio.getAuteur()));
            elivreAudio.appendChild(auteur);

            Element categorie = doc.createElement("categorie");
            categorie.appendChild(doc.createTextNode(livreAudio.getCategorie().toString()));
            elivreAudio.appendChild(categorie);


            Element langue = doc.createElement("langue");
            langue.appendChild(doc.createTextNode(livreAudio.getLangue().toString()));
            elivreAudio.appendChild(langue);
        }
        return doc;
    }


    public static Document writeChanson(List<Chanson> chansons) throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element elements;

        elements = doc.createElement("elements");
        doc.appendChild(elements);


        for (Chanson chanson : chansons) {
            Element eChanson = doc.createElement("chanson");
            elements.appendChild(eChanson);

            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(chanson.getId()));
            eChanson.appendChild(id);

            Element duree = doc.createElement("duree");
            duree.appendChild(doc.createTextNode(Integer.toString(chanson.getDuree())));
            eChanson.appendChild(duree);

            Element artiste = doc.createElement("artiste");
            artiste.appendChild(doc.createTextNode(chanson.getArtiste()));
            eChanson.appendChild(artiste);

            Element genre = doc.createElement("genre");
            genre.appendChild(doc.createTextNode(chanson.getGenre().toString()));
            eChanson.appendChild(genre);
        }


        return doc;
    }
    public static Document writeResource(List<Chanson> chansons,List<LivreAudio> livreAudios) throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element elements;

        elements = doc.createElement("elements");
        doc.appendChild(elements);


        for (Chanson chanson : chansons) {
            Element eChanson = doc.createElement("chanson");
            elements.appendChild(eChanson);

            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(chanson.getId()));
            eChanson.appendChild(id);

            Element duree = doc.createElement("duree");
            duree.appendChild(doc.createTextNode(Integer.toString(chanson.getDuree())));
            eChanson.appendChild(duree);

            Element artiste = doc.createElement("artiste");
            artiste.appendChild(doc.createTextNode(chanson.getArtiste()));
            eChanson.appendChild(artiste);

            Element genre = doc.createElement("genre");
            genre.appendChild(doc.createTextNode(chanson.getGenre().toString()));
            eChanson.appendChild(genre);
        }
        for (LivreAudio livreAudio : livreAudios) {
            Element elivreAudio = doc.createElement("livreAudio");
            elements.appendChild(elivreAudio);

            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(livreAudio.getId()));
            elivreAudio.appendChild(id);

            Element duree = doc.createElement("duree");
            duree.appendChild(doc.createTextNode(Integer.toString(livreAudio.getDuree())));
            elivreAudio.appendChild(duree);

            Element auteur = doc.createElement("auteur");
            auteur.appendChild(doc.createTextNode(livreAudio.getAuteur()));
            elivreAudio.appendChild(auteur);

            Element categorie = doc.createElement("categorie");
            categorie.appendChild(doc.createTextNode(livreAudio.getCategorie().toString()));
            elivreAudio.appendChild(categorie);


            Element langue = doc.createElement("langue");
            langue.appendChild(doc.createTextNode(livreAudio.getLangue().toString()));
            elivreAudio.appendChild(langue);
        }
        return doc;
    }

    public static Document convertStringToXMLDocument(String xmlString) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            return builder.parse(new InputSource(new StringReader(xmlString)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getStringFromDocument(Document doc) throws TransformerException, TransformerException {
        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);
        return writer.toString();
    }
}

