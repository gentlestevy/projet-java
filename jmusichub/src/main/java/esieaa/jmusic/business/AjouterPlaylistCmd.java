package esieaa.jmusic.business;

import esieaa.jmusic.Chanson;
import esieaa.jmusic.LivreAudio;
import esieaa.jmusic.PlayList;
import esieaa.jmusic.Resource;
import esieaa.jmusic.main.PojoContext;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AjouterPlaylistCmd extends AbstractCommand {
  public AjouterPlaylistCmd() {
    super(true);
  }

  @Override
  protected String greetingMessage() {
    return "Vous êtes dans l'utilitaire interactif d'ajout d'une playlist";
  }

  @Override
  public void executeInternal() {
    String nom = ask("veuillez entrer le nom de la playlist");
    PlayList playList = new PlayList();
    playList.setNom(nom);
    playList.setId(UUID.randomUUID().toString());

    do {
      String type = askWithAuthorizedValueList("Veuillez entrer le type de la ressource à ajouter à la playlist",
          Arrays.asList("chanson", "livre audio"));
      if (type.equals("chanson")) {
        String chansonTitreOuId = ask("Veuillez entrer le titre ou l'id de la chanson à ajouter");
        List<Resource> chansons = RechercherResource.rechercherChanson(chansonTitreOuId);
        handleResultat(chansons, true, playList);
      }
      else {
        String livreAudioTitreOuId = ask("Veuillez entrer le titre ou l'id du livre audio à ajouter");
        List<Resource> livresAudio = RechercherResource.rechercheLivreAudio(livreAudioTitreOuId);
        handleResultat(livresAudio, false, playList);
      }
    } while (addAnother());
    PojoContext.getInstance().getPlayLists().add(playList);
  }

  @Override
  public String alias() {
    return "p";
  }

  private boolean addAnother() {
    String res = askWithAuthorizedValueList("Voulez vous ajouter une autre ressource à la playlist ?",
        Arrays.asList("oui", "non"));
    return res.equals("oui");
  }

  private void handleResultat(List<Resource> res, boolean isChanson, PlayList playList) {
    String type = isChanson ? "Aucune chanson" : "Aucun livre audio";
    if (res.isEmpty()) {
      System.out.println("RESSOURCE NON AJOUTEE !");
      System.out.println(String.format("%1s ne correspond à votre recherche", type));
      System.out.println("voici la liste des ressource disponible");
      if (isChanson) {
        for (Resource c : PojoContext.getInstance().getChansons()) {
          printChanson((Chanson) c);
        }
      }
      else {
        for (Resource la : PojoContext.getInstance().getLivresAudio()) {
          printLivreAudio((LivreAudio) la);
        }
      }
    }
    else if (res.size() > 1) {
      handleNonUniqueRes(res, isChanson);
    }
    else {
      playList.getResources().add(res.get(0));
      System.out.println(String.format("%1s a été ajouté avec succès", isChanson ? "La chanson" : "Le livre audio"));
    }
  }

  private void handleNonUniqueRes(List<Resource> res, boolean isChanson) {
    String type = isChanson ? "chansons" : "livres audio";
    System.out.println("RESSOURCE NON AJOUTEE !");
    System.out.println(String.format("plusieurs %1s correspondent à votre recherche", type));
    System.out.println("voici la liste des ressources correspondant à votre recherche");
    for (Resource r : res) {
      if (isChanson) {
        printChanson((Chanson) r);
      }
      else {
        printLivreAudio((LivreAudio) r);
      }
    }
  }

  private void printLivreAudio(LivreAudio a) {
    System.out.println(String.format
        ("Livre audio: id : %1s - titre: %2s - auteur: %3s - catégorie: %4s - durée: %5s - langues: %6s",
            a.getId(), a.getTitre(), a.getAuteur(), a.getCategorie().toString(), a.getDuree(), a.getLangue().toString()));
  }

  private void printChanson(Chanson c) {
    System.out.println(String.format
        ("Chanson: id : %1s - titre: %2s - artiste: %3s - genre: %4s - durée: %5s",
            c.getId(), c.getTitre(), c.getArtiste(), c.getGenre().toString(), c.getDuree()));
  }
}
