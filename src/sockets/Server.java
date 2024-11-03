package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6666)) {
            System.out.println("Server is listening on port 6666");
            Socket socket = serverSocket.accept();
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String message = reader.readLine();
            System.out.println("Received: " + message);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
