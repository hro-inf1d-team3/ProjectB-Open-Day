package app.inf1d_team3.open_day;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        LocalDatabase.init();
    }

    // dat is om van een pagina naar andere te gaan
    public void openOpenDaysList(View view) {
        Intent intent = new Intent(this, OpenDaysActivity.class);
        startActivity(intent);
    }

    protected void onDestroy(){
        super.onDestroy();

        LocalDatabase.destroy();
    }

    //dat is de rest van de boveaan code
    public void openActivity2(){
        Intent intent = new Intent(MainActivity.this, InstituteInfoActivity.class);
        startActivity(intent);
    }
}