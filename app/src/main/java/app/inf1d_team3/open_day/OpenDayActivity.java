package app.inf1d_team3.open_day;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import app.inf1d_team3.open_day.adapter.OpenDayEventsAdapter;

public class OpenDayActivity extends AppCompatActivity {
    private LocalDatabase.OpenDay openDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_day);

        Intent intent = getIntent();
        int index = intent.getIntExtra(OpenDaysActivity.EXTRA_OPEN_DAY_INDEX, 0);

        openDay = LocalDatabase.openDaysList.get(index);
        TextView title = findViewById(R.id.textView_open_day_title);
        TextView description = findViewById(R.id.textView_open_day_desciption);

        title.setText(openDay.getName());
        description.setText(openDay.getDescription());

        RecyclerView eventsView = findViewById(R.id.recyclerView_open_day_events);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter eventsAdapter = new OpenDayEventsAdapter(openDay.getOpenDayEvents());

        eventsView.hasFixedSize();
        eventsView.setLayoutManager(layoutManager);
        eventsView.setAdapter(eventsAdapter);
    }

    public void addToCalendar(View view){
        Calendar date = Calendar.getInstance();
        date.setTime(openDay.getDate());

        String title = getResources().getString(R.string.open_day_calendarTitle) + " " + openDay.getName();

        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, date.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.DESCRIPTION, openDay.getDescription())
                //.putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_TENTATIVE);
        startActivity(intent);
    }
}
