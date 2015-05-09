package edu.washington.ruokua.quizdroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import edu.washington.ruokua.quizdroid.QuizApp;
import edu.washington.ruokua.quizdroid.R;
import edu.washington.ruokua.quizdroid.activity.QuizDroidModel;
import edu.washington.ruokua.quizdroid.activity.TopicList;
import edu.washington.ruokua.quizdroid.util.Topic;


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
    private Topic currentTopic;

    /**
     *
     * {@inheritDoc}
     *
     * @return the Answer view contain the  user select answer for current problem,
     * correct answer for the current problem and the user's score so far
     *
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_answer, container, false);


        QuizDroidModel QuizDroid = (QuizDroidModel)getActivity();
        QuizApp quizApp = (QuizApp)QuizDroid.getApplication();
        currentTopic =  quizApp.getRepository().getCurrentTopic(quizApp.getTopicIndex());

        //Display the Select Answer for current problem
        int selectIndex = currentTopic.getCurSelect();
        List<String> options = currentTopic.getCurrentQuestion().getOptions();
        String answerGiven = options.get(selectIndex);

        TextView selectAnswer = (TextView) view.findViewById(R.id.answerGiven);
        selectAnswer.setText(answerGiven);

        //Display the Correct Answer for current Problem
        int answerIndex = currentTopic.getCurrentQuestion().getAnswer();
        String answerCorrect = options.get(answerIndex);
        TextView correctAnswer = (TextView) view.findViewById(R.id.answer);
        correctAnswer.setText(answerCorrect);

        //Add score if user answer correctly
        if (answerIndex == selectIndex) {
            currentTopic.addScore();
        }


        //Display the score board
        String scoreBoard = "You have " + currentTopic.getScore() + " out of "
                + (currentTopic.getQuestionNum())+ " correct";

        TextView scoreRatio = (TextView) view.findViewById(R.id.scoreBoard);
        scoreRatio.setText(scoreBoard);


        //If correct question is not the last question, head user to next question,
        // Otherwise, head to the currentTopic list allow user to select currentTopic take quiz on
        String buttonText;
        if (currentTopic.hasNextQuestion()) {
            buttonText = "Next";
        } else {
            buttonText = "Finish";
        }
        Button button = (Button)view.findViewById(R.id.btnNext);
        button.setText(buttonText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentTopic.hasNextQuestion()) {

                    currentTopic.nextQuestion();

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
