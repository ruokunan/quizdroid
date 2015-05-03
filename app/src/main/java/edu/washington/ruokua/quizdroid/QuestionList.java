package edu.washington.ruokua.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruokua on 4/27/15.
 */
public class QuestionList implements Serializable {
    private List<String> desc;
    private List<List<String>> option;
    private List<Integer> answer;
    private int questionContain;
    private int questionNum;
    private int select;
    private int score;


    public void setSelect(int select) {
        this.select = select;
    }

    public int getSelect() {

        return select;
    }

    public int addScore() {
        return score++;
    }



    public int getScore() {
        return score;
    }

    public QuestionList(List<String> desc, List<List<String>> option, List<Integer> answer) {
        if (desc.size() != option.size() || desc.size() != answer.size()) {
            throw new IllegalArgumentException();
        }
        this.desc = desc;
        this.option = option;
        this.answer = answer;
        this.questionContain = desc.size();
    }


    public int getQuestionNum() {
        return questionNum;
    }

        
    public int getQuestionContain() {
        return questionContain;
    }


    public void nextQuestion() {
        questionNum++;
    }

    public boolean hasNextQuestion() {
        return questionNum < questionContain - 1;
    }


    public String getDesc() {
        if (questionNum < 0 || questionNum > questionContain) {
            return "";
        }
        return desc.get(questionNum);
    }

    public List<String> getOption() {
        if (questionNum < 0 || questionNum > questionContain) {
            return new ArrayList<String>();
        }
        return option.get(questionNum);
    }

    public int getAnswer() {
        if (questionNum < 0 || questionNum > questionContain) {
            return 0;
        }
        return answer.get(questionNum);
    }
}


