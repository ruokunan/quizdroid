package edu.washington.ruokua.quizdroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.washington.ruokua.quizdroid.R;
import edu.washington.ruokua.quizdroid.fragment.TopicOverviewFragment;
import edu.washington.ruokua.quizdroid.util.Topic;

import static java.util.Arrays.asList;

/**
 * @author ruokunan
 *
 * Provide topic description and number of question in that topic for
 * user selected topic and head to the overview of user select topic
 *
 * Create a list of questions base on the user select topic
 * if user decided take quiz on that topic
 *
 */

public class QuizDroidModel extends AppCompatActivity {

    //Number of Question in the topic
    //hard code, allow lazy initialization for the last of question
    private final int NUM_Math_QUESTIONS = 3;

    private final int NUM_Physics_QUESTIONS = 3;

    private final int NUM_MARVEL_QUESTIONS = 3;

    //description of the topic
    private final String MATH_DESC = "The abstract science of number, quantity, " +
            "and space. Mathematics may be studied in its own right ( pure Mathematics )," +
            "or as it is applied to other disciplines such as Physics and " +
            "engineering ( applied Mathematics ).";

    private final String PHYSICS_DESC = "The branch of science concerned with the nature and " +
            "properties of matter and energy. The subject matter of Physics, distinguished from " +
            "that of chemistry and biology, includes mechanics, heat, light and other radiation, " +
            "sound, electricity, magnetism, and the structure of atoms.";

    private final String MARVEL_DESC = "The Marvel Super Heroes is an American / Canadian " +
            "animated television series starring five comic-book superheroes from Marvel Comics." +
            " The first TV series based on Marvel characters," +
            " it debuted in syndication on U.S. television in 1966. ";

    // a list of question of topic
    private Topic MathQuestions;

    private Topic PhysicsQuestions;

    private Topic MarvelQuestions;


    //the user select topic
    private String topic;


    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get the user select topic
        Intent launchingIntent = getIntent();
        topic = launchingIntent.getStringExtra("topic");
        //head to the overview of user select topic
        setContentView(R.layout.topic_overview);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new TopicOverviewFragment())
                .commit();

    }

    /**
     * @return user select topic
     */
    public    String getTopic() {
        return  topic;
    }

    /**
     * @return topic description for user select topic
     */
    public String getCurrentDesc() {
        switch (topic) {
            case "Math":

                return MATH_DESC;
            case "Physics":

                return PHYSICS_DESC;
            default:

                return MARVEL_DESC;
        }
    }

    /**
     *
     * @return number of problems in user select topic
     */
    public int getNumProblem() {
        switch (topic) {
            case "Math":
                return NUM_Math_QUESTIONS;
            case "Physics":
                return NUM_Physics_QUESTIONS;
            default:

                return NUM_MARVEL_QUESTIONS;
        }
    }


    /**
     *
     * @return the list of topic for user select topic
     */
    public Topic getCurrentQuestions() {
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


    /**
     * initialize a list of math questions
     */
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
        MathQuestions = new Topic(desc, options, answers);
    }


    /**
     * initialize a list of PhysicsQuestions questions
     */ 
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
        PhysicsQuestions = new Topic(desc, options, answers);

    }

    /**
     * initialize a list of Marvel questions
     */
     private void setMarvelQuestions() {
        List<String> desc = new ArrayList<>(Arrays.asList(
                "The name of batman?",
                "The name of superman",
                "The name of batman's car"));
        List<List<String>> options = asList(
                asList("batman for sure", "bat-man", "batttttman", "select this, this is right"),
                asList("I do not know", "SuperMan",
                        "Eric Chee "
                        , "Me"),
                asList("BatMan Dragster", "Audi", "BMW", "Mustang")
        );
        List<Integer> answers = new ArrayList<>(Arrays.asList(
                1,
                1,
                0));
        MarvelQuestions = new Topic(desc, options, answers);

    }





}
