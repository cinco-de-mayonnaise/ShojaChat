package com.abdullah.shojachat;

import java.io.Serializable;

public class Packet implements Serializable
{
    // hold a type for identifying what type of packet it is
    // acknowledgement packet, datasending packet,
    //
    long id; // packet number
    Serializable obj; // a generic object that is contained inside this packet.
    Class<?> classType; // type of the class above.
    public enum PacketType {
        DATA,           // data packet, reciever has to send ACK
        ACK,            // acknowledge packet, contains no data, sender should send next packet
    }
    PacketType ptype;

    Packet(long id, Serializable obj)
    {
        this.classType = obj.getClass();
        this.obj = obj;
        this.id = id;
        this.ptype = PacketType.DATA;
    }

    Packet(long id)
    {
        this.id = id;
        this.ptype = PacketType.ACK;
    }

    public Serializable getData()
    {
        if (ptype == PacketType.DATA)
            return obj;
        else
            return null;
    }

    public Class<?> getPacketClassType()
    {
        if (ptype == PacketType.DATA)
            return classType;
        else
            return null;
    }
}
