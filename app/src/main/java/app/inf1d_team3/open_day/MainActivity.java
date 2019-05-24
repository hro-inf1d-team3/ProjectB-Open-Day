package app.inf1d_team3.open_day;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements FragmentOnClickable {

    private ActionBar toolbar;
    private BottomNavigationView navigation;
    private String currentTitle;
    private Fragment currentFragment;
    private FragmentOnClickable clickableFragment;

    private Fragment openDaysFragment;
    private Fragment askQuestionFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_openDays:
                    if(openDaysFragment == null) openDaysFragment = new OpenDaysFragment();
                    loadFragment(openDaysFragment, true);
                    return true;
                case R.id.navigation_askQuestion:
                    if(askQuestionFragment == null) askQuestionFragment = new AskQuestionFragment();
                    loadFragment(askQuestionFragment, true);
                    return true;
            }
            return false;
        }
    };

    private FragmentManager.OnBackStackChangedListener mOnBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            Fragment newCurrentFragment = getSupportFragmentManager().findFragmentById(R.id.frameContainer_main);
            if(newCurrentFragment != null && newCurrentFragment != currentFragment){
                setCurrentFragment(newCurrentFragment);
                Menu menu = navigation.getMenu();

                if(currentFragment instanceof OpenDaysFragment){
                    setCurrentTitle(getResources().getString(R.string.title_openDays));
                    menu.findItem(R.id.navigation_openDays).setChecked(true);
                } else if (currentFragment instanceof AskQuestionFragment){
                    setCurrentTitle(getResources().getString(R.string.title_askQuestion));
                    menu.findItem(R.id.navigation_askQuestion).setChecked(true);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocalDatabase.init();

        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();

        navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().addOnBackStackChangedListener(mOnBackStackChangedListener);

        if (savedInstanceState != null) {
            //Restore the currentFragment's instance
            Fragment savedFragment = getSupportFragmentManager().getFragment(savedInstanceState, "currentFragment");
            String cT = savedInstanceState.getString("currentTitle");
            Log.d(this.getClass().getName(), cT);
            setCurrentTitle(cT);
            if(savedFragment != null) loadFragment(savedFragment, false);
        } else {
            if(openDaysFragment == null) openDaysFragment = new OpenDaysFragment();
            setCurrentTitle(getResources().getString(R.string.title_openDays));
            loadFragment(openDaysFragment, false);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the currentFragment's instance
        getSupportFragmentManager().putFragment(outState, "currentFragment", currentFragment);
        outState.putString("currentTitle", currentTitle);
    }

    private void setCurrentFragment(Fragment fragment){
        currentFragment = fragment;
        clickableFragment = currentFragment instanceof FragmentOnClickable ? (FragmentOnClickable)currentFragment : null;
    }

    private void setCurrentTitle(String title){
        currentTitle = title;
        toolbar.setTitle(title);
    }

    private void loadFragment(Fragment fragment, boolean addToBackStack){
        String fragmentTag =  fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStackImmediate(fragmentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameContainer_main, fragment);
        if(addToBackStack){
            transaction.addToBackStack(fragmentTag);
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();

        if(!addToBackStack) setCurrentFragment(fragment);
    }

    @Override
    public void fragmentOnClick(View view){
        if(clickableFragment != null) clickableFragment.fragmentOnClick(view);
    }

    protected void onDestroy(){
        super.onDestroy();

        LocalDatabase.destroy();
    }

}
