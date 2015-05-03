package edu.washington.ruokua.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ruokunan
 *         <p/>
 *         A QuestionList is an immutable list of Questions allow user
 *         to take quiz on, display one problem to user each time
 *         <p/>
 *         Rep invariant:
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

public class QuestionList implements Serializable {
    //a list of question descriptions
    private List<String> questionDesc;
    //a list of list of options correspond to each question
    private List<List<String>> option;
    //the  answer choice for each question
    private List<Integer> answer;
    //the number of questions contained
    private int questionContain;
    //the current question number
    private int curQuestionNum;
    //the current select answer
    private int curSelect;
    //the user's current score for this list of question
    private int score;


    /**
     * Construct a new list of questions
     *
     * @param desc   a list of question descriptions
     * @param option a list of list of options correspond to each question
     * @param answer the current answer choice for each question
     */
    public QuestionList(List<String> desc, List<List<String>> option, List<Integer> answer) {
        if (desc.size() != option.size() || desc.size() != answer.size()) {
            throw new IllegalArgumentException();
        }
        this.questionDesc = desc;
        this.option = option;
        this.answer = answer;
        this.questionContain = desc.size();
    }


    // set the user curSelect answer for current display problem
    public void setCurSelect(int curSelect) {
        this.curSelect = curSelect;
    }

    /**
     * @return the user curSelect answer for current problem
     */
    public int getCurSelect() {
        return curSelect;
    }

    /**
     * increment the user score by 1
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
     * @return the current question number
     */
    public int getQuestionNum() {
        return curQuestionNum;
    }


    /**
     * set next question
     * to current displayed question if  next question exist
     */
    public void nextQuestion() {
        if (hasNextQuestion()) {
            curQuestionNum++;
        }
    }

    /**
     * @return if the next question exist
     */
    public boolean hasNextQuestion() {
        return curQuestionNum < questionContain - 1;
    }


    /**
     * @return the question description for current question
     * if the question number of current question
     * is valid
     */
    public String getDesc() {
        if (curQuestionNum < 0 || curQuestionNum > questionContain) {
            return "";
        }
        return questionDesc.get(curQuestionNum);
    }

    /**
     * @return the question options for current question
     * if the question number of current question
     * is valid
     */
    public List<String> getOption() {
        if (curQuestionNum < 0 || curQuestionNum > questionContain) {
            return new ArrayList<String>();
        }
        return option.get(curQuestionNum);
    }

    /**
     * @return the correct choice number description
     * if the question number of current question
     * is valid
     */
    public int getAnswer() {
        if (curQuestionNum < 0 || curQuestionNum > questionContain) {
            return 0;
        }
        return answer.get(curQuestionNum);
    }
}


