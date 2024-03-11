package UDP;

import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("-----服务端启动-----");
        //创建服务端对象
        DatagramSocket socket=new DatagramSocket(6666);
        //创建数据包对象
        byte[] buffer=new byte[1024*64];
        DatagramPacket packet=new DatagramPacket(buffer, buffer.length);

        while (true){
            //接受数据
            socket.receive(packet);
            String name="用户";
            int len= packet.getLength();//获得发送的数据的大小
            int port=packet.getPort();//获得发送段的端口号
            if(port==7777){
                name="张三";
            } else if (port==8888) {
                name="王五";
            }

            String rs=new String(buffer,0,len);//转换为字符串
            System.out.println(name+":"+rs);
            //System.out.println(port);
            //System.out.println("------------------");
        }
    }
}
