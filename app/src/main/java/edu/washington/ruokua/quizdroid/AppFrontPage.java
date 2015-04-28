package edu.washington.ruokua.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class AppFrontPage extends AppCompatActivity {
    // A list of different topics on which allow user to take quiz


    private final int NUM_Math_QUESTIONS = 3;

    private final int NUM_Physics_QUESTIONS = 0;

    private final int NUM_MARVEL_QUESTIONS = 0;

    private final String[] TOPICS = {"Math", "Physics", "Marvel Super Heroes"};


    //The view of topic
    private ListView topicList;

    private String topic;
    /**
     * Display a List of topics on which allow user to take quiz
     *
     * @param savedInstanceState contains the data it most recently
     *                           supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent launchingIntent = getIntent();

        topicList = (ListView) findViewById(R.id.lstTopic);

        ArrayAdapter<String> items = new ArrayAdapter<String>(this,
                R.layout.topic_list_item, TOPICS);
        topicList.setAdapter(items);

        topicList.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent topicDesc = new Intent(AppFrontPage.this, TopicFrontPage.class);
                topic = TOPICS[position];

                topicDesc.putExtra("topic", topic);

                int numQuestion = getNumProblem();
                topicDesc.putExtra("numProblem", numQuestion);

                startActivity(topicDesc);


            }
        });


    }




    private int getNumProblem() {
        switch (topic) {
            case "Math":
                return NUM_Math_QUESTIONS;
            case "Physics":
                return NUM_Physics_QUESTIONS;
            default:

                return NUM_MARVEL_QUESTIONS;
        }
    }


}
