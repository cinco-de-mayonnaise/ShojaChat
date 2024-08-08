package com.abdullah.shojachat.logic;

import java.io.Serializable;

public class ServerStatistics implements Serializable
{
    /* we want a nice statistics class that will
    *      track server statistics if the server has it enabled
    * or will ignore and do nothing otherwise
    *
    * to do the 'nothing' part, we want the serverstats class methods to be refer to some
    * internal bool to see if tracking is allowed,
    * so do nothing, otherwise increment some variable...
    */
    int usersRegistered = 0;
    int messagesSent = 0;


    final private boolean trackStats;

    /**
     * Create an instance of ServerStatistics. For code conformity, all servers must have a ServerStatistics class
     * even if they don't want to track statistics. But they can choose to opt out by passing in false to the constructor
     * which will then return an instance of ServerStatistics that does nothing.
     *
     * @param trackStats whether statistics should be counted or not.
     */


    // retard we dont wanna raise an exception if its disabled dumbass
    private void raiseExceptionIfDisabled()
    {
        if (trackStats)
            return;
        else
            throw new RuntimeException("This server has statistics disabled");
    }

    public ServerStatistics(boolean trackStats)
    {
        this.trackStats = trackStats;
    }

    public void t_newUserRegistered()
    {
        if (trackStats)
            usersRegistered++;
    }

    public void t_messageSent()
    {
        if (trackStats)
            messagesSent++;
    }

    // GETTERS
    public int getUsersRegistered() {
        return usersRegistered;
    }

    public int getMessagesSent() {
        return messagesSent;
    }

    public boolean isTrackingStats() {
        return trackStats;
    }
}
