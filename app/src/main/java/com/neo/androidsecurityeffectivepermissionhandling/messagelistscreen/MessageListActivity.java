package com.neo.androidsecurityeffectivepermissionhandling.messagelistscreen;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.neo.androidsecurityeffectivepermissionhandling.R;

import java.util.ArrayList;
import java.util.List;

public class MessageListActivity extends AppCompatActivity {
    private static final String TAG = "MessageListActivity";

    // constants
    public static final String EXTRA_CONTACT_NAME = "MessageListActivity.contactName";
    public static final int MY_PERMISSION_REQUEST = 100;

    // widgets
    private RecyclerView mMessageRecycler;

    //vars
    private MessageListAdapter mMessageAdapter;
    private List<Message> mMessageList = new ArrayList<>();
    private String sender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        this.sender = getIntent().getStringExtra(MessageListActivity.EXTRA_CONTACT_NAME);

        setTitle(sender);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageAdapter = new MessageListAdapter(this, mMessageList);
        mMessageRecycler.setAdapter(mMessageAdapter);

        updateMessageList();

        findViewById(R.id.button_take_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });
        findViewById(R.id.button_send_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLocation();
            }
        });
    }

    private void sendLocation() {
        requestLocationPermissions();
    }

    private void requestLocationPermissions() {
        if (!checkPermission()) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                showPermissionRationale();       // show dialog explaining why permission needed and asks for permission
            } else {                            // no need to explain
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSION_REQUEST);
            }
        } else {
            // permission already granted and proceed to work
            return;
        }
    }

    private void showPermissionRationale(){   // shows explanation dialog to user and asks permission if +ve btn clicked
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Location permission explanation")
                .setMessage("PluralChat needs the location permission to send your location to your friends")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MessageListActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSION_REQUEST);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }


    private boolean checkPermission() {  // checks if requested permission is already granted
        final boolean check = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        return check;
    }


    /**
     * func launches dev cam app
     */
    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        MediaStore.ACTION_IMAGE_CAPTURE
        startActivityForResult(intent, 100);
    }

    public void updateMessageList() {
        Message temp = new Message("Hiiii", "12:10pm", this.sender, false);
        mMessageList.add(temp);
        temp = new Message("Hey", "12:11pm", this.sender, true);
        mMessageList.add(temp);
        temp = new Message("What's the plan?", "12:14pm", this.sender, false);
        mMessageList.add(temp);
        temp = new Message("You are coming to the party right?", "12:14pm", this.sender, false);
        mMessageList.add(temp);
        temp = new Message("Yep! I am in", "12:20pm", this.sender, true);
        mMessageList.add(temp);
        temp = new Message("Cool, Cya at my place then", "12:22pm", this.sender, false);
        mMessageList.add(temp);
        temp = new Message("Same time", "12:30pm", this.sender, false);
        mMessageList.add(temp);
        temp = new Message("Cya", "12:35pm", this.sender, true);
        mMessageList.add(temp);

        mMessageAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // call back method after user makes a choice on the permission dialog
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: permission granted");
            } else {
                Log.d(TAG, "onRequestPermissionsResult: permission denied");
            }
        }
    }
}
