package edu.washington.ruokua.quizdroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import edu.washington.ruokua.quizdroid.service.DownloadService;
import edu.washington.ruokua.quizdroid.R;
import edu.washington.ruokua.quizdroid.fragment.TopicOverviewFragment;
import edu.washington.ruokua.quizdroid.util.QuizApp;

/**
 * @author ruokunan
 *         <p/>
 *         Provide topic description and number of question in that topic for
 *         user selected topic and head to the overview of user select topic
 *         <p/>
 *         Create a list of questions base on the user select topic
 *         if user decided take quiz on that topic
 */

public class TakeQuizActivity extends AppCompatActivity {

    //the user select topic
    private int topicPosition;
    private int RESULT = -1;
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
        assert (topicPosition != -1);
        //head to the overview of user select topic

        quizApp.setTopicIndex(topicPosition);
        setContentView(R.layout.activity_topic_model);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new TopicOverviewFragment())
                .commit();

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

            Intent PreferenceActivity = new Intent(this, SettingsActivity.class);
            startActivityForResult(PreferenceActivity, RESULT);
            assert (RESULT != -1);
            return true;


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == RESULT) {
            DownloadService.startOrStopAlarm(this, true);
        }
    }




}
