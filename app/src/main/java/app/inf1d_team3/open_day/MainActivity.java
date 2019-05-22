package app.inf1d_team3.open_day;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements FragmentOnClickable {

    private ActionBar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_openDays:
                    return true;
                case R.id.navigation_askQuestion:
                    return true;
            }
            return false;
        }
    };

    private FragmentManager.OnBackStackChangedListener mOnBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            //Fragment newCurrentFragment = getSupportFragmentManager().findFragmentById(R.id.frame)
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocalDatabase.init();

        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();

        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void fragmentOnClick(View view){

    }

    protected void onDestroy(){
        super.onDestroy();

        LocalDatabase.destroy();
    }

}
