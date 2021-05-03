package esieaa.jmusic;

public enum Genre {
  JAZZ("Jazz"), CLASSIQUE("Classique"), HIP_HOP("Hip-hop"), ROCK("Rock"), POP("Pop"), RAP("Rap");
  private String value;

  Genre(String value) {
    this.value = value;
  }
  @Override
  public String toString() {
    return this.value;
  }
}
