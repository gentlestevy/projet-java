package esieaa.jmusic.util;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class ParserUtils {
  private static SAXParserFactory factory;

  private static SAXParser parser;

  public static <T> void parse(File file, GenericDefaultHandler<T> handler) throws ParserConfigurationException, SAXException, IOException {
    initParserIfNeeded();
    parser.parse(file, handler);
  }

  private static void initParserIfNeeded() throws ParserConfigurationException, SAXException {
    if (factory == null) {
      factory = SAXParserFactory.newDefaultInstance();
      parser = factory.newSAXParser();
    }
  }
}
