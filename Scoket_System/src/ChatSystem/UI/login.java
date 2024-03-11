package ChatSystem.UI;


import ChatSystem.Client.ClientFile;
import ChatSystem.Client.UserClient;
import ChatSystem.Message;
import ChatSystem.MessageSend;
import ChatSystem.Server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame {
    public static UserClient userClient = new UserClient();//用于登录
    public static MessageSend messageSend = new MessageSend();//发送消息
    public static ClientFile file = new ClientFile();//发送文件
    private static final long serialVersionUID = 5475179439752076273L;
    private Container container = getContentPane();
    private JLabel titleLabel=new JLabel("用户登录");
    private JLabel userLabel = new JLabel("账 号:");
    private JTextField usernameField = new JTextField();
    private JLabel passLabel = new JLabel("密 码:");
    private JPasswordField passwordField = new JPasswordField();
    private JButton okBtn = new JButton("确定");
    private JButton cancelBtn = new JButton("清空");
    //public static boolean flag4=true;//提示进入聊天系统消息

    public login() {
        setTitle("多人聊天系统");
        // 设计窗体大小
        setBounds(800, 400, 550, 350);
        // 添加一块桌布
        container.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 初始化窗口
        init();
        // 设计窗口可见
        setVisible(true);
    }

    private void init() {
        /*输入部分--Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        Font font=new Font("宋体",Font.BOLD,20);
        Font font1=new Font("宋体",Font.BOLD,30);

        titleLabel.setBounds(197,5,200,50);
        userLabel.setBounds(120, 60, 100, 80);//用户
        passLabel.setBounds(120, 100, 100, 80);//密码
        userLabel.setFont(font);
        passLabel.setFont(font);
        titleLabel.setFont(font1);
        fieldPanel.add(userLabel);
        fieldPanel.add(passLabel);
        fieldPanel.add(titleLabel);

        usernameField.setBounds(220, 85, 150, 30);
        passwordField.setBounds(220, 125, 150, 30);
        passwordField.setEditable(true);
        passwordField.setColumns(6);
        fieldPanel.add(usernameField);
        fieldPanel.add(passwordField);
        container.add(fieldPanel, "Center");

        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        okBtn.setFont(font);
        cancelBtn.setFont(font);
        buttonPanel.add(okBtn);
        buttonPanel.add(cancelBtn);
        container.add(buttonPanel, "South");
        listerner();
    }
    public void listerner() {
        /** 登录系统 */
        okBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String username = usernameField.getText();
                        String password = String.valueOf(passwordField.getPassword());
                        if (null == username
                                || password == null
                                || username.trim().length() == 0
                                || password.trim().length() == 0) {
                            JOptionPane.showMessageDialog(null, "用户名或密码不能为空");
                        }
                        boolean flag3=false;//判断是否登录成功
                        for (String key : Server.userArray.keySet()) {
                            String value=Server.userArray.get(key).getPassword();
                            //简单校验用户密码
                            if(key.equals(username)&& value.equals(password)){
                                JOptionPane.showMessageDialog(null, "登录成功");
                                flag3=true;
                                if(userClient.checkUser(username, password)){
                                dispose();//清除上一界面
                                MainView mainView=new MainView(username);
                                String notice="！！！用户【"+username+"】进入聊天室！！！";
                                messageSend.sendNotice(notice,username);
                                }
                                break;
                                //登录成功后跳转页面
                            }
                        }
                        if(!flag3){
                            JOptionPane.showMessageDialog(null, "登录失败");
                        }


                        // 登录操作
                    }
                });
        /** 清空输入信息 */
        cancelBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                });
    }
}


