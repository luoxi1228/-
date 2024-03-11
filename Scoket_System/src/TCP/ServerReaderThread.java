package TCP;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
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
                   System.out.println(msg);
               } catch (Exception e) {
                   System.out.println(socket.getRemoteSocketAddress()+"下线");
                   dis.close();
                   socket.close();
                   break;
               }
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }


   }

}
