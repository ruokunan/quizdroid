package edu.washington.ruokua.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * A brief description of certain topic,
 * and the total number of question in certain topic
 */
public class TopicOverview extends AppCompatActivity {

    // Hard Code number of Question
    private final int NUM_Math_QUESTIONS = 3;

    private final int NUM_Physics_QUESTIONS = 3;

    private final int NUM_MARVEL_QUESTIONS = 3;


    private final String MATH_DESC = "the abstract science of number, quantity, " +
            "and space. Mathematics may be studied in its own right ( pure Mathematics )," +
            "or as it is applied to other disciplines such as Physics and " +
            "engineering ( applied Mathematics ).";

    private final String PHYSICS_DESC = "the branch of science concerned with the nature and " +
            "properties of matter and energy. The subject matter of Physics, distinguished from " +
            "that of chemistry and biology, includes mechanics, heat, light and other radiation, " +
            "sound, electricity, magnetism, and the structure of atoms.";

    private final String MARVEL_DESC = "The Marvel Super Heroes is an American / Canadian " +
            "animated television series starring five comic-book superheroes from Marvel Comics." +
            " The first TV series based on Marvel characters," +
            " it debuted in syndication on U.S. television in 1966. ";

    private QuestionList MathQuestions;

    private QuestionList PhysicsQuestions;

    private QuestionList MarvelQuestions;

    private String topic;


    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent launchingIntent = getIntent();
        topic = launchingIntent.getStringExtra("topic");
        String description = getCurrentDesc();


        setContentView(R.layout.topic_overview);

        TextView quizTopic = (TextView) findViewById(R.id.quizTopic);
        quizTopic.setText(topic);
        TextView topicDesc = (TextView) findViewById(R.id.topicDesc);
        topicDesc.setText(description);
        TextView quizNumber = (TextView) findViewById(R.id.quizNumber);
        quizNumber.setText("Total number of Questions: " + Integer.toString(getNumProblem()));
        startButton = (Button) findViewById(R.id.btnStart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendQuestions = new Intent(TopicOverview.this, Question.class);
                sendQuestions.putExtra("Questions", getCurrentQuestions());
                startActivity(sendQuestions);


            }


        });


    }

    private String getCurrentDesc() {
        switch (topic) {
            case "Math":

                return MATH_DESC;
            case "Physics":

                return PHYSICS_DESC;
            default:

                return MARVEL_DESC;
        }
    }

    private QuestionList getCurrentQuestions() {
        switch (topic) {
            case "Math":
                if (MathQuestions == null) {
                    setMathQuestions();
                }

                return MathQuestions;
            case "Physics":
                if (PhysicsQuestions == null) {
                    setPhysicsQuestions();
                }
                return PhysicsQuestions;
            default:
                if (MarvelQuestions == null) {
                    setMarvelQuestions();
                }
                return MarvelQuestions;
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
        List<Integer> answers = new ArrayList<>(Arrays.asList(
                1,
                3,
                0));
        MathQuestions = new QuestionList(desc, options, answers);
    }


    private void setPhysicsQuestions() {
        List<String> desc = new ArrayList<>(Arrays.asList(
                "Acceleration of an object due to gravity?",
                "Acceleration of an object due to gravity in the moon?",
                "Acceleration of an object due to gravity in the bible?"));
        List<List<String>> options = asList(
                asList("9.8 m/s/s", "10 mi/s", "1 in/s", "Magic"),
                asList("9.8 m/s/s", "1.622 m/s/s10 mi/s", "1 in/s", "I do not know"),
                asList("guess", "make a guess", "there is no god", "goD bless me")
        );
        List<Integer> answers = new ArrayList<>(Arrays.asList(
                0,
                1,
                2));
        PhysicsQuestions = new QuestionList(desc, options, answers);

    }


    private void setMarvelQuestions() {
        List<String> desc = new ArrayList<>(Arrays.asList(
                "The name of batman?",
                "The name of superman",
                "The name of batman's car"));
        List<List<String>> options = asList(
                asList("batman for sure", "bat-man", "batttttman", "select this, this is right"),
                asList("I do not know", "SuperMan",
                        "Eric Chee(This is a wrong answer and you should never select) "
                        , "select the third option will cause serious problem" +
                                " believe me or not"),
                asList("BatMan Dragster", "Audi", "BMW", "Mustang")
        );
        List<Integer> answers = new ArrayList<>(Arrays.asList(
                1,
                1,
                0));
        MarvelQuestions = new QuestionList(desc, options, answers);

    }


    private int getNumProblem() {
        switch (topic) {
            case "Math":
                return NUM_Math_QUESTIONS;
            case "Physics":
                return NUM_Physics_QUESTIONS;
            default:

                return NUM_MARVEL_QUESTIONS;
        }
    }


}