package bookSql;

import java.awt.EventQueue;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MainJF extends Frame {
	private Image offScreenImage = null;
	public MainJF() {
		super("图书管理系统");

		JPanel contentpanel = new JPanel();
		contentpanel.setLayout(null);
		JButton btnNewButton = new JButton("新增图书");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBounds(20, 60, 120, 50);
		btnNewButton.setFocusPainted(false);
		contentpanel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("修改图书");
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBounds(150, 60, 120, 50);
		contentpanel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("删除图书");
		btnNewButton_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setBounds(280, 60, 120, 50);
		contentpanel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("借阅图书");
		btnNewButton_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.setBounds(20, 170, 120, 50);
		contentpanel.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("归还图书");
		btnNewButton_4.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setFocusPainted(false);
		btnNewButton_4.setBounds(150, 170, 120, 50);
		contentpanel.add(btnNewButton_4);

		JButton btnNewButton_6 = new JButton("新增借记");
		btnNewButton_6.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton_6.setContentAreaFilled(false);
		btnNewButton_6.setFocusPainted(false);
		btnNewButton_6.setBounds(280, 170, 120, 50);
		contentpanel.add(btnNewButton_6);

		JButton btnNewButton_5 = new JButton("查询图书");
		btnNewButton_5.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton_5.setContentAreaFilled(false);
		btnNewButton_5.setFocusPainted(false);
		btnNewButton_5.setBounds(180, 300, 150, 50);
		contentpanel.add(btnNewButton_5);
		
		JButton btnNewButton_9 = new JButton("查询借书卡");
		btnNewButton_9.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnNewButton_9.setContentAreaFilled(false);
		btnNewButton_9.setFocusPainted(false);
		btnNewButton_9.setBounds(180, 360, 150, 50);
		contentpanel.add(btnNewButton_9);
		btnNewButton_9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IDcard_select iDcard_select=new IDcard_select();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Book_insert bjf1 = new Book_insert();
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Book_change bjf2 = new Book_change();
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Book_delete bjf3 = new Book_delete();
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Book_borrow bjf4 = new Book_borrow();
			}
		});
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Book_return bjf5 = new Book_return();
			}
		});
		btnNewButton_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Book_require bjf6 = new Book_require();
			}
		});
		btnNewButton_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IDcard_new bjf7 = new IDcard_new();
			}
		});
		setResizable(false);
		setVisible(true);
		add(contentpanel);
		setLocationRelativeTo(null);
		setSize(420, 480);
		this.paint(this.getGraphics());
		this.addWindowListener(new WindowAdapter() {
	          @Override
	          public void windowClosing(WindowEvent e)
	          {
	             System.exit(0);
	          }
	      });
	}

	public void paint(Graphics g) {
		super.paint(g);
		Image image = new ImageIcon("src/line.gif").getImage();
		Image backiImage = new ImageIcon("src/11.png").getImage();
		Image backiImage2 = new ImageIcon("src/22.png").getImage();
		Image backiImage3 = new ImageIcon("src/01.gif").getImage();
		g.drawImage(image, 0, 260, this);
		g.drawImage(backiImage, 110, 30, this);
		g.drawImage(backiImage2, 110, 140, this);
		g.drawImage(backiImage3, 20, 300, this);

	}
	public void update(Graphics g) {
	    if(offScreenImage == null) {
	          offScreenImage = this.createImage(420, 480);
	    }
	 
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);

	    //双缓冲机制防止图片闪烁
	}

}