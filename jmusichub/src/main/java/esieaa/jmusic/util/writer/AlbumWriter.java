package esieaa.jmusic.util.writer;

import esieaa.jmusic.Album;
import esieaa.jmusic.Chanson;
import esieaa.jmusic.main.AppMain;
import esieaa.jmusic.util.ConversionUtils;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

public class AlbumWriter extends AbstractWriter<List<Album>> {
  @Override protected String pathToFile() {
    return AppMain.BASE_DIR + "\\albums.xml";
  }

  @Override
  protected void internalWrite(List<Album> input) throws ParserConfigurationException {
    init();
    Element root = document.createElement("albums");
    if (input != null && !input.isEmpty()) {
      for (Album a : input) {
        Element album = document.createElement("album");
        album.appendChild(this.createElementWithTxt("titre", a.getTitre()));
        album.appendChild(this.createElementWithTxt("id", a.getId()));
        album.appendChild(this.createElementWithTxt("artiste", a.getArtiste()));
        album.appendChild(this.createElementWithTxt("duree", String.valueOf(a.getDuree())));
        album.appendChild(this.createElementWithTxt("dateDeSortie", a.getDateDeSortie().toString()));
        Element chansons = document.createElement("chansons");
        if (a.getChansons() != null && !a.getChansons().isEmpty()) {
          for (Chanson c : a.getChansons()) {
            chansons.appendChild(this.writeResource(c));
          }
        }
        album.appendChild(chansons);
        root.appendChild(album);
      }
    }
    document.appendChild(root);
  }
}
