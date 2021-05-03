package esieaa.jmusic.business;

import esieaa.jmusic.Chanson;
import esieaa.jmusic.Genre;
import esieaa.jmusic.ResourceType;
import esieaa.jmusic.main.PojoContext;
import esieaa.jmusic.util.ConversionUtils;

import java.util.Arrays;
import java.util.UUID;

/**
 * defines the add song "(chanson)" command
 */
public class ChansonCmd extends AbstractCommand {

  public ChansonCmd() {
    super(true);
  }

  @Override
  public void executeInternal() {
    Chanson chanson = new Chanson();
    String titre = ask("Veuillez rentrer le titre de la chanson");
    String genre = askWithAuthorizedValueList("Veuillez rentrer le genre de la chanson.",
        Arrays.asList("Jazz", "Classique", "Hip-hop", "Rock", "Pop", "Rap"));
    String artiste = ask("Veuillez rentrer l'artiste compositeur de la chanson");
    String id = UUID.randomUUID().toString();
    ResourceType type = ResourceType.CHANSON;
    int duree = askForInteger("Veuillez entrer la durée en secondes");
    chanson.setTitre(titre);
    chanson.setGenre((Genre) ConversionUtils.toEnumFromVal(Genre.values(), genre));
    chanson.setArtiste(artiste);
    chanson.setId(id);
    chanson.setDuree(duree);
    PojoContext.getInstance().addChanson(chanson);
    System.out.println("Chanson ajoutée avec succès !");
  }

  @Override
  public String alias() {
    return "c";
  }

  @Override
  protected String greetingMessage() {
    return "Vous êtes dans l'utilitaire interactif d'ajout de chanson";
  }
}
