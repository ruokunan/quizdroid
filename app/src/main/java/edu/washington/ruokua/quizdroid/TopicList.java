package edu.washington.ruokua.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Provide user a list of topic
 * a user could select one topic to take quiz
 */
public class TopicList extends AppCompatActivity {



    // A list of different topics on which allow user to take quiz
    private final String[] TOPICS = {"Math", "Physics", "Marvel Super Heroes"};


    //The view of topic
    private ListView topicList;

    /**
     * Display a List of topics on which allow user to take quiz
     *
     * @param savedInstanceState contains the data it most recently
     *                           supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_list);



        topicList = (ListView) findViewById(R.id.lstTopic);

        ArrayAdapter<String> items = new ArrayAdapter<String>(this,
                R.layout.topic_list_item, TOPICS);
        topicList.setAdapter(items);

        topicList.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent topicDesc = new Intent(TopicList.this, QuizDroidModel.class);
                topicDesc.putExtra("topic",  TOPICS[position]);
                startActivity(topicDesc);


            }
        });


    }






}
