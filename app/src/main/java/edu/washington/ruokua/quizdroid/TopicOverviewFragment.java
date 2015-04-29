package edu.washington.ruokua.quizdroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**

 */
public class TopicOverviewFragment extends Fragment {
    private Button startQuiz;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_topic_overview, container, false);
        String description = ((QuizDroidModel) getActivity()).getCurrentDesc();


        TextView quizTopic = (TextView) view.findViewById(R.id.quizTopic);
        quizTopic.setText(((QuizDroidModel) getActivity()).getTopic());
        TextView topicDesc = (TextView) view.findViewById(R.id.topicDesc);
        topicDesc.setText(description);
        TextView quizNumber = (TextView) view.findViewById(R.id.quizNumber);
        quizNumber.setText("Total number of Questions: " + Integer.toString((
                (QuizDroidModel) getActivity()).getNumProblem()));

        startQuiz = (Button) view.findViewById(R.id.btnStart);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .add(R.id.container, new QuestionFragment())
                        .commit();

            }


        });


        return view;
    }

}
