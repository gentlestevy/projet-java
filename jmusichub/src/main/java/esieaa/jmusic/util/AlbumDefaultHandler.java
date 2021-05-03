package esieaa.jmusic.util;

import esieaa.jmusic.Album;
import esieaa.jmusic.Chanson;

import java.util.ArrayList;
import java.util.List;

/**
 * classes thats loads saved {@link esieaa.jmusic.Album} from xml file
 */
public class AlbumDefaultHandler extends GenericDefaultHandler<List<Album>> {
  // iterable section
  public static final String CHANSONS = "chansons";

  public static final String ALBUMS = "albums";

  // mapping section
  public static final String CHANSON = "CHANSON";

  public static final String ALBUM = "ALBUM";

  public AlbumDefaultHandler() {
    super();
  }

  @Override
  protected List<Album> instantiate() {
    return new ArrayList<>();
  }

  @Override
  protected String[] collectionFields() {
    return new String[] { CHANSONS, ALBUMS };
  }

  @Override
  protected Object map(String qName) {
    if (CHANSON.equals(qName.toUpperCase())) {
      return new Chanson();
    }
    else if (ALBUM.equalsIgnoreCase(qName.toUpperCase())) {
      return new Album();
    }
    return null;
  }
}
