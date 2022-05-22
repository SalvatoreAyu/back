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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Book_return extends JFrame{
	private JTextField textField;
	private ResultSet r;

	public Book_return() {
		setTitle("图书归还");
		setVisible(true);
		setBounds(100, 100, 450, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(20, 21, 387, 47);
		getContentPane().add(panel);
		
		JLabel lblisbn = new JLabel("请输入还书书号（ISBN）：");
		lblisbn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblisbn.setBounds(10, 10, 156, 27);
		panel.add(lblisbn);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(176, 13, 156, 21);
		panel.add(textField);
		
		JButton btnNewButton = new JButton("归还");
		btnNewButton.setBounds(314, 107, 93, 23);
		getContentPane().add(btnNewButton);
		

		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(b_return()){
				JOptionPane.showMessageDialog(null,"还书成功啦！！"); 
			}
	} 
	 });	
}

     public Boolean b_return() {
	  boolean result = false;
	           Connection conn = null;
	           LocalDate ld=LocalDate.now();
	         try {	        
	           conn = Login.getCon();  //建立数据库连接
	           String sqlupdate2=null;  
	           String sqlupdate1 = "select Ld  from lend  "
		           		+ " where ISBN ="+textField.getText()
		           		+"  and rtn is null";
	           PreparedStatement stmt = conn.prepareStatement(sqlupdate1);
	           r= stmt.executeQuery();
	           r.next();
	           String LD=r.getString(1); 
	           String today=ld.toString();
	           int diff1=LocalDate.parse(today).getDayOfYear();
	           int diff2=LocalDate.parse(LD).getDayOfYear();
	           int OVERTIME=diff1-diff2;
	           if(OVERTIME>=30) {JOptionPane.showMessageDialog(null,"你已超期，请及时缴清罚金"); }  
	           sqlupdate2 = "update  lend  set rtn='"+ld+"'"
	           		+ " where ISBN ="+textField.getText()
	           		+"  and rtn is null";
	          stmt = conn.prepareStatement(sqlupdate2);   //会抛出异常   
	          int i = stmt.executeUpdate();           
	          if (i==1) {
	              result = true;
	          
	          }}
	          
	           catch (SQLException e) {
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
