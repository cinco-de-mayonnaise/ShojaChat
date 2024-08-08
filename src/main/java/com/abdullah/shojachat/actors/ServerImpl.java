package com.abdullah.shojachat.actors;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import com.abdullah.shojachat.util.Hasher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Behold, the server class. Each server class instance represents a unique server, containing all information
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
public class ServerImpl implements ShojaChatDirectoryServer
{
    //// Server data
    Logger logger = LoggerFactory.getLogger("ShojaChat Server - Software Implementation");
    private final ServerImplData serverData;   // server is a runtime classes, needs this data to work xd
    private Hasher hasher = null;

    // we probably definitely need some network configuration sht but cant think of any right now
    private ConcurrentHashMap<String, User> connections = new ConcurrentHashMap<>();          // server needs to have a reference to the socket to be able to send/receive data to/from the client

    ////////////////////////////////////////////
    public ServerImpl(ServerImplData s)
    {
        this.serverData = s;

        try
        {
            this.hasher = new Hasher(s.getHashType());
        }
        catch (NoSuchAlgorithmException e)
        {
            logger.error("An already existing server was somehow set to a Hash type that is not available on this machine configuration. Change it and try again.");
            throw new RuntimeException("Failed to start server: Hash type not available");
        }

        logger.trace("Successfully loaded server {}", this.getServerName());
    }

    public ServerImpl(String hashType, int maxUsers, boolean requireEmail, boolean allowImages, boolean allowFiles, boolean enableStatistics,long size_spaceAllowed_bytes, String name, int maxUsersOnline) throws NoSuchAlgorithmException, IllegalArgumentException
    {
        hasher = new Hasher(hashType);  // check if hashType is valid or itll throw an exception

        if (maxUsers <= 1)
            throw new IllegalArgumentException("A server must allow at least 2 users!");   // ensure maxUser is at least 2, or set it to default 20

        requireEmail = false;            // false always, for now

        // a server should have at least 1 mb of space available
        if (size_spaceAllowed_bytes < 1024*1024)
            throw new IllegalArgumentException("A server should have at least 1 mb of space available!");

        if (maxUsersOnline <= 1)
            throw new IllegalArgumentException("A server must allow at least 2 concurrent users to connect!");   // ensure maxUser is at least 2, or set it to default 20

        if (name == null || name.length() >= 4 || name.isBlank() || name.isEmpty())
            throw new IllegalArgumentException("A server should have a descriptive name");

        serverData = new ServerImplData(
                                        UUID.randomUUID(),
                                        hashType,
                                        requireEmail,
                                        allowImages,
                                        allowFiles,
                                        enableStatistics,
                                        size_spaceAllowed_bytes,
                                        maxUsers,
                                        maxUsersOnline
        );

    }


    // Server
    @Override
    public String getServerName() {
        return serverData.getName();
    }

    @Override
    public void setServerName(String name)
    {
        if (name == null || name.length() >= 4 || name.isBlank() || name.isEmpty())
            throw new IllegalArgumentException("A server should have a descriptive name");

        logger.info("Server name has changed, old: \"{}\", new: \"{}\"", serverData.getName(), name);
        serverData.setName(name);
    }

    @Override
    public long getServerCacheSize() {
        return serverData.getSize_spaceAllowed_bytes();
    }

    @Override
    public void setServerCacheSize(long newsize)
    {
        if (newsize < 1024*1024)
            throw new IllegalArgumentException("A server should have at least 1 mb of space available!");

        logger.info("Server Cache size has changed, old: \"{}\", new: \"{}\"", serverData.getSize_spaceAllowed_bytes(), newsize);
        serverData.setSize_spaceAllowed_bytes(newsize);
    }

    @Override
    public int getMaxUsersAllowed() {
        return serverData.getMaxUsers();
    }

    @Override
    public void setMaxUsersAllowed(int newcapacity) {
        if (newcapacity <= 1)
            throw new IllegalArgumentException("A server must allow at least 2 users!");   // ensure maxUser is at least 2, or set it to default 20

        logger.info("Max users allowed to register to server has changed, old: \"{}\", new: \"{}\"", serverData.getMaxUsers(), newcapacity);
        serverData.setMaxUsers(newcapacity);
    }

    @Override
    public int getMaxUsersAllowedOnline() {
        return 0;
    }

    @Override
    public void setMaxUsersAllowedOnline(int newcapacity) {
        if (newcapacity <= 1)
            throw new IllegalArgumentException("A server must allow at least 2 concurrent users to connect!");   // ensure maxUser is at least 2, or set it to default 20

        logger.info("Max users allowed to concurrently stay on server has changed, old: \"{}\", new: \"{}\"", serverData.getMaxUsersOnline(), newcapacity);
        serverData.setMaxUsersOnline(newcapacity);
    }


}
