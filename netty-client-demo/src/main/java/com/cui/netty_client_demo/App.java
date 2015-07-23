package com.cui.netty_client_demo;


/**
 * 服务启动入口
 * 
 * @author cuipengfei
 *
 */
public class App {
	public static void main(String[] args) {
		TcpClient.getInstance().start();
	}
}
