package com.example.potholedetectionapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivityAnimation extends AppCompatActivity {

    private Button startButton;
    private LottieAnimationView animatedElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        startButton = findViewById(R.id.start_button);
        animatedElement = findViewById(R.id.animated_element);

        // Set animation file name and start the animation
        animatedElement.setAnimation("json/animation.json");
        animatedElement.playAnimation();

        // Set a click listener for the start button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the next page
                Intent intent = new Intent(MainActivityAnimation.this, MainActivityLogIn.class);
                startActivity(intent);
            }
        });
    }
}
