package edu.washington.ruokua.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 *
 * @author ruokunan
 * Disaply the user select answer for current question
 * Once the user confrim of her/his choice
 * Head to answer page show the select/correct answer for that
 * question
 *
 */

public class AnswerFragment extends Fragment {
    private QuestionList currentQuestions;

    /**
     *
     * {@inheritDoc}
     * Instantiate the Answer view contain the  user select answer for current problem,
     * correct answer for the current problem and the user's score so far
     *

     * @return an answer page view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_answer, container, false);


        currentQuestions =  ((QuizDroidModel)getActivity()).getCurrentQuestions();

        //Display the Select Answer for current problem
        int selectIndex = currentQuestions.getCurSelect();
        String answerGiven = currentQuestions.getOption().get(selectIndex);
        TextView selectAnswer = (TextView) view.findViewById(R.id.answerGiven);
        selectAnswer.setText(answerGiven);

        //Display the Correct Answer for current Problem
        int answerIndex = currentQuestions.getAnswer();
        String answerCorrect = currentQuestions.getOption().get(answerIndex);
        TextView correctAnswer = (TextView) view.findViewById(R.id.answer);
        correctAnswer.setText(answerCorrect);


        if (answerIndex == selectIndex) {
            currentQuestions.addScore();
        }


        //Display the score board
        String scoreBoard = "You have " + currentQuestions.getScore() + " out of "
                + (currentQuestions.getQuestionNum() + 1)+ " correct";

        TextView scoreRatio = (TextView) view.findViewById(R.id.scoreBoard);
        scoreRatio.setText(scoreBoard);


        //If correct question is not the last question, head user to next question,
        // Otherwise, head to the topic list allow user to select topic take quiz on
        String buttonText;
        if (currentQuestions.hasNextQuestion()) {
            buttonText = "Next";
        } else {
            buttonText = "Finish";
        }
        Button button = (Button)view.findViewById(R.id.btnNext);
        button.setText(buttonText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentQuestions.hasNextQuestion()) {

                    currentQuestions.nextQuestion();

                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter,  R.anim.exit)

                            .replace(R.id.container, new QuestionFragment())
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
