package th.in.nattawut.plancrop.fragment;

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

import org.json.JSONArray;
import org.json.JSONObject;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.CropViewAdpter;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.Myconstant;

public class CropViewFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create ListView
        createListView();
    }

    private void createListView() {
        ListView listView = getView().findViewById(R.id.listViewCrop);
        Myconstant myconstant = new Myconstant();
        String[] columnStrings = myconstant.getColumnCropString();
        try{
            GetData getData = new GetData(getActivity());
            getData.execute(myconstant.getUrlselectCrop());

            String jsonString = getData.get();
            Log.d("4กุมภาพันธ์","Json Crop ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            final String[] cidString = new String[jsonArray.length()];
            final String[] cropString = new String[jsonArray.length()];
            final String[] tidString = new String[jsonArray.length()];
            //final String[] croptypeString = new String[jsonArray.length()];
            final String[] beginharvestString = new String[jsonArray.length()];
            final String[] harvestperiodString = new String[jsonArray.length()];
            final String[] yield = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                cidString[i] = jsonObject.getString(columnStrings[0]);
                cropString[i] = jsonObject.getString(columnStrings[1]);
                tidString[i] = jsonObject.getString(columnStrings[2]);
                //croptypeString[i] = jsonObject.getString(columnStrings[3]);
                beginharvestString[i] = jsonObject.getString(columnStrings[3]);
                harvestperiodString[i] = jsonObject.getString(columnStrings[4]);
                yield[i] = jsonObject.getString(columnStrings[5]);
            }

            CropViewAdpter cropViewAdpter = new CropViewAdpter(getActivity(),
                    cidString,cropString,tidString,beginharvestString,harvestperiodString,yield);
            listView.setAdapter(cropViewAdpter);
            //edit
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //deleteorEditCropType(tidString[position],cropTypeString[position]);
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_view_crop,container, false);
        return view;
    }
}

