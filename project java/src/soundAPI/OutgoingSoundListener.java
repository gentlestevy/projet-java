package soundAPI;





import javax.sound.midi.spi.SoundbankReader;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.*;
import java.net.Socket;


public class OutgoingSoundListener{
    public static void runAudio(Socket s, String path) throws IOException {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(FileReader)
            AudioInputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



