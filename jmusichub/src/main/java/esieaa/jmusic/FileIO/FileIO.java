package esieaa.jmusic.FileIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO
{
    public static final String albumPath = "C:\\jmusichub\\file\\albums.xml";
    public static final String resourcePath = "C:\\jmusichub\\file\\elements.xml";
    public static final String playlistPath = "C:\\jmusichub\\file\\playlists.xml";

    public static void WriteFile(String s,String path){
        File fold=new File(path);
        fold.delete();
        File fnew=new File(path);
        try {
            FileWriter f2 = new FileWriter(fnew, false);
            f2.write(s);
            f2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
