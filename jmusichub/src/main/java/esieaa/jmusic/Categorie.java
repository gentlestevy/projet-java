package esieaa.jmusic;

public enum Categorie {
  JEUNESSE("Jeunesse"), ROMAN("Roman"), THEATRE("Théâtre"), DISCOURS("Discours"), DOCUMENTAIRE("Documentaire");

  private String value;

  Categorie(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
