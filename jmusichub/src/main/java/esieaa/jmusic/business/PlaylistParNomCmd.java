package esieaa.jmusic.business;

import esieaa.jmusic.main.PojoContext;

import java.util.Comparator;

public class PlaylistParNomCmd extends AbstractCommand {
  public PlaylistParNomCmd() {
    super(false);
  }

  @Override protected String greetingMessage() {
    return null;
  }

  @Override public void executeInternal() {
    PojoContext.getInstance().getPlayLists().stream().sorted(Comparator.comparing(x -> x.getNom())).forEach(
        p -> System.out.println(String.format("[Playlist] nom: %1s - id: %2s", p.getNom(), p.getId()))
    );
  }

  @Override public String alias() {
    return "lpn";
  }
}
