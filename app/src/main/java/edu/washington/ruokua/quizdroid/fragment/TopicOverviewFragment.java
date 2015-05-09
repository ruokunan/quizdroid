package edu.washington.ruokua.quizdroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.washington.ruokua.quizdroid.util.QuizApp;
import edu.washington.ruokua.quizdroid.R;
import edu.washington.ruokua.quizdroid.activity.QuizDroidModel;
import edu.washington.ruokua.quizdroid.util.Topic;

/**
 * @author ruokunan
 * Displays a brief description of certain Topic
 * The total number of questions in this topic,
 * and button taking user to the first question.
 */
public class TopicOverviewFragment extends Fragment {
    //when user click button, head to first question of the topic
    private Button startQuiz;



    /**
     * {@inheritDoc}
     * Instantiate overall topic view
     *
     * @returns: the overall topic view to user
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_topic_overview, container, false);


        QuizDroidModel QuizDroid = (QuizDroidModel)getActivity();
        QuizApp quizApp = (QuizApp)QuizDroid.getApplication();
        Topic currentTopic =  quizApp.getRepository().getCurrentTopic(quizApp.getTopicIndex());


        //Display the quiz topic
        String topic = currentTopic.getTitle();
        TextView quizTopic = (TextView) view.findViewById(R.id.quizTopic);
        quizTopic.setText(topic);

        //Display the topic description
        String description = currentTopic.getLongDesc();
        TextView topicDesc = (TextView) view.findViewById(R.id.topicDesc);
        topicDesc.setText(description);

        // Display the Total number of quiz questions in the topic
        TextView quizNumber = (TextView) view.findViewById(R.id.quizNumber);
        quizNumber.setText("Total number of Questions: " + Integer.toString((
              currentTopic.getNumQuestionContain())));

        // when click the start button, bring user to the first question of topic
        startQuiz = (Button) view.findViewById(R.id.btnStart);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter, R.anim.exit)
                        .replace(R.id.container, new QuestionFragment())
                        .commit();

            }


        });


        return view;
    }

}
