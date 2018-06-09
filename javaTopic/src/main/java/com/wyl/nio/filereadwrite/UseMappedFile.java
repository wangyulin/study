package com.wyl.nio.filereadwrite;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class UseMappedFile {

    static private final int start = 0;
    static private final int size = 999999999;

    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile( "/Users/wangyulin/usemappedfile.txt", "rw" );
        FileChannel fileChannel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = fileChannel.map( FileChannel.MapMode.READ_WRITE,
                start, size );

        mappedByteBuffer.put( 100000, (byte)97 );
        mappedByteBuffer.put( size-6, (byte)122 );
        /*for (int i = start; i < size; i++) {
            System.out.print((char) mappedByteBuffer.get(i));
        }*/
        randomAccessFile.close();
    }

}
