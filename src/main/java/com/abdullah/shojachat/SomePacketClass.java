package com.abdullah.shojachat;

import java.io.Serializable;

public class SomePacketClass implements Serializable
{
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String message;
    String username;
}
