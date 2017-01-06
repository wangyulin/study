package com.wyl.thread.scoket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangyulin on 06/01/2017.
 */
public class MultiThreadEchoServer {

    public static ExecutorService tp = Executors.newCachedThreadPool ();

    static class HandeMsg implements Runnable {
        Socket clientSocket;

        public HandeMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public static void main(String[] args) {
            ServerSocket echoServer = null;
            Socket clientSocket = null;
            try {
                echoServer = new ServerSocket ( 8000 );
            } catch (IOException e) {
                e.printStackTrace ();
            }

            while(true) {
                try {
                    clientSocket = echoServer.accept ();
                    System.out.println (clientSocket.getRemoteSocketAddress () + " connect!");
                    tp.execute ( new HandeMsg ( clientSocket ) );
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }

        }

        @Override
        public void run() {
            BufferedReader is = null;
            PrintWriter os = null;

            try {
                is = new BufferedReader ( new InputStreamReader ( clientSocket.getInputStream () ) );
                os = new PrintWriter ( clientSocket.getOutputStream (), true);
                String inputLine = null;
                long b = System.currentTimeMillis ();
                System.out.println ("Begin time is : " + b);
                while((inputLine = is.readLine ()) != null) {
                    System.out.println (inputLine);
                    os.println ( inputLine );
                }
                long e = System.currentTimeMillis ();
                System.out.println ("Speed : " + (e-b) + "ms");
            } catch (IOException e) {
                e.printStackTrace ();
            } finally {
                try {
                    if(is != null) {
                        is.close ();
                    }
                    if(os != null) {
                        os.close ();
                    }
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        }
    }
}
