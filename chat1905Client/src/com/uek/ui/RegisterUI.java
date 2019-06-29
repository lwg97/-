package com.uek.ui;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.uek.model.Member;
import com.uek.start.Client;

public class RegisterUI extends JFrame{
	
	private JLabel jl1 , jl2 ,jl3 ,jl4 ,jl5 ,jl6;
	private JTextField jtf1 , jtf2, jtf3,jtf4;
	private JPasswordField jpf1 , jpf2;
	private JButton jb1 ,jb2;
	
	
	public void init() {
		
		//The value of the local variable jl1 is not used
		//局部变量 jl1的值没有被用过 提示
		//因为每个局部变量开辟都需要内存，如果不用，浪费 代码质量的一方面
		jl1 = new JLabel("名字");
		jl2 = new JLabel("昵称");
		jl3 = new JLabel("签名");
		jl4 = new JLabel("密码");
		jl5 = new JLabel("确认");
		jl6 = new JLabel("头像");
		
		jtf1 = new JTextField(20);
		jtf2 = new JTextField(20);
		jtf3 = new JTextField(20);
		jtf4 = new JTextField(20);
		
		jpf1 = new JPasswordField(20);
		jpf2 = new JPasswordField(20);
		
		jb1 = new JButton("重置");
		jb2 = new JButton("注册");
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//接收页面填写的数据
				String name = jtf1.getText();
				String nickname = jtf2.getText();
				String sinature = jtf3.getText();
				String header = jtf4.getText();
				String password = jpf1.getText();
				String repassword = jpf2.getText();
				
				Member member = new Member(name,nickname,sinature,password,header);
				
				//当点击注册按钮的时候，不是直接连接数据库，因为客户端没有权限连接数据库，应该通过服务器
				//应该根据注册协议给服务器发送数据
				
				//PrintStream适合输出字符串
				PrintStream ps;
				try {
					ps = new PrintStream(Client.clientSocket.getOutputStream());
					
					ps.println("1,register");//根据协议先发送一个字符串，告诉服务器准备注册
					
					//ObjectOutputStream是对象输出流 可以直接把一个对象写出去
					ObjectOutputStream oos = new ObjectOutputStream(Client.clientSocket.getOutputStream());
					oos.writeObject(member);//注意必须实现序列化 才能用oos写
					oos.flush();
					
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
			}
		});
		
		//目前为止我们学习了BorderLayout 是JFrame的默认布局  上下左右中
		//其实还有一种相对布局是 FlowLayout 是JPanle的默认布局 从中间往俩边 放不下换行
		//相对布局中然后还有一种比较常用的GridLayout 表格布局 n行n列
		
		//修改JFrame的默认布局BorderLayout，改成GridLayout
		
		LayoutManager lm = new GridLayout(7, 2);
		this.setLayout(lm);
		
		this.add(jl1);this.add(jtf1);
		this.add(jl2);this.add(jtf2);
		this.add(jl3);this.add(jtf3);
		this.add(jl4);this.add(jpf1);
		this.add(jl5);this.add(jpf2);
		this.add(jl6);this.add(jtf4);
		
		this.add(jb1);this.add(jb2);
		
		//this.setSize(400,800);
		this.pack();//打包 自己不用写死长宽 ，以合适的大小打包 就好比去超市购物，根据购物的多少给个合适大小的袋子
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}
	
	
	public static void main(String[] args) {
		
		RegisterUI re = new RegisterUI();
		re.init();
		
	}
}
