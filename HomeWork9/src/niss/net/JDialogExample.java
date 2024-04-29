package niss.net;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JDialogExample {
	public static void main(String[] args) {
		JFrame frame = new JFrame("工具栏对话框示例");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建工具栏
        JToolBar toolBar = new JToolBar();
        JButton dialog1Button = new JButton("对话框1");
        JButton dialog2Button = new JButton("对话框2");

        // 添加按钮点击事件监听器
        dialog1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建对话框1
                JDialog dialog1 = new JDialog(frame, "对话框1", true);
                dialog1.setSize(300, 200);
                dialog1.setLocationRelativeTo(frame);

                JLabel label1 = new JLabel("这是对话框1");
                dialog1.add(label1);

                dialog1.setVisible(true);
            }
        });

        dialog2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建对话框2
                JDialog dialog2 = new JDialog(frame, "对话框2", true);
                dialog2.setSize(300, 200);
                dialog2.setLocationRelativeTo(frame);

                JLabel label2 = new JLabel("这是对话框2");
                dialog2.add(label2);

                dialog2.setVisible(true);
            }
        });

        // 将按钮添加到工具栏
        toolBar.add(dialog1Button);
        toolBar.add(dialog2Button);

        // 创建面板并设置布局
        JPanel panel = new JPanel(new BorderLayout());
        // 将工具栏添加到面板的北部（上方）
        panel.add(toolBar, BorderLayout.NORTH);

        frame.add(panel);
        frame.setVisible(true);
    }
}
