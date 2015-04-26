package edu.washington.ruokua.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class TopicFrontPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent launchingIntent = getIntent();
        String topic = launchingIntent.getStringExtra("topic");
        Toast.makeText(this, topic, Toast.LENGTH_SHORT).show();
        switch (topic) {
            case  "Physics":
                setContentView(R.layout.physics_topic_overview);
                break;
            case  "Marvel Super Heroes":
                setContentView(R.layout.marvel_topic_overview);
                break;

            case "Computer Science":
                setContentView(R.layout.computer_topic_overview);
                break;

            case  "Math" :
                setContentView(R.layout.math_topic_overview);
                break;


        }

    }



}
