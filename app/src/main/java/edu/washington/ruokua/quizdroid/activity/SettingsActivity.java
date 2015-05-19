package edu.washington.ruokua.quizdroid.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import edu.washington.ruokua.quizdroid.R;

/**
 * Created by ruokua on 5/16/15.
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preference);
    }


}
