package com.example.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyObjectAdapter extends ArrayAdapter<Match> {

    private Context context;
    private List<Match> objects;

    public MyObjectAdapter(Context context, List<Match> objects) {
        super(context, 0, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Match match = getItem(position);
        
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Lookup view for data population
        TextView textViewHome = convertView.findViewById(R.id.home);
        TextView textViewAway = convertView.findViewById(R.id.away);

        // Populate the data into the template view using the data object
        assert match != null;
        textViewHome.setText(match.getHomeTeam());
        textViewAway.setText(match.getAwayTeam());

        // Return the completed view to render on screen
        return convertView;
    }

    public void updateData(List<Match> newObjects) {
        this.objects.clear();
        this.objects.addAll(newObjects);
        notifyDataSetChanged();
    }
}
