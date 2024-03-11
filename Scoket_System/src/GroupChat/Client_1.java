package GroupChat;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client_1 {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        System.out.println("-----客户端启动！-----");
        //创建Socket对象，并请求与服务端的连接
        Socket socket=new Socket("127.0.0.1",8888);//127.0.0.1是本机ip

        //创建一个独立的线程，从服务端接受其他客户端发送的消息
        new ClientReaderThread(socket).start();

        //从Socket通道获得字节输出流
        OutputStream os=socket.getOutputStream();
        //将字节输出流转换为数据输出流
        DataOutputStream dos=new DataOutputStream(os);
        //写数据
        while (true) {
            //System.out.print(socket.getRemoteSocketAddress()+"：");
            String msg=sc.nextLine();
            if(msg.equals("exit")){
                System.out.println("退出聊天！");
                dos.close();
                socket.close();
                break;
            }
            dos.writeUTF(msg);
            dos.flush();
        }
    }
}
