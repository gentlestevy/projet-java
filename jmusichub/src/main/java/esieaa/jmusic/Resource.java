package esieaa.jmusic;

public class Resource {
  protected String titre;

  protected int duree;

  protected String id;

  protected ResourceType type;

  public String getTitre() {
    return titre;
  }

  public void setTitre(String titre) {
    this.titre = titre;
  }

  public int getDuree() {
    return duree;
  }

  public void setDuree(int duree) {
    this.duree = duree;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ResourceType getType() {
    return type;
  }

  public void setType(ResourceType type) {
    this.type = type;
  }
}
