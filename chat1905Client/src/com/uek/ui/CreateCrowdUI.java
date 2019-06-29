package com.uek.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.uek.model.Member;
import com.uek.utils.MessageUtil;

public class CreateCrowdUI extends JFrame{
	private JLabel jl1 , jl2;
	private JTextField jtf;
	private JTextArea jta;
	private JButton jb1 , jb2;
	
	public void init(final Member self){
		
		jl1 = new JLabel("名称");
		jl2 = new JLabel("描述");
		
		jtf = new JTextField(20);
		jta = new JTextArea(5,5);
		
		jb1 = new JButton("重置");
		jb2 = new JButton("创建");
		
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String crowdName = jtf.getText();
				String description = jta.getText();
				
				String protoMessage = "6,"+self.getId()+","+crowdName+","+description;
				
				MessageUtil.sendMessage(protoMessage);
				
			}
		});
		this.setLayout(new GridLayout(3, 2));
		this.add(jl1);this.add(jtf);
		this.add(jl2);this.add(jta);
		this.add(jb1);this.add(jb2);
		
		
		
		this.setTitle(self.getId()+"正在创建群");
		this.pack();
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		CreateCrowdUI createCrowdUI = new CreateCrowdUI();
		createCrowdUI.init(new Member());
	}
}
