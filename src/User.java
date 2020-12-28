import java.io.IOException;

public interface User {
    void sendMessage() throws IOException;
    void receiveMessage() throws IOException;
}