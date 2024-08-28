package com.abdullah.shojachat.logic.events;

import java.util.EventObject;

/**
 * Distinct from events generated by JavaFX, AppEvents are the key events representing certain actions
 * occurring in ShojaChat that we want to keep track of and handle appropriately when generated.
 *
 *
 */
public class AppEvent extends EventObject
{
    /**
     * Constructs a Logic Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    protected AppEvent(Object source) {
        super(source);
    }

    public Class<? extends AppEvent> getEventType()
    {
        return this.getClass();
    }
}