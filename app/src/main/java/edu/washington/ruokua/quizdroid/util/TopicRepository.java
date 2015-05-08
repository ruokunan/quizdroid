package edu.washington.ruokua.quizdroid.util;

/**
 * @author Ruokun An
 * @interface Repository
 */
public interface TopicRepository {

    /**
     * @return title of the topic
     */
    public String getTitle();

    /**
     * @return short description of topic
     */
    public String getShortDesc();

    /**
     * @return long description of topic
     */
    public String getLongDesc();


    /**
     * @return number of question in the topic
     */
    public int getNumQuestionContain();

    /**
     * @return the user select answer for current problem
     * @throws: RuntimeException if user did not select any answer
     */
    public int getCurSelect();

    /**
     * @param curSelect user current select answer
     * @effects: set the user select answer for current problem in this topic
     */
    public void setCurSelect(int curSelect);

    /**
     * @effects: increment the user score by 1
     */
    public void addScore();


    /**
     * @return the user current score
     */
    public int getScore();

    /**
     * @return the  number of current question in ordinal number
     */
    public int getQuestionNum();


    /**
     * @effects: set the current question to next question
     * set the user select answer to initial state
     */
    public void nextQuestion();

    /**
     * @return if the current question is the last question in the topic
     */
    public boolean hasNextQuestion();

    /**
     * @return the current question user should take quiz on
     */
    public Question getCurrentQuestion();
}