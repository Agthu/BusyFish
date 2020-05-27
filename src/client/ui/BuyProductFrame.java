package client.ui;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import client.UserClient;
public class BuyProductFrame extends javax.swing.JFrame {
	public static int id;
	private UserClient user;
	public static  String productname;
	public static String productseller;
	public static String productdescription="dqwaaa";
	public static String productprice;
	public BuyProductFrame(UserClient user) {
		// 当前用户
		this.user = user;
		initComponents();



		this.setLocationRelativeTo(null);                  //居中显示 
	}
	private void initComponents() {
		
jPanel = new javax.swing.JPanel();
Image image=new ImageIcon("images\\bg1.jpg").getImage();                 //创建图片对象
jPanel = new BackgroundPanel(image);                                    // 加入背景图片
jLabel1 = new javax.swing.JLabel();
s_productNameTxt = new javax.swing.JTextField();
jLabel2 = new javax.swing.JLabel();
jb_search = new javax.swing.JButton();
jScrollPane1 = new javax.swing.JScrollPane();
productTable = new javax.swing.JTable();
jb_cost = new javax.swing.JButton();
jb_detail = new javax.swing.JButton();
jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("交易广场"));    //设置标题为 交易广场  
jLabel1.setText("搜索商品");
jLabel2.setText("商品类别");
jb_search.setIcon(new javax.swing.ImageIcon("./images\\search.png")); //  设置搜索按钮的图片
jb_search.setText("搜索");
jb_search.addActionListener(new java.awt.event.ActionListener() {          //设置搜索按钮的监听器
public void actionPerformed(java.awt.event.ActionEvent evt) {                
jb_searchActionPerformed(evt);
}
});
productTable.setModel(new javax.swing.table.DefaultTableModel(                  //创建商品列表的表格
	new Object [][] {	},
	new String [] {
		 "商品名称", "发布人", "商品描述", "商品价格", "商品id"
	}
) {
	boolean[] canEdit = new boolean [] {
		 false, false, false, false,false
	};
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit [columnIndex];
	}
});
TableColumn idColumn= productTable.getColumnModel().getColumn(4);
idColumn.setWidth(0);
idColumn.setMaxWidth(0);
idColumn.setMinWidth(0);
productTable.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0); 
productTable.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);  
/*
 *  把商品列表中的id栏隐藏
 */

productTable.addMouseListener(new java.awt.event.MouseAdapter() {              //设置点击表格的监听器
public void mousePressed(java.awt.event.MouseEvent evt) {              
productTableMousePressed(evt);
}
});
jScrollPane1.setViewportView(productTable);            //使表格可以滚动

jb_cost.setIcon(new javax.swing.ImageIcon("./images\\shopcart.png")); //   添加购买按钮的图片
jb_cost.setText("购买");
jb_cost.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jb_costActionPerformed(evt);                                             //设置购买按钮监听器
}
});

jb_detail.setIcon(new javax.swing.ImageIcon("./images\\detail.png")); //   添加详情按钮的图片
jb_detail.setText("详情");
jb_detail.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jb_detailActionPerformed(evt);                                             //设置详情按钮监听器
}
});


javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel);
jPanel.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(                          //创建水平连续组
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addGap(20, 20, 20)
.addComponent(jLabel1)        //搜索商品按钮
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(s_productNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)//设置商品名称搜索框的长度
.addGap(18, 18, 18)
.addComponent(jb_cost)
.addGap(18, 18, 18)
.addComponent(jb_search)          //设置搜索按钮
.addGap(45, 45, 45)

.addComponent(jb_detail)
.addGap(50, 50, 50))
.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
);
jPanel1Layout.setVerticalGroup(         //创建垂直连续组
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jLabel1)
.addComponent(s_productNameTxt, 20,20,20)		 //设置商品搜索框的大小
.addComponent(jb_search)
.addComponent(jb_detail))                               
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)       
.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            					//设置表格高度
.addComponent(jb_cost)                        	// 
.addContainerGap(30, Short.MAX_VALUE)));        // 设置购买按钮下间隙

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
	
	
	private void jb_searchActionPerformed(java.awt.event.ActionEvent evt) {
		                                                       // 设置点击 搜索 后的触发事件 (根据商品名字查找商品)
	}public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {             
			public void run() {
				new BuyProductFrame(null).setVisible(true);
			}
		});}
	
	
	private void jb_costActionPerformed(java.awt.event.ActionEvent evt) {             //设置点击 购买 后的触发事件（进行数据库和网络操作）
		
	}
	private void productTableMousePressed(java.awt.event.MouseEvent evt) {    
		int row = this.productTable.getSelectedRow();            //获取鼠标放在表格中对应的行
		 productname =(String) productTable.getValueAt(row,0 );//   获取第1列的内容
		 productseller = (String) productTable.getValueAt(row,1 );//   获取第2列的内容
		 productdescription =(String) productTable.getValueAt(row,2 );//   获取第3列的内容
		 productprice = (String) productTable.getValueAt(row,3 );//   获取第4列的内容
		 id = (Integer) productTable.getValueAt(row,4 );           //   获取第5列（被隐藏）的内容
		
		
	}
	/*
	 * //设置点击 在商品列表 后的移动鼠标停放在对应商品的触发事件（用来选中表格中的商品）
	 */
   private void jb_detailActionPerformed(ActionEvent evt) {             //设置点击 详情 后的触发事件（进入商品详情界面）
	   
	   new ProductDetailFrame(null,null).setVisible(true); 
		
	}
	
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton jb_cost;
	private javax.swing.JButton jb_detail;
	private javax.swing.JButton jb_search;
	private javax.swing.JTable productTable;
	private javax.swing.JTextField s_productNameTxt;

}
