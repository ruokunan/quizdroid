package edu.washington.ruokua.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    // A list of different topics on which allow user to take quiz
    private String[] topics = {"Math", "Physics", "Marvel Super Heroes"};
    private final String MATH_DESC = "the abstract science of number, quantity, " +
            "and space. Mathematics may be studied in its own right ( pure mathematics )," +
            "or as it is applied to other disciplines such as physics and " +
            "engineering ( applied mathematics ).";

    private final String PHYSICS_DESC = "the branch of science concerned with the nature and " +
            "properties of matter and energy. The subject matter of physics, distinguished from " +
            "that of chemistry and biology, includes mechanics, heat, light and other radiation, " +
            "sound, electricity, magnetism, and the structure of atoms.";

    private final String MARVEL_DESC = "The Marvel Super Heroes is an American / Canadian " +
            "animated television series starring five comic-book superheroes from Marvel Comics." +
            " The first TV series based on Marvel characters," +
            " it debuted in syndication on U.S. television in 1966. ";
    private String[] description = {MATH_DESC, PHYSICS_DESC,MARVEL_DESC};

    //The view of topic
    private ListView topicList;


    /**
     * Display a List of topics on which allow user to take quiz
     *
     * @param savedInstanceState contains the data it most recently
     *                           supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topicList = (ListView) findViewById(R.id.lstSummerHeroes);

        ArrayAdapter<String> items = new ArrayAdapter<String>(this,
                R.layout.topic_list_item, topics);
        topicList.setAdapter(items);

        topicList.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent topicFrontPage = new Intent(MainActivity.this,
                        TopicFrontPage.class);
                topicFrontPage.putExtra("topic", topics[position]);
                topicFrontPage.putExtra("description", description[position]);

                startActivity(topicFrontPage);


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
