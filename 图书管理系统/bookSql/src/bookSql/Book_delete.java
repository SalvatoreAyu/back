package bookSql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Book_delete extends JFrame {
	private JTextField ISBN_text;
	private JTextField Bname_text;

	/**
	 * Initialize the contents of the frame.
	 */
	public Book_delete() {
		setTitle("图书删除");
		setVisible(true);
		setBounds(100, 100, 396, 328);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(23, 30, 334, 73);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("书号（ISBN）：");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 27, 95, 15);
		panel.add(lblNewLabel_2);

		ISBN_text = new JTextField();
		ISBN_text.setBounds(101, 24, 125, 21);
		panel.add(ISBN_text);
		ISBN_text.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(22, 144, 334, 83);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel label = new JLabel("书名：");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(10, 37, 95, 15);
		panel_1.add(label);

		Bname_text = new JTextField();
		Bname_text.setColumns(10);
		Bname_text.setBounds(101, 34, 125, 21);
		panel_1.add(Bname_text);

		JLabel lblNewLabel = new JLabel("按书号（ISBN）删除");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel.setBounds(22, 10, 143, 15);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("按书名删除");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(22, 124, 143, 15);
		getContentPane().add(lblNewLabel_1);

		JButton button = new JButton("删除");
		button.setBounds(164, 246, 93, 23);
		getContentPane().add(button);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (b_delete()) {
					JOptionPane.showMessageDialog(null, "删除成功啦！！");
				}
			}
		});
	}

	public Boolean b_delete() {
		boolean result = false;
		Connection conn = null;
		try {
			conn = Login.getCon(); // 建立数据库连接
			String sqldelete;
			
			if (ISBN_text.getText() != null) {
				sqldelete = "delete from Book  where ISBN =" + ISBN_text.getText();  //按ISBN删除
			} else {
				sqldelete = "delete from Book where Bname ='" + Bname_text.getText() + "'"; //按书名删除
			}

			PreparedStatement stmt = conn.prepareStatement(sqldelete); 
			int i = stmt.executeUpdate();
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
