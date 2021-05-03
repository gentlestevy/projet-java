package esieaa.jmusic;

public enum Langues {
  // pour éviter la problématique de charactère spéciaux dans la console français sans le "ç"
  FRANCAIS("Français"), ANGLAIS("Anglais"), ITALIEN("Italien"), ESPAGNOL("Espagnol"), ALLEMAND("Allemand");

  private String value;

  Langues(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }
}
