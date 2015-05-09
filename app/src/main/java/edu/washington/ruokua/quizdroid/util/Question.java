package edu.washington.ruokua.quizdroid.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ruokunan
 *         <p/>
 *         A Question is an immutable object. Represent one question allow
 *         user to take quiz on. Contain a description of question,
 *         four options allow user to take choices and the correct answer
 *         <p/>
 *         Rep invariant:
 *              description != null && description.length() > 0
 *              &&  questionoptions.size () == 4 &&
 *              answer >= 0 && answer <= 4
 *         <p/>
 *         Abstraction function:
 *              The question description = description
 *              The options allows user to take choices = options
 *              options = O(index from 0 to 4) where O(0) = Choice one
 *                        O(1) = Choice two
 *                        O(2) = Choice three
 *                        O(3) = Choice four
 *         The index of correct option = index
 */
public class Question {
    private String description;
    private List<String> options;
    private int answer;
    private final boolean CHECK_REP = false;
    public static final int OPTIONS_PER_QUESTION = 4;


    /**
     * Check the rep invariant.
     *
     * @effects: nothing if this satisfies rep invariant;
     * otherwise throws an exception
     */
    private void checkRep() {
        if (options == null || options.size() != OPTIONS_PER_QUESTION) {
            throw new RuntimeException("option size out of range");
        } else if (answer < 0 || answer > 4) {
            throw new RuntimeException("answer index out of range");
        } else if (description == null && description.length() > 0) {
            throw new RuntimeException("question description is not available ");
        }
    }

    /**
     * @param questions question description
     * @param options   options allow user take choices
     * @param answer    index of correct answer
     * @effects make a new quiz with given question description
     * options, and answer
     */
    public Question(String questions, List<String> options, int answer) {
        //initialize the quiz
        this.description = questions;
        this.options = options;
        this.answer = answer;
        if (CHECK_REP) {
            checkRep();
        }
    }

    /**
     * @return the index of correct answer
     */
    public int getAnswer() {
        return answer;
    }

    /**
     * @return the description of question
     */
    public String getDesc() {
        return description;
    }

    /**
     * @return the options allow user to take choices
     */
    public List<String> getOptions() {
        return new ArrayList<>(options);
    }


}
