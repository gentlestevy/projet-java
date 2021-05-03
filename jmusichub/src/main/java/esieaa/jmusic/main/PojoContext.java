package esieaa.jmusic.main;

import esieaa.jmusic.Album;
import esieaa.jmusic.Chanson;
import esieaa.jmusic.LivreAudio;
import esieaa.jmusic.PlayList;
import esieaa.jmusic.Resource;
import esieaa.jmusic.ResourceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * pojo representation for xml files
 */
public class PojoContext {
  private List<Album> albums;

  private List<Resource> elements;

  private List<PlayList> playLists;

  public List<PlayList> getPlayLists() {
    if (playLists == null) {
      playLists = new ArrayList<>();
    }
    return playLists;
  }

  public List<Resource> getElements() {
    return elements;
  }

  public void setPlayLists(List<PlayList> playLists) {
    this.playLists = playLists;
  }

  // singleton instance
  private static PojoContext instance;

  public List<Album> getAlbums() {
    if (albums == null) {
      albums = new ArrayList<>();
    }
    return albums;
  }

  public void setElements(List<Resource> elements) {
    this.elements = elements;
  }

  public void setAlbums(List<Album> albums) {
    this.albums = albums;
  }

  private PojoContext() {
  }

  public static PojoContext getInstance() {
    if (instance == null) {
      instance = new PojoContext();
    }
    return instance;
  }

  public List<Resource> getChansons() {
    if (this.elements == null || elements.isEmpty()) {
      return Collections.emptyList();
    }
    return elements.stream().filter(e -> e.getType().equals(ResourceType.CHANSON))
        .collect(Collectors.toList());
  }

  public List<Resource> getLivresAudio() {
    if (this.elements == null || elements.isEmpty()) {
      return Collections.emptyList();
    }
    return elements.stream().filter(e -> e.getType().equals(ResourceType.LIVRE_AUDIO))
        .collect(Collectors.toList());
  }

  public void addChanson(Chanson c) {
    this.elements.add(c);
  }

  public void addLivreAudio(LivreAudio l) {
    this.elements.add(l);
  }
}
