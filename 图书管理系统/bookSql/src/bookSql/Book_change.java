package bookSql;

import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Book_change extends JFrame {

	private JTextField textField;
	private JTextField Bname_text;
	private JTextField Bauthor_text;
	private JTextField Bpublish_text;
	private JTextField Bsort_text;
	private JTextField Bprice_text;
	private JTextArea Bcomment_text;

	/**
	 * Initialize the contents of the .
	 */
	public Book_change() {
		setTitle("图书修改");
		setVisible(true);
		setBounds(100, 100, 500, 411);
		setLocationRelativeTo(null);
		;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("图书修改(确认书号)：");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 16));
		lblNewLabel_2.setBounds(23, 10, 156, 29);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("ISBN(书号：)");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 12));
		lblNewLabel.setBounds(23, 59, 111, 19);
		getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(133, 59, 130, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("请输入要修改项（不改动的留白）");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(23, 95, 240, 29);
		getContentPane().add(label);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(23, 122, 451, 210);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label_1 = new JLabel("图书名：");
		label_1.setBounds(22, 24, 63, 19);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(label_1);

		Bname_text = new JTextField();
		Bname_text.setBounds(95, 24, 92, 21);
		Bname_text.setColumns(10);
		panel.add(Bname_text);

		JLabel label_2 = new JLabel("作者名：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_2.setBounds(249, 24, 63, 19);
		panel.add(label_2);

		Bauthor_text = new JTextField();
		Bauthor_text.setColumns(10);
		Bauthor_text.setBounds(322, 24, 92, 21);
		panel.add(Bauthor_text);

		JLabel label_3 = new JLabel("出版社：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_3.setBounds(249, 65, 63, 19);
		panel.add(label_3);

		Bpublish_text = new JTextField();
		Bpublish_text.setColumns(10);
		Bpublish_text.setBounds(322, 65, 92, 21);
		panel.add(Bpublish_text);

		JLabel label_4 = new JLabel("图书简介：");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_4.setBounds(22, 148, 70, 15);
		panel.add(label_4);

		JLabel label_5 = new JLabel("类别：");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_5.setBounds(22, 63, 63, 19);
		panel.add(label_5);

		Bsort_text = new JTextField();
		Bsort_text.setColumns(10);
		Bsort_text.setBounds(95, 63, 92, 21);
		panel.add(Bsort_text);

		JLabel label_6 = new JLabel("价格：");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_6.setBounds(22, 102, 63, 19);
		panel.add(label_6);

		Bprice_text = new JTextField();
		Bprice_text.setColumns(10);
		Bprice_text.setBounds(95, 102, 92, 21);
		panel.add(Bprice_text);

		Bcomment_text = new JTextArea();
		Bcomment_text.setRows(3);
		Bcomment_text.setText("请输入简介，50字以内。");
		Bcomment_text.setBounds(95, 144, 319, 48);
		panel.add(Bcomment_text);

		JButton button = new JButton("修改");
		button.setBounds(345, 58, 93, 23);
		getContentPane().add(button);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (b_change()) {
					JOptionPane.showMessageDialog(null, "更改成功啦！！");
				} else {
					JOptionPane.showMessageDialog(null, "请更改为已有作者或分类！！");
				}
			}
		});
	}

	public Boolean b_change() {
		boolean result = false;
		Connection conn = null;
		try {
			conn = Login.getCon(); // 建立数据库连接
			int flag = 0;
			String sqlupdate = "UPDATE book SET ";
			String where = " WHERE ISBN=" + textField.getText();
			//多重if判断文本框是否未空值，若不为空值，则更修改sqlupdate语句
			if (!Bname_text.getText().equals("")) {
				if (flag == 0) {
					sqlupdate = sqlupdate + "Bname='" + Bname_text.getText() + "'";
					flag = 1;
				}
			}
			if (!Bauthor_text.getText().equals("")) {
				if (flag == 0) {
					sqlupdate = sqlupdate + "Bauthor='" + Bauthor_text.getText() + "'";
					flag = 1;
				} else {
					sqlupdate = sqlupdate + "," + "Bauthor='" + Bauthor_text.getText() + "'";
				}
			}
			if (!Bpublish_text.getText().equals("")) {
				if (flag == 0) {
					sqlupdate = sqlupdate + "Bpublish='" + Bpublish_text.getText() + "'";
					flag = 1;
				} else {
					sqlupdate = sqlupdate + "," + "Bpublish='" + Bpublish_text.getText() + "'";
				}
			}
			if (!Bsort_text.getText().equals("")) {
				if (flag == 0) {
					sqlupdate = sqlupdate + "Bsort='" + Bsort_text.getText() + "'";
					flag = 1;
				} else {
					sqlupdate = sqlupdate + "," + "Bsort='" + Bsort_text.getText() + "'";
				}
			}
			if (!Bprice_text.getText().equals("")) {
				if (flag == 0) {
					sqlupdate = sqlupdate + "Bprice=" + Bprice_text.getText();
					flag = 1;
				} else {
					sqlupdate = sqlupdate + "," + "Bprice=" + Bprice_text.getText();
				}
			}
			if (!Bcomment_text.getText().equals("") && !Bcomment_text.getText().equals("请输入简介，50字以内。")) {
				if (flag == 0) {
					sqlupdate = sqlupdate + "Bcomment='" + Bcomment_text.getText() + "'";
					flag = 1;
				} else {
					sqlupdate = sqlupdate + "," + "Bcomment='" + Bcomment_text.getText() + "'";
				}
			}
			PreparedStatement stmt = conn.prepareStatement(sqlupdate); // 会抛出异常
			int i = stmt.executeUpdate(); //返回受影响行数
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // finally的用处是不管程序是否出现异常，都要执行finally语句，所以在此处关闭连接
			try {
				conn.close(); // 打开一个Connection连接后，最后一定要调用它的close（）方法关闭连接，以释放系统资源及数据库资源
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
