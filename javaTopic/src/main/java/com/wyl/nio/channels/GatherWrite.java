package com.wyl.nio.channels;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GatherWrite {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/wangyulin/email_1.txt","rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        ByteBuffer header = ByteBuffer.allocate(8);
        ByteBuffer body   = ByteBuffer.allocate(8);

        header.put(new byte[]{'h', 'o', 'l', 'l', 'e', ' ', 'w', 3});
        body.put(new byte[]{'H', 'O', 'L', 'L', 'E', ' ', 'W', 'L'});

        header.flip();
        body.flip();

        ByteBuffer[] byteBuffers = new ByteBuffer[]{header, body};

        long length = fileChannel.write(byteBuffers);

        System.out.println(length);
        randomAccessFile.close();

    }

}
