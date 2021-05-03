package fileTransfert;

import XMLmanager.XMLmanager;
import org.w3c.dom.Document;

import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileTransaction {

    public static Document receiveDoc(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String xmlString = in.readLine();
        Document doc = XMLmanager.convertStringToXMLDocument(xmlString);
        System.out.println(xmlString);
        return doc;
    }

    public static void sendDoc(Socket socket, Document doc) throws IOException, TransformerException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String xmlString = XMLmanager.getStringFromDocument(doc);

        out.println(xmlString);


    }

}
