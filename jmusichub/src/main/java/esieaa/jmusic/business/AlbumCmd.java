package esieaa.jmusic.business;

import esieaa.jmusic.Album;
import esieaa.jmusic.main.PojoContext;

import java.util.Date;
import java.util.UUID;

public class AlbumCmd extends AbstractCommand {

  public AlbumCmd() {
    super(true);
  }

  @Override protected String greetingMessage() {
    return "Vous êtes dans l'utilitaire interactif d'ajout d'un album";
  }

  @Override public void executeInternal() {
    String titre = ask("Veuillez entrer le titre de l'album");
    String artiste = ask("Veuillez entrer l'artiste de l'album");
    int duree = askForInteger("Veuillez entrer la durée de l'album");
    Date dateDeSortie = askForDate("Veuillez entrer la date de sortie de l'album");
    String id = UUID.randomUUID().toString();

    Album album = new Album();
    album.setArtiste(artiste);
    album.setDuree(duree);
    album.setTitre(titre);
    album.setId(id);
    album.setDateDeSortie(dateDeSortie);
    PojoContext.getInstance().getAlbums().add(album);
    System.out.println("l'Album a été ajouté avec succès");
  }

  @Override public String alias() {
    return "a";
  }
}
