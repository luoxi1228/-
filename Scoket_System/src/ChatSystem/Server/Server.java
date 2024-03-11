package ChatSystem.Server;

import ChatSystem.Client.ClientThread;
import ChatSystem.Client.ManageClientThread;
import ChatSystem.Message;
import ChatSystem.User;
import ChatSystem.messageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.CheckedOutputStream;

//服务器IP地为127.0.0.1，端口为9999
public class Server {
    private ServerSocket ss = null;

    //创建一个集合，存放一些用户,ConcurrentHashMap可以处理并发
    public static ConcurrentHashMap<String, User> userArray = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, ArrayList<Message>> offlineArray = new ConcurrentHashMap<>();

    //在静态代码块初始化
    static {
        userArray.put("100", new User("100", "111111"));
        userArray.put("200", new User("200", "222222"));
        userArray.put("300", new User("300", "333333"));
        userArray.put("400", new User("400", "444444"));
        userArray.put("500", new User("500", "555555"));
        userArray.put("罗皙", new User("400", "123456"));
        userArray.put("刘倬宇", new User("500", "123456"));

    }

    //验证用户是否有效
    private boolean checkUser(String userId, String password) {
        User user = userArray.get(userId);
        if (user == null) {//用户不存在
            System.out.println(userId + "不存在！");
            return false;
        }
        if (!user.getPassword().equals(password)) {//用户存在但密码错误
            System.out.println(userId + "的密码错误！");
            return false;
        }
        return true;
    }
    public Server() {
        try {
            System.out.println("端口9999，正在监听。。。");
            ss = new ServerSocket(9999);

            while (true) {//与客户端用户连接后，继续监听
                Socket socket = ss.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                User u = (User) ois.readObject();//读取客户端发送的User对象

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                Message msg = new Message();

                //验证
                if (checkUser(u.getUserId(), u.getPassword())) {//登录成功
                    msg.setMesType(messageType.MESSAGE_LOGIN_SUCCEED);
                    //将message回复给客户端
                    oos.writeObject(msg);
                    //创建线程与客户端保持连接
                    ServerThread serverThread = new ServerThread(u.getUserId(), socket);
                    //启动线程
                    serverThread.start();
                    //将线程放入到在线集合
                    ManageServerThread.addServerThread(u.getUserId(), serverThread);
                    //showLeaveMsg(u.getUserId());

                } else {
                    System.out.println(u.getUserId() + "登录失败！！！");
                    msg.setMesType(messageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(msg);
                    socket.close();
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //如果服务端对此while循环，说明服务器不再监听，退出serverSocket
            try {
                ss.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
