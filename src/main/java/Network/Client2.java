package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client2 {

  private static final String SERVER_ADDRESS = "localhost";
  private static final int SERVER_PORT = 12345;

  public static void main(String[] args) {
    try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

      System.out.println("Connected to server");

      String serverResponse;
      while ((serverResponse = in.readLine()) != null) {
        System.out.println("Server response: " + serverResponse);
        break;
      }

    } catch (IOException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

}
