package bookSql;

import java.awt.Font;
import java.awt.TextArea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class IDcard_select extends JFrame {
	private ResultSet r;
	private TextArea textArea;

	public IDcard_select() {
		setTitle("借记卡查询");
		System.out.println("sdf");
		setBounds(100, 100, 450, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		textArea=new TextArea();
		textArea.setText("ID\t性别\t单位\t工作\t姓名");
		textArea.setBounds(20, 20, 400, 150);
		add(textArea);
		setVisible(true);
		boolean result = false;
		Connection conn = null;
		try {
			conn = Login.getCon(); // 建立数据库连接
			String sqlInset = "select ID,sex,danwei,job,name from IDCard";
			PreparedStatement stmt = conn.prepareStatement(sqlInset); // 会抛出异常
			r = stmt.executeQuery();
			while (r.next()) {
				result = true;
				String s1 = r.getString(1);
				String s2 = r.getString(2);
				String s3 = r.getString(3);
				String s4 = r.getString(4);
				String s5 = r.getString(5);
				textArea.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				textArea.append("\n"+s1+"\t"+s2+"\t"+s3+"\t"+s4+"\t"+s5);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally的用处是不管程序是否出现异常，都要执行finally语句，所以在此处关闭连接
			try {
				conn.close(); // 打开一个Connection连接后，最后一定要调用它的close（）方法关闭连接，以释放系统资源及数据库资源
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
