package esieaa.jmusic.util.writer;

import esieaa.jmusic.PlayList;
import esieaa.jmusic.Resource;
import esieaa.jmusic.main.AppMain;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

public class PlaylistWriter extends AbstractWriter<List<PlayList>> {

  @Override protected String pathToFile() {
    return AppMain.BASE_DIR + "\\playlists.xml";
  }

  @Override
  protected void internalWrite(List<PlayList> input) throws ParserConfigurationException {
    init();
    Element root = document.createElement("playlists");
    if (input != null && !input.isEmpty()) {
      for (PlayList p : input) {
        Element playlist = document.createElement("playlist");
        playlist.appendChild(this.createElementWithTxt("nom", p.getNom()));
        playlist.appendChild(this.createElementWithTxt("id", p.getId()));
        Element resources = document.createElement("resources");
        if (p.getResources() != null && !p.getResources().isEmpty()) {
          for (Resource r : p.getResources()) {
            resources.appendChild(this.writeResource(r));
          }
        }
        playlist.appendChild(resources);
        root.appendChild(playlist);
      }
    }
    document.appendChild(root);
  }
}
