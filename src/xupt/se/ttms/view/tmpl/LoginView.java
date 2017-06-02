package xupt.se.ttms.view.tmpl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet.FontAttribute;

public class LoginView extends JFrame implements ActionListener {

	GridLayout grid;
	JLabel title, people;
	Box box1, box2, box3, baseBox;
	JLabel labelLoginPsw;
	JTextField textField;
	JPasswordField password;
	JButton confirm, cancel, register;
	ImageIcon background;
	JPanel imagePanel;
	private JLabel labelLoginAct;

	
	public static void main(String[] args) {
		LoginView win=new LoginView();
		win.setTitle("登陆");
		win.setBounds(50, 50, 500, 500);
	}
	// LoginListener listener;
	public LoginView() {
		init();
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void init() {

		setLayout(new FlowLayout());
		background = new ImageIcon("G:\\picture\\LoginBackground.png");
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, background.getIconWidth(),
				background.getIconHeight());
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());
		// this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		this.setSize(background.getIconWidth(), background.getIconHeight());

		labelLoginAct = new JLabel("账号:");
		labelLoginPsw = new JLabel("密码:");
		textField = new JTextField(10);
		password = new JPasswordField(10);

		confirm = new JButton("确定");
		confirm.setBackground(Color.cyan);
		cancel = new JButton("取消");
		cancel.setBackground(Color.CYAN);
		// register = new JButton("注册");

		box1 = Box.createHorizontalBox();
		box1.add(labelLoginAct);
		box1.add(Box.createHorizontalStrut(30));
		box1.add(textField);

		box2 = Box.createHorizontalBox();
		box2.add(labelLoginPsw);
		box2.add(Box.createHorizontalStrut(30));
		box2.add(password);

		box3 = Box.createHorizontalBox();
		box3.add(confirm);
		box3.add(Box.createHorizontalStrut(30));
		box3.add(cancel);
		// box3.add(Box.createHorizontalStrut(10));
		// box3.add(register);

		baseBox = Box.createVerticalBox();
		baseBox.add(Box.createVerticalStrut(150));
		baseBox.add(box1);
		baseBox.add(Box.createVerticalStrut(30));
		baseBox.add(box2);
		baseBox.add(Box.createVerticalStrut(30));
		baseBox.add(box3);

		// add(title);
		add(baseBox);
		// add(people);

		// title.setVisible(false);
		confirm.addActionListener(this);
		cancel.addActionListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("确定")) {
			if (textField.getText().equals("admin")
					&& password.getText().equals("admin")) {

//				WinMenu winMenu = new WinMenu();
//				winMenu.setTitle("菜单窗口");
//				winMenu.setBounds(50, 50, 500, 500);
//				this.setVisible(false);
			} else {

				JOptionPane.showMessageDialog(this, "密码错误", "警告",
						JOptionPane.ERROR_MESSAGE);
				password.setText(null);
			}
		}
		if (e.getActionCommand().equals("取消")) {

			System.exit(0);
		}
	}

}

