package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DefaultSocketClient extends Thread {

  /**************************************************************************/
  // DATA FIELDS
  /**************************************************************************/
  private ObjectOutputStream out;

  private ObjectInputStream in;
  private Socket clientSocket;
  private BuildCarModelOptions protocol;
  private boolean DEBUG = false;

  /**************************************************************************/
  // CONSTRUCTORS
  /**************************************************************************/
  public DefaultSocketClient(Socket clientSocket) {
    this.clientSocket = clientSocket;
  }

  /**************************************************************************/
  // INSTANCE METHODS
  /**************************************************************************/
  public void handleConnection(Socket sock) {
    if (DEBUG) System.out.println("Creating server object streams ... ");
    try {
      out = new ObjectOutputStream(sock.getOutputStream());
      in = new ObjectInputStream(sock.getInputStream());
    } catch (IOException e) {
      System.err.println("Error creating server object streams ... ");
      System.exit(1);
    }

    protocol = new BuildCarModelOptions();
    String menu =
        "\n--------------------- SELECT MAIN MENU OPTION ---------------------"
            + "\nEnter 1 to upload a new Automobile"
            + "\nEnter 2 to configure an Automobile"
            + "\nEnter 0 to terminate connection"
            + "\n-------------------------------------------------------------------";

    try {
      do {
        if (DEBUG) System.out.println("Sending client interaction choices ... ");
        int request = -1;
        sendOutput(menu); // send menu to client
        if (DEBUG) System.out.println("Reading client request ... ");

        try {
          request = Integer.parseInt(in.readObject().toString());
        } catch (Exception e) {
          request = -1;
        }
        if (request == 0) break;

        if (DEBUG) System.out.println("Sending client request follow-up ... ");

        sendOutput(
            protocol.setRequest(request)); // returns String instructions from setRequest method

        if (request >= 1 && request <= 2) handleInput(request);

      } while (in.readObject() != null);

      if (DEBUG)
        System.out.println(
            "Closing server input stream for client " + sock.getInetAddress() + " ... ");
      in.close();
    } catch (IOException e) {
      System.err.println("Error handling client connection ... ");
      System.exit(1);
    } catch (ClassNotFoundException e) {
      System.err.println("Error in uploaded object, object may be corrupted ... ");
      System.exit(1);
    }
  }

  public void handleInput(int request) {
    Object fromClient = null;
    Object toClient = null;

    try {
      switch (request) {
        case 1: // Client requests to upload auto
          if (DEBUG) System.out.println("Waiting for client to upload file ... ");
          fromClient = in.readObject(); // send filename or props to server
          toClient = protocol.processRequest(fromClient); // build auto
          sendOutput(toClient); // return String confirmation to client
          break;

        case 2: // Client requests to configure existing auto
          System.out.print("Enter full car name, for example \"BMW i7 2018\": ");
          if (DEBUG) System.out.println("Waiting for client to select Automobile ... ");

          fromClient = in.readObject();
          toClient = protocol.processRequest(fromClient);
          sendOutput(toClient);
          if (DEBUG) System.out.println("Sending Automobile object to client ... ");

          fromClient = in.readObject();
          if (DEBUG) System.out.println("Waiting for client to return configured Automobile ... ");
          toClient = protocol.processRequest(fromClient);
          sendOutput(toClient);
          break;

        default:
          ; // Invalid choices
      }
    } catch (ClassNotFoundException e) {
      System.err.println("Error in uploaded object, file may be corrupted ... ");
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Error in retrieving object from client ... ");
      System.exit(1);
    }
  }

  @Override
  public void run() {
    handleConnection(clientSocket);

    // Close client output stream
    if (DEBUG)
      System.out.println(
          "Closing server output stream for client " + clientSocket.getInetAddress() + " ... ");
    try {
      out.close();
    } catch (IOException e) {
      System.err.println(
          "Error closing server output stream for client "
              + clientSocket.getInetAddress()
              + " ... ");
    }

    // Close client socket
    if (DEBUG)
      System.out.println("Closing client socket " + clientSocket.getInetAddress() + " ... ");
    try {
      clientSocket.close();
    } catch (IOException e) {
      System.err.println("Error closing client socket " + clientSocket.getInetAddress() + " ... ");
    }
  }

  public void sendOutput(Object obj) {
    try {
      out.writeObject(obj);
    } catch (IOException e) {
      System.err.println("Error returning output to client ... ");
      System.exit(1);
    }
  }
}
