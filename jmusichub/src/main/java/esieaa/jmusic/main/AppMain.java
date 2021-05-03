package esieaa.jmusic.main;

import esieaa.jmusic.business.AjouterPlaylistCmd;
import esieaa.jmusic.business.AlbumCmd;
import esieaa.jmusic.business.ChansonCmd;
import esieaa.jmusic.business.ChansonToAlbumCmd;
import esieaa.jmusic.business.HelpCmd;
import esieaa.jmusic.business.ICommand;
import esieaa.jmusic.business.ListAlbumDateSortie;
import esieaa.jmusic.business.ListerChansonAlbumCmd;
import esieaa.jmusic.business.ListerLivreAudioParAuteurCmd;
import esieaa.jmusic.business.LivreAudioCmd;
import esieaa.jmusic.business.PlaylistParNomCmd;
import esieaa.jmusic.business.SaveCmd;
import esieaa.jmusic.business.SupprimerPlaylistCmd;
import esieaa.jmusic.exception.ConfigNonTrouveeException;
import esieaa.jmusic.socket.SocketConnection;
import esieaa.jmusic.util.AlbumDefaultHandler;
import esieaa.jmusic.util.ElementDefaultHandler;
import esieaa.jmusic.util.ParserUtils;
import esieaa.jmusic.util.PlaylistDefaultHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppMain {

  public static final String BASE_DIR = "C:\\jmusichub\\file";

  private static List<ICommand> registerCommands() {
    List<ICommand> commands = new ArrayList<>();
    commands.add(new ChansonCmd());
    commands.add(new HelpCmd());
    commands.add(new ListerChansonAlbumCmd());
    commands.add(new LivreAudioCmd());
    commands.add(new AlbumCmd());
    commands.add(new ChansonToAlbumCmd());
    commands.add(new AjouterPlaylistCmd());
    commands.add(new SupprimerPlaylistCmd());
    commands.add(new SaveCmd());
    commands.add(new ListAlbumDateSortie());
    commands.add(new ListerLivreAudioParAuteurCmd());
    commands.add(new PlaylistParNomCmd());
    return commands;
  }

  public static void main(String[] args) throws IOException {

    File dir = new File(BASE_DIR);
    if (!dir.exists()) {
      System.err.println("Veuillez vous assurer que le dossier de sources a �t� plac� � la racine C: avec le nom jmusichub");
      throw new ConfigNonTrouveeException("LE REPERTOIRE " + BASE_DIR + " N'EXISTE PAS ! ");
    }

    File file1 = new File(BASE_DIR + "\\albums.xml");
    File file2 = new File(BASE_DIR + "\\playlists.xml");
    File file3 = new File(BASE_DIR + "\\elements.xml");

    if (!file1.exists() || !file2.exists() || !file3.exists()) {
      throw new ConfigNonTrouveeException("un ou plusieurs des fichiers [albums.xml, playlists.xml, elements.xml] n'a(ont) pas �t� trouv�(s) dans le r�pertoire " + BASE_DIR);

    }
    SocketConnection socketConnection = new SocketConnection();
    socketConnection.accountConnection();

    // register and get available commands
    List<ICommand> commands = registerCommands();

    // global context (as singleton, because will be accessed from each command)
    PojoContext pojoContext = PojoContext.getInstance();
    try {
      AlbumDefaultHandler albumDefaultHandler = new AlbumDefaultHandler();
      ParserUtils.parse(file1, albumDefaultHandler);
      pojoContext.setAlbums(albumDefaultHandler.content());

      PlaylistDefaultHandler playlistDefaultHandler = new PlaylistDefaultHandler();
      ParserUtils.parse(file2, playlistDefaultHandler);
      pojoContext.setPlayLists(playlistDefaultHandler.content());

      ElementDefaultHandler elementDefaultHandler = new ElementDefaultHandler();
      ParserUtils.parse(file3, elementDefaultHandler);
      PojoContext.getInstance().setElements(elementDefaultHandler.content());

      Scanner sc = new Scanner(System.in);
      String commandStr = "";
      System.out.println("Bonjour, je suis jarvis votre utilitaire de commande");
      do {
        System.out.println("j'attends votre commande... (exit pour quitter)");
        commandStr = sc.next();
        ICommand command = findCommandByAlias(commandStr, commands);
        if (command != null) {
          command.execute();
        }
        else if (!commandStr.equals("exit")) {
          System.out.println("Commande (" + commandStr + ") inconnue - taper h pour voir l'aide");
        }

      } while (!commandStr.equalsIgnoreCase("exit"));

      System.out.println("Au revoir !");

    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static ICommand findCommandByAlias(String c, List<ICommand> cmds) {
    return cmds.stream().filter(cmd -> cmd.alias().equals(c)).findFirst().orElse(null);
  }
}
