package Network;

import java.io.*;
import java.net.Socket;

public class OneElseSender {

    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 1234);
            System.out.println("Connected to server");

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());

            int num1 = 10;

            outToServer.writeInt(num1);

            String result = inFromServer.readUTF();
            System.out.println("Result: " + result);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
