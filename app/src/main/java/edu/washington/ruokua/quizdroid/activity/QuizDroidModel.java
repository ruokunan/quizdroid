package edu.washington.ruokua.quizdroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.washington.ruokua.quizdroid.util.QuizApp;
import edu.washington.ruokua.quizdroid.R;
import edu.washington.ruokua.quizdroid.fragment.TopicOverviewFragment;

/**
 * @author ruokunan
 *
 * Provide topic description and number of question in that topic for
 * user selected topic and head to the overview of user select topic
 *
 * Create a list of questions base on the user select topic
 * if user decided take quiz on that topic
 *
 */

public class QuizDroidModel extends AppCompatActivity {

    //the user select topic
    private int topicPosition;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get the user select topic
        Intent launchingIntent = getIntent();
        topicPosition = launchingIntent.getIntExtra("topicPosition", -1);
        QuizApp quizApp = (QuizApp) getApplication();
        assert(topicPosition != -1);
        //head to the overview of user select topic

        quizApp.setTopicIndex(topicPosition);
        setContentView(R.layout.topic_overview);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new TopicOverviewFragment())
                .commit();

    }








}
