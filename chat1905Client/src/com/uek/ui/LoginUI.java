package com.uek.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.uek.start.Client;
import com.uek.utils.FrameUtils;
import com.uek.utils.MessageUtil;

public class LoginUI extends JFrame{
	private JPanel jp1 ,jp2 , jp3;
	private JLabel jl1 , jl2;
	private JTextField jtf;
	private JPasswordField jpf;
	private JButton jb1 , jb2;
	
	//main + alt + /
	public void init() {
//		System.out.println(1);
		
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		
		
		//选控件
		jl1 = new JLabel("用户名");
		jl2 = new JLabel("密码");
		
		jtf = new JTextField(20);
		jpf = new JPasswordField(20);
		
		jb1 = new JButton("注册");
		
		//给jb1按钮添加一个事件响应，当点击jb1的时候执行监听器中的 actionPerformed
		//匿名内部类，实际是new了一个实现了ActionListener接口的一个类的对象，这个类叫什么呢？看不到？所以是匿名
		//匿名内部类的语法比较难理解
//		jb1.addActionListener(new A());
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				RegisterUI registerUI = new RegisterUI();
				registerUI.init();
				
				FrameUtils.registerUI = registerUI;
				
				//this代表的是当前类的实例 对象
				//LoginUI.this.setVisible(false);
				//因为注册之后很快要登陆 所以别dispose
				FrameUtils.loginUI.setVisible(false);
			}
		});
		
		jb2 = new JButton("登录");
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//1 接收界面输入
				String name = jtf.getText();
				String password = jpf.getText();//中划线代表过时的方法 过时的衣服可以穿
				
				
				//2 根据协议拼接字符串
				String  protoMessage = "2,login,"+name+","+password;
				
				//3 发送信息
				MessageUtil.sendMessage(protoMessage);
				
			}
		});
		
		//把控件放到容器界面
		//控件往容器中放的时候，有俩种思路，绝对布局和相对布局
		//先交大家相对布局 分为n种，需要掌握JFrame默认用的是BorderLayout 上下左右中
//		this.add(jl1 ,BorderLayout.WEST);
//		this.add(jl2,BorderLayout.EAST);
//		this.add(jtf,BorderLayout.CENTER);
//		this.add(jpf,BorderLayout.NORTH);
//		this.add(jb1,BorderLayout.SOUTH);
//		this.add(jb2);
		
		//控件加到面板
		jp1.add(jl1);
		jp1.add(jtf);
		
		
		//面板加到界面
		this.add(jp1 , BorderLayout.NORTH);
		
		
		
		jp2.add(jl2);
		jp2.add(jpf);
		
		this.add(jp2);
		
		jp3.add(jb1);
		jp3.add(jb2);
		this.add(jp3 , BorderLayout.SOUTH);
		
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}

	public JTextField getJtf() {
		return jtf;
	}

	public void setJtf(JTextField jtf) {
		this.jtf = jtf;
	}
	
	
	public static void main(String[] args) {
		
		LoginUI loginUI = new LoginUI();
		loginUI.init();
		
	}
	

}


