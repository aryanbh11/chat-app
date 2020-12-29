import java.io.IOException;

public interface User {
    void sendMessage() throws IOException;
    int receiveMessage() throws IOException;
}