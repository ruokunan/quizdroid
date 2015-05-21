package edu.washington.ruokua.quizdroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import edu.washington.ruokua.quizdroid.activity.TakeQuizActivity;
import edu.washington.ruokua.quizdroid.util.QuizApp;
import edu.washington.ruokua.quizdroid.R;
import edu.washington.ruokua.quizdroid.util.Topic;

/**
 * @author ruokunan
 * Disaply a question to user, contain question description
 * and answer options allow user to choose
 * Once the user confrim of her/his choice
 * Head to answer page show the select/correct answer for that
 * question
 *
 */
public class QuestionFragment extends Fragment {
    //the current user select answer
    private int select;
    //the current answer displayed
    private Topic currentTopic;

    /**
     *
     * {@inheritDoc}
     *
     * @returns: a question view, contain question description
     * and options allow user to choose
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_question, container, false);
        final Button submit = (Button) view.findViewById(R.id.btnSubmit);

        TakeQuizActivity QuizDroid = (TakeQuizActivity)getActivity();
        QuizApp quizApp = (QuizApp)QuizDroid.getApplication();
        currentTopic =  quizApp.getCurrentTopic();

        //Display the topic description
        String desc = currentTopic.getCurrentQuestion().getDesc();
        TextView problemDesc = (TextView)  view.findViewById(R.id.desc);
        problemDesc.setText(desc);


        //Display the options of the question and allow user select
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        List<String> options = currentTopic.getCurrentQuestion().getOptions();
        for (int i = 0; i < options.size(); i++) {
            String option = options.get(i);
            ((RadioButton) radioGroup.getChildAt(i)).setText(option);
        }

        //Get the user select answer
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submit.setVisibility(View.VISIBLE);
                RadioButton radioButton = (RadioButton) getView().findViewById(checkedId);

                select = Integer.parseInt((String)radioButton.getTag());

                currentTopic.setCurSelect(select);
            }
        });


        // Once the user confirm the select answer
        // head user to the answer page
         submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter,  R.anim.exit)
                        .replace(R.id.container, new AnswerFragment())
                        .commit();

            }
        });


        return view;
    }





}
