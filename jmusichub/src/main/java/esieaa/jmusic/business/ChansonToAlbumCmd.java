package esieaa.jmusic.business;

import esieaa.jmusic.Album;
import esieaa.jmusic.Chanson;
import esieaa.jmusic.Resource;
import esieaa.jmusic.main.PojoContext;

import java.util.List;
import java.util.stream.Collectors;

public class ChansonToAlbumCmd extends AbstractCommand {

  public ChansonToAlbumCmd() {
    super(true);
  }

  @Override protected String greetingMessage() {
    return "Vous �tes dans l'utilitaire interactif d'ajout d'une chanson � un album";
  }

  @Override public void executeInternal() {
    List<Album> all = PojoContext.getInstance().getAlbums();
    if (all.isEmpty()) {
      System.out.println("il n'existe aucun album acutellement");
      return;
    }
    String titreOrId = ask("Veuillez entrer le titre ou l'id de l'album");

    List<Album> albums = all.stream()
        .filter(a -> a.getTitre().equalsIgnoreCase(titreOrId) || a.getId().equals(titreOrId)).collect(Collectors.toList());
    if (albums.isEmpty()) {
      System.out.println("l'album recherch� n'existe pas");
      System.out.println("voici la liste des albums :");
      printAlbum(all);
    }
    else if (albums.size() > 1) {
      System.out.println("plusieurs albums correspondent � votre crit�re veuillez essayer la recherche par id");
      System.out.println("voici la liste des albums trouv�s :");
      printAlbum(albums);
    }
    else {
      String chanson = ask("Veuillez entrer le titre ou l'id de chanson a ajouter");
      List<Resource> chansons = RechercherResource.rechercherChanson(chanson);
      boolean firstIter = true;
      do {
        if (!firstIter) {
          if (chansons.isEmpty()) {
            System.out.println("Aucune chanson ne correspond � votre recherche");
            chanson = ask("veuillez r�essayer");
          }
          else if (chansons.size() > 1) {
            System.out.println("plusieurs chanson ont �t� trouv� veuillez r�essayer en affinant la recherche par id");
            chanson = ask("veuillez r�essayer");
          }
          chansons = RechercherResource.rechercherChanson(chanson);
        }
        else {
          firstIter = false;
        }

      } while (chansons.size() != 1);
      albums.get(0).getChansons().add((Chanson) chansons.get(0));
      System.out.println("chanson avec id : " + chansons.get(0).getId() + " a �t� ajout� avec succ�s");
    }
  }

  @Override public String alias() {
    return "+";
  }

  private void printAlbum(List<Album> albums) {
    albums.forEach(a -> {
      System.out.println(String.format(
          "album - id: %1s - titre: %2s - artiste: %3s - dur�e: %4s",
          a.getId(), a.getTitre(), a.getArtiste(), a.getDuree()
      ));
    });
  }
}
