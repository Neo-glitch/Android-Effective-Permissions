package com.neo.androidsecurityeffectivepermissionhandling;

/**
 * Created by nitinsingh on 27/07/18.
 */

public class ChatListItem {
    public String contactName;
    public String chatSnippet;
    public String contactIconUrl;
    public String chatTime;

    public ChatListItem(String contactName, String chatSnippet, String chatTime){
        this.contactName = contactName;
        this.chatSnippet = chatSnippet;
        this.chatTime = chatTime;
    }
}
