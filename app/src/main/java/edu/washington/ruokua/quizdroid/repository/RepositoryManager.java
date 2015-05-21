package edu.washington.ruokua.quizdroid.repository;

import android.content.Context;

import java.util.List;

import edu.washington.ruokua.quizdroid.util.Topic;

/**
 * Created by ruokua on 5/11/15.
 */
public class RepositoryManager implements TopicRepository {
    private TopicRepository repository;
    private JsonTopicRepository jsonTopicRepository;
    /**
     *
     * @param context
     * @effects Use Json data if this could successfully read in Json data,
     * otherwise use hardcode data
     */
    public RepositoryManager(Context context) {
        jsonTopicRepository = new JsonTopicRepository(context);
        if(jsonTopicRepository.isBuildSucceed()) {
            repository = jsonTopicRepository;
        } else {
            repository =  new InMemoryTopicRepository();
        }

    }

    public void update() {
        if(jsonTopicRepository.build()) {
            repository = jsonTopicRepository;
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
