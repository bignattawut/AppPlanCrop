package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.CropTypeViewAapter;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.Myconstant;

public class CropTypeViewFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create ListView
        createListView();
    }

    private void createListView() {
        ListView listView = getView().findViewById(R.id.listViewCropType);
        Myconstant myconstant = new Myconstant();
        String[] columnStrings = myconstant.getColumnCropTypeString();

        try{
            GetData getData = new GetData(getActivity());
            getData.execute(myconstant.getUrlselectcroptype());

            String jsonString = getData.get();
            Log.d("4กุมภาพันธ์","Json CropType ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);
            final String[] tidString = new String[jsonArray.length()];
            final String[] cropTypeString = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                tidString[i] = jsonObject.getString(columnStrings[0]);
                cropTypeString[i] = jsonObject.getString(columnStrings[1]);
            }
            CropTypeViewAapter cropTypeViewAapter = new CropTypeViewAapter(getActivity(),tidString,cropTypeString);
            listView.setAdapter(cropTypeViewAapter);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_view_croptype,container, false);
        return view;
    }
}
