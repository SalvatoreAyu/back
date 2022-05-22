package bookSql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class Book_borrow extends JFrame {
	private JTextField textField;
	private ResultSet r;
	private JTextField textField_1;
	private JTextArea textArea;
	/**
	 * Initialize the contents of the frame.
	 */
	public Book_borrow() {
		setTitle("图书借出");
		setVisible(true);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 21, 387, 47);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("请输入借书书号（ISBN）：");
		lblNewLabel.setBounds(10, 10, 156, 27);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		textField = new JTextField();
		textField.setBounds(176, 13, 156, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("查看");
		 button.setBounds(196, 216, 93, 23);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("借出");
		button_1.setBounds(315, 216, 93, 23);
		getContentPane().add(button_1);
		
	    textArea = new JTextArea();
	    textArea.setText("书号         书名         作者      价格");
		textArea.setBounds(21, 159, 387, 47);
		getContentPane().add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("存有状态：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(21, 137, 76, 23);
		getContentPane().add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(21, 80, 387, 47);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblid = new JLabel("请输入借书证号（ID）：");
		lblid.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblid.setBounds(10, 10, 156, 27);
		panel_1.add(lblid);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(176, 13, 156, 21);
		panel_1.add(textField_1);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!b_borrow1()){
					JOptionPane.showMessageDialog(null,"此书可以借阅"); 
				}
				else {
					JOptionPane.showMessageDialog(null,"该书已被借出，请选择其他书");
				}
		} 
		 });	
	
		button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(b_borrow2()){
				JOptionPane.showMessageDialog(null,"借书成功啦！！"); 
			}
			else {
				JOptionPane.showMessageDialog(null,"没有此书或者已经被借走啦！");
			}
	} 
	 });		
		
	}
	
		public Boolean b_borrow1() {
					boolean result = false;
		           Connection conn = null;
		         try {	        
		           conn = Login.getCon();  //建立数据库连接
		           String sqlrequire;
		           sqlrequire = "select *" + 
		           		"from lend " + 
		           		"where ISBN ="+textField.getText()+"  and rtn is  null";;          
		          PreparedStatement stmt = conn.prepareStatement(sqlrequire);   //会抛出异常   
		           r= stmt.executeQuery();  
		          if (r.next()) {
		        	  result=true;
		              String s1 =r.getString(1);
		              String s2 =r.getString(2);	            
		              String s3 = r.getString(3);
		              String s4 = r.getString(4);
		              textArea.append("\n"+s1+"  "+s2+"   "+s3+"   "+s4);   
		          }
		          else {
		        	  result=false;
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
		          return result;  }
		          
			public Boolean b_borrow2(){ 
				 boolean result = false;
				  LocalDate ld=LocalDate.now();
				    Connection conn = null;
				         try {	    
				        	 conn = Login.getCon();
				        	 String sqlInset = "insert into Lend(ID,ISBN,Ld)"
			          		+ "values('"+textField_1.getText()+"','"+textField.getText()+"','"+ld+"')";   
				 PreparedStatement stmt = conn.prepareStatement(sqlInset); 
		          int i = stmt.executeUpdate();
		          if(i==1) result=true;
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
