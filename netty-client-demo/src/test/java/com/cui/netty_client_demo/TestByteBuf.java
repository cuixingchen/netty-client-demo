package com.cui.netty_client_demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import junit.framework.TestCase;

/**
 * 测试了解ByteBuf
 * 
 * @author cuipengfei
 *
 */
public class TestByteBuf extends TestCase {

	public void testByteBufMethod(){
		ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer();
		buf.writeByte(0);
		Byte b=buf.readByte();
		System.out.println(b);
	}
}
