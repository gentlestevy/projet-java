package esieaa.jmusic.business;

import esieaa.jmusic.main.PojoContext;
import esieaa.jmusic.util.ConversionUtils;

public class ListAlbumDateSortie extends AbstractCommand {

  public ListAlbumDateSortie() {
    super(false);
  }

  @Override protected String greetingMessage() {
    return null;
  }

  @Override public void executeInternal() {
    PojoContext.getInstance().getAlbums().stream().sorted((o1, o2) -> o1.getDateDeSortie().after(o2.getDateDeSortie()) ? 1 : -1)
        .forEach(a -> {
          System.out.println(String.format("[ALBUM] titre: %1s - date de sortie: %2s", a.getTitre(), ConversionUtils.formatDate(a.getDateDeSortie())));
        });
    ;
  }

  @Override public String alias() {
    return "lad";
  }
}
