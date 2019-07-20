package th.in.nattawut.plancrop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tuann.floatingactionbuttonexpandable.FloatingActionButtonExpandable;

import org.json.JSONArray;
import org.json.JSONObject;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.CropTypeViewAapter;
import th.in.nattawut.plancrop.utility.DeleteCropType;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.ListViewPlantPic;
import th.in.nattawut.plancrop.utility.Myconstant;
import th.in.nattawut.plancrop.utility.PlantPictureViewAapter;

public class PlantPictureViewFragment extends Fragment {

    ListView listView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        createListView();

        plantPicController();
    }
    private void plantPicController() {
        FloatingActionButtonExpandable floatingActionButtonViewPlant = getView().findViewById(R.id.floatingActionButtonViewPlantPic);
        floatingActionButtonViewPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlantPictureFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void createListView() {
        listView = getView().findViewById(R.id.listViewPlantPicture);
        Myconstant myconstant = new Myconstant();
        String[] columnStrings = myconstant.getColumnPlantPicString();


        try{
            GetData getData = new GetData(getActivity());
            getData.execute(myconstant.getUrlselectPlantPic());


            String jsonString = getData.get();
            Log.d("4กุมภาพันธ์","Json PlantPic ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            String[] picno = new String[jsonArray.length()];
            final String[] SCodeString = new String[jsonArray.length()];
            String[] imageStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                picno[i] = jsonObject.getString(columnStrings[0]);
                imageStrings[i] = jsonObject.getString(columnStrings[1]);
                SCodeString[i] = jsonObject.getString(columnStrings[2]);

            }

            PlantPictureViewAapter plantPictureViewAapter = new PlantPictureViewAapter(getActivity(),picno,imageStrings,SCodeString);
            listView.setAdapter(plantPictureViewAapter);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_view_plantpicture, container, false);
        return view;
    }
}
