package com.abdullah.shojachat.logic.events;

/**
 * Event that is called when the network thread has successfully received and decoded a message.
 * The network thread should now notify the client to update and show this message.
 * This causes the JavaFX thread to activate to update necessary scene nodes.
 */
public class RecieveMessage_Event extends AppEvent
{
    /**
     * Constructs a Logic Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    protected RecieveMessage_Event(Object source) {
        super(source);
    }
}
