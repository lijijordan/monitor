package com.monitor.common.util.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocketUtil {

	protected static Socket server;
	// private String url="";
	// private int port=9766;
	private boolean isConnected = true;

	public ClientSocketUtil(int port) {// 链接自己，本机上测试的时候用
		try {
			server = new Socket(InetAddress.getLocalHost(), port);
		} catch (Exception e) {
			isConnected = false;
		}
	}

	public ClientSocketUtil(String url, int port) {
		try {
			server = new Socket(url, port);
		} catch (Exception e) {
			isConnected = false;
		}
	}

	// 向服务端程序发送数据
	public void send(String data) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(
					server.getOutputStream(), "GBK");
			BufferedWriter bw = new BufferedWriter(osw);

			bw.write(data + "\r\n");
			bw.flush();
		} catch (IOException e) {

		}

	}

	/**
	 * 从服务端程序接收数据,返回一个BufferedReader
	 * 
	 * @return
	 */
	public BufferedReader recieve() {
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(server.getInputStream(), "GBK");
			br = new BufferedReader(isr);
		} catch (IOException e) {

		}
		return br;
	}

	public void close() {
		try {
			if (server != null || server.isConnected()) {
				server.close();
			}
		} catch (IOException e) {
		}
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
}