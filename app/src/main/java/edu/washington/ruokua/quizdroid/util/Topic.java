package edu.washington.ruokua.quizdroid.util;

import java.io.Serializable;
import java.util.List;

/**
 * @author ruokunan
 *         <p/>
 *         A Topic contain a title of topic name, the
 *         short and long descripiton of the topic,  a
 *         collection of questions allow user to take quiz on
 *         and
 *         <p/>
 *         Rep invariant:
 *         title != null && title.length() > 0 &&
 *         shortDesc != null  && shortz
 *         questionDesc != null && option != null && answers != null
 *         questionDesc.size() == option.size() && questionDescs.size == answer.size() &&
 *         questionDesc.size() == questionContain;
 *         questionNum >=0 && questionNum < questionContain
 *         curSelect >= 0 && answer >= 0 && curSelect < option.get(0).size &&
 *         answer < option.get(0).size
 *         score >= 0 && score <= questionNum
 *         <p/>
 *         Abstraction function:
 *         The number of question = questionContain
 *         The score for user so far = score
 *         The current displayed question description show to use
 *         = questionDesc[curQuestionNum]
 *         The current displayed question choice show to user = option[curQuestionNum]
 *         The right choice for current displayed answer = answe[curQuestionNum]
 *         The user choice for the answer  = curSelect
 */

public class Topic implements Serializable {


    //the bias convert index of curQuestionNum to ordinal number
    private final int ORDINAL_NUMBER_BIAS = 1;
    //the title of topic
    private String title;
    //the short description of topic
    private String shortDesc;
    //the long description of topic
    private String longDesc;
    //the questions contained in this topic
    private List<Question> questions;
    //the current question number
    private int curQuestionNum;
    //the current select answer
    private int curSelect;
    //the user's current score for this list of question
    private int score;


    /**
     * @param title     the title of topic
     * @param shortDesc the short description of topic
     * @param longDesc  the long description of topic
     * @param questions the questions contains in this topic
     * @effects: Construct a new topic with given title, short description of
     * topic, long description of topic and
     */
    public Topic(String title, String shortDesc, String longDesc, List<Question> questions) {
        this.title = title;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.questions = questions;

        curQuestionNum = 0;
        score = 0;
        curSelect = -1;
    }

    /**
     * @return title of the topic
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return short description of topic
     */
    public String getShortDesc() {
        return shortDesc;
    }

    /**
     * @return long description of topic
     */
    public String getLongDesc() {
        return longDesc;
    }


    /**
     * @return number of question in the topic
     */
    public int getNumQuestionContain() {
        return questions.size();
    }

    /**
     * @return the user select answer for current problem
     * @throws: RuntimeException if user did not select any answer
     */
    public int getCurSelect() {
        if (curSelect == -1) {
            throw new RuntimeException("user did not select any answer");
        }
        return curSelect;
    }

    /**
     *
     * @param curSelect user current select answer
     * @effects: set the user select answer for current problem in this topic
     */
    public void setCurSelect(int curSelect) {
        this.curSelect = curSelect;
    }

    /**
     * @effects: increment the user score by 1
     */
    public void addScore() {
        score++;
    }


    /**
     * @return the user current score
     */
    public int getScore() {
        return score;
    }


    /**
     * @return the  number of current question in ordinal number
     */
    public int getQuestionNum() {
        return curQuestionNum + ORDINAL_NUMBER_BIAS;
    }


    /**
     * @effects: set the current question to next question
     * set the user select answer to initial state
     */
    public void nextQuestion() {
        if (hasNextQuestion()) {
            curQuestionNum++;
            curSelect = -1;
        }
    }

    /**
     * @return if the current question is the last question in the topic
     */
    public boolean hasNextQuestion() {
        return curQuestionNum < getNumQuestionContain();
    }

    /**
     * @return the current question user should take quiz on
     */
    public Question getCurrentQuestion() {
        return questions.get(curQuestionNum);
    }
}


