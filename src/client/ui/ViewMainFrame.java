package client.ui;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import client.UserClient;

import data.User;
public class ViewMainFrame extends javax.swing.JFrame {
	private   UserClient user;
	public ViewMainFrame(UserClient user) {
		this.user=user;
		 //改变系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 18);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		initComponents();
		JLayeredPane layeredPane;
		//创建一个Panel和一个Label用于存放图片，作为背景。
		JPanel jp;
		JLabel jl;
		ImageIcon image;
		layeredPane=new JLayeredPane();
		image=new ImageIcon("images\\bg2.jpg");//加入背景图片		
		//创建背景的那些东西
		jp=new JPanel();
		jp.setBounds(0,0,image.getIconWidth(),image.getIconHeight());	
		jl=new JLabel(image);
		jp.add(jl);			
		//将jp放到最底层。
		layeredPane.add(jp,JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(jLabel1,JLayeredPane.MODAL_LAYER);
		layeredPane.add(jb_buyer,JLayeredPane.MODAL_LAYER);
		layeredPane.add(jb_seller,JLayeredPane.MODAL_LAYER);
		layeredPane.add(jButton,JLayeredPane.MODAL_LAYER);   		
		this.setLayeredPane(layeredPane);
		this.setSize(550,350);
		this.setLocationRelativeTo(null);
	}	
	private void initComponents() {		
		jLabel1 = new javax.swing.JLabel();
		jb_buyer = new javax.swing.JButton();
		jb_seller = new javax.swing.JButton();
		jButton = new javax.swing.JButton();
		

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		jLabel1.setFont(new java.awt.Font("宋体", Font.BOLD, 40));
		jLabel1.setText("BusyFish");
		jLabel1.setIcon(new javax.swing.ImageIcon("./images\\fish.jpg")); //  设置搜索按钮的图片
		
		jb_buyer.setText("我是买家");
		jb_buyer.addActionListener(new java.awt.event.ActionListener() {                //设置 我是买家监听器
			public void actionPerformed(java.awt.event.ActionEvent evt) {              
				try {
					jb_BuyProductActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		});
		jb_seller.setText("我是卖家"); 
		jb_seller.addActionListener(new java.awt.event.ActionListener() {                // 设置 我是卖家监听器
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jb_AddProductActionPerformed(evt);
			}
		});
		jButton.setText("我的消息");		
		jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {                 //  设置 我的消息 监听器
				jb_MessagetActionPerformed(evt);
			}
		});
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(   //创建GroupLayout的水平连续组
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(  //指示元素从原点对齐
				layout.createSequentialGroup().addContainerGap(111,Short.MAX_VALUE).
						addComponent(jLabel1,javax.swing.GroupLayout.PREFERRED_SIZE, 300,javax.swing.GroupLayout.PREFERRED_SIZE)
						/*
						 * 控制JLabel水平连续组的对应的间隙
						 */
					    .addContainerGap()).addGroup(
				layout.createSequentialGroup().addGap(27, 27, 27).addComponent(jb_buyer).
				addGap(57, 57, 57).addComponent(jb_seller).
                addGap(57,57,57).addComponent(jButton).addGap(22, 22,22))); //控制JButton水平连续组对应的间隙
		layout
				.setVerticalGroup(layout                             //创建GroupLayout的垂直连续组
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(29, 29, 29)//控制JLabel和顶端距离
				        .addComponent(jLabel1,javax.swing.GroupLayout.PREFERRED_SIZE,130,javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(27, 27, 27)//控制JLabel和JButton距离
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jb_buyer)
														.addComponent(jb_seller)
														.addComponent(jButton))
										.addContainerGap(87, Short.MAX_VALUE))); // 控制JButton和底部距离

		pack();
	}
	public void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {                      //
			public void run() {
				new ViewMainFrame(user).setVisible(true);
			}
		});
	}
	private void jb_BuyProductActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {             //设置点击 我是买家 后的触发事件
		new BuyProductFrame(user).setVisible(true);                                           
	}                                                             
	private void jb_AddProductActionPerformed(java.awt.event.ActionEvent evt) {             //设置点击 我是卖家 后的触发事件
		new AddProductFrame(user).setVisible(true); 
	}
	private void jb_MessagetActionPerformed(java.awt.event.ActionEvent evt) {             //设置点击 我的 后的触发事件	
            	new MessageFrame(user).setVisible(true);                          //点击消息后的触发事件       
        ;
	}
	
	private javax.swing.JButton jButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JButton jb_seller;
	private javax.swing.JButton jb_buyer;
}
