package edu.washington.ruokua.quizdroid.service;

import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by ruokua on 5/19/15.
 */
public class DownloadService extends IntentService {
    private DownloadManager downloadManager;
    public static final String DEFAULT_DOWNLOAD_URL =
            "http://tednewardsandbox.site44.com/questions.json";
    private static final String TAG = DownloadService.class.getName();

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        Log.i(TAG, "entered onHandleIntent()");

        SharedPreferences sharedPreferences = PreferenceManager.
                getDefaultSharedPreferences(DownloadService.this);
        String url = sharedPreferences.getString(AlarmReceiver.DOWNLOAD_URL,
                DEFAULT_DOWNLOAD_URL);
        downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        long downloadID = downloadManager.enqueue(request);
        Log.i(TAG,downloadID + "");
    }

    public static void startOrStopAlarm(Context context, boolean on) {
        Log.i(TAG, "startOrStopAlarm on = " + on);

        Intent alarmReceiverIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, -1, alarmReceiverIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (on) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            String refreshIntervalString = sharedPreferences.getString("download_interval", "5");

            //TODO: use time unit
            int refreshInterval = Integer.parseInt(refreshIntervalString) * 60000;

            Log.i(TAG, "setting alarm to " + refreshInterval);

            // Start the alarm manager to repeat
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), refreshInterval, pendingIntent);
        } else {
            manager.cancel(pendingIntent);
            pendingIntent.cancel();

            Log.i(TAG, "Stopping alarm");
        }
    }
}

