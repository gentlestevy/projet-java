package esieaa.jmusic.util.writer;

import esieaa.jmusic.Resource;
import esieaa.jmusic.main.AppMain;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

public class ElementWriter extends AbstractWriter<List<Resource>> {
  @Override protected String pathToFile() {
    return AppMain.BASE_DIR + "\\elements.xml";
  }

  @Override
  protected void internalWrite(List<Resource> input) throws ParserConfigurationException {
    init();
    Element root = document.createElement("elements");
    if (input != null && !input.isEmpty()) {
      for (Resource r : input) {
        root.appendChild(this.writeResource(r));
      }
    }
    document.appendChild(root);
  }
}
