package edu.washington.ruokua.quizdroid;

import android.app.Application;
import android.util.Log;

/**
 * Created by ruokua on 5/8/15.
 */
public class QuizApp  extends Application {

    private static QuizApp instance = null;
    private static final String TAG = QuizApp.class.getName();





    /**
     * {@inheritDoc}
     * Emit a message to diagnose log to ensure QuizApp being loaded and run
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "The QuizApp successfully constructed");
    }


    /* Protect MyApp at runtime */

    public QuizApp() {
        if (instance == null) {
            instance = this;
        } else {
            throw new RuntimeException("Cannot create more than one MyApp");
        }
    }



}
