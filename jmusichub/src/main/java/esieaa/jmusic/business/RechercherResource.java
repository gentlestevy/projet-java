package esieaa.jmusic.business;

import esieaa.jmusic.Album;
import esieaa.jmusic.Resource;
import esieaa.jmusic.main.PojoContext;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RechercherResource {
  public static List<Resource> rechercherChanson(String titreOrid) {
    List<Resource> chansons = PojoContext.getInstance().getChansons();
    if (chansons.isEmpty()) {
      return Collections.emptyList();
    }
    else {
      return chansons.stream().filter(c -> c.getTitre().equalsIgnoreCase(titreOrid) || c.getId().equals(titreOrid))
          .collect(Collectors.toList());
    }
  }

  public static List<Resource> rechercheLivreAudio(String titreOuId) {
    List<Resource> livresAudio = PojoContext.getInstance().getLivresAudio();
    if (livresAudio.isEmpty()) {
      return Collections.emptyList();
    }
    else {
      return livresAudio.stream().filter(l -> l.getTitre().equalsIgnoreCase(titreOuId) || l.getId().equals(titreOuId))
          .collect(Collectors.toList());
    }
  }

  public static List<Album> rechercherAlbum(String titreOuid) {
    List<Album> albums = PojoContext.getInstance().getAlbums();
    if (albums == null || albums.isEmpty()) {
      return Collections.emptyList();
    }

    return albums.stream().filter(a -> a.getTitre().equals(titreOuid) || a.getId().equals(titreOuid))
        .collect(Collectors.toList());
  }
}
