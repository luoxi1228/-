package GroupChat;

import java.io.*;
import java.net.Socket;

//线程
public class ServerReaderThread extends Thread{
    private Socket socket;
    ServerReaderThread(Socket socket){
        this.socket=socket;
    }
   @Override
    public void run(){
      try {
           InputStream is= socket.getInputStream();
           DataInputStream dis=new DataInputStream(is);
           while (true){
               try {
                   String msg=dis.readUTF();
                   System.out.println(socket.getInetAddress()+": "+msg);
                   //把该消息分发给全部客户段
                   sendMsgToAll(msg);
               } catch (Exception e) {
                   System.out.println(socket.getInetAddress()+"下线！");
                   Server.onLineSocket.remove(socket);//离线就从集合中删除
                   dis.close();
                   socket.close();
                   break;
               }
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }


   }

    private void sendMsgToAll(String msg) throws Exception {
         //发送给全部在线的socket
        for (Socket socket1 : Server.onLineSocket) {
            OutputStream os=socket1.getOutputStream();
            DataOutputStream dos=new DataOutputStream(os);
            dos.writeUTF(msg);
            dos.flush();
        }
    }

}
