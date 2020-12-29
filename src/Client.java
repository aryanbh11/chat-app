import java.io.*;
import java.net.*;
import java.util.*;

class ClientSetup implements User {
    
    private OutputStream output;
    private InputStream input; 
    private ChatFrame clientChatFrame;

    public ClientSetup() {
        clientChatFrame = new ChatFrame(this, "Client", "Server", true);
    }

    public void run() throws IOException {
            Socket socket = new Socket("127.0.0.1", 9999);
            System.out.println("CLIENT:- Connected to Server!");

            input = socket.getInputStream();
            output = socket.getOutputStream();        
    }

    @Override
    public void sendMessage() throws IOException {
        Scanner sc = new Scanner(System.in);
        String send = clientChatFrame.getMessage();
        if (send != null && !send.equals("")) {
            output.write(send.getBytes());
            clientChatFrame.addMessage("YOU", send);
            System.out.println("CLIENT:- Message sent to Server: " + send);
        }
    }

    @Override
    public int receiveMessage() throws IOException {
        byte[] response = new byte[1000];
        int status = input.read(response);
        String received = new String(response).trim();

        if (received != null && !received.equals("")) {
            clientChatFrame.addMessage("SERVER", received);
            System.out.println("CLIENT:- Received message from server: " + received);
        }
        return status;
    }

}

public class Client {
    
    public static void main(String[] args) throws IOException {
        ClientSetup client = new ClientSetup();
        client.run();

        while (true) {
            int status = client.receiveMessage();
            if (status == -1) {
                System.exit(0);
            }
        }
    }
}