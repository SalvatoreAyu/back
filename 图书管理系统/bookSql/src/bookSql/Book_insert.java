package bookSql;

import java.awt.EventQueue;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Checkbox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import java.awt.Font;

public class Book_insert extends JFrame {
	private JTextArea Bname_text;
	private JTextArea Bauthor_text;
	private JTextArea Bprice_text;
	private JTextArea ISBN_text;
	private JTextArea Bpublish_text;
	private JTextArea Bcomment_text;
	private JTextArea Bsort;

	/**
	 * Create the application.
	 */
	public Book_insert() {
		super("图书添加");
		setVisible(true);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JLabel label = new JLabel("图书名字：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(35, 78, 71, 15);
		getContentPane().add(label);

		Bname_text = new JTextArea();
		Bname_text.setColumns(10);
		Bname_text.setBounds(114, 74, 83, 24);
		getContentPane().add(Bname_text);

		JLabel label_1 = new JLabel("图书作者：");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(35, 122, 71, 15);
		getContentPane().add(label_1);

		Bauthor_text = new JTextArea();
		Bauthor_text.setColumns(10);
		Bauthor_text.setBounds(114, 118, 83, 24);
		getContentPane().add(Bauthor_text);

		JLabel label_2 = new JLabel("图书价格：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(35, 163, 71, 15);
		getContentPane().add(label_2);

		Bprice_text = new JTextArea();
		Bprice_text.setColumns(10);
		Bprice_text.setBounds(114, 159, 83, 24);
		getContentPane().add(Bprice_text);

		JLabel lblIsbn = new JLabel("书号（ISBN）：");
		lblIsbn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsbn.setBounds(10, 36, 96, 15);
		getContentPane().add(lblIsbn);

		ISBN_text = new JTextArea();
		ISBN_text.setColumns(10);
		ISBN_text.setBounds(114, 32, 83, 24);
		getContentPane().add(ISBN_text);

		JLabel label_3 = new JLabel("出版社：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(47, 201, 59, 15);
		getContentPane().add(label_3);

		Bpublish_text = new JTextArea();
		Bpublish_text.setColumns(10);
		Bpublish_text.setBounds(114, 197, 83, 24);
		getContentPane().add(Bpublish_text);

		JLabel label_4 = new JLabel("内容简介（50字内）：");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(224, 78, 146, 15);
		getContentPane().add(label_4);

		JButton button = new JButton("提交");
		button.setBounds(231, 197, 84, 23);
		getContentPane().add(button);

		JButton btnNewButton = new JButton("返回");
		btnNewButton.setBounds(332, 197, 77, 23);
		getContentPane().add(btnNewButton);

		Bcomment_text = new JTextArea();
		Bcomment_text.setRows(8);
		Bcomment_text.setBounds(227, 105, 161, 73);
		getContentPane().add(Bcomment_text);

		JLabel label_5 = new JLabel("类别：");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_5.setBounds(224, 36, 96, 15);
		getContentPane().add(label_5);

		Bsort = new JTextArea();
		Bsort.setColumns(10);
		Bsort.setBounds(286, 32, 102, 24);
		getContentPane().add(Bsort);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				;
			}
		});

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (b_insert()) {
					JOptionPane.showMessageDialog(null, "添加成功啦！！");
				} else {
					JOptionPane.showMessageDialog(null, "请输入已有作者或分类！！");
				}
			}
		});

	}

	public Boolean b_insert() {
		boolean result = false;
		Connection conn = null;
		try {
			conn = Login.getCon(); // 建立数据库连接
			String sqlInset = "insert into Book(ISBN,Bname,Bprice,Bcomment,Bpublish,Bauthor,Bsort)" + "values('"
					+ ISBN_text.getText() + "','" + Bname_text.getText() + "','" + Bprice_text.getText() + "','"
					+ Bcomment_text.getText() + "','" + Bpublish_text.getText() + "','" + Bauthor_text.getText() + "','"
					+ Bsort.getText() + "')";  //sql语句进行向book表进行插入运算
			PreparedStatement stmt = conn.prepareStatement(sqlInset); 

			int i = stmt.executeUpdate();//返回受影响行数
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally { 
			try {
				conn.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
