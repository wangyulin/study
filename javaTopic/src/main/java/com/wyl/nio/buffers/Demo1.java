package com.wyl.nio.buffers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class Demo1 {

    public static void main(String[] args) {

        String str = " WangYulin";
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);//.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte)'h').put(0, (byte)'H').putChar('A').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'o');
        byteBuffer.put(str.getBytes(), 0, str.getBytes().length);
        byteBuffer.flip();
        //byteBuffer.flip(); 两次flip导致limit为0

        byteBuffer.hasRemaining();
        System.out.println(byteBuffer.order().toString());
        //Arrays.sort(byteBuffer.array());
        //byteBuffer.compareTo();
        //byteBuffer.clear();

        //byteBuffer.position(0);

        byte[] temp = new byte[10];
        byteBuffer.get(temp);
        System.out.println(new String(temp));

        byteBuffer.mark();
        System.out.println((char)byteBuffer.get());
        //byteBuffer.compact();
        byteBuffer.reset();
        System.out.println((char)byteBuffer.get());

        System.out.println(new String(byteBuffer.array()).trim());

    }

}
