package edu.washington.ruokua.quizdroid.activity;

import android.os.Bundle;

import edu.washington.ruokua.quizdroid.R;

/**
 * Created by ruokua on 5/16/15.
 */
public class PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
