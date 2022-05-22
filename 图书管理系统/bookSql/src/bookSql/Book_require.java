package bookSql;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Book_require extends JFrame{
	private JTextField bname_text;
	private JTextField bsort_text;
	private JTextField textField_2;
	private JTextArea textArea;
	private ResultSet r;

	/**
	 * Create the application.
	 */
	public Book_require() {
		setTitle("图书查询页面");
		setVisible(true);
		setBounds(100, 100, 450, 558);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(22, 30, 385, 73);
		getContentPane().add(panel);
		
		JLabel label_bname = new JLabel("书名：");
		label_bname.setHorizontalAlignment(SwingConstants.CENTER);
		label_bname.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_bname.setBounds(10, 27, 95, 15);
		panel.add(label_bname);
		
		bname_text = new JTextField();
		bname_text.setColumns(10);
		bname_text.setBounds(101, 24, 125, 21);
		panel.add(bname_text);
		
		JButton button_bname = new JButton("查询");
		button_bname.setBounds(255, 15, 107, 50);
		panel.add(button_bname);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(21, 144, 388, 73);
		getContentPane().add(panel_1);
		
		JLabel label_1 = new JLabel("作者：");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setBounds(10, 37, 95, 15);
		panel_1.add(label_1);
		
		bsort_text = new JTextField();
		bsort_text.setColumns(10);
		bsort_text.setBounds(101, 34, 125, 21);
		panel_1.add(bsort_text);
		
		JButton button_4 = new JButton("查询");
		button_4.setBounds(257, 15, 105, 50);
		panel_1.add(button_4);
		
		JLabel label_bname_select = new JLabel("按书名查询图书");
		label_bname_select.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_bname_select.setBounds(21, 10, 143, 15);
		getContentPane().add(label_bname_select);
		
		JLabel label_3 = new JLabel("按分类查询图书");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_3.setBounds(21, 124, 143, 15);
		getContentPane().add(label_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(23, 270, 385, 73);
		getContentPane().add(panel_2);
		
		JLabel label_sort = new JLabel("这里可以查询所有图书");
		label_sort.setHorizontalAlignment(SwingConstants.CENTER);
		label_sort.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_sort.setBounds(10, 27, 200, 17);
		panel_2.add(label_sort);
		
		JButton button_5 = new JButton("查询");
		button_5.setBounds(255, 15, 104,50 );
		panel_2.add(button_5);
		
		JLabel label_all_select = new JLabel("查询所有图书");
		label_all_select.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_all_select.setBounds(22, 250, 143, 15);
		getContentPane().add(label_all_select);
		
		textArea = new JTextArea();
		textArea.setText("书号\t书名\t作者\t价格");
		textArea.setBounds(22, 370, 385, 123);
		getContentPane().add(textArea);
		

	
		button_bname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			     String sqlrequire;
			       sqlrequire =  "select ISBN,Bname,Bauthor,Bpublish,Bprice,Bcomment from book  " + 
				       		"where Bname='"+bname_text.getText()+"'"; 
			     
				if(!b_require(sqlrequire)){
					JOptionPane.showMessageDialog(null,"未找到记录"); 
				}
		} 
		 });	
		
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				  String sqlrequire;
			       sqlrequire = "select ISBN,Bname,Bauthor,Bpublish,Bprice,Bcomment from book  " + 
				       		"where Bsort='"+bsort_text.getText()+"'"; 
			     
				if(!b_require(sqlrequire)){
					JOptionPane.showMessageDialog(null,"未找到记录"); 
				}
		} 
		 });	
		
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				  String sqlrequire;
			       sqlrequire = "select ISBN,Bname,Bauthor,Bpublish,Bprice,Bcomment from book  ";
				if(!b_require(sqlrequire)){
					JOptionPane.showMessageDialog(null,"未找到记录"); 
				}
		} 
		 });
		
	}
	
	public Boolean b_require(String sqlrequire) {
		boolean result = false;
       Connection conn = null;
     try {	        
       conn = Login.getCon();  //建立数据库连接        
       PreparedStatement stmt = conn.prepareStatement(sqlrequire);   //会抛出异常   
       r= stmt.executeQuery();  
      while(r.next()) {
    	  result=true;
          String s1 =r.getString(1);
          String s2 =r.getString(2);	            
          String s3 = r.getString(3);
          String s4 = r.getString(5);
          textArea.append("\n"+s1+"\t"+s2+"\t"+s3+"\t"+s4);   
      } 
     } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
       } finally { //finally的用处是不管程序是否出现异常，都要执行finally语句，所以在此处关闭连接
           try {
               conn.close(); //打开一个Connection连接后，最后一定要调用它的close（）方法关闭连接，以释放系统资源及数据库资源
          } catch(SQLException e) {
               e.printStackTrace();
          }
      }
      return result;  }
}
