package com.wyl.thread.nio;

import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by wangyulin on 07/01/2017.
 */
public class SocketClientExample {

    public static void main (String [] args)
            throws IOException, InterruptedException {

        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 5454);
        SocketChannel client = SocketChannel.open(hostAddress);

        System.out.println("Client sending messages to server...");

        // Send messages to server

        String [] messages = new String [] {"Time goes fast.", "What now?", "Bye."};

        for (int i = 0; i < messages.length; i++) {

            byte [] message = new String(messages [i]).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            client.write(buffer);

            System.out.println(messages [i]);
            buffer.clear();
            Thread.sleep(3000);
        }

        client.close();
    }
}
