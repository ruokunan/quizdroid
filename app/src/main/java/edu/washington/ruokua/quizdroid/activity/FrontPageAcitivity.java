package edu.washington.ruokua.quizdroid.activity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import edu.washington.ruokua.quizdroid.R;
import edu.washington.ruokua.quizdroid.service.DownloadService;
import edu.washington.ruokua.quizdroid.service.QuestionJsonDownLoadReceiver;
import edu.washington.ruokua.quizdroid.util.QuizApp;
import edu.washington.ruokua.quizdroid.util.Topic;
import edu.washington.ruokua.quizdroid.util.TopicListAdapter;

/**
 * @author ruokunan
 *         Provide user a list of topic
 *         a user could select one topic to take quiz
 */
public class FrontPageAcitivity extends AppCompatActivity {
    private DownloadManager downloadManager;
    private int RESULT = 0;
    private final int INVALID = 0;
    private final String TAG = FrontPageAcitivity.class.getName();
    // This is your receiver that you registered in the onCreate that will receive any messages
    // that match a download-complete like broadcast
    private final BroadcastReceiver receiver = new QuestionJsonDownLoadReceiver();

    /**
     * {@inheritDoc}
     * <p/>
     * Display a List of topics on which allow user to take quiz
     * When user click the topic on the list, head to the overview of that topic
     * <p/>
     * Check the network connection, display a message to user if there
     * is no network connection
     * <p/>
     * if the user turn the airplane mode on cause no network connection
     * ask user if turned airplane mode off
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);

        // Register Receiver aka Listen if the DownloadManager from Android OS publishes a "Download has complete"-like broadcast
        //      -note that  DownloadManager is a system service that can be accessed anywhere.
        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE); // Add more filters here that you want the receiver to listen to
        registerReceiver(receiver, filter);

        //Check the network connection
        if (!isNetworkConnectionOn(this)) {
            Log.d(TAG, " internet connection unavailable...");
            if (isAirplaneModeOn(this)) {
                onAirplaneModeOn(this);
            } else {
                Toast.makeText(this, "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        } 
        QuizApp quizApp = (QuizApp) getApplication();
        ListView topicList = (ListView) findViewById(R.id.topic_list);

        List<Topic> topics = quizApp.getTopicList();
        if (topics == null) {
            throw new RuntimeException("There is not any topic exist in the repository");
        }

        TopicListAdapter items = new TopicListAdapter(this,
                R.layout.topic_list_item, topics);
        topicList.setAdapter(items);


        //when user click the topic on the list, head to the overview of the topic
        topicList.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent topicDesc = new Intent(FrontPageAcitivity.this, TakeQuizActivity.class);
                topicDesc.putExtra("topicPosition", position);
                startActivity(topicDesc);


            }
        });



    }


    // Ask if user want to turn off airplane mode,
    // take user to the setting if user would like to turn off airplane mode
    private void onAirplaneModeOn(final Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("No Internet Connection, Would you like " +
                "turn the AirPlane Mode off")
                .setTitle("AirPlane Mode On");

        builder.setPositiveButton("Turn off AirPlane Mode", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent settingsIntent = new Intent(Settings.ACTION_SETTINGS);
                settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(settingsIntent);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();

        dialog.show();

    }


    //Gets the state of Airplane Mode.
    //@return true if Airplane enabled.
    @SuppressWarnings("deprecation")
    private boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, INVALID) != INVALID;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, INVALID) != INVALID;
        }


    }


    /**
     *  Gets the state of NetWork Connect
     *  @return true if the Mobile Phone has network connection
     */
    public boolean isNetworkConnectionOn(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    /*
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_perference, menu);
        return true;
    }


    /*
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent PreferenceActivity = new Intent(FrontPageAcitivity.this, SettingsActivity.class);
            startActivityForResult(PreferenceActivity, RESULT);

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    /*
     * {@inheritDoc}
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_CONTACT_REQUEST) {
//            if (resultCode == RESULT_OK) {

        DownloadService.startOrStopAlarm(this, true);
//            }
//        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        DownloadService.startOrStopAlarm(this, false);
        Log.i(TAG, "turn off receiver");
        unregisterReceiver(receiver);
    }


}
