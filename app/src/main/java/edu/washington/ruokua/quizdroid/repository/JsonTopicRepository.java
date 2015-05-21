package edu.washington.ruokua.quizdroid.repository;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.washington.ruokua.quizdroid.jsonObject.QuestionTemplate;
import edu.washington.ruokua.quizdroid.jsonObject.TopicTemplate;
import edu.washington.ruokua.quizdroid.service.QuestionJsonDownLoadReceiver;
import edu.washington.ruokua.quizdroid.util.Question;
import edu.washington.ruokua.quizdroid.util.Topic;

/**
 * Created by ruokua on 5/11/15.
 */
public class JsonTopicRepository implements TopicRepository {
    //The list of topic allow user to take quiz on
    private List<Topic> serverTopics;
    //The bias use to correct the index of answer when parse each answer
    private final int ORDINAL_NUMBER_BIAS = 1;
    //Indicate if this RepositoryManager read in data successfully
    private boolean buildSucceed;
    // Hold a list of TopicTemplate from parsed data
    private List<TopicTemplate> jsonMappers;

    private Context context;

    private String TAG = JsonTopicRepository.class.getName();



    /**
     * @param context the context of this topic repository
     * @effects: initialize an JonTopic RepositoryManager and by read given file
     * @effects: fill servertopics with  topic with only title and short description
     */

    public JsonTopicRepository(Context context) {

        serverTopics = new ArrayList<>();
        this.context = context;
        build();

        assert (serverTopics != null);

    }



    public boolean build() {
        try {
            //Read in Json File
            InputStream inputStream = context.getAssets().
                    open(QuestionJsonDownLoadReceiver.QUESTIONS_JSON_FILE);
            ObjectMapper mapper = new ObjectMapper();

            jsonMappers = mapper.readValue(inputStream,
                    new TypeReference<List<TopicTemplate>>() {
                    });

            Log.d(TAG, jsonMappers.get(0).toString());




            //Build topic from parsed data
            for (int i = 0; i < jsonMappers.size(); i++) {
                serverTopics.add(createTopics(i));
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }

    }

    /**
     * @return if the JsonTopicRepository successfully
     * Read in Json File
     */
    public boolean isBuildSucceed() {
        return buildSucceed;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Topic> getTopicList() {
        if (serverTopics == null) {
            return new ArrayList<>();
        }
        return serverTopics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Topic getCurrentTopic(int topicIndex) {
        return serverTopics.get(topicIndex);
    }

    /**
     * @param topicIndex the index of given topic
     * @return fully initialize the given topic with given data
     */
    private Topic createTopics(int topicIndex) {
        TopicTemplate topicTemplate = jsonMappers.get(topicIndex);

        return new Topic.Builder(topicTemplate.getTitle(), topicTemplate.getDesc())
                .questions(new ArrayList<>(createQuestions(topicIndex)))
                .build();

    }

    /**
     * @param topicIndex the index of given topic
     * @return construct a list of question for given topic from
     * the parsed Json Data
     */
    private List<Question> createQuestions(int topicIndex) {
        List<Question> questions = new ArrayList<>();
        List<QuestionTemplate> questionTemplates = jsonMappers.get(topicIndex).getQuestions();
        assert (questionTemplates != null);
        for (QuestionTemplate questionTemplate : questionTemplates) {
            assert (questionTemplate != null);
            questions.add(new Question(questionTemplate.getDescription(),
                    questionTemplate.getOptions(),
                    questionTemplate.getAnswer() - ORDINAL_NUMBER_BIAS));
        }
        Log.e(TAG, questions.toString());
        assert (questions != null);
        return questions;
    }

}
