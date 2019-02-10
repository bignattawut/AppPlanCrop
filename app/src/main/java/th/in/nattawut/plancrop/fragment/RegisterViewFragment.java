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
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.Myconstant;
import th.in.nattawut.plancrop.utility.RegisterViewAdpter;

public class RegisterViewFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //CreateLisView
        createLisView();

    }

    private void createLisView() {
        ListView listView = getView().findViewById(R.id.listViewRegister);
        Myconstant myconstant = new Myconstant();
        String[] columString = myconstant.getComlumRegisterString();

        try {
            GetData getData = new GetData(getActivity());
            getData.execute(myconstant.getUrlGetRegister());

            String jsonString = getData.get();
            Log.d("19jan","JSon register ==> "+ jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            String[] userString = new String[jsonArray.length()];
            String[] passwordString = new String[jsonArray.length()];
            String[] nameString = new String[jsonArray.length()];
            String[] idString = new String[jsonArray.length()];
            String[] addressString = new String[jsonArray.length()];
            String[] phonString = new String[jsonArray.length()];
            String[] emailString = new String[jsonArray.length()];
            String[] midString = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                userString[i] = jsonObject.getString(columString[1]);
                passwordString[i] = jsonObject.getString(columString[2]);
                nameString[i] = jsonObject.getString(columString[3]);
                idString[i] = jsonObject.getString(columString[4]);
                addressString[i] = jsonObject.getString(columString[5]);
                phonString[i] = jsonObject.getString(columString[6]);
                emailString[i] = jsonObject.getString(columString[7]);
                midString[i] = jsonObject.getString(columString[0]);
            }
            RegisterViewAdpter registerAdpter = new RegisterViewAdpter(getActivity(),userString, passwordString, nameString, idString, addressString, phonString, emailString);
            listView.setAdapter(registerAdpter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }







    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_view_register,container, false);
        return view;
    }
}
