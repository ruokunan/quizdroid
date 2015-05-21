package edu.washington.ruokua.quizdroid.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ruokua on 5/21/15.
 */
public  class NetworkDetected {
    private static final int INVALID = 0;
    public static void checkNetwork(Context context) {

        //Check the network connection
        if (!isNetworkConnectionOn(context.getApplicationContext())) {
            Log.d("TAG", " internet connection unavailable...");
            if (isAirplaneModeOn(context.getApplicationContext())) {
                onAirplaneModeOn(context.getApplicationContext());
            } else {
                Toast.makeText(context.getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }
    }


    /**
     * Gets the state of NetWork Connect
     *
     * @return true if the Mobile Phone has network connection
     */
    private static boolean isNetworkConnectionOn(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //Gets the state of Airplane Mode.
    //@return true if Airplane enabled.
    @SuppressWarnings("deprecation")
    private static boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, INVALID) != INVALID;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, INVALID) != INVALID;
        }


    }



    // Ask if user want to turn off airplane mode,
    // take user to the setting if user would like to turn off airplane mode
    private static void onAirplaneModeOn(final Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("No Internet Connection, Would you like " +
                "turn the AirPlane Mode off")
                .setTitle("AirPlane Mode On");

        builder.setPositiveButton("Turn off AirPlane Mode", new DialogInterface.OnClickListener() {
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
