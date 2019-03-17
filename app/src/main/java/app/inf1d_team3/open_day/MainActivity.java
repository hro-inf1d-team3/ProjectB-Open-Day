package app.inf1d_team3.open_day;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalDatabase.init();
    }

    public void openOpenDaysList(View view) {
        Intent intent = new Intent(this, OpenDaysActivity.class);
        startActivity(intent);
    }

    protected void onDestroy(){
        super.onDestroy();

        LocalDatabase.destroy();
    }
}