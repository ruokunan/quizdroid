package edu.washington.ruokua.quizdroid.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import edu.washington.ruokua.quizdroid.fragment.SettingFragment;

/**
 * Created by ruokua on 5/16/15.
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                            .replace(android.R.id.content, new SettingFragment())
                            .commit();
    }


}
