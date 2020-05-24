package client.ui;
import javax.swing.*;

import client.UserClient;

import java.awt.*;
import java.awt.event.*;
public class MessageFrame extends javax.swing.JFrame {
    public static void main(String[] args) {
        MessageFrame f = new MessageFrame(user);
    }
    private  UserClient user;
    
    public MessageFrame(UserClient user) {
		// 当前用户
		this.user = user;
		JFrame chat_frame = new JFrame();
		chat_frame.setSize(450,480);                              //设置大小
		chat_frame.setLocationRelativeTo(null);
		chat_frame.setLayout(new FlowLayout());
 
		JTextArea show_area = new JTextArea(10,28);                //设置聊天框大小
		JTextArea input_area = new JTextArea(5,28);                // 设置输入框大小
		JButton send = new JButton("发送消息");	         	
		chat_frame.add(show_area);
		chat_frame.add(input_area);
		chat_frame.add(send);
		chat_frame.setVisible(true);
		 show_area.setEditable(false);
	       /*
	        * 设置不可编辑 
	        */
		
		
	send.addActionListener(new java.awt.event.ActionListener() {                //设置 发送消息监听器
			public void actionPerformed(java.awt.event.ActionEvent evt) {              
				sendActionPerformed(evt);
			}
		});
 
	}
    private void sendActionPerformed(java.awt.event.ActionEvent evt) {             //设置点击 发送消息 后的触发事件
		                                          
	} 
}
