import java.io.*;
import java.net.*;
import java.util.*;

class ServerSetup implements User {

    private ChatFrame serverChatFrame;
    private InputStream input;
    private OutputStream output;

    public ServerSetup() {
        serverChatFrame = new ChatFrame(this, "Server", "Client", false);
    }

    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("SERVER:- Started listening to 9999");
        
        serverChatFrame.setActive(false);
        System.out.println("SERVER:- Waiting for client");
        Socket socket = serverSocket.accept();

        input = socket.getInputStream();
        output = socket.getOutputStream();

        System.out.println("SERVER:- Connected to Client!");
        serverChatFrame.setActive(true);
    }

    public void sendMessage() throws IOException {
        Scanner sc = new Scanner(System.in);
        String send = serverChatFrame.getMessage();
        if (send != null && !send.equals("")) {
            output.write(send.getBytes());
            System.out.println("SERVER:- Message sent to client: " + send);
        }
    }

    @Override
    public void receiveMessage() throws IOException {
        byte request[] = new byte[100];
        input.read(request);
        String received = new String(request).trim();
        if (received != null && !received.equals("")) {
            System.out.println("SERVER:- Received message from client: " + received);
        }
    }
}

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSetup server = new ServerSetup();
        server.run();

        while (true) {
            server.receiveMessage();
        }
    }
}