import java.io.*;
import java.net.*;
import java.util.*;

class ServerSetup implements User {

    private ChatFrame serverChatFrame;
    private InputStream input;
    private OutputStream output;
    private Socket socket;
    private ServerSocket serverSocket;

    public ServerSetup() {
        serverChatFrame = new ChatFrame(this, "Server", "Client", false);
    }

    public void run() throws IOException {
        serverSocket = new ServerSocket(9999);
        System.out.println("SERVER:- Started listening to 9999");

        serverChatFrame.setActive(false);
        System.out.println("SERVER:- Waiting for client");
        socket = serverSocket.accept();

        input = socket.getInputStream();
        output = socket.getOutputStream();

        System.out.println("SERVER:- Connected to Client!");
        serverChatFrame.resetChat();
        serverChatFrame.setActive(true);
    }

    @Override
    public void sendMessage() throws IOException {
        Scanner sc = new Scanner(System.in);
        String send = serverChatFrame.getMessage();
        if (send != null && !send.equals("")) {
            output.write(send.getBytes());
            serverChatFrame.addMessage("YOU", send);
            System.out.println("SERVER:- Message sent to client: " + send);
        }
    }

    @Override
    public int receiveMessage() throws IOException {
        byte request[] = new byte[1000];
        int status = input.read(request);
        String received = new String(request).trim();
        if (received != null && !received.equals("")) {
            serverChatFrame.addMessage("CLIENT", received);
            System.out.println("SERVER:- Received message from client: " + received);
        }
        return status;
    }

    public void reset() throws IOException {
        serverChatFrame.setActive(false); 
        serverSocket.close();  
        this.run(); 
    }
}

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSetup server = new ServerSetup();
        server.run();

        while (true) {
            int status = server.receiveMessage();
            if (status == -1) {
                server.reset();
            }
        }
    }
}
