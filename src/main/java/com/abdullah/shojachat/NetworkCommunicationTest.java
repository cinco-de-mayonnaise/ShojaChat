package com.abdullah.shojachat;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkCommunicationTest
{
    public static void server_main(String[] args)
    {
        // Establish connection with client and send a string as message.
        try {
            ServerSocket s = new ServerSocket(9392, 50, InetAddress.getByName("127.0.0.1"));
            System.out.printf("Established server with IP: %s, at port %d\n", s.getInetAddress().getHostAddress(), s.getLocalPort());
            Socket client = s.accept();

            System.out.println("-- Connected to client: " + client.toString());
            ObjectOutputStream clout = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream clin = new ObjectInputStream(client.getInputStream());

            // keep alive a thread to constantly get data from the streams.



        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void client_main(String[] args)
    {
        //
    }
}
