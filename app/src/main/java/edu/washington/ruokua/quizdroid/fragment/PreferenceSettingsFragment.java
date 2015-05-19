package edu.washington.ruokua.quizdroid.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import edu.washington.ruokua.quizdroid.R;

/**
 * Created by ruokua on 5/19/15.
 */
public class PreferenceSettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
    }


}
