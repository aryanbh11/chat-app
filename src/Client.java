import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        ChatFrame clientChatFrame = new ChatFrame("Client", "Server", true);
        Socket socket = new Socket("127.0.0.1", 9999);
        System.out.println("Connected to Server!");

        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // output.write();

        byte[] response = new byte[100];
        input.read(response);
        String received = new String(response).trim();
        System.out.println("Received message from server: " + received);
        
        socket.close();
    }

}