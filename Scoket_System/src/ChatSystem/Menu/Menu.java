package ChatSystem.Menu;

import ChatSystem.Client.ClientFile;
import ChatSystem.Client.ClientThread;
import ChatSystem.Client.UserClient;
import ChatSystem.MessageSend;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        new Menu().LoginMenu();
        System.out.println("退出系统!!!");
    }

    private static boolean loop = true;
    Scanner sc = new Scanner(System.in);
    private String key;//获取键盘输入的值
    private UserClient userClient = new UserClient();//用于登录
    private MessageSend messageSend = new MessageSend();//发送消息
    private ClientFile file = new ClientFile();//发送文件

    //登录界面
    private void LoginMenu() {
        while (loop) {
/*          System.out.println("===========聊天系统===========");
            System.out.println("\t\t 1 用户登录");
            System.out.println("\t\t 2 退出系统");
            System.out.print("输入：");
            key = sc.next();*/
            //switch (key) {
            //case "1":
            System.out.println("============登录===========");
            System.out.print("用户ID: ");
            String userID = sc.next();
            System.out.print("用户密码: ");
            String password = sc.next();
            if (userClient.checkUser(userID, password)) {
                while (loop) {
                    System.out.println("===========聊天菜单（用户" + userID + "）===========");
                    System.out.println("\t\t1 显示在线用户数量");
                    System.out.println("\t\t2 群发消息");
                    System.out.println("\t\t3 私聊消息");
                    System.out.println("\t\t4 发送文件");
                    System.out.println("\t\t5 退出系统");
                    System.out.print("输入：");
                    key = sc.next();
                    switch (key) {
                        case "1":
                            userClient.olineList();
                            break;
                        case "2":
                            System.out.print("输入消息内容：");
                            String s = sc.next();
                            messageSend.sendPublicMsg(s, userID);
                            break;
                        case "3":
                            System.out.print("输入你想私聊的在线用户ID: ");
                            String getterId = sc.next();
                            System.out.print("输入消息内容: ");
                            String content = sc.next();
                            //编写方法将消息发送给服务端
                            messageSend.sendPrivateMsg(content, userID, getterId);
                            break;
                        case "4":
                            System.out.print("输入接收者的用户ID：");
                            String getid = sc.next();
                            System.out.print("输入文件的发送路径【形式如：D:/*/*.txt】：");
                            String src = sc.next();
                            System.out.print("输入文件的接收路径【形式如：D:/*/*.txt】：");
                            String des = sc.next();
                            file.sendFile(src,des, userID, getid);
                            break;
                        case "5":
                            userClient.exit();
                            loop = false;
                            break;
                    }

                }
            } else {
                System.out.println("登录失败!!!");
                System.out.println();
            }
/*                case "2":
                    loop = false;
                    break;*/
        }

    }
}
