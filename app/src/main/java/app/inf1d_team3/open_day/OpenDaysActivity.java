package app.inf1d_team3.open_day;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import app.inf1d_team3.open_day.adapter.OpenDaysAdapter;

import static app.inf1d_team3.open_day.LocalDatabase.openDaysList;

public class OpenDaysActivity extends AppCompatActivity {
    public static final String EXTRA_OPEN_DAY_INDEX = "app.inf1d_team3.open_day.OPEN_DAY_INDEX";
    private Button button;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter openDaysAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocalDatabase.init();

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

                Intent intent = new Intent(context, OpenDayActivity.class);
                intent.putExtra(EXTRA_OPEN_DAY_INDEX, pos);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(openDaysAdapter);


    }

    public void openInfopage(View view){
        Intent intent = new Intent(this, InstituteInfoActivity.class);
        startActivity(intent);
    }

    protected void onDestroy(){
        super.onDestroy();

        LocalDatabase.destroy();
    }
    public void openQuestionActivity(View view){
        Intent intent = new Intent(OpenDaysActivity.this, AskQuestionActivity.class);
        startActivity(intent);
    }
}
