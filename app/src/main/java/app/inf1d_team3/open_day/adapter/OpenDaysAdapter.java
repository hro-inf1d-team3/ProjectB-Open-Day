package app.inf1d_team3.open_day.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.inf1d_team3.open_day.LocalDatabase;
import app.inf1d_team3.open_day.R;

public class OpenDaysAdapter extends RecyclerView.Adapter<OpenDaysAdapter.OpenDaysViewHolder> {
    private LocalDatabase.OpenDay[] dataset;
    private static View.OnClickListener clickListener;

    public OpenDaysAdapter(LocalDatabase.OpenDay[] openDaysDataset, View.OnClickListener callback) {
        this.dataset = openDaysDataset;
        clickListener = callback;
    }

    @NonNull
    @Override
    public OpenDaysAdapter.OpenDaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_open_days_item, parent, false);
        OpenDaysViewHolder holder = new OpenDaysViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(v);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OpenDaysViewHolder holder, int position) {
        holder.item.setText(dataset[position].name);
        holder.date.setText(dataset[position].getDateAsText());
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    public static class OpenDaysViewHolder extends RecyclerView.ViewHolder {

        public TextView item;
        public TextView date;
        public OpenDaysViewHolder(@NonNull View view) {
            super(view);
            this.item = view.findViewById(R.id.textView_open_days_item);
            this.date = view.findViewById(R.id.textView_open_days_date);
        }
    }
}
