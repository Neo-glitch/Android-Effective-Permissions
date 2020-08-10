package com.neo.androidsecurityeffectivepermissionhandling;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.neo.androidsecurityeffectivepermissionhandling.messagelistscreen.MessageListActivity;

import java.security.Permission;
import java.security.Permissions;
import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    private static final String TAG = "ChatListActivity";
    public static final int MY_PERMISSION_REQUEST = 100;

    private ListView mListView;
    private ChatListAdapter mListAdapter;
    private ArrayList<ChatListItem> mChatListItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        mListView = (ListView) findViewById(R.id.chat_list);
        mListAdapter = new ChatListAdapter(this, mChatListItems);
        mListView.setAdapter(mListAdapter);

        mListView.setOnItemClickListener(this);
        updateChatList();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }




    private void updateChatList() {
        ChatListItem temp = new ChatListItem("Harry", "How are you son?", "9:30pm");
        mChatListItems.add(temp);
        temp = new ChatListItem("Doakes", "See you at the cabin", "8:00pm");
        mChatListItems.add(temp);
        temp = new ChatListItem("Dexter", "I am out on my boat", "12:30am");
        mChatListItems.add(temp);
        temp = new ChatListItem("Debra", "Want to grab a beer", "yesterday");
        mChatListItems.add(temp);
        temp = new ChatListItem("Morgan", "What's up bro", "yesterday");
        mChatListItems.add(temp);
        temp = new ChatListItem("Rita", "My car broke down", "yesterday");
        mChatListItems.add(temp);
        temp = new ChatListItem("Codey", "Where are my doughnuts", "yesterday");
        mChatListItems.add(temp);
        temp = new ChatListItem("Masuka", "Exciting stuff man", "yesterday");
        mChatListItems.add(temp);
        temp = new ChatListItem("Batista", "Wanna go bowling", "yesterday");
        mChatListItems.add(temp);

        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(this, MessageListActivity.class);
        intent.putExtra(MessageListActivity.EXTRA_CONTACT_NAME, mChatListItems.get(position).contactName);
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // method gets the result of our permission request and helps check
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case MY_PERMISSION_REQUEST:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // if array is > 0 and first permission item is granted
                } else{
                    // permission denied
                }
                return;
        }
    }
}
