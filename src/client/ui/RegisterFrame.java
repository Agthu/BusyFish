package client.ui;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.border.EmptyBorder;
import javax.swing.*;

import client.UserClient;

public class RegisterFrame extends JFrame {
	private static JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private  UserClient user;
 
	public static void main(String[] args) {
		RegisterFrame frame = new RegisterFrame();

		frame.setVisible(true);
	}
 
	public RegisterFrame() {
		setTitle("用户注册");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
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
		 * 帐号输入界面引用 
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
		 * 昵称输入界面引用 
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
		 * 密码输入界面引用 
		 */
		
 
		JPanel panel5 = new JPanel();
		panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(panel5);
 
		JLabel label5 = new JLabel("确认密码  ");
		panel5.add(label5);
 
		passwordField2 = new JPasswordField();
		passwordField2.setColumns(18);
		panel5.add(passwordField2);
		/*
		 * 确认密码输入界面引用 
		 */
		
 
		JPanel panel6 = new JPanel();
		contentPane.add(panel6);
 
		JButton button = new JButton("注册");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				try {
					do_button_actionPerformed(e1);
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel6.add(button);	}
		/*
		 * 注册按钮界面引用 
		 */
	

	
		protected void do_button_actionPerformed(ActionEvent e1) throws IOException, ClassNotFoundException {	
			String  id=textField1.getText();
			String  name=textField2.getText();
			String password1=new String(passwordField1.getPassword());
			String password2=new String(passwordField2.getPassword());
			
			if (id.equals("")) {
				JOptionPane.showMessageDialog(null, "用户名不能为空");	
			} else
			if (name.equals("")) {
				JOptionPane.showMessageDialog(null, "昵称不能为空");
			} else
			if (password1.equals("")) {
				JOptionPane.showMessageDialog(null, "密码不能为空");
			} else
			if(!password2.equals(password1)){
				JOptionPane.showMessageDialog(null, "两次密码要相同");	  
			} else
			if(UserClient.register(textField1.getText(),textField2.getText(),passwordField1.getText())) {
				JOptionPane.showMessageDialog(null, "注册成功！");
			}
			else {
				JOptionPane.showMessageDialog(null, "失败");
			}
         }//添加注册后的事件
}
