package org.Angel.www.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import org.Angel.www.listener.RecevieCommandListener;



public class SocketUtils {

	private Socket socket;
	private RecevieCommandListener lister;

	public SocketUtils(Socket socket, RecevieCommandListener lister) {
		this.socket = socket;
		this.lister = lister;
		// 启动接受客户端消息的线程
		new Thread(new ReceiveCommandThread()).start();
	}

	public void send(String content) {

		new Thread(() -> {

			if (SocketUtils.this.socket != null && !SocketUtils.this.socket.isClosed()) {
				try {
					PrintStream ps = new PrintStream(SocketUtils.this.socket.getOutputStream());
					ps.println(content);
					ps.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}).start();
	}

	public class ReceiveCommandThread extends Thread {
		private BufferedReader br;

		public ReceiveCommandThread() {
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {

			String receiveCommand = null;
			// 不断接收客户端发送过来的数据
			try {
				while ((receiveCommand = br.readLine()) != null) {
					// 客户端有数据发送，立即回调此方法
					lister.receiveCallBack(receiveCommand);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
