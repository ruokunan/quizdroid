package edu.washington.ruokua.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TopicFrontPage extends AppCompatActivity {
    Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent launchingIntent = getIntent();
        String topic = launchingIntent.getStringExtra("topic");
        String description = launchingIntent.getStringExtra("description");

        Toast.makeText(this, topic, Toast.LENGTH_SHORT).show();

        setContentView(R.layout.topic_overview);

        TextView quizTopic = (TextView)findViewById(R.id.quizTopic);
        quizTopic.setText(topic);
        TextView topicDesc = (TextView)findViewById(R.id.topicDesc);
        topicDesc.setText(description);

        startButton = (Button)findViewById(R.id.btnStart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }


            });

    }



}
