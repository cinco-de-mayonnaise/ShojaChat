package com.abdullah.shojachat;

import java.net.ServerSocket;
import java.util.ArrayList;

public class ClientConnection extends Thread
{
    ServerSocket client_connection;
    boolean running;

    public ServerSocket getSocket() {
        return client_connection;
    }

    @Override
    public void run()
    {
        while (running)
        {

        }
    }
}
