package GroupChat;

import GroupChat.ServerReaderThread;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<Socket>onLineSocket=new ArrayList<>();//存放在线socket的集合
    public static void main(String[] args) throws Exception {
        System.out.println("-----服务端启动！-----");
        //创建ServerSocket对象，同时为为其分配端口
        ServerSocket serverSocket=new ServerSocket(8888);
        while (true) {
            //使用serverSocket创建socket对象，并调用accept方法,等待客户端连接请求
            Socket socket=serverSocket.accept();
            onLineSocket.add(socket);
            System.out.println(socket.getInetAddress()+"上线！");
            new ServerReaderThread(socket).start();
        }

    }
}
