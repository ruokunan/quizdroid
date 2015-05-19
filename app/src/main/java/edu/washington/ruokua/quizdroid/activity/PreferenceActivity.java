package edu.washington.ruokua.quizdroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import edu.washington.ruokua.quizdroid.R;
import edu.washington.ruokua.quizdroid.fragment.PreferenceSettingsFragment;

/**
 * Created by ruokua on 5/16/15.
 */
public class PreferenceActivity  extends AppCompatActivity   {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PreferenceSettingsFragment())
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_perference, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()== R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
