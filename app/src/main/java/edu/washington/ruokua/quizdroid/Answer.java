package edu.washington.ruokua.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Answer extends AppCompatActivity {
    private QuestionList currentQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Intent launchingIntent = getIntent();
        currentQuestions = (QuestionList) launchingIntent.getSerializableExtra("Questions");
        int answerIndex = currentQuestions.getAnswer();
        int selectIndex = currentQuestions.getSelect();
        if (answerIndex == selectIndex) {
            currentQuestions.addScore();
        }

        String answerGiven = currentQuestions.getOption().get(selectIndex);
        String answerCorrect = currentQuestions.getOption().get(answerIndex);

        String scoreBoard = "You have " + currentQuestions.getScore() + " out of "
                + currentQuestions.getQuestionContain() + " correct";
        String buttonText;
        if (currentQuestions.hasNextQuestion()) {
            buttonText = "Next";
        } else {
            buttonText = "Finish";
        }

        TextView selectAnswer = (TextView) findViewById(R.id.answerGiven);
        selectAnswer.setText(answerGiven);

        TextView correctAnswer = (TextView) findViewById(R.id.answer);
        correctAnswer.setText(answerCorrect);

        TextView scoreRatio = (TextView) findViewById(R.id.scoreBoard);
        scoreRatio.setText(scoreBoard);


        Button button = (Button) findViewById(R.id.btnNext);
        button.setText(buttonText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (currentQuestions.hasNextQuestion()) {
                    Toast.makeText(Answer.this, "Wait a moment, " +
                            "I am select question right know", Toast.LENGTH_SHORT).show();
                    currentQuestions.nextQuestion();
                    Intent sendQuestions = new Intent(Answer.this, Question.class);
                    sendQuestions.putExtra("Questions", currentQuestions);
                    startActivity(sendQuestions);
                } else {
                    Toast.makeText(Answer.this, "cotinue to take quiz since you cannot exit",
                            Toast.LENGTH_SHORT).show();
                    Intent backToFront = new Intent(Answer.this, TopicList.class);
                    startActivity(backToFront);
                }

            }

        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "I know you gonna hack", Toast.LENGTH_SHORT).show();
    }


}
