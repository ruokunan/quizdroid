package edu.washington.ruokua.quizdroid.util;

import java.io.Serializable;
import java.util.List;

import edu.washington.ruokua.quizdroid.R;

/**
 * @author ruokunan
 *         <p/>
 *         A Topic contain a title of topic name, the
 *         short and long descripiton of the topic,  a
 *         collection of questions allow user to take quiz on
 *
 *         The topic could have 0 questions
 *         <p/>
 *         Rep invariant:
 *         title != null && title.length() > 0 &&
 *         shortDesc != null  && shortz
 *
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

    public int getIcon() {
        return icon;
    }

    private int icon;


    public static class Builder {
        //Require title and short desc for each topic
        private final String title;//required
        private final String shortDesc;//required

        //Optional Parameters
        //initialized to default values
        private String longDesc = null;//optional
        //the questions contained in this topic
        private List<Question> questions = null;//optional
        private int icon = R.drawable.common_signin_btn_icon_dark;



        public Builder(String title, String shortDesc) {
            this.title = title;
            this.shortDesc = shortDesc;
        }

        public Builder longDesc(String longDesc) {
            this.longDesc = longDesc;
            return this;
        }

        public Builder questions( List<Question> questions) {
            if(questions == null) {
                throw new IllegalArgumentException("questions list " +
                        "for topic cannot be null");
            }
            this.questions = questions;
            return this;

        }

        public Builder icon(int icon) {
            this.icon = icon;
            return this;

        }

        public Topic build() {
            return new Topic(this);
        }



    }

    /**
     *@param builder construct a new Topic with the given builder
     * @effects: Construct a new topic with given title, short description of
     * topic, long description of topic and
     */
    public Topic(Builder builder) {
        this.title = builder.title;
        this.shortDesc = builder.shortDesc;
        this.longDesc = builder.longDesc;
        this.questions = builder.questions;
        this.icon = builder.icon;

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
     * @return if the current question is the last question in this topic
     */
    public boolean hasNextQuestion() {
        return curQuestionNum + ORDINAL_NUMBER_BIAS < getNumQuestionContain();
    }

    /**
     * @return the current question user should take quiz on in this topic
     */
    public Question getCurrentQuestion() {
        return questions.get(curQuestionNum);
    }
}


