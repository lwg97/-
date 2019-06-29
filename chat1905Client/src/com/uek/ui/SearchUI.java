package com.uek.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.uek.model.Member;
import com.uek.utils.MessageUtil;

/**
 * 最佳實踐
 * @author 86139
 *
 */
public class SearchUI extends JFrame{
	
	//null 成员变量的默认值 基本数据类型  
	//int 0 boolean false doble 0.0 引用类型 null
	private JPanel jp1 , jp2;
	private JTextField jtf11;
	private JButton jb11 , jb12 ,jb13,jb14;
	private JTable jt21;
	private JScrollPane jsp21;
	
	public void init(final Member self)
	{
		jp1 = new JPanel();
		jp2 = new JPanel();
		
		
		jtf11 = new JTextField(20);
		jb11 = new JButton("搜索好友");
		jb11.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//1 接收界面输入 2 根据协议拼出来准备发送的信息字符串3 发送给服务器
				
				//1接收
				String nameKey = jtf11.getText();
				
				//2准备协议字符串
				
				String protoMessage = "3,search,"+nameKey;
				
				//3 发送
				MessageUtil.sendMessage(protoMessage);
				
			}
		});
		
		jb12 = new JButton("添加好友");
		jb12.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 得到选中table的某一行会员的id 没有选中返回-1
				int selectedRow = jt21.getSelectedRow();
				
				//先转Object成String，再把StringZ换成Integer 再Inteager自动拆箱
				int friendId =Integer.parseInt(jt21.getValueAt(selectedRow, 1).toString());
				
				
				// 准备给服务器发送的协议字符串
				String protoMessage = "4,"+self.getId()+","+friendId;
				
				MessageUtil.sendMessage(protoMessage);
				
			}
		});
		
		jb13 = new JButton("搜索群");
		jb13.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//1 接收界面输入 2 根据协议拼出来准备发送的信息字符串3 发送给服务器
				
				//1接收
				String crowdNameKey = jtf11.getText();
				
				//2准备协议字符串
				
				String protoMessage = "7,search,"+crowdNameKey;
				
				//3 发送
				MessageUtil.sendMessage(protoMessage);
				
			}
		});

		jb14 = new JButton("添加群");
		jb14.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 得到选中table的某一行会员的id 没有选中返回-1
				int selectedRow = jt21.getSelectedRow();
				
				//先转Object成String，再把StringZ换成Integer 再Inteager自动拆箱
				int crowdId =Integer.parseInt(jt21.getValueAt(selectedRow, 1).toString());
				
				
				// 准备给服务器发送的协议字符串
				String protoMessage = "8,"+self.getId()+","+crowdId;
				
				MessageUtil.sendMessage(protoMessage);
				
			}
		});
		jp1.add(jtf11);
		jp1.add(jb11);
		jp1.add(jb12);
		jp1.add(jb13);
		jp1.add(jb14);
		
		this.add(jp1,  BorderLayout.NORTH);
		
		//JTable表格控件的初始化一个重点，经过分析表格应该包括2部分：表头+多行的数据
//		jt21 = new JTable();
		
		//Vector底层实现就是数组 长度可变的数组！！！ <String>是泛型语法，规定数组必须存String元素 否则编译不通过
		Vector<String> tableHeaders = new Vector<String>();
		tableHeaders.add("序号");
		tableHeaders.add("名字");
		tableHeaders.add("昵称");
		tableHeaders.add("签名");
		tableHeaders.add("头像");
		
		Vector<Vector<String>> rows = new Vector<Vector<String>>();
		
		Vector<String> row1 = new Vector<String>();
		row1.add(1+"");
		row1.add("gosling");
		row1.add("gosling");
		row1.add("i am gosling");
		row1.add("1.png");
		
		Vector<String> row2 = new Vector<String>();
		row2.add(2+"");
		row2.add("linus");
		row2.add("linus");
		row2.add("i am linus");
		row2.add("2.png");
		
		rows.add(row1);
		rows.add(row2);
		
		jt21 = new JTable(rows  , tableHeaders);
		//jtable必须放到JScrollpane里边才能显示表头
		
		jsp21 = new JScrollPane(jt21);
		
		jp2.add(jsp21);
		this.add(jp2 , BorderLayout.CENTER);
		
		this.setTitle(self.getName()+"的搜索页面");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public void setJt21(JTable jt21) {
		this.jt21 = jt21;
	}


	public JPanel getJp2() {
		return jp2;
	}


	public static void main(String[] args) {
		SearchUI searchUI = new SearchUI();
//		searchUI.init();
	}
}
