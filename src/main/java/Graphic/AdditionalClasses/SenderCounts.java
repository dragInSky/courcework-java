package Graphic.AdditionalClasses;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SenderCounts {
    public String getResult() {
        return result;
    }

    private String result = "LOSE";

    public SenderCounts sendCountToServer(int count) {
        try {
            Socket clientSocket = new Socket("localhost", 1234);
            System.out.println("Connected to server");

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());


            outToServer.writeInt(count);

            String result = inFromServer.readUTF();
            System.out.println("Result: " + result);
            this.result = result;
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

}
