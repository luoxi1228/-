package ChatSystem.Client;

import ChatSystem.Message;
import ChatSystem.User;
import ChatSystem.messageType;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class UserClient {
    private User u = new User();

    //根据userID和password到服务器验证该用户是否合法
    public boolean checkUser(String userId, String password) {
        boolean res = false;
        u.setUserId(userId);
        u.setPassword(password);

        try {
            //连接到服务器，发送u对象
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            //得到ObjectOutputStream对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //发送user对象
            oos.writeObject(u);
            //读取服务端回复的message对象
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message msg = (Message) ois.readObject();

            if (msg.getMesType().equals(messageType.MESSAGE_LOGIN_SUCCEED)) {//可以登录成功

                //创建一个与服务器保持通讯的线程
                ClientThread ct = new ClientThread(socket);
                //启动线程
                ct.start();
                //为了客户端的扩展，我们将一个客户端的多个线程放入集合管理
                ManageClientThread.addClientThread(userId, ct);
                res = true;

            } else {//登录失败，不能启动线程,关闭socket
                socket.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    //向服务器请求在线的用户数量
    public void olineList() {
        //向服务器发送一个message，类型为MESSAGE_GET_ONLINE
        Message msg = new Message();
        msg.setMesType(messageType.MESSAGE_GET_ONLINE);
        msg.setSender(u.getUserId());
        try {
            //从管理线程的集合中，得到该userId所对应的线程
            ClientThread clientThread = ManageClientThread.getClientThread(u.getUserId());
            //根据线程得到socket
            Socket socket = clientThread.getSocket();
            //从socket中得到输出流，发送message
            ObjectOutputStream oss = new ObjectOutputStream(socket.getOutputStream());
            oss.writeObject(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //退出登录
    public void exit() {
        Message msg = new Message();
        msg.setMesType(messageType.MESSAGE_CLIENT_EXIT);
        msg.setSender(u.getUserId());//指定发送该消息的客户端

        try {
            //根据userId得到线程
            ClientThread clientThread = ManageClientThread.getClientThread(u.getUserId());
            //根据线程得到socke
            Socket socket = clientThread.getSocket();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(msg);
            System.out.println(" 退出系统！！！");
            System.exit(0);//结束进程
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
