package ChatSystem.Server;

import ChatSystem.Client.ClientThread;
import ChatSystem.Client.ManageClientThread;
import ChatSystem.Message;
import ChatSystem.messageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class ServerThread extends Thread {
    private Socket socket;
    private String userId;
    public static boolean flag1 = true;//判断用户是否在线
    public ServerThread(String userId, Socket socket) {
        this.socket = socket;
        this.userId = userId;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public void run() {

        while (true) {
            try {
                System.out.println("服务端与用户" + userId + "已经连接，正在读取数据...");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) ois.readObject();
                //根据客户端发送的msg类型，执行不同的功能
                if (msg.getMesType().equals(messageType.MESSAGE_GET_ONLINE)) {//客户端向服务端请求在线用户
                    System.out.println(msg.getSender() + "请求在线用户列表");
                    String onlineUser = ManageServerThread.getOnlineUser();
                    //将字符串类型的“在线用户”包装为message，返回给客户端
                    Message msg2 = new Message();
                    msg2.setMesType(messageType.MESSAGE_RET_ONLINE);
                    msg2.setGetter(msg.getGetter());
                    msg2.setContent(onlineUser);
                    //将msg2写入通道
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(msg2);
                } else if (msg.getMesType().equals(messageType.MESSAGE_CLIENT_EXIT)) {
                    System.out.println(userId + "退出系统！！！");
                    ManageServerThread.removeServerThread(msg.getSender());
                    socket.close();//关闭连接
                    break;//退出

                } else if (msg.getMesType().equals(messageType.MESSAGE_PRIVATE_MES)||msg.getMesType().equals(messageType.MESSAGE_GROUP_MES)) {
                    HashMap<String, ServerThread> hm = ManageServerThread.getHm();
                    Iterator<String> iterator = hm.keySet().iterator();
                    boolean flag2=true;
                    while (iterator.hasNext()) {
                        //取出在线用户id
                        String onlineUserId = iterator.next().toString();
                        if (onlineUserId.equals(msg.getGetter())) {
                            //根据message得到getter,在集合中找到对应的线程，再将该消息转发到目标客户端
                            msg.setOnline(true);
                            ServerThread serverThread = ManageServerThread.getServerThread(msg.getGetter());
                            ObjectOutputStream oos = new ObjectOutputStream(serverThread.getSocket().getOutputStream());
                            oos.writeObject(msg);
                            flag2=false;
                            break;
                        }
                    }
                    if(flag2){
                        msg.setOnline(false);//接收方用户不在线，给发送方发送提示
                        ServerThread serverThread = ManageServerThread.getServerThread(msg.getSender());
                        ObjectOutputStream oos = new ObjectOutputStream(serverThread.getSocket().getOutputStream());
                        oos.writeObject(msg);
                        System.out.println("用户"+msg.getGetter()+"不在线");
                    }
                    //如果用户不在线，可以保存到数据库实现离线留言

                } else if (msg.getMesType().equals(messageType.MESSAGE_PUBLIC_MES)||msg.getMesType().equals(messageType.MESSAGE_GROUP_PUBLIC_MES)) {
                    //遍历集合拿到所有线程的socket，再将message转发
                    HashMap<String, ServerThread> hm = ManageServerThread.getHm();
                    Iterator<String> iterator = hm.keySet().iterator();
                    while (iterator.hasNext()) {
                        //取出在线用户id
                        String onlineUserId = iterator.next().toString();
                        ObjectOutputStream oos = new ObjectOutputStream(hm.get(onlineUserId).getSocket().getOutputStream());
                        oos.writeObject(msg);
                    }
                } else if (msg.getMesType().equals(messageType.MESSAGE_FILE_MES)||msg.getMesType().equals(messageType.MESSAGE_GROUP_FILE)) {
                    //根据message得到getter,在集合中找到对应的线程，再将该消息转发到目标客户端
                    ServerThread serverThread = ManageServerThread.getServerThread(msg.getGetter());
                    ObjectOutputStream oos = new ObjectOutputStream(serverThread.getSocket().getOutputStream());
                    oos.writeObject(msg);
                    //如果用户不在线，可以保存到数据库实现离线留言
                } else if ( msg.getMesType().equals(messageType.MESSAGE_NOTICE)) {
                    //遍历集合拿到所有线程的socket，再将message转发
                    HashMap<String, ServerThread> hm = ManageServerThread.getHm();
                    Iterator<String> iterator = hm.keySet().iterator();
                    while (iterator.hasNext()) {
                        //取出在线用户id
                        String onlineUserId = iterator.next().toString();
                        ObjectOutputStream oos = new ObjectOutputStream(hm.get(onlineUserId).getSocket().getOutputStream());
                        oos.writeObject(msg);
                    }
                } else if (msg.getMesType().equals(messageType.MESSAGE_IMAGE)||msg.getMesType().equals(messageType.MESSAGE_GROUP_IMAGE)) {
                    //根据message得到getter,在集合中找到对应的线程，再将该消息转发到目标客户端
                    ServerThread serverThread = ManageServerThread.getServerThread(msg.getGetter());
                    ObjectOutputStream oos = new ObjectOutputStream(serverThread.getSocket().getOutputStream());
                    oos.writeObject(msg);
                } else {

                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
