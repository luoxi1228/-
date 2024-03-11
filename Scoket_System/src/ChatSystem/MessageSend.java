package ChatSystem;

import ChatSystem.Client.ClientThread;
import ChatSystem.Client.ManageClientThread;
import ChatSystem.Message;
import ChatSystem.messageType;
import jdk.nashorn.internal.runtime.regexp.joni.constants.CCSTATE;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class MessageSend {
    public void sendGroupMsg(String content, String senderId, String getterId) {//组聊邀请消息
        Message msg = new Message();
        msg.setMesType(messageType.MESSAGE_GROUP_MES);
        msg.setSender(senderId);
        msg.setGetter(getterId);
        msg.setContent(content);
        msg.setSendTime(new Date().toString());//发送时间
        //System.out.println(senderId+"给"+getterId+"发消息说："+content);

        try {//将消息发送给服务端
            ClientThread clientThread = ManageClientThread.getClientThread(senderId);
            Socket socket = clientThread.getSocket();
            ObjectOutputStream oss = new ObjectOutputStream(socket.getOutputStream());
            oss.writeObject(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendPublicGroupMsg(String content, String senderId) {//群发消息
        Message msg = new Message();
        msg.setMesType(messageType.MESSAGE_GROUP_PUBLIC_MES);
        msg.setSender(senderId);
        msg.setContent(content);
        msg.setSendTime(new Date().toString());//发送时间
        msg.setOnline(true);
        //System.out.println(senderId+"给"+getterId+"发消息说："+content);

        try {//将消息发送给服务端
            ClientThread clientThread = ManageClientThread.getClientThread(senderId);
            Socket socket = clientThread.getSocket();
            ObjectOutputStream oss = new ObjectOutputStream(socket.getOutputStream());
            oss.writeObject(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendPrivateMsg(String content, String senderId, String getterId) {//私聊消息
        Message msg = new Message();
        msg.setMesType(messageType.MESSAGE_PRIVATE_MES);
        msg.setSender(senderId);
        msg.setGetter(getterId);
        msg.setContent(content);
        msg.setSendTime(new Date().toString());//发送时间
        //System.out.println(senderId+"给"+getterId+"发消息说："+content);

        try {//将消息发送给服务端
            ClientThread clientThread = ManageClientThread.getClientThread(senderId);
            Socket socket = clientThread.getSocket();
            ObjectOutputStream oss = new ObjectOutputStream(socket.getOutputStream());
            oss.writeObject(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendPublicMsg(String content, String senderId) {//群发消息
        Message msg = new Message();
        msg.setMesType(messageType.MESSAGE_PUBLIC_MES);
        msg.setSender(senderId);
        msg.setContent(content);
        msg.setSendTime(new Date().toString());//发送时间
        msg.setOnline(true);
        //System.out.println(senderId+"给"+getterId+"发消息说："+content);

        try {//将消息发送给服务端
            ClientThread clientThread = ManageClientThread.getClientThread(senderId);
            Socket socket = clientThread.getSocket();
            ObjectOutputStream oss = new ObjectOutputStream(socket.getOutputStream());
            oss.writeObject(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendNotice(String content, String senderId) {//系统提示消息
        Message msg = new Message();
        msg.setMesType(messageType.MESSAGE_NOTICE);
        msg.setSender(senderId);
        msg.setContent(content);
        msg.setSendTime(new Date().toString());//发送时间
        msg.setOnline(true);
        //System.out.println(senderId+"给"+getterId+"发消息说："+content);

        try {//将消息发送给服务端
            ClientThread clientThread = ManageClientThread.getClientThread(senderId);
            Socket socket = clientThread.getSocket();
            ObjectOutputStream oss = new ObjectOutputStream(socket.getOutputStream());
            oss.writeObject(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
