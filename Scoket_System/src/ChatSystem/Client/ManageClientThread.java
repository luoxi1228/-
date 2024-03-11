package ChatSystem.Client;

import java.util.HashMap;

//用一个集合管理同一个客户端连接到服务端的多个线程
public class ManageClientThread {
    //将多个线程放入一个hashmao中，key为userID,value为clientServerThread
    private static HashMap<String, ClientThread> hm = new HashMap<>();

    //添加进集合
    public static void addClientThread(String userId, ClientThread thread) {
        hm.put(userId, thread);
    }

    //从集合获取线程
    public static ClientThread getClientThread(String userId) {
        return hm.get(userId);
    }

}

