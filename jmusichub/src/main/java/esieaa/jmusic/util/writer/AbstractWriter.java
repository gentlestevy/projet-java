package esieaa.jmusic.util.writer;

import esieaa.jmusic.Chanson;
import esieaa.jmusic.LivreAudio;
import esieaa.jmusic.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public abstract class AbstractWriter<T> {
  protected Document document;

  protected void init() throws ParserConfigurationException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    this.document = builder.newDocument();
  }

  protected abstract String pathToFile();

  public void write(T input) {
    try {
      internalWrite(input);
      DOMSource source = new DOMSource(document);

      File myFile = new File(pathToFile());

      StreamResult file = new StreamResult(myFile);

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transf = transformerFactory.newTransformer();

      transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      transf.setOutputProperty(OutputKeys.INDENT, "yes");
      transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      transf.transform(source, file);
    } catch (ParserConfigurationException e) {
      // do nothing
    } catch (TransformerConfigurationException e) {
      e.printStackTrace();
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }

  protected Element createElementWithTxt(String tag, String content) {
    Element el = document.createElement(tag);
    el.appendChild(document.createTextNode(content));
    return el;
  }

  protected Element writeResource(Resource resource) {
    boolean isChanson = resource instanceof Chanson;
    Element res;
    if (isChanson) {
      res = document.createElement("chanson");
    }
    else {
      res = document.createElement("livreAudio");
    }
    res.appendChild(this.createElementWithTxt("titre", resource.getTitre()));
    res.appendChild(this.createElementWithTxt("id", resource.getId()));
    res.appendChild(this.createElementWithTxt("duree", String.valueOf(resource.getDuree())));
    if (isChanson) {
      Chanson chanson = (Chanson) resource;
      res.appendChild(this.createElementWithTxt("artiste", chanson.getArtiste()));
      res.appendChild(this.createElementWithTxt("genre", handleEnumNull(chanson.getGenre())));

    }
    else {
      LivreAudio livreAudio = (LivreAudio) resource;
      res.appendChild(this.createElementWithTxt("auteur", livreAudio.getAuteur()));
      res.appendChild(this.createElementWithTxt("categorie", handleEnumNull(livreAudio.getCategorie())));
      res.appendChild(this.createElementWithTxt("langue", handleEnumNull(livreAudio.getLangue())));
    }
    return res;
  }

  private String handleEnumNull(Enum val) {
    if (val == null) {
      return null;
    }
    return val.toString();
  }

  protected abstract void internalWrite(T input) throws ParserConfigurationException;
}
