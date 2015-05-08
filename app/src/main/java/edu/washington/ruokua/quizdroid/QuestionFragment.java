package edu.washington.ruokua.quizdroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import edu.washington.ruokua.quizdroid.util.Question;
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
     * Instantiate a question view, contain question description
     * and options allow user to choose
     *
     *
     * @returns: the overall topic view to user
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_question, container, false);
        final Button submit = (Button) view.findViewById(R.id.btnSubmit);


        currentTopic = ((QuizDroidModel)getActivity()).getCurrentQuestions();
        Question currentQuestion = currentTopic.getCurrentQuestion();

        //Display the topic description
        String desc = currentTopic.getCurrentQuestionDesc();
        TextView problemDesc = (TextView)  view.findViewById(R.id.desc);
        problemDesc.setText(desc);


        //Display the options of the question and allow user select
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

        for (int i = 0; i < 4; i++) {
            String option = currentTopic.getCurrentQuestionOption().get(i);
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
