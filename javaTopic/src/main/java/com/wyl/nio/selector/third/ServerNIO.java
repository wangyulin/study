package com.wyl.nio.selector.third;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class ServerNIO {

    public static void main(String[] args) throws IOException {

        //1. 获取服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9898));
        //2. 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);

        //3. 打开一个监听器
        Selector selector = Selector.open();
        //4. 向监听器注册接收事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            //5. 获取监听器上所有的监听事件值
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            //6. 如果有值
            while (it.hasNext()) {
                //7. 取到SelectionKey
                SelectionKey selectionKey = it.next();

                //8. 根据key值判断对应的事件
                if (selectionKey.isAcceptable()) {
                    //9. 接入处理
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    //10. 可读事件处理
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    readMsg(channel);
                }
                //11. 移除当前key
                it.remove();
            }
        }
    }

    private static void readMsg(SocketChannel channel) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int len = 0;
        while ((len = channel.read(buf)) > 0) {
            buf.flip();
            byte[] bytes = new byte[1024];
            buf.get(bytes, 0, len);
            System.out.println(new String(bytes, 0, len));
        }
    }

}
