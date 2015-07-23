package com.cui.netty_client_demo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * tcp handler
 * 
 * @author cuipengfei
 *
 */
public class TcpClientHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = LoggerFactory
			.getLogger(TcpClientHandler.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object obj) { // (2)
		try {
			if (obj instanceof byte[]) {
				final byte[] msgbytes = (byte[]) obj;
				Msg msg=new Msg();
				msg.bodyfromBytes(msgbytes);
				logger.info("client收到消息内容:"+msg);
			} else {
				logger.error("client主handler---消息解码有误，请检查！！");
			}

		} finally {
			ReferenceCountUtil.release(obj);
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		logger.info("-------------client建立连接--------------");
		TcpClient.getInstance().setChtx(ctx);
		Msg m=new Msg();
		m.setUserId(100);
		m.setPassWord(101);
		TcpClient.getInstance().send(m);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

		InetSocketAddress address = (InetSocketAddress) ctx.channel()
				.remoteAddress();
		InetAddress inetAdd = address.getAddress();
		logger.info("client断开连接：" + ctx.name() + ",IP:" + inetAdd.getHostAddress()
				+ ",port:" + address.getPort());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		// Close the connection when an exception is raised.

		logger.error("client主handle---rexceptionCaught异常", cause);
		ctx.close();
		ctx = null;
	}

}
