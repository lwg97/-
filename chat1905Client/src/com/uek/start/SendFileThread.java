package com.uek.start;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

// 读取发送文件的客户端的本地文件
// 发送数据给网络(目前的架构下边是给服务器发送) 
class SendFileThread extends Thread {
	private Socket socket;
	private File file;

	public SendFileThread(Socket socket, File file) {
		this.socket = socket;
		this.file = file;
	}

	// 读本地文件,写到网络socket
	public void run() {
		System.out.println("马上开始传数据了!");
		OutputStream fos = null;
		
		FileInputStream in = null ;

		try {

			in = new FileInputStream(file);//输入流关联文件，也就是读本地文件
			fos = socket.getOutputStream();//输出流关联网络，也就是写给网络
			 
			byte[] buf = new byte[1024];

			int length = -1;

			while ((length = in.read(buf)) != -1) {
				// 因为最后一次读的时候字节数组不能读满
				// fos.write(buf);
				fos.write(buf, 0, length);

			}

			System.out.println("数据传输完毕了!");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}