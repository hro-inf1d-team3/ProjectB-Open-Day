package app.inf1d_team3.open_day;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

import app.inf1d_team3.open_day.adapter.OpenDayEventsAdapter;

public class OpenDayActivity extends AppCompatActivity {
    private LocalDatabase.OpenDay openDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_day);
        Intent intent = getIntent();
        int index = intent.getIntExtra(OpenDaysFragment.EXTRA_OPEN_DAY_INDEX, 0);
        openDay = LocalDatabase.openDaysList.get(index);
        TextView title = findViewById(R.id.textView_open_day_title);
        TextView description = findViewById(R.id.textView_open_day_desciption);
        title.setText(openDay.name);
        description.setText(openDay.description);
        RecyclerView eventsView = findViewById(R.id.recyclerView_open_day_events);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter eventsAdapter = new OpenDayEventsAdapter(openDay.getOpenDayEvents());
        eventsView.hasFixedSize();
        eventsView.setLayoutManager(layoutManager);
        eventsView.setAdapter(eventsAdapter);
    }

    public void addToCalendar(View view){
        Calendar date = Calendar.getInstance();
        date.setTime(openDay.date);
        String title = getResources().getString(R.string.open_day_calendarTitle) + " " + openDay.name;
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, date.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.DESCRIPTION, openDay.description)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, openDay.location)
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_TENTATIVE);
        startActivity(intent);
    }

    public void share(View view){
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        String shareSub= getResources().getString(R.string.open_day_shareTitle) + " " + openDay.name;
        String shareBody=  shareSub + "\n\n"
                + openDay.description + "\n\n"
                + openDay.location;
        myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
        myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(myIntent, getResources().getString(R.string.open_day_shareMenuName)));
    }
}
