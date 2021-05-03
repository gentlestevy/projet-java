package audioModels;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Album {
  private List<Chanson> chansons;

  private String titre;

  private String artiste;

  private int duree;

  private LocalDate dateDeSortie;

  private String id;

  public List<Chanson> getChansons() {
    return chansons;
  }

  public void setChansons(List<Chanson> chansons) {
    this.chansons = chansons;
  }

  public String getTitre() {
    return titre;
  }

  public void setTitre(String titre) {
    this.titre = titre;
  }

  public String getArtiste() {
    return artiste;
  }

  public void setArtiste(String artiste) {
    this.artiste = artiste;
  }

  public int getDuree() {
    return duree;
  }

  public void setDuree(int duree) {
    this.duree = duree;
  }

  public LocalDate getDateDeSortie() {
    return dateDeSortie;
  }

  public void setDateDeSortie(LocalDate dateDeSortie) {
    this.dateDeSortie = dateDeSortie;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
