package edu.washington.ruokua.quizdroid.service;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.ParcelFileDescriptor;
import android.provider.Settings;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import edu.washington.ruokua.quizdroid.activity.FrontPageAcitivity;
import edu.washington.ruokua.quizdroid.util.QuizApp;

/**
 * Created by ruokua on 5/20/15.
 */
public class QuestionJsonDownLoadReceiver extends BroadcastReceiver {
    public static final String QUESTIONS_JSON_FILE = "questions.json";
    public static final String CHARSET_NAME =  "UTF-8";
    private Context context;


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        this.context = context;
        DownloadManager downloadManager = (DownloadManager) context.getSystemService
                (context.DOWNLOAD_SERVICE);

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
                    Log.d("Tag", "Status Check: " + status);
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {

                        // The download-complete message said the download was "successfu" then run this code
                        ParcelFileDescriptor file;
                        StringBuffer strContent = new StringBuffer("");

                        try {
                            file = downloadManager.openDownloadedFile(downloadID);
                            if(file == null) {
                                onDownloadFAILED();





                            } else {
                                Log.i("begin write", " begin write");
                                InputStream initialStream = new FileInputStream(file.getFileDescriptor());
                                byte[] buffer = new byte[initialStream.available()];
                                initialStream.read(buffer);


                                File targetFile = new File(context.getFilesDir().getAbsolutePath(), "/" +
                                        QUESTIONS_JSON_FILE);
                                OutputStream outStream = new FileOutputStream(targetFile);
                                outStream.write(buffer);
                                QuizApp quizApp = (QuizApp) context.getApplicationContext();
                                quizApp.updateRepository();

                                Intent topicDesc = new Intent(context.getApplicationContext(), FrontPageAcitivity.class);
                                  context.startActivity(topicDesc);


                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            onDownloadFAILED();

                            Log.e("tag", "please class this one");
                        }

                    } else  {
                        onDownloadFAILED();
                        // YOUR CODE HERE! Your download has failed! Now what do you want it to do? Retry? Quit application? up to you!

                    }
                }
            }
        }

    }


  
    private void onDownloadFAILED() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Retry to download?")
                .setTitle("Download Failed");

        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
             SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String url = sharedPreferences.getString("", "http://tednewardsandbox.site44.com/questions.json");

              
                DownloadManager downloadManager  = (DownloadManager)context.getSystemService(DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                downloadManager.enqueue(request);
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


}
