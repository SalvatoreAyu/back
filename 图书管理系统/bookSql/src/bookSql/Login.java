package bookSql;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.*;

public class Login extends JFrame {
	//登录界面
	private JButton jb1=new JButton("登录");
	private JButton jb2=new JButton("退出");
	private JPasswordField passwordTxt=new JPasswordField(20);
	private JTextField textField = new JTextField(20);
	private static Connection conn = null;
	private static  String user ;
	private static  String psw ;  
	
	public Login() {
		super("登录连接到数据库");
		jb1.setContentAreaFilled(false);
		jb2.setContentAreaFilled(false);
		jb1.setFocusPainted(false);
		jb2.setFocusPainted(false);
		JPanel p1=new JPanel();
		JPanel p1_1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		
		Container cp=getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(p1,BorderLayout.NORTH);
		cp.add(p2,BorderLayout.CENTER);
		cp.add(p3,BorderLayout.SOUTH);
		p1.setLayout(new BorderLayout());
		p1.add(p1_1,BorderLayout.SOUTH);
		p1_1.setLayout(new FlowLayout());
		p1_1.add(new JLabel("用户名:"));
		p1_1.add(textField);
		p2.setLayout(new FlowLayout());
		p2.add(new JLabel("密    码:"));
		p2.add(passwordTxt);
		p3.setLayout(new FlowLayout());
	    p3.add(jb1);
		p3.add(jb2);
		
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					setVisible(false);
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								user=textField.getText();
								psw =passwordTxt.getText();
								if(getCon()!=null)
								{MainJF mjf= new MainJF();}
								else {
								//用户名及密码为你的MySQL数据库的用户名及密码
								//一般的用户名都是root，密码就是你mysql软件安装时使用的密码
								JOptionPane.showMessageDialog(null,"请输入正确管理员账号密码"); 
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					}

		});
		
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					System.exit(0);
		} 
		 });
		
		setSize(300,150);
		setVisible(true);
		setLocation(800,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
		
	 public static Connection getCon() {
         try {
             Class.forName("com.mysql.cj.jdbc.Driver"); //加载数据库连接驱动
             String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC&characterEncoding=UTF8";     
            conn = DriverManager.getConnection(url, user, psw);  //获取连接
         } catch (Exception e) {
             System.out.println("连接数据库失败");
             e.printStackTrace();
        }
         return conn;
       }
	 
	public static void main(String arg[]) {
		 Login jf=new Login();
	}
}
