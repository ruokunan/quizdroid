package edu.washington.ruokua.quizdroid.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import edu.washington.ruokua.quizdroid.R;

/**
 * Created by ruokua on 5/20/15.
 */
public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
    }
}