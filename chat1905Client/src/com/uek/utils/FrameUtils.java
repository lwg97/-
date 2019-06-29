package com.uek.utils;

import java.util.HashMap;

import com.uek.ui.ChatUI;
import com.uek.ui.CreateCrowdUI;
import com.uek.ui.CrowdChatUI;
import com.uek.ui.LoginUI;
import com.uek.ui.MainUI;
import com.uek.ui.RegisterUI;
import com.uek.ui.SearchUI;

/**
 * 缓存所有在客户端打开的页面 ，方便后续更新界面的时候使用
 * @author 86139
 *
 */
public class FrameUtils {

	public static LoginUI loginUI;
	public static MainUI mainUI;
	public static RegisterUI registerUI;
	public static SearchUI searchUI;

	//因为一个用户可以和多个好友聊天，所以用HashMap存储聊天界面
	public static HashMap<Integer , ChatUI> friendId2ChatUI = new HashMap<Integer , ChatUI>();
	
	//因为一个用户可以和多个群好友聊天，所以用HashMap存储群聊天界面
	public static HashMap<Integer , CrowdChatUI> CrowdId2CrowdChatUI = new HashMap<Integer , CrowdChatUI>();
		
	public static CreateCrowdUI createCrowdUI;
}
