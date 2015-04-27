package edu.washington.ruokua.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class AppFrontPage extends AppCompatActivity {
    // A list of different topics on which allow user to take quiz
    private String[] topics;


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
        setContentView(R.layout.activity_main);


        Intent launchingIntent = getIntent();
        topics = launchingIntent.getStringArrayExtra("topics");

        topicList = (ListView) findViewById(R.id.lstTopic);

        ArrayAdapter<String> items = new ArrayAdapter<String>(this,
                R.layout.topic_list_item, topics);
        topicList.setAdapter(items);

        topicList.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent topicChoose = new Intent(AppFrontPage.this,
                        QuizDroidModel.class);
                topicChoose.putExtra("topicIndex", position);

                startActivity(topicChoose);


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
