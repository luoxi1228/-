package TCP;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("-----服务端启动-----");
        //创建ServerSocket对象，同时为为其分配端口
        ServerSocket serverSocket=new ServerSocket(8888);

        //使用serverSocket创建socket对象，并调用accept方法等待连接请求
        while (true) {
            Socket socket=serverSocket.accept();
            System.out.println(socket.getRemoteSocketAddress()+"上线");
            new ServerReaderThread(socket).start();
        }

    }
}
