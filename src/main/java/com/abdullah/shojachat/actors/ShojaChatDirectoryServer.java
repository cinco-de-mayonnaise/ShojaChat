package com.abdullah.shojachat.actors;

/**
 * <p>Behold, the ShojaChat Directory server class. Each server class instance represents a unique directory server, containing all information
 * and data needed for the server to run. Server instances should never be obtainable and passed around.
 * (as they'll contain instance methods used by the server program!) .
 * </p>
 *
 * <p>To identify a server, pass the server id or its hash instead!</p>
 *
 * <p>Server is the sole class meant to contain the all <code>User</code> instances. When a client logs into the server,
 * the server authenticates and verifies them, and only then assigns them a <code>User</code> instance, which
 * allows them to engage in user actions like sending/receiving messages.</p>
 */
public interface ShojaChatDirectoryServer
{
    public String getServerName();
    public void setServerName(String name);

    // server cache is the temporary storage a server uses to store messages and images/files
    public long getServerCacheSize();
    public void setServerCacheSize(long newsize);

    // server limitations
    public int getMaxUsersAllowed();
    public void setMaxUsersAllowed(int newcapacity);
    public int getMaxUsersAllowedOnline();
    public void setMaxUsersAllowedOnline(int newcapacity);
}
