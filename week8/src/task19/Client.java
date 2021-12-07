package task19;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clSock = new Socket("localhost", 5000);
        DataOutputStream dataOutputStream = new DataOutputStream(clSock.getOutputStream());
        dataOutputStream.writeUTF("Hello, world!");
        dataOutputStream.flush();
        dataOutputStream.close();

        clSock.close();
    }
}
