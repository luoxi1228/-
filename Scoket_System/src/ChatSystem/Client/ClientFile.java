package ChatSystem.Client;

import ChatSystem.Message;
import ChatSystem.UI.GroupChat;
import ChatSystem.UI.MainView;
import ChatSystem.messageType;

import java.io.*;
import java.net.Socket;

//文件传输服务
public class ClientFile {
    public static void sendFile(String src, String des, String senderId, String getterId){
        //读取文件，封装成byte数组，再封装到message里面
        Message msg=new Message();
        msg.setMesType(messageType.MESSAGE_FILE_MES);
        msg.setSender(senderId);
        msg.setGetter(getterId);
        msg.setSrc(src);
        msg.setDes(des);

        //文件输入流
        FileInputStream fileInputStream=null;
        byte[] fileBytes=new byte[(int)new File(src).length()];
        try {
            fileInputStream = new FileInputStream(src);
            fileInputStream.read(fileBytes);//将文件以字节流读入数组
            msg.setFileBytes(fileBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //关闭
            if(fileInputStream!=null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //提示发送成功消息
        MainView.textArea.append("\n 已将路径为：’"+src+"‘的文件发送到用户【"+getterId+"】\n");
        //发送
        try {
            Socket socket=ManageClientThread.getClientThread(senderId).getSocket();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void sendFileToAll(String src, String des, String senderId, String getterId){
        //读取文件，封装成byte数组，再封装到message里面
        Message msg=new Message();
        msg.setMesType(messageType.MESSAGE_GROUP_FILE);
        msg.setSender(senderId);
        msg.setGetter(getterId);
        msg.setSrc(src);
        msg.setDes(des);

        //文件输入流
        FileInputStream fileInputStream=null;
        byte[] fileBytes=new byte[(int)new File(src).length()];
        try {
            fileInputStream = new FileInputStream(src);
            fileInputStream.read(fileBytes);//将文件以字节流读入数组
            msg.setFileBytes(fileBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //关闭
            if(fileInputStream!=null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //提示发送成功消息
       GroupChat.textArea.append("\n 已将路径为：’"+src+"‘的文件发送到用户【"+getterId+"】\n");
        //发送
        try {
            Socket socket=ManageClientThread.getClientThread(senderId).getSocket();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //发送图片
    public static void sendImage(String src, String des, String senderId, String getterId){
        //读取文件，封装成byte数组，再封装到message里面
        Message msg=new Message();
        msg.setMesType(messageType.MESSAGE_IMAGE);
        msg.setSender(senderId);
        msg.setGetter(getterId);
        msg.setSrc(src);
        msg.setDes(des);

        //文件输入流
        FileInputStream fileInputStream=null;
        byte[] fileBytes=new byte[(int)new File(src).length()];
        try {
            fileInputStream = new FileInputStream(src);
            fileInputStream.read(fileBytes);//将文件以字节流读入数组
            msg.setFileBytes(fileBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //关闭
            if(fileInputStream!=null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //提示发送成功消息
        MainView.textArea.append("\n 已将路径为：’"+src+"‘的图片发送到用户【"+getterId+"】\n");
        //发送
        try {
            Socket socket=ManageClientThread.getClientThread(senderId).getSocket();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //群发图片
    public static void sendGroupImage(String src, String des, String senderId, String getterId){
        //读取文件，封装成byte数组，再封装到message里面
        Message msg=new Message();
        msg.setMesType(messageType.MESSAGE_GROUP_IMAGE);
        msg.setSender(senderId);
        msg.setGetter(getterId);
        msg.setSrc(src);
        msg.setDes(des);

        //文件输入流
        FileInputStream fileInputStream=null;
        byte[] fileBytes=new byte[(int)new File(src).length()];
        try {
            fileInputStream = new FileInputStream(src);
            fileInputStream.read(fileBytes);//将文件以字节流读入数组
            msg.setFileBytes(fileBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //关闭
            if(fileInputStream!=null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //提示发送成功消息
        //GroupChat.textArea.append("\n 已将路径为：’"+src+"‘的图片发送到用户【"+getterId+"】\n");
        //发送
        try {
            Socket socket=ManageClientThread.getClientThread(senderId).getSocket();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
