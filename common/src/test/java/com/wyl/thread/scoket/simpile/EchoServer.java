package com.wyl.thread.scoket.simpile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wangyulin on 06/01/2017.
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(10007);
        }
        catch (IOException e)
        {
            System.err.println("Could not listen on port: 10007.");
            System.exit(1);
        }

        Socket clientSocket = null;
        System.out.println ("Waiting for connection.....");

        try {
            clientSocket = serverSocket.accept();
        }
        catch (IOException e)
        {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        System.out.println ("Connection successful");
        System.out.println ("Waiting for input.....");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader ( clientSocket.getInputStream()));

        String inputLine;

        while ((inputLine = in.readLine()) != null)
        {
            System.out.println ("Server: " + inputLine);
            out.println(inputLine);

            if (inputLine.equals("Bye."))
                break;
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}