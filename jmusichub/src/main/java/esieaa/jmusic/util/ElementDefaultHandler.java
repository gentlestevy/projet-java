package esieaa.jmusic.util;

import esieaa.jmusic.Chanson;
import esieaa.jmusic.LivreAudio;
import esieaa.jmusic.Resource;

import java.util.ArrayList;

public class ElementDefaultHandler extends GenericDefaultHandler<ArrayList<Resource>> {
  @Override protected ArrayList<Resource> instantiate() {
    return new ArrayList<>();
  }

  @Override protected String[] collectionFields() {
    return new String[] { "elements" };
  }

  @Override protected Object map(String qName) {
    if (qName.equals("chanson")) {
      return (Resource) new Chanson();
    }
    else if (qName.equals("livreAudio")) {
      return (Resource) new LivreAudio();
    }
    return null;
  }
}
