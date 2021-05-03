package esieaa.jmusic.business;

import esieaa.jmusic.FileIO.FileIO;
import esieaa.jmusic.main.PojoContext;
import esieaa.jmusic.socket.SocketConnection;
import esieaa.jmusic.util.writer.AlbumWriter;
import esieaa.jmusic.util.writer.ElementWriter;
import esieaa.jmusic.util.writer.PlaylistWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveCmd extends AbstractCommand {
  public SaveCmd() {
    super(true);
  }

  @Override protected String greetingMessage() {
    return "Vous �tes dans l'utilitaire interactif de sauvegarde";

  }

  @Override
  public void executeInternal() throws IOException {
    ElementWriter elementWriter = new ElementWriter();
    AlbumWriter albumWriter = new AlbumWriter();
    PlaylistWriter playlistWriter = new PlaylistWriter();
    getFromFile();
    SocketConnection.sendXML();
    elementWriter.write(PojoContext.getInstance().getElements());
    System.out.println("sauvegarde elements effectu�e");
    albumWriter.write(PojoContext.getInstance().getAlbums());
    System.out.println("sauvegarde albums effectu�e");
    playlistWriter.write(PojoContext.getInstance().getPlayLists());
    System.out.println("sauvegarde playlist effectu�e");

  }
public void getFromFile() throws IOException {
  Path fileName = Path.of(FileIO.albumPath);
  SocketConnection.albumXML = Files.readString(fileName);

  Path fileName1 = Path.of(FileIO.playlistPath);
  SocketConnection.plalistXML = Files.readString(fileName1);

  Path fileName2 = Path.of(FileIO.resourcePath);
  SocketConnection.resouceXML = Files.readString(fileName2);
  System.out.println(SocketConnection.resouceXML);
}
  @Override public String alias() {
    return "s";
  }
}
