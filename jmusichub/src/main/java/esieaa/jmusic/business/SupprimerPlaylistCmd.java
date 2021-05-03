package esieaa.jmusic.business;

import esieaa.jmusic.PlayList;
import esieaa.jmusic.main.PojoContext;

import java.util.List;
import java.util.stream.Collectors;

public class SupprimerPlaylistCmd extends AbstractCommand {

  public SupprimerPlaylistCmd() {
    super(true);
  }

  @Override protected String greetingMessage() {
    return "Vous �tes dans l'utilitaire interactif de suppresion d'une playlist";
  }

  @Override
  public void executeInternal() {
    String nomOuId = ask("Veuillez entrer le titre ou l'id");
    List<PlayList> playLists = PojoContext.getInstance().getPlayLists().stream().filter(p -> p.getNom().equals(nomOuId) || p.getId().equals(nomOuId)).collect(Collectors.toList());
    if (playLists.isEmpty()) {
      System.out.println("Playlist non trouv�e");
      System.out.println("Voici la liste des playlists");
      PojoContext.getInstance().getPlayLists().forEach(this::printPlaylist);
    }
    else if (playLists.size() > 1) {
      System.out.println("Playlist non supprim�e");
      System.out.println("Plusieurs ressources correpondent � la recherche");
      System.out.println("Voici la liste des playlists trouv�es");
      playLists.forEach(this::printPlaylist);
    }
    else {
      PojoContext.getInstance().getPlayLists().remove(playLists.get(0));
      System.out.println("playlist supprim�e avec succ�s");
    }
  }

  @Override public String alias() {
    return "-";
  }

  private void printPlaylist(PlayList playList) {
    System.out.println(String.format(
        "[PLAYLIST] id: %1s - nom %1s", playList.getId(), playList.getNom()
    ));
  }
}
