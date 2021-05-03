package esieaa.jmusic.business;

import esieaa.jmusic.LivreAudio;
import esieaa.jmusic.main.PojoContext;

import java.util.Comparator;

public class ListerLivreAudioParAuteurCmd extends AbstractCommand {
  public ListerLivreAudioParAuteurCmd() {
    super(false);
  }

  @Override protected String greetingMessage() {
    return null;
  }

  @Override public void executeInternal() {
    PojoContext.getInstance().getLivresAudio().stream()
        .sorted((Comparator.comparing(x -> ((LivreAudio) x).getAuteur()))).forEach(item -> {
      System.out.println(String.format("[LIVRE AUDIO] titre: %1s - id: %2s - auteur: %3s",
          item.getTitre(), item.getId(), ((LivreAudio) item).getAuteur()));
    });
  }

  @Override public String alias() {
    return "lla";
  }
}
