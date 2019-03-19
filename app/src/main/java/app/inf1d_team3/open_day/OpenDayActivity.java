package app.inf1d_team3.open_day;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OpenDayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_day);

        Intent intent = getIntent();
        int index = intent.getIntExtra(OpenDaysActivity.EXTRA_OPEN_DAY_INDEX, 0);

        LocalDatabase.OpenDay openDay = LocalDatabase.openDaysList.get(index);
        TextView title = findViewById(R.id.textView_open_day_title);
        TextView description = findViewById(R.id.textView_open_day_desciption);

        title.setText(openDay.getName());
        description.setText(openDay.getDescription());
    }
}
