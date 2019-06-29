package com.uek.start;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.uek.model.Crowd;
import com.uek.model.Member;
import com.uek.ui.ChatUI;
import com.uek.ui.CrowdChatUI;
import com.uek.ui.LoginUI;
import com.uek.ui.MainUI;
import com.uek.ui.SearchUI;
import com.uek.utils.FrameUtils;
import com.uek.utils.MessageUtil;

public class Client {

	public static Socket clientSocket;
	public static Member self;
	
	public static void main(String[] args) throws Exception{
		
		Socket socket = new Socket("localhost" , 8888);
		
		//将在客户端生成的socket缓存到Client类 做成public static，将来任何的地方要用，通过Client.clientSocket 
		Client.clientSocket = socket;
		
		new ClientThread(socket).start();
		
		LoginUI loginUI = new LoginUI();
		loginUI.init();
		
		//缓存登陆界面
		FrameUtils.loginUI = loginUI;
		
	}
}

class ClientThread extends Thread
{
	private Socket socket;
	
	public ClientThread(Socket socket)
	{
		this.socket = socket;
	}
	
	@Override
	public void run() {
		
		String line = null;
		//IO流 输入输出的工具 很多的api 类和接口 有很多种分法 
		//有一种是字节流和字符流 比如图片 视频只能用字节流读 纯文本既可以用字符流读 字节流读
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			

			//客户端不停的读取键盘输入，发送给服务器
			while( (line = br.readLine()) !=null )//这里会线程阻塞、17代码实际没有机会执行 所以需要把这里写到单独的线程
			{
				
				
				System.out.println("收到服务器的信息了:"+line);
				
				//1,ok  1,nook,username is exist	
				//2,zs,hello everyone 
				//3,zs,ls ,hello ls
				String[] strs = line.split(",");//split是字符串分割方法，返回字符串数组
				
				//注册
				if("1".equals(strs[0]))
				{
					//1,ok,zs  1,nook,username is exist	
					String okMessage = strs[1];
					String name = strs[2];
					
					if("ok".equals(okMessage))
					{
						System.out.println("注册成功，可以输入聊天信息了");
						
						//如果成功 1销毁注册界面 2显示登陆界面
						FrameUtils.registerUI.dispose();
						
						LoginUI loginUI = FrameUtils.loginUI;
						
						loginUI.getJtf().setText(name);
						
						loginUI.setVisible(true);
						
					}
					
					if("nook".equals(okMessage))
					{
						//弹出款显示不成功
						JOptionPane.showMessageDialog(null, strs[2]);
					}
				}
				
				//登录 客户端接收服务器的登录反馈
				if("2".equals(strs[0]))
				{
					//2,nook,用户名不存在|密码不正确
					String result = strs[1];
				
					
					if("nook".equals(result))
					{
						String message = strs[2];
						
						//第一个参数，是弹出来的弹出框的位置，相对于那个父控件 谈到父控件shagbian.
						JOptionPane.showMessageDialog(null, message);
					}
					
					if("ok".equals(result))
					{
						ObjectInputStream ois = new ObjectInputStream(Client.clientSocket.getInputStream());
						try {
							List<Member> selfAndfriends = (List<Member>)ois.readObject();
							
							List<Crowd> crowds = (List<Crowd>)ois.readObject();
							
							System.out.println(selfAndfriends.size());
							
							for (Member member : selfAndfriends) {
								System.out.println(member);
							}
							
							System.out.println(crowds.size());
							
							for (Crowd crowd : crowds) {
								System.out.println(crowd);
							}
							
							//缓存自己self到Client的static self方便后续使用
							Client.self = selfAndfriends.get(0);
							
							//收到服务器的好友列表之后，更新客户端的界面 1 关闭登录界面 2显示主界面
							
							FrameUtils.loginUI.dispose();
							
							
							MainUI mainUI = new MainUI();
							mainUI.init(selfAndfriends , crowds);
							
							FrameUtils.mainUI = mainUI;
							
							
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
					
				}
				
				//搜索好友
				if("3".equals(strs[0]))
				{
					
					//3,ok
					
					if("ok".equals(strs[1]))
					{
						ObjectInputStream ois = new ObjectInputStream(Client.clientSocket.getInputStream());
						
						//强调：有继承关系才可以强转，否则编译不通过 比如Dog转成Cat 但是Animal可以强转成Dog或Cat，编译没错运行可能有错
						try {
							List<Member> members = (List<Member>)ois.readObject();
							
							System.out.println(members.size());
							
							for (Member member : members) {
								System.out.println(member);
							}
							
							//更新客户端的界面 
							
							//Vector底层实现就是数组 长度可变的数组！！！ <String>是泛型语法，规定数组必须存String元素 否则编译不通过
							Vector<String> tableHeaders = new Vector<String>();
							tableHeaders.add("序号");
							tableHeaders.add("id");
							tableHeaders.add("名字");
							tableHeaders.add("昵称");
							tableHeaders.add("签名");
							tableHeaders.add("头像");
							
							Vector<Vector<String>> rows = new Vector<Vector<String>>();
							
							for (int i=0 ; i<members.size() ;i++) {
								Member mem = members.get(i);
								
								Vector<String> row1 = new Vector<String>();
								
								row1.add((i+1)+"");
								row1.add(mem.getId()+"");
								row1.add(mem.getName());
								row1.add(mem.getNickname());
								row1.add(mem.getSignature());
								row1.add(mem.getHeader());
								
								rows.add(row1);
							}
			
							
							JTable jt21 = new JTable(rows  , tableHeaders);
							//jtable必须放到JScrollpane里边才能显示表头
							
							
							JScrollPane jsp21 = new JScrollPane(jt21);
							
							SearchUI searchUI = FrameUtils.searchUI;
							
							searchUI.setJt21(jt21);
							
							JPanel jp2 = searchUI.getJp2();
							
							//清空Jp2这个面板
							jp2.removeAll();
							
							jp2.add(jsp21);
							
							//最后一定要记得更新界面
							jp2.updateUI();
							
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						
					}
					
					if("nook".equals(strs[1]))
					{
						
					}
					
					
				}
				
				//添加
				if("4".equals(strs[0]))
				{
					if ("ok".equals(strs[1])) {
						
						ObjectInputStream ois = new ObjectInputStream(Client.clientSocket.getInputStream());
						
						//强调：有继承关系才可以强转，否则编译不通过 比如Dog转成Cat 但是Animal可以强转成Dog或Cat，编译没错运行可能有错
						
						List<Member> members;
						try {
							members = (List<Member>)ois.readObject();

							System.out.println(members.size());
							
							for (Member member : members) {
								System.out.println(member);
							}
							
							
							//更新主界面好友列表，主要是把最新加上的好友刷出来
							MainUI mainUI = FrameUtils.mainUI;
							JPanel jp = mainUI.getJp3();
							
							//把主界面原来的好友列表全部清空
							jp.removeAll();
							
							for (int i=0 ; i<members.size() ; i++) {
								
								final Member friend = members.get(i);
								
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
											chatUI.init(Client.self,friend);
											
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
										
							
							
								
								jp.add(jp31);
								
							}
							
							//重新把每个好友加入主界面的好友列表区块后，整个刷新好友列表
							jp.updateUI();
						
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					
					if ("nook".equals(strs[1])) {
						
					}
				}
				
				//私聊
				if("5".equals(strs[0]))
				{
					//5,3,4,hello 4
					int selfId = Integer.parseInt(strs[1]);
					int friendId = Integer.parseInt(strs[2]);
					String chatContent = strs[3];
					
					//找到selfId对应的私聊界面chatUI
					ChatUI chatUI = FrameUtils.friendId2ChatUI.get(selfId);
					
					JTextArea jta1 = chatUI.getJta11();
					jta1.setText(jta1.getText()+"\r\n"+new Date().toLocaleString()+"说: "+chatContent);
				}
				
				//创建群的反馈
				if("6".equals(strs[0]))
				{
					
					if("ok".equals(strs[1]))
					{
						Crowd crowd = (Crowd)MessageUtil.readObject();
						
						System.out.println("客户端收到了服务器发过来的新创建的群了，"+crowd);
						
						MainUI mainUI = FrameUtils.mainUI;
						JPanel jp = mainUI.getJp3();
						
						List<Crowd> crowds = new ArrayList<Crowd>();
						crowds.add(crowd);
						
						//把主界面原来的好友列表全部清空
						jp.removeAll();
						
						for (int i=0 ; i<crowds.size() ; i++) {
							
							final Crowd newCrowd = crowds.get(i);
							
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
									
//									//如果没有打开过该好友的聊天窗口，就打开，否则就不打开了
//									if(!FrameUtils.friendId2ChatUI.containsKey(friend.getId()))
//									{
//										ChatUI chatUI = new ChatUI();
//										chatUI.init(Client.self,friend);
//										
//										FrameUtils.friendId2ChatUI.put(friend.getId(), chatUI);
//									}
//									
									
								}
							});
							
							jl311.setBounds(5, 5, 40, 40);
							
							jl311.setIcon(new ImageIcon("headers\\header1.png"));
							jl311.setBorder(BorderFactory.createLineBorder(Color.red));
							jp31.add(jl311);
							
							
							jl312.setText(newCrowd.getCrowdName());
							jl312.setBounds(60, 5, 40, 20);
							jp31.add(jl312);
							
							jl313.setText(newCrowd.getDescription());
							jl313.setBounds(60, 20, 120, 40);
							jp31.add(jl313);
									
							jp.add(jp31);
							
						}
						
						jp.updateUI();
						
					}
				
				}
				
				
				//搜索好友 和3是非常类似的，如要消除代码荣誉，需要反射
				if("7".equals(strs[0]))
				{
					
					//7,ok
					
					if("ok".equals(strs[1]))
					{
						
						try {
							List<Crowd> crowds = (List<Crowd>)MessageUtil.readObject();
							
							System.out.println(crowds.size());
							
							for (Crowd crowd : crowds) {
								System.out.println(crowd);
							}
							
							//更新客户端的界面 
							
							//Vector底层实现就是数组 长度可变的数组！！！ <String>是泛型语法，规定数组必须存String元素 否则编译不通过
							Vector<String> tableHeaders = new Vector<String>();
							tableHeaders.add("序号");
							tableHeaders.add("id");
							tableHeaders.add("名字");
							tableHeaders.add("描述");
							
							Vector<Vector<String>> rows = new Vector<Vector<String>>();
							
							for (int i=0 ; i<crowds.size() ;i++) {
								Crowd cro = crowds.get(i);
								
								Vector<String> row1 = new Vector<String>();
								
								row1.add((i+1)+"");
								row1.add(cro.getId()+"");
								row1.add(cro.getCrowdName());
								row1.add(cro.getDescription());
								
								rows.add(row1);
							}
			
							
							JTable jt21 = new JTable(rows  , tableHeaders);
							//jtable必须放到JScrollpane里边才能显示表头
							
							
							JScrollPane jsp21 = new JScrollPane(jt21);
							
							SearchUI searchUI = FrameUtils.searchUI;
							
							//如果不做这个set操作，看到的好友列表和实际searchUI指向的好哟列表jtable不是1个
							searchUI.setJt21(jt21);
							
							JPanel jp2 = searchUI.getJp2();
							
							//清空Jp2这个面板
							jp2.removeAll();
							
							jp2.add(jsp21);
							
							//最后一定要记得更新界面
							jp2.updateUI();
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
					
					if("nook".equals(strs[1]))
					{
						
					}
					
					
				}
				
				//搜索好友 和3是非常类似的，如要消除代码荣誉，需要反射
				if("8".equals(strs[0]))
				{
					
					//8,ok
					
					if("ok".equals(strs[1]))
					{
						
						try {
							Crowd crowd = (Crowd)MessageUtil.readObject();
							
							MainUI mainUI = FrameUtils.mainUI;
							
							List<Crowd> crowds = mainUI.getCrowds();
							crowds.add(crowd);
							
							mainUI.initFriendListAndCrowdList(mainUI.getSelfAndfriends() , crowds);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
					
					if("nook".equals(strs[1]))
					{
						
					}
					
					
				}
				
				//处理打开群聊窗口请求服务器给的反馈
				if("9".equals(strs[0]))
				{
					
					//9,ok
					
					if("ok".equals(strs[1]))
					{
						int crowdId = Integer.parseInt(strs[2]);
						
						try {
							Crowd crowd = (Crowd)MessageUtil.readObject();
							List<Member> crowdMembers = (List<Member>)MessageUtil.readObject();
							
							MainUI mainUI = FrameUtils.mainUI;
							
							//如果没有打开过该群的聊天窗口，就打开，否则就更新
							if(!FrameUtils.CrowdId2CrowdChatUI.containsKey(crowdId))
							{
								CrowdChatUI crowdChatUI = new CrowdChatUI();
								
								crowdChatUI.init(Client.self, crowd, crowdMembers);
								
								FrameUtils.CrowdId2CrowdChatUI.put(crowd.getId(), crowdChatUI);
							}else
							{
								CrowdChatUI crowdChatUI = FrameUtils.CrowdId2CrowdChatUI.get(crowdId);
								crowdChatUI.initCrowdMembers(crowdMembers);
								
							}
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
					
					if("nook".equals(strs[1]))
					{
						
					}
					
					
				}
				
				
				//处理打开群聊窗口请求服务器给的反馈
				if("10".equals(strs[0]))
				{
					int senderId = Integer.parseInt(strs[1]);
					int crowdId = Integer.parseInt(strs[2]);
					String chatContent = strs[3];
					
					Crowd crowd = (Crowd)MessageUtil.readObject();
					
					List<Member> crowdMembers = (List<Member>)MessageUtil.readObject();
				
					//如果没有打开过该群的聊天窗口，就打开，否则就更新
					if(!FrameUtils.CrowdId2CrowdChatUI.containsKey(crowdId))
					{
						CrowdChatUI crowdChatUI = new CrowdChatUI();
						
						crowdChatUI.init(Client.self, crowd, crowdMembers);
						
						crowdChatUI.getJta11().setText(senderId+"和大家说:"+chatContent);
						
						FrameUtils.CrowdId2CrowdChatUI.put(crowd.getId(), crowdChatUI);
					}else
					{
						CrowdChatUI crowdChatUI = FrameUtils.CrowdId2CrowdChatUI.get(crowdId);
						
						crowdChatUI.initCrowdMembers(crowdMembers);
						
						
						crowdChatUI.getJta11().setText(senderId+"和大家说:"+chatContent);
						
					}
						
						
					
				}
				
				
				//收到服务器转过来的有好友给自己发文件的请求
				if("11".equals(strs[0]))
				{
					if("request".equals(strs[1]))//有好友要给自己发文件
					{
						
						String fromUsername = strs[2];
						String toUsername = strs[3];
						String fileName = strs[4];

						int select = JOptionPane.showConfirmDialog(null, fromUsername
								+ "向你传输文件: " + fileName);

						System.out.println("你的选择是:" + select);


						String returnMessage = null;
						
						if (select == 1) {//拒绝接受
							
							returnMessage = "11,response,nook,"+fromUsername+","+toUsername+","+fileName;
							
							MessageUtil.sendMessage(returnMessage);
						}

						if (select == 0) {//决定接受
							
							returnMessage = "11,response,ok,"+fromUsername+","+toUsername+","+fileName;

							MessageUtil.sendMessage(returnMessage);
							
							// 决定同意接收来自好友的文件
							// 那就单独连服务器中提供文件的服务
							// 并且因为文件接收可能非常耗时，所以单独启动一条线程完成
							ServerSocket ss = new ServerSocket(8890);
							System.out.println("客户端启动8890端口，准备接收数据");

							Socket fileSocket = ss.accept();//会阻塞
							
							File file = new File(fileName);
							//耗时的操作一般要放到线程中执行，这样不影响主线程
							new ReceiveFileThread(fileSocket, file).start();

						}

					}
						
					
					if("response".equals(strs[1]))
					{
						String fileName = strs[5];
						
						// 如果对方同意接收文件
						if ("ok".equals(strs[2])) {
							ServerSocket ss = new ServerSocket(8889);
							System.out.println("客户端启动8889端口，准备发送数据");
							Socket sendSocket = ss.accept();

							
							File file = new File(fileName);
							//耗时的操作一般要放到线程中执行，这样不影响主线程
							new SendFileThread(sendSocket, file).start();
						}

						if ("nook".equals(strs[2])) {
							
							JOptionPane.showMessageDialog(null, strs[4]
									+ "拒绝你发送的" + fileName);
						}

						
					}
						
					
				}
				
				
				//收到服务器发送的系统消息
				if("12".equals(strs[0]))
				{
					JOptionPane.showMessageDialog(null, strs[1]);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
