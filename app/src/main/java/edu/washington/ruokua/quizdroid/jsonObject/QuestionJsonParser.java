package edu.washington.ruokua.quizdroid.jsonObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by ruokua on 5/10/15.
 */
public class QuestionJsonParser {
    private String desc;
    private List<String> options;
    private int answer;



    @JsonProperty("text")
    public String getDescription() {
        return desc;
    }

    @JsonProperty("text")
    public void setDescription(String description) {
        this.desc = description;
    }

    @JsonProperty("answers")
    public List<String> getOptions() {
        return options;
    }

    @JsonProperty("answers")
    public void setOptions(List<String> options) {
        this.options = options;
    }

    @JsonProperty("answer")
    public int getAnswer() {
        return answer;
    }
    @JsonProperty("answer")
    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionJsonParser{" +
                "desc='" + desc + '\'' +
                ", options=" + options +
                ", answer=" + answer +
                '}';
    }
}
