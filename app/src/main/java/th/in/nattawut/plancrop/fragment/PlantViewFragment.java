package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.Myconstant;
import th.in.nattawut.plancrop.utility.PantAdapter;
import th.in.nattawut.plancrop.utility.PlatAdpter;

public class PlantViewFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //planViewController
        plantViewController();

        //Create ListView
        createListView();
    }

    private void plantViewController() {
        FloatingActionButton floatingActionButton = getView().findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlantFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void createListView() {
        final ListView listView = getView().findViewById(R.id.listViewPlant);
        Myconstant myconstant = new Myconstant();
        String[] columnStrings = myconstant.getColumnPlantString();

        try {

            GetData getData = new GetData(getActivity());
            getData.execute(myconstant.getUrlselectPlant());


            String jsonString = getData.get();
            Log.d("NatTWut","JSON plant ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            final String[] noStrings = new String[jsonArray.length()];
            final String[] pdataString = new String[jsonArray.length()];
            final String[] cidStrings = new String[jsonArray.length()];
            final String[] yieldStrings = new String[jsonArray.length()];
            final String[] cropStrings = new String[jsonArray.length()];
            final String[] areaStrings = new String[jsonArray.length()];
            final String[] midStrings = new String[jsonArray.length()];
            final String[] nameStrings = new String[jsonArray.length()];
            final String[] snoStrings = new String[jsonArray.length()];
            final String[] latStrings = new String[jsonArray.length()];
            final String[] lonStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                noStrings[i] = jsonObject.getString(columnStrings[0]);
                pdataString[i] = jsonObject.getString(columnStrings[1]);
                cidStrings[i] = jsonObject.getString(columnStrings[2]);
                yieldStrings[i] = jsonObject.getString(columnStrings[3]);
                cropStrings[i] = jsonObject.getString(columnStrings[4]);
                areaStrings[i] = jsonObject.getString(columnStrings[5]);
                midStrings[i] = jsonObject.getString(columnStrings[6]);
                nameStrings[i] = jsonObject.getString(columnStrings[7]);
                snoStrings[i] = jsonObject.getString(columnStrings[8]);
                latStrings[i] = jsonObject.getString(columnStrings[9]);
                lonStrings[i] = jsonObject.getString(columnStrings[10]);
            }

            PlatAdpter platAdpter = new PlatAdpter(getActivity(),
                    noStrings,pdataString,cidStrings,yieldStrings,cropStrings,areaStrings,
                    midStrings,nameStrings,snoStrings,latStrings,lonStrings);
            listView.setAdapter(platAdpter);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_view_plant,container, false);
        return view;
    }
}
