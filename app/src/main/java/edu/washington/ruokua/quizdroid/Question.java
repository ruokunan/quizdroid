package edu.washington.ruokua.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



public class Question extends AppCompatActivity {

    private int select;
    private QuestionList currentQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_questions);
        final Button submit = (Button) findViewById(R.id.btnSubmit);

        Intent launchingIntent = getIntent();
        currentQuestions = (QuestionList) launchingIntent.getSerializableExtra("Questions");

        String desc = currentQuestions.getDesc();
        TextView problemDesc = (TextView) findViewById(R.id.desc);
        problemDesc.setText(desc);



        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submit.setVisibility(View.VISIBLE);
                RadioButton radioButton = (RadioButton) findViewById(checkedId);

                select = Integer.parseInt((String)radioButton.getTag());



                if(currentQuestions.getOption().get(2).equals("Eric Chee(This is a wrong answer and you should never select) "
                ) && select == 2) {

                      System.exit(1);
//
//                    Intent intent = new Intent(Intent.ACTION_MAIN);
//                    intent.addCategory(Intent.CATEGORY_HOME);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
                }

                currentQuestions.setSelect(select);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendQuestions = new Intent(Question.this, Answer.class);
                sendQuestions.putExtra("Questions", currentQuestions);
                startActivity(sendQuestions);

            }





        });



        for (int i = 0; i < 4; i++) {
            String option = currentQuestions.getOption().get(i);
            ((RadioButton) radioGroup.getChildAt(i)).setText(option);
        }


    }


    /**
     * Disable the bcak button in this acitivity
     */
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "I know you gonna hack", Toast.LENGTH_SHORT).show();
    }



}
