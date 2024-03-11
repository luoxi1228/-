package UDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client_1 {
    public static void main(String[] args) throws IOException {
        System.out.println("-----客户端启动-----");

        //创建客户端
        DatagramSocket sockt=new DatagramSocket(7777);

        Scanner sc=new Scanner(System.in);

        while (true){
            System.out.println("请说：");
            String msg=sc.nextLine();
            if(msg.equals("exit")){
                System.out.println("成功退出！");
                sockt.close();
                break;
            }
            //创建数据包对象
            byte[] buffer=msg.getBytes(); //64kb
            DatagramPacket packet=new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(),6666);
            sockt.send(packet);
        }
    }
}
