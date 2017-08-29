package com.wyl.nio.guide.demo1;

import com.wyl.utils.LocalUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by wangyulin on 3/2/16.
 */
public class Demo1 {

    public static void main(String[] args) throws IOException {
        File path = LocalUtils.getLocalDataPath();

        File fromData = new File(path,"data1.txt");
        RandomAccessFile aFile = new RandomAccessFile(fromData, "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            //System.out.println("\n");
            //buf.rewind();
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();

            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

}
