package edu.washington.ruokua.quizdroid.util;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

import java.util.List;

import edu.washington.ruokua.quizdroid.repository.InMemoryTopicRepository;
import edu.washington.ruokua.quizdroid.repository.TopicRepository;

/**
 * Created by ruokua on 5/8/15.
 */
public class QuizApp  extends Application {

    private static QuizApp instance = null;
    private static final String TAG = QuizApp.class.getName();
    private int topicIndex;


    private TopicRepository repository;

    private final static String URL = "http://tednewardsandbox.site44.com/questions.json";


    /**
     * {@inheritDoc}
     * Emit a message to diagnose log to ensure QuizApp being loaded and run
     */
    @Override
    public void onCreate() {
        super.onCreate();


        Log.i(TAG, "The QuizApp successfully constructed");
    }

    /* Protect MyApp at runtime to be a singleton*/
    public QuizApp() {
        if (instance == null) {
            instance = this;

            setRepository(new InMemoryTopicRepository());
        } else {
            throw new RuntimeException("Cannot create more than one MyApp");
        }
    }

    public void setRepository(TopicRepository repository) {
        this.repository = repository;
    }

    public void setTopicIndex(int topicIndex) {
        assert(topicIndex >= 0 && topicIndex < repository.getTopicList().size());
        this.topicIndex = topicIndex;
    }

    public List<Topic>  getTopicList() {
        return repository.getTopicList();
    }

    public Topic getCurrentTopic() {
        return repository.getCurrentTopic(topicIndex);
    }





}
