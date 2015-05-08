package edu.washington.ruokua.quizdroid.util;

/**
 * Created by ruokua on 5/8/15.
 */
public class MemoryTopicRepository implements TopicRepository{
    private Topic currentTopic;
    private Topic mathTopic;
    private Topic physicsTopic;
    private Topic
    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return currentTopic.getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getShortDesc() {
        return currentTopic.getShortDesc();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLongDesc() {
        return currentTopic.getLongDesc();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumQuestionContain() {
        return currentTopic.getNumQuestionContain();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurSelect() {
        return currentTopic.getCurSelect();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurSelect(int curSelect) {
         currentTopic.setCurSelect(curSelect);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addScore() {
        currentTopic.addScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return currentTopic.getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getQuestionNum() {
        return currentTopic.getQuestionNum();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void nextQuestion() {
        currentTopic.nextQuestion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNextQuestion() {
        return currentTopic.hasNextQuestion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Question getCurrentQuestion() {
        return currentTopic.getCurrentQuestion();
    }
}
