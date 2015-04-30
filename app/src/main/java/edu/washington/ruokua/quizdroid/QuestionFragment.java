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


public class QuestionFragment extends Fragment {

    private int select;
    private QuestionList currentQuestions;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_question, container, false);
        final Button submit = (Button) view.findViewById(R.id.btnSubmit);
;
        currentQuestions = ((QuizDroidModel)getActivity()).getCurrentQuestions();

        String desc = currentQuestions.getDesc();
        TextView problemDesc = (TextView)  view.findViewById(R.id.desc);
        problemDesc.setText(desc);



        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submit.setVisibility(View.VISIBLE);
                RadioButton radioButton = (RadioButton) getView().findViewById(checkedId);

                select = Integer.parseInt((String)radioButton.getTag());

                currentQuestions.setSelect(select);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter,  R.anim.exit)

                        .replace(R.id.container, new AnswerFragment())
                        .commit();

            }
        });

        for (int i = 0; i < 4; i++) {
            String option = currentQuestions.getOption().get(i);
            ((RadioButton) radioGroup.getChildAt(i)).setText(option);
        }

        return view;
    }





}
