package edu.washington.ruokua.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class AnswerFragment extends Fragment {
    private QuestionList currentQuestions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_answer, container, false);

        currentQuestions =  ((QuizDroidModel)getActivity()).getCurrentQuestions();
        int answerIndex = currentQuestions.getAnswer();
        int selectIndex = currentQuestions.getSelect();
        if (answerIndex == selectIndex) {
            currentQuestions.addScore();
        }

        String answerGiven = currentQuestions.getOption().get(selectIndex);
        String answerCorrect = currentQuestions.getOption().get(answerIndex);

        String scoreBoard = "You have " + currentQuestions.getScore() + " out of "
                + (currentQuestions.getQuestionNum() + 1)+ " correct";

        String buttonText;
        if (currentQuestions.hasNextQuestion()) {
            buttonText = "Next";
        } else {
            buttonText = "Finish";
        }

        TextView selectAnswer = (TextView) view.findViewById(R.id.answerGiven);
        selectAnswer.setText(answerGiven);

        TextView correctAnswer = (TextView) view.findViewById(R.id.answer);
        correctAnswer.setText(answerCorrect);

        TextView scoreRatio = (TextView) view.findViewById(R.id.scoreBoard);
        scoreRatio.setText(scoreBoard);


        Button button = (Button)view.findViewById(R.id.btnNext);
        button.setText(buttonText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (currentQuestions.hasNextQuestion()) {

                    currentQuestions.nextQuestion();
                    getFragmentManager().beginTransaction()
                            .add(R.id.container, new QuestionFragment())
                            .commit();




                } else {

                    Intent backToFront = new Intent(getActivity(), TopicList.class);
                    startActivity(backToFront);
                }

            }

        });
        return view;
    }




}
