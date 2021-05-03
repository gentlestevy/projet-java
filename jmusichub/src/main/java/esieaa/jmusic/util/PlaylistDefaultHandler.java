package esieaa.jmusic.util;

import esieaa.jmusic.Chanson;
import esieaa.jmusic.LivreAudio;
import esieaa.jmusic.PlayList;
import esieaa.jmusic.Resource;

import java.util.ArrayList;

public class PlaylistDefaultHandler extends GenericDefaultHandler<ArrayList<PlayList>> {
  @Override protected ArrayList<PlayList> instantiate() {
    return new ArrayList<>();
  }

  @Override protected String[] collectionFields() {
    return new String[] { "playlists", "resources" };
  }

  @Override protected Object map(String qName) {
    if (qName.equals("playlist")) {
      return new PlayList();
    }
    else if (qName.equals("chanson")) {
      return (Resource) new Chanson();
    }
    else if (qName.equals("livreAudio")) {
      return (Resource) new LivreAudio();
    }
    return null;
  }
}
