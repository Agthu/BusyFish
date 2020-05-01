package ui;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import server.DbUtil;
import server.StringUtil;
import data.User;


 
public class RegisterFrame extends JFrame {
	DbUtil dbutil = new DbUtil();
	//CustomerUserDao customerUserDao=new CustomerUserDao();
	
	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
 
	public static void main(String[] args) {
		RegisterFrame frame = new RegisterFrame();
		frame.setVisible(true);
	}
 
	public RegisterFrame() {
		setTitle("用户注册");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(6, 1, 5, 5));
 
		JPanel panel1 = new JPanel();
		contentPane.add(panel1);
 
		JLabel label1 = new JLabel("新用户注册");
		panel1.add(label1);
 
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(panel2);
 
		JLabel label2 = new JLabel("帐         号");
		panel2.add(label2);
 
		textField1 = new JTextField();
		panel2.add(textField1);
		textField1.setColumns(18);
		/*
		 * 帐号界面引用 
		 */
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(panel3);
 
		JLabel label3 = new JLabel("昵         称");
		panel3.add(label3);
 
		textField2= new JTextField();
		panel3.add(textField2);
		textField2.setColumns(18);
		/*
		 * 昵称界面引用 
		 */
		
		
		JPanel panel4 = new JPanel();
		panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(panel4);
 
		JLabel label4 = new JLabel("密         码");
		panel4.add(label4);
 
		passwordField1 = new JPasswordField();
		passwordField1.setColumns(18);
		panel4.add(passwordField1);
		/*
		 * 密码界面引用 
		 */
		
 
		JPanel panel5 = new JPanel();
		panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(panel5);
 
		JLabel label5 = new JLabel("确认密码");
		panel5.add(label5);
 
		passwordField2 = new JPasswordField();
		passwordField2.setColumns(18);
		panel5.add(passwordField2);
		/*
		 * 确认密码界面引用 
		 */
		
 
		JPanel panel6 = new JPanel();
		contentPane.add(panel6);
 
		JButton button = new JButton("注册");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				do_button_actionPerformed(e1);
			}
		});
		panel6.add(button);	}
		/*
		 * 注册界面引用 
		 */
	
	public ResultSet userList(Connection con,User user)throws Exception{
		StringBuffer sb=new StringBuffer("select * from account");
		if(StringUtil.isNotEmtpty(user.getId())){
			sb.append(" and id like '%"+user.getId()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
/*
 * 查询数据库是否有相同用户名
 */
	public int userAdd(Connection con,User user)throws Exception{
		String sql="insert into account values(?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getPassword());
		return pstmt.executeUpdate();
	}/*
	*将注册的信息插入数据库中
	*/
		protected void do_button_actionPerformed(ActionEvent e1) {
		
		String  id=this.textField1.getText();
		String  name=this.textField2.getText();
		String password=new String(this.passwordField1.getPassword());
		User user=new User(  id,  name,  password );
		Connection con = null;
        try {
        	con=dbutil.getDatabaseConnection();
        	ResultSet rs = userList(con, user);
        	while(rs.next()){   
				String accountId1=rs.getString("id"); //查找数据库中是否有相同用户名
				if(id.equals(accountId1)){
					

					JOptionPane.showMessageDialog(null, "用户名已存在！");
										return;
									}}
										con = DbUtil.getDatabaseConnection();			
										

										int 	addNum=userAdd(con, user);
										if(addNum==1){
											

					JOptionPane.showMessageDialog(null, "注册成功");
											this.dispose();
										}else{
											

					JOptionPane.showMessageDialog(null, "注册失败");
										}
									
									
									
								
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "注册失败");
		}finally{
			try {
				dbutil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
	}}
