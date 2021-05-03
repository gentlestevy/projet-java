package esieaa.jmusic;

public class LivreAudio extends Resource {
  private String auteur;

  private Langues langue;

  private Categorie categorie;

  public LivreAudio() {
    this.type = ResourceType.LIVRE_AUDIO;
  }

  public String getAuteur() {
    return auteur;
  }

  public void setAuteur(String auteur) {
    this.auteur = auteur;
  }

  public Langues getLangue() {
    return langue;
  }

  public void setLangue(Langues langue) {
    this.langue = langue;
  }

  public Categorie getCategorie() {
    return categorie;
  }

  public void setCategorie(Categorie categorie) {
    this.categorie = categorie;
  }
}
