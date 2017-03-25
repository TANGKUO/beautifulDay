package com.tk.cn.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	private class Handler extends Thread {
		private Socket socket;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				while (true) {
					/** 接受模块 */
					InputStream is = socket.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String line = br.readLine();
					if (line != null) {
						System.out.println(line);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class sendThread extends Thread {
		private Socket socket;

		public sendThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			/** 发送模块 */
			try {
				while (true) {
					Scanner scanner = new Scanner(System.in);
					String str = scanner.nextLine();
//					System.out.println(str);
					OutputStream os = socket.getOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(os);
					PrintWriter pw = new PrintWriter(osw);
					pw.println(str);
					pw.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() throws Exception {
		ServerSocket ss = new ServerSocket(8888);
		ServerSocket ss1 = new ServerSocket(9999);
		Socket socket = ss.accept();
		Socket socket1 = ss1.accept();// 属于阻塞方法，一直等待如果等不到则一直在此等待，程序不往下走
		Handler handler = new Handler(socket);
		sendThread send = new sendThread(socket1);
		handler.start();
		send.start();

	}

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		server.start();
	}
}
