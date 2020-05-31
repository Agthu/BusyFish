package client.ui;
import javax.swing.*;

import client.UserClient;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class MessageFrame extends javax.swing.JFrame {
	JTextArea show_area = new JTextArea(10,28);                //设置聊天框大小
	JTextArea input_area = new JTextArea(5,28);                // 设置输入框大小
	JButton send = new JButton("发送消息");	  
	JLabel id=new JLabel(" 输入接收人id:");
	JTextField id_input=new JTextField(15);                     // 设置输入接收人id的输入框
	public static void main(String[] args) {
        MessageFrame f = new MessageFrame(null);
    }
    private  UserClient user;
    
    public MessageFrame(UserClient user) {
		// 当前用户
		this.user = user;
		JFrame chat_frame = new JFrame();
		chat_frame.setSize(450,520);                              //设置大小
		chat_frame.setLocationRelativeTo(null);
		chat_frame.setLayout(new FlowLayout());
		chat_frame.add(show_area);
		chat_frame.add(input_area);
		chat_frame.add(id);
		chat_frame.add(id_input);		
		chat_frame.add(send);
		chat_frame.setVisible(true);
		 show_area.setEditable(false);
	       /*
	        * 设置不可编辑 
	        */

		 //chat_frame.pack();
	send.addActionListener(new java.awt.event.ActionListener() {                //设置 发送消息监听器
			public void actionPerformed(java.awt.event.ActionEvent evt) {              
				sendActionPerformed(evt);
			}
		});
	}   
    private void sendActionPerformed(java.awt.event.ActionEvent evt) /*throws IOException*/ {             //设置点击 发送消息 后的触发事件
    	//user.sendMessage(id_input.getText(),input_area.getText()); 
    	show_area.append("\n"+input_area.getText());                                    //发送后显示框显示输入框内容
    	input_area.setText("");                                                      //发送后输入框清空
	
	} 
}
