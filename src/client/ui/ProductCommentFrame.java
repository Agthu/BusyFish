package client.ui;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


import client.UserClient;
import data.Comment;
import data.Product;
public class ProductCommentFrame extends javax.swing.JFrame {
	public  int id;
	private UserClient user;
	public ProductCommentFrame(UserClient user, int id) throws IOException, ClassNotFoundException {
		// 当前用户
		this.user = user;
		this.id=id;
		getcomment();
		initComponents();
		this.setLocationRelativeTo(null);                  //居中显示 
	}
	private void initComponents() {		
jPanel = new javax.swing.JPanel();
Image image=new ImageIcon("images\\bg3.jpg").getImage();                 //创建图片对象
jPanel = new BackgroundPanel(image);                                    // 加入背景图片
jScrollPane1 = new javax.swing.JScrollPane();
commentTable = new javax.swing.JTable();
jb_comment = new javax.swing.JButton();
jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("商品评论"));    //设置标题为商品评论 
commentTable.setModel(new javax.swing.table.DefaultTableModel(                  //创建表格
	new Object [][] {	},
	new String [] {
		 "发布人id", "评论"
	}
) {
	boolean[] canEdit = new boolean [] {
		 false, false,
	};
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit [columnIndex];
	}
});
TableColumn id= commentTable.getColumnModel().getColumn(0);
id.setWidth(200);
commentTable.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(200); 
commentTable.getTableHeader().getColumnModel().getColumn(0).setMinWidth(200);  
/*
 *  把id栏变短
 */

jScrollPane1.setViewportView(commentTable);            //使表格可以滚动

jb_comment.setText("我要评论");
jb_comment.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
try {
	jb_commentActionPerformed(evt);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}                                             //设置我要评论按钮监听器
}
});


javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel);
jPanel.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(                          //创建水平连续组
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()

.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addGap(320, 320, 320)
.addComponent(jb_comment)
.addGap(18, 18, 18)
)
.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
);
jPanel1Layout.setVerticalGroup(         //创建垂直连续组
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE))                               
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)       
.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)                         					//设置表格高度
.addComponent(jb_comment)                        	// 
.addContainerGap(30, Short.MAX_VALUE)));        // 设置我要评论按钮下间隙
javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
)                       //添加JPanel
;

pack();
}
	/*
	 * 设置布局
	 */
		public  void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {             
			public void run() {
				try {
					new ProductCommentFrame(user, id).setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});}
public void getcomment() throws IOException, ClassNotFoundException{
		 LinkedList<Comment> productList =user.getCommentOf(id);             //获取商品信息
		   DefaultTableModel dtm = (DefaultTableModel) commentTable.getModel();
		   dtm.setRowCount(0);//把前面的数据释放掉
		   for(Comment p: productList){
		   Vector v = new Vector();
			v.add(p.getPublisher_id());                      //将获得的商品名称填入表格
			v.add(p.getContent());                     //将获得的商品发布人填入表格          
			dtm.addRow(v);}
}
	public void jb_commentActionPerformed(java.awt.event.ActionEvent evt) throws IOException {             //设置点击 立即评论 后的触发事件（进行数据库和网络操作）
		 String str= JOptionPane.showInputDialog(null, "请输入您的评论","评论", JOptionPane.PLAIN_MESSAGE);	
		 user.addComment(id,str);
		 JOptionPane.showMessageDialog(null, "发表成功！");
	}
	
	private javax.swing.JPanel jPanel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton jb_comment;
	private javax.swing.JTable commentTable;


}
