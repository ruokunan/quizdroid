package edu.washington.ruokua.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;


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


    private final int NUM_MATH_QUESTIONS = 3;

    private final int NUM_PHYSICS_QUESTIONS = 0;

    private final int NUM_MARVEL_QUESTIONS = 0;


    private final String[] TOPICS = {"Math", "Physics", "Marvel Super Heroes"};


    private static final String TAG = QuizDroidModel.class.getName();

    private QuestionList mathQuestions;

    private QuestionList physicsQuestions;

    private QuestionList marvelQuestions;

    private QuestionList currentQuestion;

    private int numQuestion;
    private String topic = "";
    private boolean takeQuiz;
    private int questionNum = 0;
    private int topicIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent launchingIntent = getIntent();

        int score = 0;


        takeQuiz = launchingIntent.getBooleanExtra("takeQuiz", false);
        topicIndex = Math.max(topicIndex, launchingIntent.getIntExtra("topicIndex", -1));


        Log.e(TAG, topicIndex + "");
        Log.e(TAG, topic);

        boolean showAnswer = launchingIntent.getBooleanExtra("answer", false);

        if (topicIndex == -1) {
            displayTopic();

            Log.i(TAG, "go front");

        } else if (!takeQuiz) {
            topic = TOPICS[topicIndex];
            displayTopicDesc();

            Log.i(TAG, "go topic");

        } else if (questionNum < numQuestion) {
            if (currentQuestion == null) {
                currentQuestion = getCurrentQuestions();
            }
            Log.i(TAG, "go problem");

            Intent sendQuestion = new Intent(QuizDroidModel.this, QuizQuestions.class);
            sendQuestion.putExtra("questionDesc", currentQuestion.getDesc(questionNum));
            List<String> options = currentQuestion.getOption(questionNum);
            for (int i = 0; i < options.size(); i++) {
                sendQuestion.putExtra("questionOption".concat(Integer.toString(i)), options.get(i));

            }
            startActivity(sendQuestion);


        } else if (showAnswer) {
            showAnswer = false;

        } else {

        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart event fired.");
        Log.e(TAG, topicIndex + "");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume event fired.");
        Log.e(TAG, topicIndex + "");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause event fired.");
        Log.e(TAG, topicIndex + "");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart event fired.");
        Log.e(TAG, topicIndex + "");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop event fired.");
        Log.e(TAG, topicIndex + "");

    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy event fired.");
        Log.e(TAG, "We're going down, Captain!");
        super.onDestroy();


    }


    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        Log.e(TAG, "cao cao cao");
        saveInstanceState.putInt("topicIndex", topicIndex);

        saveInstanceState.putString("topic", topic);
        saveInstanceState.putInt("numQuestion", numQuestion);
        saveInstanceState.putInt("questionNum", questionNum);

        saveInstanceState.putBoolean("takeQuiz", takeQuiz);
        super.onSaveInstanceState(saveInstanceState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "cao ni ma cao ni ma cao ni ma cao ni ma ");

        topicIndex = savedInstanceState.getInt("topicIndex");
        topic = savedInstanceState.getString("topic");
        numQuestion = savedInstanceState.getInt("numQuestion");
        questionNum = savedInstanceState.getInt("questionNum");
        takeQuiz = savedInstanceState.getBoolean("takeQuiz");
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

        numQuestion = getNumProblem();
        topicDesc.putExtra("numProblem", numQuestion);

        startActivity(topicDesc);
    }


    private int getNumProblem() {
        switch (topic) {
            case "math":
                return NUM_MATH_QUESTIONS;
            case "physics":
                return NUM_PHYSICS_QUESTIONS;
            default:

                return NUM_MARVEL_QUESTIONS;
        }
    }

    private String getCurrentDesc() {
        switch (topic) {
            case "math":

                return MATH_DESC;
            case "physics":

                return PHYSICS_DESC;
            default:

                return MARVEL_DESC;
        }
    }

    private QuestionList getCurrentQuestions() {
        switch (topic) {
            case "math":
                if (mathQuestions == null) {
                    setMathQuestions();
                }

                return mathQuestions;
            case "physics":
                if (physicsQuestions == null) {
                    setPhysicsQuestions();
                }
                return physicsQuestions;
            default:
                if (marvelQuestions == null) {
                    setMarvelQuestions();
                }
                return marvelQuestions;
        }

    }


    private void setMathQuestions() {
        List<String> desc = new ArrayList<>(Arrays.asList(
                "1 + 1 = ?",
                "2 * 3 = ?",
                "10 % 10 = ?"));
        List<List<String>> options = asList(
                asList("1", "2", "3", "4"),
                asList("3", "4", "5", "6"),
                asList("0", "1", "2", "3")
        );
        List<String> answers = new ArrayList<>(Arrays.asList(
                "2",
                "6",
                "0"));
        mathQuestions = new QuestionList(desc, options, answers);
    }


    private void setPhysicsQuestions() {

    }


    private void setMarvelQuestions() {

    }


    private class QuestionList {
        private List<String> desc;
        private List<List<String>> option;
        private List<String> answer;
        int numQuestion;

        private QuestionList(List<String> desc, List<List<String>> option, List<String> answer) {
            if (desc.size() != option.size() || desc.size() != answer.size()) {
                throw new IllegalArgumentException();
            }
            this.desc = desc;
            this.option = option;
            this.answer = answer;
            numQuestion = desc.size();
        }

        public String getDesc(int problemNumber) {
            if (problemNumber < 0 || problemNumber > numQuestion) {
                return "";
            }
            return desc.get(problemNumber);
        }

        public List<String> getOption(int problemNumber) {
            if (problemNumber < 0 || problemNumber > numQuestion) {
                return new ArrayList<String>();
            }
            return option.get(problemNumber);
        }

        public String getAnswer(int problemNumber) {
            if (problemNumber < 0 || problemNumber > numQuestion) {
                return "";
            }
            return answer.get(problemNumber);
        }
    }


}
