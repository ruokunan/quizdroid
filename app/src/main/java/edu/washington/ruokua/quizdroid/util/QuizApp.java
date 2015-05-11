package edu.washington.ruokua.quizdroid.util;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.List;

import edu.washington.ruokua.quizdroid.jsonObject.TopicJsonParser;
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

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://tednewardsandbox.site44.com/questions.json");
            HttpResponse response = client.execute(httpGet);

            ObjectMapper mapper = new ObjectMapper();
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if(statusCode != 200) {
                throw  new RuntimeException("Net work Probelm");
            }
            HttpEntity entity = response.getEntity();



            // read from file, convert it to user class
            TopicJsonParser[] jsonMappers = mapper.readValue(entity.getContent(), TopicJsonParser[].class);
            // display to console
            // System.out.println(user);
            Log.i("This is it" ,jsonMappers[0].toString());
        } catch (JsonGenerationException e) {

            e.printStackTrace();
            Log.e("Fuck you" , "File Not Found");

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }














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
