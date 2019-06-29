package com.uek.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;

import com.uek.start.Client;


public class MessageUtil {

	public static void sendMessage(String line)
	{
		PrintStream ps;
		try {
			ps = new PrintStream(Client.clientSocket.getOutputStream());
			ps.println(line);
			ps.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Object readObject()
	{
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(Client.clientSocket.getInputStream());
			
			return ois.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	
}
