import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // 创建窗口
        JFrame frame = new JFrame("Window with Text and Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建面板1，用于显示文本消息
        JPanel textPanel = new JPanel();
        textPanel.setBackground(Color.WHITE);
        textPanel.setPreferredSize(new Dimension(400, 200));

        // 创建文本标签
        JLabel textLabel = new JLabel("This is a text message.");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // 将文本标签添加到面板1中
        textPanel.add(textLabel);

        // 创建面板2，用于显示图片信息
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setPreferredSize(new Dimension(400, 300));

        // 创建用于显示图片的标签
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\ASUS\\Desktop\\accept\\2.jpg");
        imageLabel.setIcon(imageIcon);

        // 将图片标签添加到面板2中
        imagePanel.add(imageLabel);

        // 创建主面板，使用 BorderLayout 布局管理器
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(textPanel, BorderLayout.NORTH);
        mainPanel.add(imagePanel, BorderLayout.CENTER);

        // 将主面板添加到窗口中
        frame.getContentPane().add(mainPanel);

        // 调整窗口大小并显示窗口
        frame.pack();
        frame.setVisible(true);
    }
}

