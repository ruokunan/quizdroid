package edu.washington.ruokua.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


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
                    currentQuestions.nextQuestion();
                    Intent sendQuestions = new Intent(Answer.this, QuizQuestions.class);
                    sendQuestions.putExtra("Questions",currentQuestions);
                    startActivity(sendQuestions);
                } else {
                    Intent backToFront = new Intent(Answer.this, AppFrontPage.class);
                    startActivity(backToFront);
                }

            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
