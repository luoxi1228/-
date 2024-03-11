package GroupChat;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class ClientReaderThread extends Thread {
    private Socket socket;

    ClientReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            while (true) {
                try {
                    String msg = dis.readUTF();
                    System.out.println(socket.getInetAddress()+": "+msg);
                } catch (Exception e) {
                    //System.out.println("本地下线!");
                    dis.close();
                    socket.close();
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
