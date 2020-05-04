package client.ui;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.*;
public class AddProductFrame extends javax.swing.JFrame {
    public static void main(String[] args) {
        AddProductFrame f = new AddProductFrame();
    }

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JTextField product_name;
    JTextField product_description;
    JTextField product_price;
    JTextField tf;
    JButton Add;

    public AddProductFrame() {
        this.setVisible(true);
        this.setSize(280, 220);
        this.setVisible(true);
        this.setLocation(400, 200);

        label1 = new JLabel(" 发布商品");                                 // 添加按钮
        label2 = new JLabel("商品名称：");
        label3 = new JLabel("商品描述：");
        label4 = new JLabel("商品价格：");
        product_name = new JTextField();
        product_description = new JTextField();
        product_price = new JTextField();
        Add = new JButton("发布");
        Add.addActionListener(new java.awt.event.ActionListener() {                // 设置 发布按钮监听器
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
                .addComponent(label3).addComponent(label4));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(product_name)
                .addComponent(product_description).addComponent(product_price).addComponent(Add));
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
                .addComponent(product_description));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label4)
                .addComponent(product_price));
        vGroup.addGroup(layout.createParallelGroup(Alignment.TRAILING)
                .addComponent(Add));
        vGroup.addGap(10);
        //设置垂直组
        layout.setVerticalGroup(vGroup);
       
    }
    private void AddActionPerformed(java.awt.event.ActionEvent evt) {             //设置点击 发布 后的触发事件
		                                      
	}   
}