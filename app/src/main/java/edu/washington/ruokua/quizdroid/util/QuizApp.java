package edu.washington.ruokua.quizdroid.util;

import android.app.Application;
import android.util.Log;

import java.util.List;

import edu.washington.ruokua.quizdroid.repository.Repository;

/**
 * Created by ruokua on 5/8/15.
 */
public class QuizApp extends Application {

    private static QuizApp instance = null;
    private static final String TAG = QuizApp.class.getName();
    private int topicIndex;
    private int MINIMAL_TOPIC_INDEX = 0;
    private Repository repository;



    /**
     * {@inheritDoc}
     * Emit a message to diagnose log to ensure QuizApp being loaded and run
     * @effects: initialize a repository which contain topics
     */
    @Override
    public void onCreate() {
        super.onCreate();
        this.repository =  new Repository(getApplicationContext());
        Log.i(TAG, "The QuizApp successfully constructed");
    }

    /* Protect MyApp at runtime to be a singleton*/
    public QuizApp() {
        if (instance == null) {
            instance = this;

        } else {
            throw new RuntimeException("Cannot create more than one MyApp");
        }
    }

    public void updateRepository() {
        repository.update();
    }


    /**
     *
     * @param topicIndex user select topic
     * @effects change topic index to represent user select topic
     */
    public void setTopicIndex(int topicIndex) {
        assert (topicIndex >= MINIMAL_TOPIC_INDEX && topicIndex < repository.getTopicList().size());
        this.topicIndex = topicIndex;
    }

    /**
     *
     * @return the list of topic this app allow user to take quiz on
     */
    public List<Topic> getTopicList() {
        return repository.getTopicList();
    }

    /**
     *
     * @return the topic the user currently take quiz on
     */
    public Topic getCurrentTopic() {
        return repository.getCurrentTopic(topicIndex);
    }

    /**
     * Check the rep invariant.
     *
     * @effects: nothing if this satisfies rep invariant;
     * otherwise throws an exception
     */
    private void checkRep() {

    }
}
