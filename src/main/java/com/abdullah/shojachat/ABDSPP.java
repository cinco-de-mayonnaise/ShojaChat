package com.abdullah.shojachat;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

// Abdullah's Simple Packet Protocol
public class ABDSPP extends Thread
{
    // simple packet protocol
    // a packet sent through a stream may not be guaranteed to arrive. hence we send packets like this

    // suppose server sends 5 packets to client
    // server: sends packet 1, copies packet 1 to buffer
    // client: recieves packet 1, sends packet 1 ack
    // server: receives packet 1 ack, removes packet 1 from buffer
    // server: sends packet 2, copies packet 2 to buffer
    // client: did not recieve packet 2
    // server: did not recieve packet 2 ack, keeps sending packet 2 until the server recieves an ack packet
    // client: already has packet 2, keeps sending packet 2 ack until server sends new packet.
    // server: recieves packet 2 acks, removes packet 2 from buffer and sends packet 3.
    // server: recieves packet 2 ack, ignores as it is currently sending packet 3.

    // suppose the client gets multiple copies of packet2. even though it already has packet2. It sends packet2 acks.
    ArrayList<Packet> buf;
    ObjectInputStream sock_istream;
    ObjectOutputStream sock_ostream;
    Socket sock;


    ABDSPP(Socket s) throws IOException
    {
        buf = new ArrayList<>();
        sock = s;
        if (s.isConnected())
        {
            sock_istream = new ObjectInputStream(sock.getInputStream());
            sock_ostream = new ObjectOutputStream(sock.getOutputStream());
        }

    }



}
