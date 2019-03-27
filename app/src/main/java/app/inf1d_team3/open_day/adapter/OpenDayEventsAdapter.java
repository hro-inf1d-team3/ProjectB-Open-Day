package app.inf1d_team3.open_day.adapter;

import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.inf1d_team3.open_day.LocalDatabase;
import app.inf1d_team3.open_day.R;

public class OpenDayEventsAdapter extends RecyclerView.Adapter<OpenDayEventsAdapter.OpenDayEventsViewHolder> {
    private LocalDatabase.OpenDayEvent[] dataset;

    public OpenDayEventsAdapter(LocalDatabase.OpenDayEvent[] openDayEventsDataset) {
        this.dataset = openDayEventsDataset;
    }

    @NonNull
    @Override
    public OpenDayEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_open_day_event, parent, false);

        return new OpenDayEventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OpenDayEventsViewHolder holder, int position) {
        LocalDatabase.OpenDayEvent openDayEvent = dataset[position];

        holder.title.setText(openDayEvent.getName());
        holder.description.setText(openDayEvent.getDescription());
        holder.time.setText(openDayEvent.getDateTimeString());
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    public static class OpenDayEventsViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView description;
        public TextView time;
        public OpenDayEventsViewHolder(@NonNull View view){
            super(view);

            this.title = view.findViewById(R.id.textView_open_day_event_title);
            this.description = view.findViewById(R.id.textView_open_day_event_description);
            this.time = view.findViewById(R.id.textView_open_day_event_time);
        }
    }
}
