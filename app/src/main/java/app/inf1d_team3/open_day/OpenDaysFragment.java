package app.inf1d_team3.open_day;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.inf1d_team3.open_day.adapter.OpenDaysAdapter;

import static app.inf1d_team3.open_day.LocalDatabase.openDaysList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OpenDaysFragment extends Fragment implements FragmentOnClickable {
    public static final String EXTRA_OPEN_DAY_INDEX = "app.inf1d_team3.open_day.OPEN_DAY_INDEX";

    private RecyclerView recyclerView;
    private Context context;

    public OpenDaysFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_open_days, container, false);
        context = getActivity();
        recyclerView = fragmentView.findViewById(R.id.recycler_view_open_days);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter openDaysAdapter = new OpenDaysAdapter(openDaysList.toArray(new LocalDatabase.OpenDay[0]), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.indexOfChild(v);

                Intent intent = new Intent(context, OpenDayActivity.class);
                intent.putExtra(EXTRA_OPEN_DAY_INDEX, pos);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(openDaysAdapter);

        return fragmentView;
    }

    private void openInfopage(View view){
        Intent intent = new Intent(context, InstituteInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void fragmentOnClick(View v) {
        if (v.getId() == R.id.button_open_days_aboutInstitute) openInfopage(v);
    }
}
