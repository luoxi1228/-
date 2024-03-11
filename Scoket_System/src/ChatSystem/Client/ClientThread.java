package ChatSystem.Client;

import ChatSystem.Message;
import ChatSystem.Server.ServerThread;
import ChatSystem.UI.GroupChat;
import ChatSystem.UI.MainView;
import ChatSystem.UI.login;
import ChatSystem.messageType;

import javax.swing.*;
import javax.xml.bind.SchemaOutputResolver;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread {
    private Socket socket;

    //构造器接受一个socket对象
    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //System.out.print("输入：");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //如果服务器没有发送message过来，就会一直阻塞在这里
                Message msg = (Message) ois.readObject();
                //根据msg的类型执行相应的任务
                if (msg.getMesType().equals(messageType.MESSAGE_RET_ONLINE)) {//服务端发送的在线用户数量的信息
                    //规定以“100 200 300 ...”的形式返回，根据空格分割为字符数组
                    String[] s = msg.getContent().split(" ");
                    MainView.textArea.append("\n==在线用户列表==");
                    for (int i = 0; i < s.length; i++) {
                        MainView.textArea.append("\n===== "+s[i]+" =====");  // 添加进聊天客户端的文本区域
                    }
                    MainView.textArea.append("\n==============");

                }else if(msg.getMesType().equals(messageType.MESSAGE_GROUP_MES)){//组聊邀请
                    MainView.textArea.append("\n  用户【" + msg.getSender() + "】：" + msg.getContent());
                    MainView.textArea.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            GroupChat groupChat=new GroupChat(msg.getGetter());
                        }
                    });
                }
                else if (msg.getMesType().equals(messageType.MESSAGE_PRIVATE_MES)) {//私聊消息
                    if (msg.getOnline()) {
                        MainView.textArea.append("\n  用户【" + msg.getSender() + "】给你私发消息说：" + msg.getContent());
                    }else {
                        MainView.textArea.append("\n  用户【" + msg.getGetter() + "】不在线,发送失败！！！");
                    }
                } else if (msg.getMesType().equals(messageType.MESSAGE_PUBLIC_MES)) {//群发消息
                        MainView.textArea.append("\n  用户【" + msg.getSender() + "】：" + msg.getContent());
                        MainView.imageLabel1.setIcon(null);
                }
                else if (msg.getMesType().equals(messageType.MESSAGE_GROUP_PUBLIC_MES)) {//组聊群发消息
                    GroupChat.textArea.append("\n  用户【" + msg.getSender() + "】：" + msg.getContent());
                } else if (msg.getMesType().equals(messageType.MESSAGE_FILE_MES)) {//文件消息
                    MainView.textArea.append("\n  用户【" + msg.getSender() + "】给你的’"+msg.getDes()+"‘路径下发送了一个文件。");
                    //System.out.println("\n输入文件的接收路径【形式如：D:/*/*.txt】：");
                    //Scanner sc = new Scanner(System.in);
                    //String path = sc.nextLine();
                    //取出字节数组，通过文件输出流输出到磁盘
                    FileOutputStream fileOutputStream = new FileOutputStream(msg.getDes());
                    fileOutputStream.write(msg.getFileBytes());
                    fileOutputStream.close();
                    MainView.textArea.append("\n 文件保存成功！\n");
                    MainView.imageLabel1.setIcon(null);

                } else if (msg.getMesType().equals(messageType.MESSAGE_NOTICE)) {//系统提示
                    MainView.textArea.append("\n " + msg.getContent());
                    MainView.imageLabel1.setIcon(null);
                } else if (msg.getMesType().equals(messageType.MESSAGE_GROUP_FILE)) {//群发文件消息
                    GroupChat.textArea.append("\n  用户【" + msg.getSender() + "】给你的’" + msg.getDes() + "‘路径下发送了一个文件。");
                    //System.out.println("\n输入文件的接收路径【形式如：D:/*/*.txt】：");
                    //Scanner sc = new Scanner(System.in);
                    //String path = sc.nextLine();
                    //取出字节数组，通过文件输出流输出到磁盘
                    FileOutputStream fileOutputStream = new FileOutputStream(msg.getDes());
                    fileOutputStream.write(msg.getFileBytes());
                    fileOutputStream.close();
                    GroupChat.textArea.append("\n 文件保存成功！\n");
                } else if (msg.getMesType().equals(messageType.MESSAGE_IMAGE)) {
                    FileOutputStream fileOutputStream = new FileOutputStream(msg.getDes());
                    fileOutputStream.write(msg.getFileBytes());
                    fileOutputStream.close();
                    // 加载图片文件
                    ImageIcon imageIcon = new ImageIcon(msg.getDes());
                    // 调整图片大小
                    java.awt.Image image = imageIcon.getImage().getScaledInstance(400, 300, java.awt.Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(image);
                    // 设置标签的图标
                    MainView.imageLabel1.setIcon(imageIcon);
                } else if (msg.getMesType().equals(messageType.MESSAGE_GROUP_IMAGE)){
                    FileOutputStream fileOutputStream = new FileOutputStream(msg.getDes());
                    fileOutputStream.write(msg.getFileBytes());
                    fileOutputStream.close();
                    // 加载图片文件
                    ImageIcon imageIcon = new ImageIcon(msg.getDes());
                    // 调整图片大小
                    java.awt.Image image = imageIcon.getImage().getScaledInstance(400, 300, java.awt.Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(image);
                    // 设置标签的图标
                    GroupChat.imageLabel2.setIcon(imageIcon);
                } else{

                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
