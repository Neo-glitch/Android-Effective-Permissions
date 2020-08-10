package com.neo.androidsecurityeffectivepermissionhandling;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by nitinsingh on 27/07/18.
 */

public class ChatListAdapter extends ArrayAdapter<ChatListItem> {
    private Context context;
    private ArrayList<ChatListItem> chatListItems;

    public ChatListAdapter(Context context, ArrayList<ChatListItem> chatListItems){
        super(context, -1, chatListItems);
        this.context = context;
        this.chatListItems = chatListItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chat_list_item, parent, false);

        TextView contactName = (TextView) rowView.findViewById(R.id.contactName);
        TextView chatSnippet = (TextView) rowView.findViewById(R.id.chatSnippet);
        TextView chatTime = (TextView) rowView.findViewById(R.id.chatTime);
        ChatListItem chatListItem = chatListItems.get(position);

        contactName.setText(chatListItem.contactName);
        chatSnippet.setText(chatListItem.chatSnippet);
        chatTime.setText(chatListItem.chatTime);
        return rowView;
    }
}
