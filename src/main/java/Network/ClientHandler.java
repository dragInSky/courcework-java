package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler {

  static final List<ClientHandler> clientHandlers = new ArrayList<>();
  private final Socket clientSocket;
  private final BufferedReader in;
  private final PrintWriter out;

  public ClientHandler(Socket clientSocket) throws IOException {
    this.clientSocket = clientSocket;
    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    out = new PrintWriter(clientSocket.getOutputStream(), true);
    clientHandlers.add(this);
  }

  public void sendMessage(String message) {
    out.println(message);
  }
}
