package ChatSystem;

import java.io.Serializable;

//消息类
public class Message implements Serializable {//序列化
    private static final long serialVersionUID = 1L;
    private String sender;//发送者
    private String getter;//接受者
    private String content;//内容
    private String sendTime;//发送时间
    private String mesType;//消息类型【在接口中定义】
    private boolean online;//看是否在线

    //文件相关的变量
    private byte[] fileBytes;//将文件以字节数组的形式传输
    private int fileLen;//文件长度
    private String src;//文件源地址
    private String des;//文件目的地址


    //构造函数
    public Message() {
    }

    public Message(String sender, String getter, String content, String sendTime, String mesType, byte[] fileBytes, int fileLen, String src, String des) {
        this.sender = sender;
        this.getter = getter;
        this.content = content;
        this.sendTime = sendTime;
        this.mesType = mesType;
        this.fileBytes = fileBytes;
        this.fileLen = fileLen;
        this.src = src;
        this.des = des;
    }

    public boolean getOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }


}
