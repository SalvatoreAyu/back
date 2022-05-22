package bookSql;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class IDcard_new extends JFrame{
	private JTextArea textArea ;
	private JTextArea textArea_1 ;
	private JTextArea textArea_2 ;
	private JTextArea textArea_3 ;
	private JTextArea textArea_4 ;
	/**
	 * Create the application.
	 */
	public IDcard_new() {
		setTitle("借书卡办理");
		setVisible(true);
		setBounds(100, 100, 251, 337);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
		JLabel label = new JLabel("性别：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 108, 95, 15);
		getContentPane().add(label);
		
		textArea = new JTextArea();
		textArea.setColumns(10);
		textArea.setBounds(114, 32, 83, 24);
		getContentPane().add(textArea);
		
		JLabel label_1 = new JLabel("单位：");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 146, 96, 15);
		getContentPane().add(label_1);
		
		textArea_1 = new JTextArea();
		textArea_1.setColumns(10);
		textArea_1.setBounds(114, 66, 83, 24);
		getContentPane().add(textArea_1);
		
		textArea_2 = new JTextArea();
		textArea_2.setColumns(10);
		textArea_2.setBounds(114, 104, 83, 24);
		getContentPane().add(textArea_2);
		
		textArea_3 = new JTextArea();
		textArea_3.setColumns(10);
		textArea_3.setBounds(114, 142, 83, 24);
		getContentPane().add(textArea_3);
		
		JLabel label_3 = new JLabel("姓名：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(10, 70, 95, 15);
		getContentPane().add(label_3);
		
		textArea_4 = new JTextArea();
		textArea_4.setColumns(10);
		textArea_4.setBounds(114, 176, 83, 24);
		getContentPane().add(textArea_4);
		
		JButton button = new JButton("登记");
		button.setBounds(74, 231, 84, 23);
		getContentPane().add(button);
		
		JLabel label_2 = new JLabel("职业：");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_2.setBounds(10, 180, 96, 15);
		getContentPane().add(label_2);
		
		JLabel label_4 = new JLabel("ID：");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_4.setBounds(10, 36, 84, 15);
		getContentPane().add(label_4);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(ID_insert()){
					JOptionPane.showMessageDialog(null,"登记成功"); 
				}
		} 
		 });			
	}
	
	 public Boolean ID_insert() {
		  boolean result = false;
		           Connection conn = null;
		         try {	        
		           conn = Login.getCon();  //建立数据库连接
		          String sqlInset = "insert into IDCard(ID,sex,danwei,job,name)  "
		          		+ "values('"+textArea.getText()+"','"+textArea_2.getText()+"','"
		        		  +textArea_3.getText()+"','"+textArea_4.getText()+"','"+textArea_1.getText()+"')";
		          PreparedStatement stmt = conn.prepareStatement(sqlInset);   //会抛出异常    
		          int i = stmt.executeUpdate();           
		          if (i == 1) {
		              result = true;
		          }
		          } catch (SQLException e) {
		              // TODO Auto-generated catch block
		              e.printStackTrace();
		           } finally { 
		               try {
		                   conn.close(); 
		              } catch(SQLException e) {
		                   e.printStackTrace();
		              }
		          }
		          
		          return result;        
		}
		
}

