package com.cui.netty_client_demo;

import java.nio.ByteBuffer;

import junit.framework.TestCase;

/**
 * position：当前指针的位置，也就是接下来要读写的位置。 limit：限制，一个缓冲区可读写的范围。
 * capability：容量，一个缓冲区最多的存放的字节数。 mark：标志位，记录当前的位置。
 * 
 * 界限是用来控制当前读写的范围，如果容量为100，界限为10，则位置只能在0-10之间，即只能读写0-10之间的数据。
 * 
 * 几个操作对它们的影响：（操作都会影响到position，clear和flip会影响到limit） flip()：limit=position,
 * position=0.中文意思是“翻转”。
 * 
 * rewind()：position=0，limit不变，可以用于重复读取一段数据.
 * 扩展所有的数据，中文意思是“倒带”，也就是从头开始别的什么也不变。中文意思是“翻转”，也就是当前指，针在哪就在哪，然后从头开始。
 * 
 * clear()：position=0,limit=capability,也就是相当于清空了之前的内容，
 * 但是ByteBuffer中数组的内容在向里面写入之前是没有改变的.所有的位置与使用ByteBuffer.allocate(int
 * capacity)是一样一样的。
 * 
 * mark( ) 就是把当前的Position( ) 设置一个标记！
 * 
 * reset( ) position=mark();
 * 
 * @author cuipengfei
 *
 */
public class TestByteBuffer extends TestCase {

	public void testMark() {
		String msg = "12345";
		byte[] b = msg.getBytes();
		ByteBuffer nb = ByteBuffer.allocate(10);
		nb.put(b);
		nb.flip();
		System.out.println("1." + nb.get() + ":" + nb.mark() + ":" + nb);
		// nb.flip();
		System.out.println("2." + nb.get() + ":" + nb);
		nb.reset();
		System.out.println("3." + nb.get() + ":" + nb);
	}
}
