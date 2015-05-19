package edu.washington.ruokua.quizdroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import edu.washington.ruokua.quizdroid.R;
import edu.washington.ruokua.quizdroid.util.QuizApp;
import edu.washington.ruokua.quizdroid.util.Topic;
import edu.washington.ruokua.quizdroid.util.TopicListAdapter;

/**
 * @author ruokunan
 * Provide user a list of topic
 * a user could select one topic to take quiz
 */
public class TopicList extends AppCompatActivity {


    /**
     * {@inheritDoc}
     *
     * Display a List of topics on which allow user to take quiz
     * When user click the topic on the list, head to the overview of that topic
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);


        QuizApp quizApp = (QuizApp)getApplication();
        ListView topicList = (ListView) findViewById(R.id.topic_list);

        List<Topic> topics = quizApp.getTopicList();
        if(topics == null) {
            throw new RuntimeException("There is not any topic exist in the repository");
        }
        Log.i("fuck you ass", topics.get(1).getTitle());
        TopicListAdapter items = new TopicListAdapter(this,
                R.layout.topic_list_item, topics);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_perference, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, PreferenceActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
