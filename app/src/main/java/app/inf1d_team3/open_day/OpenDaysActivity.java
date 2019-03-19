package app.inf1d_team3.open_day;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.inf1d_team3.open_day.adapter.OpenDaysAdapter;

import static app.inf1d_team3.open_day.LocalDatabase.openDaysList;

public class OpenDaysActivity extends AppCompatActivity {
    public static final String EXTRA_OPEN_DAY_INDEX = "app.inf1d_team3.open_day.OPEN_DAY_INDEX";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter openDaysAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_days);
        context = this;

        recyclerView = findViewById(R.id.recycler_view_open_days);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        openDaysAdapter = new OpenDaysAdapter(openDaysList.toArray(new LocalDatabase.OpenDay[0]), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.indexOfChild(v);
                /*LinearLayout lin = (LinearLayout)v;
                TextView text = (TextView) lin.getChildAt(0);
                System.out.println(text.getText());*/

                System.out.println(context.getPackageName());
                Intent intent = new Intent(context, OpenDayActivity.class);
                intent.putExtra(EXTRA_OPEN_DAY_INDEX, pos);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(openDaysAdapter);

    }
}
