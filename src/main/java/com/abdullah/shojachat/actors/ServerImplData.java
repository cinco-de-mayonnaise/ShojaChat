package com.abdullah.shojachat.actors;

import com.abdullah.shojachat.logic.ServerStatistics;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * All data that ServerImpl needs to save to disk should be in here, and read/written to whenever the program opens/closes
 */
public class ServerImplData implements Serializable
{
    //// Server identifiers
    private String name;                                 // Every server needs a name, this is the public facing name visible to everyone.
    private final UUID id;           // unique identifier to identify servers.

    //// Server data
    private ConcurrentHashMap<String, User> allUsers = new ConcurrentHashMap<>();    // Every user of the server should have their instance in here. Username is Key (no same usernames allowed!)

    private final ConcurrentHashMap.KeySetView<String, Boolean> ban_Usernames = ConcurrentHashMap.newKeySet();    // ban people, think of these fields as 'Set<String>', i wanted it to be type set, but Set is not serializable
    private final ConcurrentHashMap.KeySetView<InetAddress, Boolean> ban_IPs = ConcurrentHashMap.newKeySet();
    private final ServerStatistics stats;

    //// Server runtime configuration,
    private final String hashType;                  // how are ALL the hashes in allUsers.User being stored?

    private int maxUsers;                     // self explanatory, max # of users allowed in the server
    private int maxUsersOnline;               // max number of concurrent connections allowed(any more people trying to log on get refused)
    private long size_spaceAllowed_bytes;           // how much encrypted data can the server store on server?
    private final boolean requireEmail;             // check if email is required to use the server. FALSE FOR NOW ALWAYS
    private final boolean allowImages;              // allow images to be uploaded on server
    private final boolean allowFiles;               // allow files to be uploaded to server

    public ServerImplData(UUID id,
                          String hashType,
                          boolean requireEmail,
                          boolean allowImages,
                          boolean allowFiles,
                          boolean enableStatistics,
                          long size_spaceAllowed_bytes,
                          int maxUsers,
                          int maxUsersOnline) {
        this.id = id;
        this.hashType = hashType;
        this.requireEmail = requireEmail;
        this.allowImages = allowImages;
        this.allowFiles = allowFiles;
        this.size_spaceAllowed_bytes = size_spaceAllowed_bytes;
        this.maxUsers = maxUsers;
        this.maxUsersOnline = maxUsersOnline;

        this.stats = new ServerStatistics(enableStatistics);
    }

    ///////// GETTERS

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public ConcurrentHashMap<String, User> getAllUsers() {
        return allUsers;
    }

    public Set<String> getBan_Usernames() {
        return ban_Usernames;
    }

    public String getHashType() {
        return hashType;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public int getMaxUsersOnline() {
        return maxUsersOnline;
    }

    public long getSize_spaceAllowed_bytes() {
        return size_spaceAllowed_bytes;
    }

    public boolean isRequireEmail() {
        return requireEmail;
    }

    public boolean isAllowImages() {
        return allowImages;
    }

    public boolean isAllowFiles() {
        return allowFiles;
    }

    public ServerStatistics stats()
    {
        return stats;
    }

    ///////// SETTERS

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public void setMaxUsersOnline(int maxUsersOnline) {
        this.maxUsersOnline = maxUsersOnline;
    }

    public void setSize_spaceAllowed_bytes(long size_spaceAllowed_bytes) {
        this.size_spaceAllowed_bytes = size_spaceAllowed_bytes;
    }
}
