package edu.washington.ruokua.quizdroid;

import android.app.Application;
import android.util.Log;

import edu.washington.ruokua.quizdroid.util.InMemoryTopicRepository;
import edu.washington.ruokua.quizdroid.util.TopicRepository;

/**
 * Created by ruokua on 5/8/15.
 */
public class QuizApp  extends Application {

    private static QuizApp instance = null;
    private static final String TAG = QuizApp.class.getName();
    private int topicIndex;

    public int getTopicIndex() {
        return topicIndex;
    }

    public void setTopicIndex(int topicIndex) {
        this.topicIndex = topicIndex;
    }

    private TopicRepository repository;



    /**
     * {@inheritDoc}
     * Emit a message to diagnose log to ensure QuizApp being loaded and run
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "The QuizApp successfully constructed");
    }

    public TopicRepository getRepository() {
        return repository;
    }

/* Protect MyApp at runtime */

    public QuizApp() {
        if (instance == null) {
            instance = this;
            this.repository = new InMemoryTopicRepository();
        } else {
            throw new RuntimeException("Cannot create more than one MyApp");
        }
    }



}
