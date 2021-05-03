package audioModels;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
  private List<Resource> resources;
  private List<Chanson> chansons;
  private List<LivreAudio>  livreAudios;
  private String id;

  private String nom;

  public List<Resource> getResources() {
    if (resources == null) {
      resources = new ArrayList<>();
    }
    return resources;
  }
  public List<Chanson> getChansons() {
    if (chansons == null) {
      chansons = new ArrayList<>();
    }
    return chansons;
  }
  public List<LivreAudio> getLivreAudios() {
    if (livreAudios == null) {
       livreAudios = new ArrayList<>();
    }
    return livreAudios;
  }
  public void setResources(List<Resource> resources) {
    this.resources = resources;
  }
  public void setChansons(List<Chanson> chansons) {
    this.chansons = chansons;
  }
  public void setLivreAudios(List<LivreAudio> livreAudios) {
    this.livreAudios = livreAudios;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }
}
