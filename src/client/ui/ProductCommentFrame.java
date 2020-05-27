package client.ui;
import java.awt.Image;
import java.awt.event.ActionEvent;
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
public class ProductCommentFrame extends javax.swing.JFrame {
	public String productId;
	private UserClient user;
	public String publisher_name;
	public ProductCommentFrame(UserClient user) {
		// 当前用户
		this.user = user;
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
jb_commentActionPerformed(evt);                                             //设置我要评论按钮监听器
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
		public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {             
			public void run() {
				new ProductCommentFrame(null).setVisible(true);
			}
		});}
	
	
	public Comment jb_commentActionPerformed(java.awt.event.ActionEvent evt) {             //设置点击 立即评论 后的触发事件（进行数据库和网络操作）
		 String str= JOptionPane.showInputDialog(null, "请输入您的评论","评论", JOptionPane.PLAIN_MESSAGE);
		 Comment comment = new Comment(productId, publisher_name,str);
		 return comment;

	}
		
	
	private javax.swing.JPanel jPanel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton jb_comment;
	private javax.swing.JTable commentTable;


}
