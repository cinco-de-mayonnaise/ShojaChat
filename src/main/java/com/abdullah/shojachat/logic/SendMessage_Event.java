package com.abdullah.shojachat.logic;

/**
 * Event that is called when the client has decided that a message is ready to be sent.
 * This causes the network thread to activate to transform the message into packets ready
 * to be sent over the network
 */
public class SendMessage_Event extends AppEvent
{

    /**
     * Constructs a Logic Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    protected SendMessage_Event(Object source) {
        super(source);
    }
}
