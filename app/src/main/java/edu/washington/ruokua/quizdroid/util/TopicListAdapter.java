package edu.washington.ruokua.quizdroid.util;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by ruokua on 5/8/15.
 */
public class TopicListAdapter extends ArrayAdapter<String>{
    private final Context context;
    private final List<String> topics;

    /**
     * {@inheritDoc}
     *  Construct a list adpater  
     */
    public TopicListAdapter(Context context, int resource, List<String> topics) {
        super(context, resource, topics);
        this.context = context;
        this.topics = topics;
    }
}
