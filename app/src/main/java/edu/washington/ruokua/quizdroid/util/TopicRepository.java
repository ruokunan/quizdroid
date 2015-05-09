package edu.washington.ruokua.quizdroid.util;

import java.util.List;

/**
 * @author Ruokun An
 * @interface Repository
 */
public interface TopicRepository {

    /**
     * @return a list of topic allow user to take quiz on
     */
    public List<String> getTopicList();

    /**
     *
     * @param topicIndex the number represent topic user decided to take quiz on
     * @return topic the user decided to take quiz on
     */
    public Topic getCurrentTopic(int topicIndex);
}