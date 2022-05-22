package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Login_in extends JPanel {
	int flag = 0;
	public Login_in() {
		setSize(1000, 775);
		setLayout(null);
		setFont(new Font("宋体", Font.PLAIN, 14));
		JLabel admin = new JLabel("账户");
		JLabel password = new JLabel("密码");
		JTextField textField = new JTextField();
		JPasswordField passwordField = new JPasswordField();
		JButton login = new JButton("登录");
		admin.setBounds(20, 300, 50, 20);
		password.setBounds(20, 350, 50, 20);
		textField.setBounds(50, 300, 100, 20);
		passwordField.setBounds(50, 350, 100, 20);
		login.setBounds(30, 400, 100, 20);
		add(admin);
		add(password);
		add(textField);
		add(passwordField);
		add(login);
		login.setContentAreaFilled(false);
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//初始账号为admin  密码为123456
				if (textField.getText().equals("admin") && new String(passwordField.getPassword()).equals("123456")) {					
					remove(login);
					remove(password);
					remove(passwordField);
					remove(textField);
					toplay();
				} else {
					passwordField.setText("");
					JOptionPane.showMessageDialog(null, "账户或用户名错误");
				}
			}
		});
	}
	public  void  toplay() {
		Tetris tetris = new Tetris();
		tetris.setBounds(0, 0, 600, 660);
		this.add(tetris);
		repaint();
		tetris.requestFocus();
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = new ImageIcon("src/backgroud.jpg").getImage();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	public static void main(String[] args){
    	JFrame frame=new JFrame();
    	frame.add(new Login_in());
    	frame.setSize(560,660);
    	frame.setTitle("Tetris game");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
