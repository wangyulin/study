package com.wyl.nio.filereadwrite;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFile {

    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream("/Users/wangyulin/ABC.sql");

        FileChannel fc = fin.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        fc.read(buffer);

        buffer.flip();

        StringBuffer s=new StringBuffer();
        while (buffer.remaining() > 0) {
            byte b = buffer.get();
            s.append((char)b);
        }
        System.out.print(s);

        fin.close();

    }

}
