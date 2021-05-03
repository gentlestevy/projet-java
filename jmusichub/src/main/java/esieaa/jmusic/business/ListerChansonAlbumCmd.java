package esieaa.jmusic.business;

import esieaa.jmusic.Album;
import esieaa.jmusic.Chanson;

import java.util.List;

public class ListerChansonAlbumCmd extends AbstractCommand {
  public ListerChansonAlbumCmd() {
    super(false);
  }

  @Override
  protected String greetingMessage() {
    return null;
  }

  @Override
  public void executeInternal() {
    String titreOuId = ask("Veuillez entrer le titre ou l'id de l'album");
    List<Album> albums = RechercherResource.rechercherAlbum(titreOuId);
    if (albums.isEmpty()) {
      System.out.println("l'album recherché n'a pas été trouvé");
    }
    else if (albums.size() > 1) {
      System.out.println("Plusieurs albums correspondent à la recherche");
    }
    else {
      albums.get(0).getChansons().forEach(r -> {
        Chanson c = (Chanson) r;
        System.out.println(String.format
            ("Chanson: id : %1s - titre: %2s - artiste: %3s - genre: %4s - durée: %5s",
                c.getId(), c.getTitre(), c.getArtiste(), c.getGenre().toString(), c.getDuree()));
      });
    }

  }

  @Override public String alias() {
    return "lac";
  }
}
