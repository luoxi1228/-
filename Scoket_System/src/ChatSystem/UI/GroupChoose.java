package ChatSystem.UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupChoose {
    public GroupChoose() {
        init();
    }
    public static void init() {
        String userName = "罗皙";
        JFrame frame = new JFrame("请选择组聊成员");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        JCheckBox checkBox1 = new JCheckBox("user1");
        JCheckBox checkBox2 = new JCheckBox("user2");
        JCheckBox checkBox3 = new JCheckBox("user3");
        JButton confirmButton = new JButton("确认");

        frame.add(checkBox1);
        frame.add(checkBox2);
        frame.add(checkBox3);
        frame.add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.messageSend.sendGroupMsg("邀请您进入组聊",userName,"刘倬宇");
                login.messageSend.sendGroupMsg("邀请您进入组聊",userName,"100");
                frame.dispose();
                GroupChat groupChat=new GroupChat("罗皙");
            }
        });
        frame.setVisible(true);
    }
}
