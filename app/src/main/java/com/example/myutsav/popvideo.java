package com.example.myutsav;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class popvideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popvideo);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_popvideo);
        VideoView videoView = dialog.findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.video));

        dialog.show();
        videoView.start();
    }
}