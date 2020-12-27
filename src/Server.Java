import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        ChatFrame serverChatFrame = new ChatFrame("Server", "Client", false);
        System.out.println("Started listening to 9999");

        while (true) {
            serverChatFrame.setActive(false);
            System.out.println("Waiting for client");
            Socket socket = serverSocket.accept();

            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            System.out.println("Connected to Client!");
            serverChatFrame.setActive(true);

            byte request[] = new byte[100];
            input.read(request);
            String received = new String(request).trim();

            System.out.println("Received message from client: " + received);

            // output.write();

            System.out.println("Message sent to client: ");
            socket.close();
        }

    }
}
