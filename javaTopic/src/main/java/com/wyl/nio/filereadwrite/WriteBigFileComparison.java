package com.wyl.nio.filereadwrite;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Scanner;

public class WriteBigFileComparison {

    // data chunk be written per time
    private static final int DATA_CHUNK = 128 * 1024 * 1024;

    // total data size is 2G
    private static final long LEN = 2L * 1024 * 1024L;// * 1024L;

    private static final String mbb_File_name = "/Users/wangyulin/mb.txt";


    public static void writeWithFileChannel() throws IOException {
        File file = new File("e:/test/fc.dat");
        if (file.exists()) {
            file.delete();
        }

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        FileChannel fileChannel = raf.getChannel();

        byte[] data = null;
        long len = LEN;
        ByteBuffer buf = ByteBuffer.allocate(DATA_CHUNK);
        int dataChunk = DATA_CHUNK / (1024 * 1024);
        while (len >= DATA_CHUNK) {
            System.out.println("write a data chunk: " + dataChunk + "MB");

            buf.clear(); // clear for re-write
            data = new byte[DATA_CHUNK];
            for (int i = 0; i < DATA_CHUNK; i++) {
                buf.put(data[i]);
            }

            data = null;

            buf.flip(); // switches a Buffer from writing mode to reading mode
            fileChannel.write(buf);
            fileChannel.force(true);

            len -= DATA_CHUNK;
        }

        if (len > 0) {
            System.out.println("write rest data chunk: " + len + "B");
            buf = ByteBuffer.allocateDirect((int) len);
            data = new byte[(int) len];
            for (int i = 0; i < len; i++) {
                buf.put(data[i]);
            }

            buf.flip(); // switches a Buffer from writing mode to reading mode, position to 0, limit not changed
            fileChannel.write(buf);
            fileChannel.force(true);
            data = null;
        }

        fileChannel.close();
        raf.close();
    }

    /**
     * write big file with MappedByteBuffer
     *
     * @throws IOException
     */
    public static void writeWithMappedByteBuffer() throws IOException {
        File file = new File(mbb_File_name);
        if (file.exists()) {
            file.delete();
        }

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        FileChannel fileChannel = raf.getChannel();
        int pos = 0;
        MappedByteBuffer mbb = null;
        byte[] data = null;
        long len = LEN;
        int dataChunk = DATA_CHUNK / (1024 * 1024);
        while (len >= DATA_CHUNK) {
            System.out.println("write a data chunk: " + dataChunk + "MB");

            mbb = fileChannel.map(MapMode.READ_WRITE, pos, DATA_CHUNK);
            data = new byte[DATA_CHUNK];
            data = "write a data chunk write a data chunk write a data chunk".getBytes();
            mbb.put(data);

            data = null;

            len -= DATA_CHUNK;
            pos += DATA_CHUNK;
        }

        /*if (len > 0) {
            System.out.println("write rest data chunk: " + len + "B");

            mbb = fileChannel.map(MapMode.READ_WRITE, pos, len);
            data = new byte[(int) len];
            mbb.put(data);
        }*/

        data = null;
        unmap(mbb);   // release MappedByteBuffer
        fileChannel.close();
    }

    public static void writeWithTransferTo() throws IOException {
        File file = new File("e:/test/transfer.dat");
        if (file.exists()) {
            file.delete();
        }

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        FileChannel toFileChannel = raf.getChannel();

        long len = LEN;
        byte[] data = null;
        ByteArrayInputStream bais = null;
        ReadableByteChannel fromByteChannel = null;
        long position = 0;
        int dataChunk = DATA_CHUNK / (1024 * 1024);
        while (len >= DATA_CHUNK) {
            System.out.println("write a data chunk: " + dataChunk + "MB");

            data = new byte[DATA_CHUNK];
            bais = new ByteArrayInputStream(data);
            fromByteChannel = Channels.newChannel(bais);

            long count = DATA_CHUNK;
            toFileChannel.transferFrom(fromByteChannel, position, count);

            data = null;
            position += DATA_CHUNK;
            len -= DATA_CHUNK;
        }

        if (len > 0) {
            System.out.println("write rest data chunk: " + len + "B");

            data = new byte[(int) len];
            bais = new ByteArrayInputStream(data);
            fromByteChannel = Channels.newChannel(bais);

            long count = len;
            toFileChannel.transferFrom(fromByteChannel, position, count);
        }

        data = null;
        toFileChannel.close();
        fromByteChannel.close();
    }

    /**
     * 在MappedByteBuffer释放后再对它进行读操作的话就会引发jvm crash，在并发情况下很容易发生
     * 正在释放时另一个线程正开始读取，于是crash就发生了。所以为了系统稳定性释放前一般需要检
     * 查是否还有线程在读或写
     *
     * @param mappedByteBuffer
     */
    public static void unmap(final MappedByteBuffer mappedByteBuffer) {
        try {
            if (mappedByteBuffer == null) {
                return;
            }

            mappedByteBuffer.force();
            AccessController.doPrivileged(new PrivilegedAction<Object>() {
                @Override
                @SuppressWarnings("restriction")
                public Object run() {
                    try {
                        Method getCleanerMethod = mappedByteBuffer.getClass()
                                .getMethod("cleaner", new Class[0]);
                        getCleanerMethod.setAccessible(true);
                        sun.misc.Cleaner cleaner =
                                (sun.misc.Cleaner) getCleanerMethod
                                        .invoke(mappedByteBuffer, new Object[0]);
                        cleaner.clean();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("clean MappedByteBuffer completed");
                    return null;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        //StopWatch sw = new StopWatch();

        //sw.startWithTaskName("write with file channel's write(ByteBuffer)");
        ///writeWithFileChannel();
        //sw.stopAndPrint();

        //sw.startWithTaskName("write with file channel's transferTo");
        ///writeWithTransferTo();
        //sw.stopAndPrint();

        //sw.startWithTaskName("write with MappedByteBuffer");
        //writeWithMappedByteBuffer();
        //sw.stopAndPrint();

        //writeFileByMapper();
        //readFileByMapper();

        test("/Users/wangyulin/ABC.sql");
    }

    public static void test(String sourceFilePath) throws IOException {

        String srcFile = sourceFilePath;

        String destFile = sourceFilePath + "_copy";

        RandomAccessFile rafi = new RandomAccessFile(srcFile, "r");
        RandomAccessFile rafo = new RandomAccessFile(destFile, "rw");

        FileChannel fci = rafi.getChannel();
        FileChannel fco = rafo.getChannel();

        long size = fci.size();

        MappedByteBuffer mbbi = fci.map(FileChannel.MapMode.READ_ONLY, 0, size);
        MappedByteBuffer mbbo = fco.map(FileChannel.MapMode.READ_WRITE, 0, size);

        long start = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            byte b = mbbi.get(i);
            mbbo.put(i, b);
        }

        fci.close();
        fco.close();
        rafi.close();
        rafo.close();

        System.out.println("Spend: " + (double) (System.currentTimeMillis() - start) / 1000 + "s");

    }
}