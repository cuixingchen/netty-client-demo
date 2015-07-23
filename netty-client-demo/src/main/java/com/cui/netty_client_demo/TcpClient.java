package com.cui.netty_client_demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * tcp服务端
 * 
 * @author cuipengfei
 *
 */
public class TcpClient extends Thread {

	public ChannelHandlerContext chtx;
	private static TcpClient tcpClient;

	public static TcpClient getInstance() {
		if (tcpClient == null) {
			synchronized(TcpClient.class){
				if (tcpClient == null) {
					tcpClient = new TcpClient();
				}
			}
		}
		return tcpClient;
	}

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(TcpClient.class);

	@Override
	public void run() {
		// 初始化服务器
		init();
		// 启动临时客户端管理(秒)
		// 启动消息处理
		//启动客户端管理
	}

	/**
	 * 初始化
	 */
	private void init() {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
//			final LogLevel loglevel = LogLevel.valueOf(p.getProperty("upa_loglevel").toUpperCase());
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception {
							ch.pipeline().addLast(new TcpCodec(),
//									new LoggingHandler(loglevel),
									new TcpClientHandler());
						}
					});

			ChannelFuture cf = b.connect("127.0.0.1",21108).sync();
			// cf.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			logger.error("client初始化失败：", e); //$NON-NLS-1$
			e.printStackTrace();
		} finally {
			// group.shutdownGracefully();
		}
		logger.info("client初始化完成");

	}
	/**
	 * 发送消息
	 * 
	 * @param m
	 */
	public void send(Msg m) {
		if (chtx != null && chtx.channel().isOpen()) {
			logger.info("client发送消息："+m);
			chtx.write(m);
			chtx.flush();
		}
	}

	public void setChtx(ChannelHandlerContext chtx) {
		this.chtx = chtx;
	}
	
}
