package com.wyl.nio.channels;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatterRead {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/wangyulin/email.txt", "rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        ByteBuffer header = ByteBuffer.allocate(8);
        ByteBuffer body   = ByteBuffer.allocate(11);

        ByteBuffer[] byteBuffers = new ByteBuffer[]{header, body};

        fileChannel.read(byteBuffers);

        header.flip();
        body.flip();

        //System.out.println(header.toString());
        //System.out.println(body.toString());

        while(header.hasRemaining()) {
            System.out.print((char)header.get());
        }

        System.out.print("\n-----------------------");

        while(body.hasRemaining()) {
            System.out.print((char)body.get());
        }

        randomAccessFile.close();

    }

}
