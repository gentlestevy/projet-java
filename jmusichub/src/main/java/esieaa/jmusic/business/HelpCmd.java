package esieaa.jmusic.business;

public class HelpCmd extends AbstractCommand {
  public HelpCmd() {
    super(false);
  }

  @Override
  protected String greetingMessage() {
    return null;
  }

  @Override public void executeInternal() {
    System.out.println("Bienvenue ! Utilitaire de gestion d'albums / chansons / livres audio");
    System.out.println("Liste des commandes disponibles");
    System.out.println("\n###COMMANDES D'AFFICHAGE###");
    System.out.println("lac: affichage des toutes les chansons d'un album");
    System.out.println("lad: affichage des titres tri�s par date de sortie (plus r�cent au plus vieux)");
    System.out.println("lla: affichage des livres audio part auteur");
    System.out.println("lpn: affichage des playlist tri�es par nom");

    System.out.println("\n###COMMANDES D'INTERRACTION###");
    System.out.println("c : ajout d'une nouvelle chanson");
    System.out.println("h: affichage de l'aide");
    System.out.println("l: ajout d'un livre audio");
    System.out.println("a: ajout d'un album");
    System.out.println("s: sauvegarde des modifications");
    System.out.println("+: ajout d'une chanson � un album");
    System.out.println("-: suppression d'une playlist");
    System.out.println("p: ajout d'une playlist");
  }

  @Override public String alias() {
    return "h";
  }
}
