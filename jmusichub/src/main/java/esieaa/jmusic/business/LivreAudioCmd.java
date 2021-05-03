package esieaa.jmusic.business;

import esieaa.jmusic.Categorie;
import esieaa.jmusic.Langues;
import esieaa.jmusic.LivreAudio;
import esieaa.jmusic.ResourceType;
import esieaa.jmusic.main.PojoContext;
import esieaa.jmusic.util.ConversionUtils;

import java.util.Arrays;
import java.util.UUID;

public class LivreAudioCmd extends AbstractCommand {
  public LivreAudioCmd() {
    super(true);
  }

  @Override protected String greetingMessage() {
    return "Vous �tes dans l'utilitaire interactif d'ajout d'un livre audio";
  }

  @Override public void executeInternal() {
    LivreAudio livreAudio = new LivreAudio();
    String titre = ask("Veuillez rentrer le titre du livre audio");
    String id = UUID.randomUUID().toString();
    ResourceType type = ResourceType.LIVRE_AUDIO;
    int duree = askForInteger("Veuillez entrer la dur�e en secondes");
    String auteur = ask("Veuillez entrer l'auteur du livre audio");
    String langue = askWithAuthorizedValueList("Veuillez entrer la langue du livre audio",
        Arrays.asList("Fran�ais", "Anglais", "Italien", "Espagnol", "Allemand"));
    String categorie = askWithAuthorizedValueList("Veuillez entrer la cat�gorie du livre audio",
        Arrays.asList("Jeunesse", "Roman", "Th��tre", "Discours", "Documentaire"));

    livreAudio.setTitre(titre);
    livreAudio.setId(id);
    livreAudio.setDuree(duree);
    livreAudio.setLangue((Langues) ConversionUtils.toEnumFromVal(Langues.values(), langue));
    livreAudio.setAuteur(auteur);
    livreAudio.setCategorie((Categorie) ConversionUtils.toEnumFromVal(Categorie.values(), categorie));
    PojoContext.getInstance().addLivreAudio(livreAudio);
    System.out.println("livre audio ajout� avec succ�s !");
  }

  @Override public String alias() {
    return "l";
  }
}
