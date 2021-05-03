import java.nio.charset.StandardCharsets;

public class Byte {

   static byte[] stringToByte(String s){
       byte[] bytes = s.getBytes();
       return bytes;
   }
  static String byteToString(byte[] bytes){
        String s = new String(bytes, StandardCharsets.UTF_8);
        return s;
    }
}
