package edu.washington.ruokua.quizdroid.jsonObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by ruokua on 5/10/15.
 */
public class SampleTopic {
    @JsonProperty("title")
    private String title;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("questions")
    private List<SampleQuestion> questions;

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("desc")
    public String getDesc() {
        return desc;
    }

    @JsonProperty("questions")
    public List<SampleQuestion> getQuestions() {
        return questions;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("desc")
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JsonProperty("questions")
    public void setQuestions(List<SampleQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "SampleTopic{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", questions=" + questions +
                '}';
    }
}
