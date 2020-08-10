package com.neo.androidsecurityeffectivepermissionhandling.messagelistscreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

/**
 * Created by nitinsingh on 30/09/18.
 */

public class UserStatusUpdateReceiver extends BroadcastReceiver{
    private static final String TAG = "UserStatusUpdateReceive";

    @Override
    public void onReceive(Context context, Intent intent) {
        updateUserStatus();
        Toast.makeText(context, "true" + "", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onReceive: broadcast Recieved");
    }


    private boolean hasPermissionToWriteChatContacts(Context context){
        PackageManager pm = context.getPackageManager();
        return pm.checkPermission("com.nitin.pluralchat.WRITE_CHAT_CONTACTS", "com.nitin.myapplication") == PackageManager.PERMISSION_GRANTED;
    }














    private void updateUserStatus(){

    }

}
