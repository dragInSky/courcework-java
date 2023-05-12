package Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnectionsManager {

    private static final int PORT = 12345;
    private static int connectedClients = 0;


    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                connectedClients++;
                new ClientHandler(clientSocket);

                if (connectedClients == 2) {
                    System.out.println("Both clients are connected. Sending 'Ready' message to both.");
                    sendToAllClients("Ready");
                    break;
                }

            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void sendToAllClients(String message) {
        for (ClientHandler clientHandler : ClientHandler.clientHandlers) {
            clientHandler.sendMessage(message);
        }
    }
}

