package edu.washington.ruokua.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;


public class QuizDroidModel extends AppCompatActivity {


    private final String MATH_DESC = "the abstract science of number, quantity, " +
            "and space. Mathematics may be studied in its own right ( pure mathematics )," +
            "or as it is applied to other disciplines such as physics and " +
            "engineering ( applied mathematics ).";

    private final String PHYSICS_DESC = "the branch of science concerned with the nature and " +
            "properties of matter and energy. The subject matter of physics, distinguished from " +
            "that of chemistry and biology, includes mechanics, heat, light and other radiation, " +
            "sound, electricity, magnetism, and the structure of atoms.";

    private final String MARVEL_DESC = "The Marvel Super Heroes is an American / Canadian " +
            "animated television series starring five comic-book superheroes from Marvel Comics." +
            " The first TV series based on Marvel characters," +
            " it debuted in syndication on U.S. television in 1966. ";


    private final int NUM_MATH_QUESTIONS = 0;

    private final int NUM_PHYSICS_QUESTIONS = 0;

    private final int NUM_MARVEL_QUESTIONS = 0;


    private final String[] TOPICS = {"Math", "Physics", "Marvel Super Heroes"};


    private static final String TAG = QuizDroidModel.class.getName();

    private List<Question> mathQuestions;

    private List<Question> physicsQuestions;

    private List<Question> marvelQuestions;

    private List<Question> currentQuestion;


    private String topic;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent launchingIntent = getIntent();
        int numProblem = getNumProblem();
        int score = 0;
        
        int topicIndex = launchingIntent.getIntExtra("topicIndex", -1);
        boolean takeQuiz = launchingIntent.getBooleanExtra("takeQuiz", false);
        boolean showAnswer = launchingIntent.getBooleanExtra("answer", false);
        int questionNum = 0;
        if (topicIndex == -1) {
            displayTopic();
        } else if (!takeQuiz) {
            topic = TOPICS[topicIndex];
            displayTopicDesc();
        } else if (takeQuiz && questionNum < numProblem) {
            topic = TOPICS[topicIndex];

            Intent Question = new Intent(QuizDroidModel.this, QuizQuestions.class);
            Question curQuestion = getCurrentProblem(questionNum);
            Question.putExtra("questionDesc", curQuestion.getDesc());
            List<String> options = curQuestion.getOptions();
            for(int i = 0; i < options.size();i++) {
                Question.putExtra("option".concat(Integer.toString(i)), options.get(i));

            }
            startActivity(Question);


        } else if(showAnswer){

        } else {

        }
    }


    private void displayTopic() {
        Intent topicView = new Intent(QuizDroidModel.this, AppFrontPage.class);
        topicView.putExtra("topics", TOPICS);
        startActivity(topicView);

    }


    private void displayTopicDesc() {

        Intent topicDesc = new Intent(QuizDroidModel.this, TopicFrontPage.class);
        topicDesc.putExtra("topic", topic);
        String desc = getCurrentDesc();
        topicDesc.putExtra("desc", desc);

        int numQuestion =getNumProblem();
        topicDesc.putExtra("numProblem", desc);

        startActivity(topicDesc);
    }



    private int getNumProblem() {
        if (topic.equals("math")) {
            return NUM_MATH_QUESTIONS;
        } else if (topic.equals("physics")) {
            return NUM_PHYSICS_QUESTIONS;
        } else {

            return NUM_MARVEL_QUESTIONS;
        }
    }

    private String getCurrentDesc() {
        if (topic.equals("math")) {

            return MATH_DESC;
        } else if (topic.equals("physics")) {

            return PHYSICS_DESC;
        } else {

            return MARVEL_DESC;
        }
    }
    private List<Question> getCurrentQuestions() {
        if (topic.equals("math")) {
            if (mathQuestions == null) {
                setMathQuestions();
            }

            return mathQuestions;
        } else if (topic.equals("physics")) {
            if (physicsQuestions == null) {
                setPhysicsQuestions();
            }
            return physicsQuestions;
        } else {
            if (marvelQuestions == null) {
                setMarvelQuestions();
            }
            return marvelQuestions;
        }

    }

    private Question getCurrentProblem(int numQuestion ) {
        if (currentQuestion == null) {
            getCurrentQuestions();
        }
        return currentQuestion.get(numQuestion);
    }


    private void setMathQuestions() {

    }


    private void setPhysicsQuestions() {

    }


    private void setMarvelQuestions() {

    }

    private class Question {
        private String desc;
        private String answer;
        private List<String> options;

        private Question(String desc, List<String> options, String answer) {
            this.desc = desc;
            this.options = options;
            this.answer = answer;
        }

        public String getDesc() {
            return desc;
        }

        public String getAnswer() {
            return answer;
        }

        public List<String> getOptions() {
            return options;
        }


    }


}
