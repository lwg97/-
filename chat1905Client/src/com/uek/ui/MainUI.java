package com.uek.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uek.model.Crowd;
import com.uek.model.Member;
import com.uek.utils.FrameUtils;
import com.uek.utils.MessageUtil;

public class MainUI extends JFrame{
	
	private JPanel jp1 , jp2 ,jp3 ,jp4;
	
	private JLabel jl11 , jl12 , jl13;
	
	private JLabel jl21;
	
	private JButton jb41 , jb42;
	
	private List<Member> selfAndfriends;
	
	private List<Crowd> crowds;
	
	//可以认为是规范，一个公司内部的规范
	//将定义的控件 new出来 并且排列组合好
	public void init(List<Member> selfAndfriends , List<Crowd> crowds)
	{
		//这俩行代码是后边加上的，这样将来加好友或者加群的时候，服务器没必要发完整的好友列表和群列表，只需要发单个好友和群就好
		this.selfAndfriends = selfAndfriends;
		this.crowds =crowds;
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		
		
		jl11 = new JLabel();
		jl12 = new JLabel();
		jl13 = new JLabel();
		
		jl21 = new JLabel();
		
		jb41 = new JButton("加好友|群");
		
		final Member self = selfAndfriends.get(0);
		
		jb41.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SearchUI searchUI = new SearchUI();
				searchUI.init(self);
				
				//缓存搜索页面
				FrameUtils.searchUI = searchUI;
			}
		});
		
		
		jb42 = new JButton("创建群");
		
		jb42.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				CreateCrowdUI createCrowdUI = new CreateCrowdUI();
				createCrowdUI.init(self);
				
				FrameUtils.createCrowdUI = createCrowdUI;
				
			}
		});
		
		
		//准备设置绝对布局
		this.setLayout(null);
		
		//设置面板在父容器的位置，并且设置自己的宽高  5,5是右上角相对于父容器的位置 起始位置
		jp1.setBounds(5, 5, 275, 60);
		//设置面板的边框
		jp1.setBorder(BorderFactory.createLineBorder(Color.red));
		
		//jp1必须设置绝对布局，也就是把默认的FlowLayout去掉
		jp1.setLayout(null);
		
		jl11.setBounds(5, 5, 40, 40);
		//设置显示图片
		jl11.setIcon(new ImageIcon("headers\\header1.png"));
		jl11.setBorder(BorderFactory.createLineBorder(Color.red));
		jp1.add(jl11);
		
		
		jl12.setText(selfAndfriends.get(0).getName());//等价于new Label("软院");
		jl12.setBounds(60, 5, 40, 20);
		jp1.add(jl12);
		
		jl13.setText(selfAndfriends.get(0).getSignature());//等价于new Label("软院");
		jl13.setBounds(60, 20, 120, 40);
		jp1.add(jl13);
		
		this.add(jp1);
		
		jp2.setLayout(null);
		
		jp2.setBounds(5, 70, 275, 30);
		jp2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		
		jl21.setText("输入好友名...");
		jl21.setBounds(5, 5, 260, 20);
		jp2.add(jl21);
		
		this.add(jp2);
				
	
		jp3.setBounds(5, 105, 275, 400);
		jp3.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		
		jp3.setLayout(null);
		
		//因为后续经常要更新好友列表和群列表，所以单独抽取出一个方法方便调用
		initFriendListAndCrowdList(selfAndfriends, crowds);
			
		jp4.setBounds(5, 510, 275, 40);
		jp4.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		
		
		jp4.setLayout(null);
		
		jb41.setBounds(5, 5 , 100 , 30);//只给坐标定位，不给size setLocation+setSize=setBounds
		jp4.add(jb41);
		
		jb42.setBounds(110, 5 , 100 , 30);//只给坐标定位，不给size setLocation+setSize=setBounds
		jp4.add(jb42);
		
		this.add(jp4);

				
		//this 就是 
		//JFrame public class MainUI extends JFrame
		this.setTitle(selfAndfriends.get(0).getName()+"的主界面");
		this.setSize(300, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}



	public void initFriendListAndCrowdList(List<Member> selfAndfriends,
			List<Crowd> crowds) {
		
		jp3.removeAll();
		
		final Member self = selfAndfriends.get(0);
		
		//去掉自己，剩下好友，准备好友列表
		List<Member> friends = selfAndfriends.subList(1, selfAndfriends.size());
		
		int i=0;
		for ( ; i<friends.size() ; i++) {
			
			final Member friend = friends.get(i);
			
			JPanel jp31 = new JPanel();
			JLabel jl311 = new JLabel();
			JLabel jl312 = new JLabel();
			JLabel jl313 = new JLabel();
			
			jp31.setBounds(5, 5+i*45, 265, 50);
			jp31.setBorder(BorderFactory.createLineBorder(Color.red));
			jp31.setLayout(null);
			
			//适配器的设计模式 
			//如果实现MouseListener需要实现5个方法，实际我只关心单击方法 其他4个浪费
			//所以有个适配器类，MouseAdpater
			jp31.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					//如果没有打开过该好友的聊天窗口，就打开，否则就不打开了
					if(!FrameUtils.friendId2ChatUI.containsKey(friend.getId()))
					{
						ChatUI chatUI = new ChatUI();
						chatUI.init(self,friend);
						
						FrameUtils.friendId2ChatUI.put(friend.getId(), chatUI);
					}
				
					
				}
			});
			
			jl311.setBounds(5, 5, 40, 40);
			
			jl311.setIcon(new ImageIcon("headers\\header1.png"));
			jl311.setBorder(BorderFactory.createLineBorder(Color.red));
			jp31.add(jl311);
			
			
			jl312.setText(friend.getName());
			jl312.setBounds(60, 5, 40, 20);
			jp31.add(jl312);
			
			jl313.setText(friend.getSignature());
			jl313.setBounds(60, 20, 120, 40);
			jp31.add(jl313);
					
			jp3.add(jp31);
			
		}
		
		//好友列表后边你再追加群列表
		for (int j=i ; j<i+crowds.size() ; j++) {
			
			final Crowd crowd = crowds.get(j-i);
			
			JPanel jp31 = new JPanel();
			JLabel jl311 = new JLabel();
			JLabel jl312 = new JLabel();
			JLabel jl313 = new JLabel();
			
			jp31.setBounds(5, 5+j*45, 265, 50);
			jp31.setBorder(BorderFactory.createLineBorder(Color.red));
			jp31.setLayout(null);
			
			//适配器的设计模式 
			//如果实现MouseListener需要实现5个方法，实际我只关心单击方法 其他4个浪费
			//所以有个适配器类，MouseAdpater
			jp31.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					//如果没有打开过该群的聊天窗口，就和服务器发送请求，请求该群成员，否则就不打开了
					if(!FrameUtils.CrowdId2CrowdChatUI.containsKey(crowd.getId()))
					{
						String protoMessage = "9,"+crowd.getId();
						
						MessageUtil.sendMessage(protoMessage);
						
					}else
					{
						CrowdChatUI crowdChatUI = FrameUtils.CrowdId2CrowdChatUI.get(crowd.getId());
						
						crowdChatUI.setVisible(true);
					}
				
					
				}
			});
			
			jl311.setBounds(5, 5, 40, 40);
			
			jl311.setIcon(new ImageIcon("headers\\header1.png"));
			jl311.setBorder(BorderFactory.createLineBorder(Color.red));
			jp31.add(jl311);
			
			
			jl312.setText("群: "+crowd.getCrowdName());
			jl312.setBounds(60, 5, 40, 20);
			jp31.add(jl312);
			
			jl313.setText(crowd.getDescription());
			jl313.setBounds(60, 20, 120, 40);
			jp31.add(jl313);
					
			jp3.add(jp31);
			
		}

		jp3.updateUI();
		
		this.add(jp3);
	}
	
	
	
	public JPanel getJp3() {
		return jp3;
	}

	

	public List<Member> getSelfAndfriends() {
		return selfAndfriends;
	}



	public void setSelfAndfriends(List<Member> selfAndfriends) {
		this.selfAndfriends = selfAndfriends;
	}



	public List<Crowd> getCrowds() {
		return crowds;
	}



	public void setCrowds(List<Crowd> crowds) {
		this.crowds = crowds;
	}



	public static void main(String[] args) {
		MainUI mainUI = new MainUI();
//		mainUI.init();
	}
	
	
	
}
