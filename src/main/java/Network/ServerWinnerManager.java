package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWinnerManager {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started");

            Socket client1 = serverSocket.accept();
            System.out.println("Client 1 connected");
            DataInputStream inFromClient1 = new DataInputStream(client1.getInputStream());
            DataOutputStream outToClient1 = new DataOutputStream(client1.getOutputStream());

            Socket client2 = serverSocket.accept();
            System.out.println("Client 2 connected");
            DataInputStream inFromClient2 = new DataInputStream(client2.getInputStream());
            DataOutputStream outToClient2 = new DataOutputStream(client2.getOutputStream());

            int num1Client1 = inFromClient1.readInt();
            int num1Client2 = inFromClient2.readInt();


            if (num1Client1 > num1Client2) {
                outToClient1.writeUTF("WIN");
                outToClient2.writeUTF("LOSE");
            } else {
                outToClient2.writeUTF("WIN");
                outToClient1.writeUTF("LOSE");
            }

            client1.close();
            client2.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

