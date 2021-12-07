package task19;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String args[]) throws IOException {

        ServerSocket servSoc = new ServerSocket(5000);
        Socket socket = servSoc.accept();
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String message = dataInputStream.readUTF();

        System.out.println(message); // для проверки!

        servSoc.close();
        socket.close();
    }
}
