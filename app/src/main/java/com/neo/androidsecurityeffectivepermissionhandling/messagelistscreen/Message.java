package com.neo.androidsecurityeffectivepermissionhandling.messagelistscreen;

/**
 * Created by nitinsingh on 27/07/18.
 */

public class Message {
    public String message;
    public String createdAt;
    public String senderName;
    public boolean isSent;

    public Message(String message, String createdAt, String senderName, boolean isSent){
        this.message = message;
        this.createdAt = createdAt;
        this.senderName = senderName;
        this.isSent = isSent;
    }
}
