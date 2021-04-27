import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
    private static final String SERVER_NAME = "localhost";
    private static final int SERVER_PORT = 9090;

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket(SERVER_NAME, SERVER_PORT);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        try {



            while (true) {
                String line = "";
                StringBuilder serverRes = new StringBuilder();
                do {
                    serverRes.append(line).append("\n");
                    line = in.readLine();


                } while (!line.equals("") );
                System.out.println(serverRes);
                System.out.println(">");
                String command = keyboard.readLine();
                out.println(command);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            out.close();
            in.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
