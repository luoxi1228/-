package ChatSystem;

/*消息类型
 */
public interface messageType {
    String MESSAGE_LOGIN_SUCCEED="1";//登录成功
    String MESSAGE_LOGIN_FAIL="2";//登录失败
    String MESSAGE_PRIVATE_MES="3";//私聊消息

    String MESSAGE_GET_ONLINE="4";//请求在线用户数
    String MESSAGE_RET_ONLINE="5";//返回在线用户数
    String MESSAGE_CLIENT_EXIT="6";//退出系统
    String MESSAGE_PUBLIC_MES="7";//群发消息
    String MESSAGE_FILE_MES="8";//文件消息
    String MESSAGE_NOTICE="9";//系统提示
    String MESSAGE_GROUP_MES="10";//组聊消息
    String MESSAGE_GROUP_PUBLIC_MES="11";//组聊群发消息
    String MESSAGE_GROUP_FILE="12";//组聊消息
    String MESSAGE_IMAGE="13";//群发图片
    String MESSAGE_GROUP_IMAGE="14";//群发图片

}
