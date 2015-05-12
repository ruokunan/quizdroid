package edu.washington.ruokua.quizdroid.repository;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.washington.ruokua.quizdroid.jsonObject.SampleQuestion;
import edu.washington.ruokua.quizdroid.jsonObject.SampleTopic;
import edu.washington.ruokua.quizdroid.util.Question;
import edu.washington.ruokua.quizdroid.util.Topic;

/**
 * Created by ruokua on 5/11/15.
 */
public class JsonTopicRepository implements TopicRepository {

    private List<Topic> serverTopics;

    private final int ORDINAL_NUMBER_BIAS = 1;
    private boolean buildSucceed = true;
    private List<SampleTopic> jsonMappers;

    private String TAG = JsonTopicRepository.class.getName();


    public JsonTopicRepository(Context context) {


        serverTopics = new ArrayList<>();
        try {
            InputStream inputStream = context.getAssets().open("questions.json");


            ObjectMapper mapper = new ObjectMapper();

            jsonMappers = mapper.readValue(inputStream,
                    new TypeReference<List<SampleTopic>>() {
                    });
            Log.d(TAG, jsonMappers.get(0).toString());

            for (SampleTopic sampleTopic : jsonMappers) {
                serverTopics.add(new Topic.Builder(sampleTopic.getTitle(),
                        sampleTopic.getDesc()).build());
            }
        } catch (IOException e) {
            e.printStackTrace();
            buildSucceed = false;

        }

        assert (serverTopics != null);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Topic> getTopicList() {
        if (serverTopics == null) {
            serverTopics = new ArrayList<>();
        }
        return serverTopics;
    }


    @Override
    public Topic getCurrentTopic(int topicIndex) {
        if (serverTopics.get(topicIndex).getNumQuestionContain() == -1) {
            serverTopics.set(topicIndex, createTopics(topicIndex));
        }
        return serverTopics.get(topicIndex);
    }


    private Topic createTopics(int topicIndex) {
        SampleTopic sampleTopic = jsonMappers.get(topicIndex);

        return new Topic.Builder(sampleTopic.getTitle(), sampleTopic.getDesc())
                .questions(new ArrayList<>(createQuestions(topicIndex)))
                .build();

    }

    private List<Question> createQuestions(int topicIndex) {
        List<Question> questions = new ArrayList<>();
        List<SampleQuestion> sampleQuestions = jsonMappers.get(topicIndex).getQuestions();
        assert (sampleQuestions != null);
        for (SampleQuestion sampleQuestion : sampleQuestions) {
            assert (sampleQuestion != null);
            questions.add(new Question(sampleQuestion.getDescription(),
                    sampleQuestion.getOptions(),
                    sampleQuestion.getAnswer() - ORDINAL_NUMBER_BIAS));
        }
        Log.e(TAG, questions.toString());
        assert (questions != null);
        return questions;
    }


    public boolean isBuildSucceed() {
        return buildSucceed;
    }
}
