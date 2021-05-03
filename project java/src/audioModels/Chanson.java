package audioModels;

public class Chanson extends Resource {

  private String artiste;

  private Genre genre;

  private String albumId;

  private String playlistID;

  public String getPlaylistID() {
    return playlistID;
  }

  public void setPlaylistID(String playlistID) {
    this.playlistID = playlistID;
  }

  public String getAlbumId() {
    return albumId;
  }

  public void setAlbumId(String albumId) {
    this.albumId = albumId;
  }

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
