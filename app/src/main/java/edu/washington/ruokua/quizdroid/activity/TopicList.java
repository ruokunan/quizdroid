package edu.washington.ruokua.quizdroid.activity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import edu.washington.ruokua.quizdroid.R;
import edu.washington.ruokua.quizdroid.service.DownloadService;
import edu.washington.ruokua.quizdroid.util.QuizApp;
import edu.washington.ruokua.quizdroid.util.Topic;
import edu.washington.ruokua.quizdroid.util.TopicListAdapter;

/**
 * @author ruokunan
 *         Provide user a list of topic
 *         a user could select one topic to take quiz
 */
public class TopicList extends AppCompatActivity {
    private DownloadManager downloadManager;
    private int RESULT = 0;
    private static final int INVALID = -1;
    private static final String TAG = TopicList.class.getName();

    /**
     * {@inheritDoc}
     * <p/>
     * Display a List of topics on which allow user to take quiz
     * When user click the topic on the list, head to the overview of that topic
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
                Intent topicDesc = new Intent(TopicList.this, QuizDroidModel.class);
                topicDesc.putExtra("topicPosition", position);
                startActivity(topicDesc);


            }
        });

        //Check the network connection
        if (!isNetworkConnectionOn(this)) {
            Log.d(TAG," internet connection unavailable...");
            if(isAirplaneModeOn(this)) {
                onAirplaneModeOn(this);
            } else {
                Toast.makeText(this, "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }


    }


    /**
     * Ask if user want to turn off airplane mode,
     * take user to the setting if user would like to turn off airplane mode
     * @param context
     */
    protected void onAirplaneModeOn(final Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("No Internet Connection")
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


    /**
     * Gets the state of Airplane Mode.
     *
     * @param context
     * @return true if Airplane enabled.
     */
    @SuppressWarnings("deprecation")
    private  boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, INVALID) != INVALID;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, INVALID) != INVALID;
        }


    }


    /**
     * Gets the state of NetWork Connect
     *
     * @param context
     * @return true if the Mobile Phone has network connection
     */
    private  boolean isNetworkConnectionOn(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    // This is your receiver that you registered in the onCreate that will receive any messages that match a download-complete like broadcast
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

            Log.i("MyApp BroadcastReceiver", "onReceive of registered download reciever");

            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                Log.i("MyApp BroadcastReceiver", "download complete!");
                long downloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);

                // if the downloadID exists
                if (downloadID != 0) {

                    // Check status
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(downloadID);
                    Cursor c = downloadManager.query(query);
                    if (c.moveToFirst()) {
                        int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                        Log.d("DM Sample", "Status Check: " + status);
                        switch (status) {
                            case DownloadManager.STATUS_PAUSED:
                            case DownloadManager.STATUS_PENDING:
                            case DownloadManager.STATUS_RUNNING:
                                break;
                            case DownloadManager.STATUS_SUCCESSFUL:
                                // The download-complete message said the download was "successfu" then run this code
                                ParcelFileDescriptor file;
                                StringBuffer strContent = new StringBuffer("");

                                try {
                                    // Get file from Download Manager (which is a system service as explained in the onCreate)
                                    file = downloadManager.openDownloadedFile(downloadID);
                                    FileInputStream fis = new FileInputStream(file.getFileDescriptor());

                                    // YOUR CODE HERE [convert file to String here]


                                    // YOUR CODE HERE [write string to data/data.json]
                                    //      [hint, i wrote a writeFile method in MyApp... figure out how to call that from inside this Activity]

                                    // convert your json to a string and echo it out here to show that you did download it



                                    /*
                                    String jsonString = ....myjson...to string().... chipotle burritos.... blah
                                    Log.i("MyApp - Here is the json we download:", jsonString);
                                    */

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case DownloadManager.STATUS_FAILED:
                                // YOUR CODE HERE! Your download has failed! Now what do you want it to do? Retry? Quit application? up to you!
                                break;
                        }
                    }
                }
            }
        }
    };


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

            Intent PreferenceActivity = new Intent(TopicList.this, SettingsActivity.class);
            startActivityForResult(PreferenceActivity, RESULT);
            assert (RESULT != -1);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT) {

            DownloadService.startOrStopAlarm(this, true);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DownloadService.startOrStopAlarm(this, false);
        Log.i(TAG, "turn off receiver");
        unregisterReceiver(receiver);
    }


}
