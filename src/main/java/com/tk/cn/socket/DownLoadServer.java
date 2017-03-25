package com.tk.cn.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DownLoadServer {
	public void start() throws IOException {
		ServerSocket serversocket = new ServerSocket(8889);
		while (true) {
			Socket socket = serversocket.accept();
			downThread dt=new downThread(socket);
			dt.start();
		}
	}

	private class downThread extends Thread {
		private Socket socket;

		public downThread(Socket socket) {
			super();
			this.socket = socket;
		}

		@Override
		public void run() {
			try{
				
			OutputStream os=socket.getOutputStream();
			
			File file=new File("/home/tangkuo/API.zip");
			long fileSize=file.length();
			DataOutputStream dos=new DataOutputStream(os);
			dos.writeLong(fileSize);
			
			FileInputStream fis = new FileInputStream("/home/tangkuo/API.zip");
			
			BufferedInputStream bis=new BufferedInputStream(fis);
			
			BufferedOutputStream bos=new BufferedOutputStream(os);
			
			int b;
			while((b=bis.read())!=-1){
				bos.write(b);
			}
			bos.flush();
			socket.close();
			
			}catch(Exception e){
				e.printStackTrace();
			}	
		}


	}
	
	public static void main(String[]args) throws IOException{
		DownLoadServer s=new DownLoadServer();
		s.start();
	}
}
