package ChatSystem.Server;

import ChatSystem.Client.ClientThread;
import ChatSystem.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ManageServerThread {
    private static HashMap<String, ServerThread> hm = new HashMap<>();


    public static HashMap<String, ServerThread> getHm() {
        return hm;
    }


    public static void addServerThread(String userId, ServerThread thread) {
        hm.put(userId, thread);
    }

    public static ServerThread getServerThread(String userId) {
        return hm.get(userId);
    }


    //获取在线用户
    public static String getOnlineUser() {
        //遍历hm
        Iterator<String> iterator = hm.keySet().iterator();
        String onlineUSer = "";
        while (iterator.hasNext()) {
            onlineUSer += iterator.next().toString() + " ";
        }
        return onlineUSer;
    }

    //删除线程
    public static void removeServerThread(String userId) {
        hm.remove(userId);
    }

}
