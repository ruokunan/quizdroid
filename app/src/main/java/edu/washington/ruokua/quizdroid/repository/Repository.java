package edu.washington.ruokua.quizdroid.repository;

import android.content.Context;

import java.util.List;

import edu.washington.ruokua.quizdroid.util.Topic;

/**
 * Created by ruokua on 5/11/15.
 */
public class Repository implements TopicRepository {
    private TopicRepository repository;

    /**
     *
     * @param context
     * @effects Use Json data if this could successfully read in Json data,
     * otherwise use hardcode data
     */
    public Repository(Context context) {
        JsonTopicRepository jsonTopicRepository = new JsonTopicRepository(context);
        if(jsonTopicRepository.isBuildSucceed()) {
            repository = jsonTopicRepository;
        } else {
            repository =  new InMemoryTopicRepository();
        }

    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public List<Topic> getTopicList() {
        return repository.getTopicList();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Topic getCurrentTopic(int topicIndex) {
        return repository.getCurrentTopic(topicIndex);
    }
}
