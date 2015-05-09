package edu.washington.ruokua.quizdroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.washington.ruokua.quizdroid.QuizApp;
import edu.washington.ruokua.quizdroid.R;

/**
 * @author ruokunan
 * Provide user a list of topic
 * a user could select one topic to take quiz
 */
public class TopicList extends AppCompatActivity {




    //The view of topic
    private ListView topicList;

    /**
     * {@inheritDoc}
     *
     * Display a List of topics on which allow user to take quiz
     * When user click the topic on the list, head to the overview of that topic
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_list);


        QuizApp quizApp = (QuizApp)getApplication();
        topicList = (ListView) findViewById(R.id.lstTopic);


        ArrayAdapter<String> items = new ArrayAdapter<String>(this,
                R.layout.topic_list_item, quizApp.getRepository().getTopicList());
        topicList.setAdapter(items);

        //when user click the topic on the list, head to the overview of the topic
        topicList.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent topicDesc = new Intent(TopicList.this, QuizDroidModel.class);
                topicDesc.putExtra("topicPosition",  position);
                startActivity(topicDesc);


            }
        });


    }






}
