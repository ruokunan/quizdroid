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
                            // Get file from Download Manager (which is a system service as explained in the onCreate)
                            file = downloadManager.openDownloadedFile(downloadID);
                            FileInputStream fis = new FileInputStream(file.getFileDescriptor());

                            // YOUR CODE HERE [convert file to String here]

                            // YOUR CODE HERE [write string to data/data.json]
                            //      [hint, i wrote a writeFile method in MyApp... figure out how to call that from inside this Activity]

                            // convert your json to a string and echo it out here to show that you did download it
                            String json = readJSONFile(fis);
                            writeToFile(context, json);
                            Log.i("MyApp - Here is the j:", json);
                                    /*
                                    String jsonString = ....myjson...to string().... chipotle burritos.... blah
                                    Log.i("MyApp - Here is the json we download:", jsonString);
                                    */
                            Log.i("begin write", " begin write");

                            InputStream initialStream = new FileInputStream(file.getFileDescriptor());
                            byte[] buffer = new byte[initialStream.available()];
                            initialStream.read(buffer);

                            File targetFile = new File(context.getFilesDir().getAbsolutePath(), "/" +
                                    QUESTIONS_JSON_FILE);
                            OutputStream outStream = new FileOutputStream(targetFile);
                            outStream.write(buffer);
                            QuizApp quizApp = (QuizApp)context.getApplicationContext();
                            quizApp.updateRepository();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (status == DownloadManager.STATUS_FAILED) {
                        onDownloadFAILED();
                        // YOUR CODE HERE! Your download has failed! Now what do you want it to do? Retry? Quit application? up to you!

                    }
                }
            }
        }

    }


    private String readJSONFile(InputStream inputStream) {
        try {
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer, CHARSET_NAME);

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


    private void writeToFile(Context context, String json) {
        try {
            File file = new File(context.getFilesDir().getAbsolutePath(), QUESTIONS_JSON_FILE);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(json.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onDownloadFAILED() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Retry to download?")
                .setTitle("Download Failed");

        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent settingsIntent = new Intent(Settings.ACTION_SETTINGS);
                settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(settingsIntent);
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
