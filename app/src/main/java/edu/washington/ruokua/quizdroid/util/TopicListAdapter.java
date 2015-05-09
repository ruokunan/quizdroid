package edu.washington.ruokua.quizdroid.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.washington.ruokua.quizdroid.R;

/**
 * Created by ruokua on 5/8/15.
 */
public class TopicListAdapter extends ArrayAdapter<Topic> {
    private final Context context;
    private final List<Topic> topics;

    /**
     * {@inheritDoc}
     * Construct a list adapter
     */
    public TopicListAdapter(Context context, int resource, List<Topic> topics) {
        super(context, resource, topics);
        this.context = context;
        this.topics = topics;
    }


    /**
     * {@inheritDoc}
     * every list item contain a title of topic and a short description of set topic
     */
    @Override
    public View getView(int position, View contextView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contextView = inflater.inflate(R.layout.topic_list_item, parent, false);

        TextView topicTitle = (TextView) contextView.findViewById(R.id.topic_title);

        topicTitle.setText(getItem(position).getTitle());


        TextView topicShortDesc = (TextView) contextView.findViewById(R.id.topic_short_desc);
        topicShortDesc.setText(getItem(position).getShortDesc());
        return contextView;
    }
}
