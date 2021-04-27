package audioModels;

public enum Langues {
  // pour �viter la probl�matique de charact�re sp�ciaux dans la console fran�ais sans le "�"
  FRANCAIS("Fran�ais"), ANGLAIS("Anglais"), ITALIEN("Italien"), ESPAGNOL("Espagnol"), ALLEMAND("Allemand");

  private String value;

  Langues(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }
}
