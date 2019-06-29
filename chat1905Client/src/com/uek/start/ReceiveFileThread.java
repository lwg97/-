package com.uek.start;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

// 读取服务器任意时刻发送的数据
class ReceiveFileThread extends Thread {
	private Socket socket;
	private File file;
	
	public ReceiveFileThread(Socket socket, File file) {
		this.socket = socket;
		this.file = file;
	}

	// 读socket发送过来的数据,写到本地磁盘fileName
	public void run() {
		
		System.out.println("开始接受数据了");
		
		InputStream in = null;
		
		String fileName = file.getName();
		
		File file = new File("back"+fileName) ;
		
		FileOutputStream fos = null ;

		try {

			in = socket.getInputStream();//网络作为输入
			fos = new FileOutputStream(file);//本地磁盘作为输出
			 
			byte[] buf = new byte[1024];

			int length = -1;

			//不停的从网络in读入到buf内存数组 读不到数据了返回-1 只要能读到就读到buf中
			while ((length = in.read(buf)) != -1) {
				// 因为最后一次读的时候字节数组不能读满
				// fos.write(buf);
				fos.write(buf, 0, length);

			}

			System.out.println("接受数据结束了");
			
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