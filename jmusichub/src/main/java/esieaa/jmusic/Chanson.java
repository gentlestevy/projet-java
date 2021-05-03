package esieaa.jmusic;

public class Chanson extends Resource {
  private String artiste;

  private Genre genre;

  private String chemin;


  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public Chanson() {
    this.type = ResourceType.CHANSON;
  }

  public String getArtiste() {
    return artiste;
  }

  public void setArtiste(String artiste) {
    this.artiste = artiste;
  }

}
