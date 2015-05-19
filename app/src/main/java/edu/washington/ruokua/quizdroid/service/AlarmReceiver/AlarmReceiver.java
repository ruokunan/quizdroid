package edu.washington.ruokua.quizdroid.service.AlarmReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ruokua on 5/19/15.
 */

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = AlarmReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Alarm Received");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String url = sharedPreferences.getString("download_url", null);
        assert(url != null);
        String toastMessage = "Download for the url {" + url + "}";
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();

        Intent  download = new Intent(context, DownloadService.class);
        context.startService(download);
    }

}