package edu.washington.ruokua.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class TopicFrontPage extends AppCompatActivity {


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

    private QuestionList marvelQuestions;

    private String topic;


    Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent launchingIntent = getIntent();
        topic = launchingIntent.getStringExtra("topic");
        String description = getCurrentDesc();

        Toast.makeText(this, topic, Toast.LENGTH_SHORT).show();

        setContentView(R.layout.topic_overview);

        TextView quizTopic = (TextView)findViewById(R.id.quizTopic);
        quizTopic.setText(topic);
        TextView topicDesc = (TextView)findViewById(R.id.topicDesc);
        topicDesc.setText(description);

        startButton = (Button)findViewById(R.id.btnStart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendQuestions = new Intent(TopicFrontPage.this, QuizQuestions.class);
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
        List<Integer> answers = new ArrayList<>(Arrays.asList(
                1,
                3,
                0));
        MathQuestions = new QuestionList(desc, options, answers);
    }


    private void setPhysicsQuestions() {

    }


    private void setMarvelQuestions() {

    }


    @Override
    public void onBackPressed()
    {
//        super.onBackPressed();
//        Intent topicChoose = new Intent(this,
//                QuizDroidModel.class);
//        topicChoose.putExtra("topicIndex", -1);
//        startActivity(topicChoose);


    }


}
