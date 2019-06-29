package com.uek.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.uek.model.Crowd;
import com.uek.model.Member;
import com.uek.utils.MessageUtil;

public class CrowdChatUI extends JFrame{
	
	private JPanel jp1 , jp2 ,jp3 ,jp4,jp5;
	
	private JButton jb21 , jb41, jb42;
	
	private JTextArea jta11 , jta31;
	
//	private Member self ;
//	private Member friend ;
//	
//	public ChatUI(final Member self , final Member friend)
//	{
//		this.self = self;
//		this.friend = friend;
//	}
	
	public void init(final Member self ,final Crowd crowd ,  final List<Member> crowdMembers)
	{
		this.setLayout(null);
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		
		jb21 = new JButton("发文件");
		
		jb41 = new JButton("重置");
		jb42 = new JButton("发送");
		jb42.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//1 接收界面输入参数
				String chatContent = jta31.getText();
				
				//2 拼接协议字符串
				String protoMessage = "10,"+self.getId()+","+crowd.getId()+","+chatContent;
				
				//3 发送
				MessageUtil.sendMessage(protoMessage);
			}
		});
		
		jp1.setBounds(5, 5, 250, 180);
		jp1.setBorder(BorderFactory.createLineBorder(Color.red));
		
		
		jp1.setLayout(null);
		
		jta11 = new JTextArea();
		jta11.setBounds(5, 5, 240, 170);
		jta11.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		
		jp1.add(jta11);
		
		this.add(jp1);
		
		jp2.setBounds(5, 190, 250, 40);
		jp2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		jp2.add(jb21);
		
		this.add(jp2);
		
	
		jp3.setBounds(5, 235, 250, 80);
		jp3.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		jp3.setLayout(null);
		
		jta31 = new JTextArea();
		jta31.setBounds(5, 5, 240, 70);
		jta31.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		jp3.add(jta31);
		
		this.add(jp3);
		
		jp4.setBounds(5, 320, 250, 40);
		jp4.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		
		jp4.add(jb41);
		jp4.add(jb42);
		
		this.add(jp4);
		
		jp5.setBounds(260, 5, 120, 350);
		jp5.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		
		initCrowdMembers(crowdMembers);
		
		this.add(jp5);
		
		this.setTitle(crowd.getCrowdName()+"的聊天");
		this.setSize(400, 400);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}



	public void initCrowdMembers(final List<Member> crowdMembers) {
		jp5.removeAll();
		
		for (Member member : crowdMembers) {
			
			JButton jb = new JButton(member.getNickname());
			
			jp5.add(jb);
		}
		
		jp5.updateUI();
	}
	
	
	
	public JTextArea getJta11() {
		return jta11;
	}


	

	public static void main(String[] args) {
		
		Crowd crowd = new Crowd();
		crowd.setId(1);
		crowd.setCrowdName("ubd1905班级群");
		
		Member m1 = new Member();
		m1.setNickname("m1");
		
		Member m2 = new Member();
		m2.setNickname("m22222");
		
		Member m3 = new Member();
		m3.setNickname("m3222222");
		
		List<Member> crowdMembers = new ArrayList<Member>();
		crowdMembers.add(m2);
		crowdMembers.add(m3);
		
		CrowdChatUI chatUI = new CrowdChatUI();
		chatUI.init(m1 , crowd  ,crowdMembers);
	}
	
	
}
