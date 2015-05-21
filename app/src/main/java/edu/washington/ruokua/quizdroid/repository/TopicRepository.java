package edu.washington.ruokua.quizdroid.repository;

import java.util.List;

import edu.washington.ruokua.quizdroid.util.Topic;

/**
 * @author Ruokun An
 * @interface RepositoryManager
 */
public interface TopicRepository {

    /**
     * @return a list of topic allow user to take quiz on
     */
    public List<Topic> getTopicList();

    /**
     *
     * @param topicIndex the number represent topic user decided to take quiz on
     * @return topic the user decided to take quiz on
     */
    public Topic getCurrentTopic(int topicIndex);


}