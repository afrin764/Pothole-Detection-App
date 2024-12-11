package com.example.potholedetectionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityHome extends AppCompatActivity {
    private TextView Profile,blogs,post,detect,map;



    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
       Profile = findViewById(R.id.txtprofile);
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivityHome.this,MainActivityProfile.class);
                startActivity(intent);
            }
        });

        blogs = findViewById(R.id.txtblogs);
    blogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivityHome.this,MainActivityBlogs.class);
                startActivity(intent);
            }
        });
     post= findViewById(R.id.txtpost);
    post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityHome.this,MainActivityPost.class);
                startActivity(intent);
            }
        });
        detect=findViewById (R.id.txtdetection);
      detect.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityHome.this,MainActivityDetect.class);
                startActivity(intent);
            }
        });
        map=findViewById (R.id.txtmap);
        map.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityHome.this,MainActivityMap.class);
                startActivity(intent);
            }
        });
    }
}