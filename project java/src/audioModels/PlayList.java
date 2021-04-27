package audioModels;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
  private List<Resource> resources;

  private String id;

  private String nom;

  public List<Resource> getResources() {
    if (resources == null) {
      resources = new ArrayList<>();
    }
    return resources;
  }

  public void setResources(List<Resource> resources) {
    this.resources = resources;
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
