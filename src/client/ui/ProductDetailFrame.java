package client.ui;

import java.io.IOException;

import javax.swing.GroupLayout.Alignment;
import javax.swing.*;

import client.UserClient;
import data.Product;
import data.User;
public class ProductDetailFrame extends javax.swing.JFrame {
    public void main(String[] args) throws IOException, ClassNotFoundException{
        ProductDetailFrame f = new ProductDetailFrame(user,  id);
    }
    private  UserClient user;
    public  int id;
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
    JButton comment;

    public ProductDetailFrame(UserClient user, int id, String name, String publisherId, String description, double price,int bought) throws IOException, ClassNotFoundException{
		// 当前用户
		this.user = user;
		this.id=id;
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
        confirm = new JButton("确定");
        comment = new JButton("评论");
        product_name.setEditable(false);
        product_description.setEditable(false);
        product_price.setEditable(false);
        product_id.setEditable(false);
        product_seller.setEditable(false);
       /*
        * 设置不可编辑 
        */
      Product product = new Product( id,  name,  publisherId, description, price,bought);
        product_name.setText(product.getName());                                   
        product_description.setText(product.getDescription());
        product_price.setText(String.valueOf(product.getPrice()));           
        product_id.setText(String.valueOf(product.getId()));
        product_seller.setText(product.getPublisherId());
        /*
         * 在客户端显示内容
         */
        
        
        product_description.setColumns(20);
        product_description.setRows(5);
        confirm.addActionListener(new java.awt.event.ActionListener() {                // 设置  确定监听器
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				AddActionPerformed(evt);
			}
		});
       comment.addActionListener(new java.awt.event.ActionListener() {                // 设置  评论监听器
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					commentActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
                .addComponent(product_id).addComponent(product_description).addComponent(product_price).addComponent(product_seller).addComponent(comment).addComponent(confirm));
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
                .addComponent(comment));
        vGroup.addGroup(layout.createParallelGroup(Alignment.TRAILING)
                .addComponent(confirm));
        vGroup.addGap(10);
        //设置垂直组
        layout.setVerticalGroup(vGroup);
    }
  
	private void AddActionPerformed(java.awt.event.ActionEvent evt) {             //设置点击 确定 后的触发事件(返回商品列表)
    	dispose();                                                                  //关闭该窗口   
    }   
    private void commentActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {             //设置点击 评论 后的触发事件
    	new ProductCommentFrame(user,id).setVisible(true);
    	
    }  
  
    
    
}
