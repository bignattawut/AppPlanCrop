package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.Myconstant;
import th.in.nattawut.plancrop.utility.PantAdapter;

public class PlanViewFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create ListView
        createListView();

        //Swipe Refresh Layout
        swipeRefreshLayout();

    }

    private void swipeRefreshLayout() {
        SwipeRefreshLayout swipeRefreshLayout = getView().findViewById(R.id.swiRefreshLayou);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    private void createListView() {
        ListView listView = getView().findViewById(R.id.listViewPlan);
        Myconstant myconstant = new Myconstant();
        String[] columnStrings = myconstant.getColumnPlanString();

        try {

            GetData getData = new GetData(getActivity());
            getData.execute(myconstant.getUrlGetPlan());

            String jsonString = getData.get();
            Log.d("22big","JSON plan ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            String[] planStrings = new String[jsonArray.length()];
            final String[] typeStrings = new String[jsonArray.length()];
            String[] areStrings = new String[jsonArray.length()];
            final String[] dateStrings = new String[jsonArray.length()];
            final String[] NoStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                planStrings[i] = jsonObject.getString(columnStrings[1]);
                typeStrings[i] = jsonObject.getString(columnStrings[2]);
                areStrings[i] = jsonObject.getString(columnStrings[3]);
                dateStrings[i] = jsonObject.getString(columnStrings[4]);
                NoStrings[i] = jsonObject.getString(columnStrings[0]);

            }

            PantAdapter pantAdapter = new PantAdapter(getActivity(), planStrings,typeStrings,
                    areStrings,dateStrings);
            listView.setAdapter(pantAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_view_plan,container, false);
        return view;
    }
}
