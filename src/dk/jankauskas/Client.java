package dk.jankauskas;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class Client {

    private String hostname;
    private int port;
    Socket socketClient;

    public Client(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    public void connect() throws UnknownHostException, IOException {
        System.out.println("Attempting to connect to "+hostname+":"+port);
        socketClient = new Socket(hostname,port);
        System.out.println("Connection Established");
    }

    public void readResponse() throws IOException{
        String userInput;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

        System.out.println("Response from server:");
        while ((userInput = stdIn.readLine()) != null) {
            System.out.println(userInput);
        }
    }

    public void sendRequest(String message) {
        try {
            PrintWriter out = new PrintWriter(socketClient.getOutputStream());
            out.println("zinute");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
