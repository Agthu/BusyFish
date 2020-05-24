package client.ui;

import java.io.IOException;

import javax.swing.GroupLayout.Alignment;
import javax.swing.*;

import client.UserClient;
import data.Product;
public class ProductDetailFrame extends javax.swing.JFrame {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        ProductDetailFrame f = new ProductDetailFrame(null);
    }
    private  UserClient user;

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label6;
    JTextField product_name;
    JTextArea product_description;
    JTextField product_price;  
    JTextField product_id;
    JTextField product_seller;
    JTextField tf;
    JButton confirm;
    public ProductDetailFrame(UserClient user) throws IOException, ClassNotFoundException{
		// 当前用户
		this.user = user;
        this.setVisible(true);
        this.setSize(420, 320);
        this.setVisible(true);
        this.setLocation(400, 200);
        label1 = new JLabel("               商品详情");                                 // 添加按钮
        label2 = new JLabel("[商品名称]：");        
        label3 = new JLabel("[商品id]：");
        label4 = new JLabel("[商品描述]：");
        label5 = new JLabel("[商品价格]：");
        label6 = new JLabel("[发布人   ]：");
        product_name = new JTextField();
        product_description = new JTextArea();
        product_price = new JTextField();
        product_id = new JTextField();
        product_seller = new JTextField();
        user.getProById(BuyProductFrame.id);
        confirm = new JButton("确定");
        product_name.setEditable(false);
        product_description.setEditable(false);
        product_price.setEditable(false);
        product_id.setEditable(false);
        product_seller.setEditable(false);
       /*
        * 设置不可编辑 
        */
        product_description.setColumns(20);
        product_description.setRows(5);
        confirm.addActionListener(new java.awt.event.ActionListener() {                // 设置  确定监听器
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				AddActionPerformed(evt);
			}
		});
        // 为指定的 Container 创建 GroupLayout
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        //创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGap(5);//添加间隔
        hGroup.addGroup(layout.createParallelGroup().addComponent(label2)
                .addComponent(label3).addComponent(label4).addComponent(label5).addComponent(label6));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(product_name)
                .addComponent(product_id).addComponent(product_description).addComponent(product_price) .addComponent(product_seller).addComponent(confirm));
        hGroup.addGap(5);
        layout.setHorizontalGroup(hGroup);
        //创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label1));
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label2)
                .addComponent(product_name));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label3)
                .addComponent(product_id));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label4)
                .addComponent(product_description));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label5)
                .addComponent(product_price));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label6)
                .addComponent(product_seller));
        vGroup.addGroup(layout.createParallelGroup(Alignment.TRAILING)
                .addComponent(confirm));
        vGroup.addGap(10);
        //设置垂直组
        layout.setVerticalGroup(vGroup);
    }
    private void AddActionPerformed(java.awt.event.ActionEvent evt) {             //设置点击 确定 后的触发事件(返回商品列表)
    	dispose();                                                                  //关闭该窗口   
    }   
}
