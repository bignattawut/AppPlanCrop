package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import th.in.nattawut.plancrop.HomeActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.AddCrop;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;

public class CropFragment extends Fragment {

    /*private ArrayList<String> arrCropType = new ArrayList<>();
    private ArrayList<String> arrCropTypeID = new ArrayList<>();
    private ArrayAdapter<String> adpCropType,adpCropTypeID;
    private Spinner cropTypeSpinner;
    private int rubIDprovince;*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create Toolbal
        createToolbal();

        //cropTypeSpinner
        cropTypeSpinner();

        //CropController
        cropController();

        //CropViewController
        cropViewController();
    }
    private void cropViewController() {
        Button button = getView().findViewById(R.id.btnCropView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment,new CropViewFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void cropTypeSpinner(){
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final Spinner spin = getView().findViewById(R.id.cropTypeSpinner);
        try {
            GetData getData = new GetData(getActivity());
            getData.execute(Myconstant.getUrlCropType);

            String jsonString = getData.get();
            Log.d("5/Jan CropType", "JSON ==>" + jsonString);
            JSONArray data = new JSONArray(jsonString);

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            for(int i = 0; i < data.length(); i++){
                JSONObject c = data.getJSONObject(i);

                map = new HashMap<String, String>();
                map.put("TID", c.getString("TID"));
                map.put("croptype", c.getString("croptype"));
                MyArrList.add(map);
            }
            SimpleAdapter sAdap;
            sAdap = new SimpleAdapter(getActivity(), MyArrList, R.layout.spinner_crop,
                    new String[] {"TID", "croptype"}, new int[] {R.id.textTID, R.id.textCropType});
            spin.setAdapter(sAdap);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View selectedItemView, int position, long id) {
                    //String sCustomerID = MyArrList.get(position).get("TID").toString();
                    //String sName = MyArrList.get(position).get("croptype").toString();
                }
                public void onNothingSelected(AdapterView<?> arg0) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    private void cropTypeSpinner() {
        cropTypeSpinner = getView().findViewById(R.id.cropTypeSpinner);
        final ArrayList<String>listCropType;
        final ArrayList<String>listCropTypeID;
        listCropType = new ArrayList<>();
        listCropTypeID = new ArrayList<>();

        try {
            GetData getData = new GetData(getActivity());
            getData.execute(Myconstant.getUrlCropType);

            String jsonString = getData.get();
            Log.d("1/Jan", "JSON ==>" + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject = null;

            for (int i = 0; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                listCropType.add(jsonObject.getString("croptype"));
                listCropTypeID.add(jsonObject.getString("TID"));

            }
            adpCropType = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item,arrCropType);
            //adpCropTypeID = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item,arrCropTypeID);
            cropTypeSpinner.setAdapter(adpCropType);
            arrCropType.addAll(listCropType);
            arrCropTypeID.addAll(listCropTypeID);
            adpCropType.notifyDataSetChanged();

            cropTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (cropTypeSpinner.getSelectedItem() != null) {

                        //Toast.makeText(getActivity(),"spinner" + listCropTypeID,Toast.LENGTH_SHORT).show();
                        //rubIDprovince = Integer.parseInt(listCropTypeID.get(position));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    private void cropController() {
        Button button = getView().findViewById(R.id.btnCrop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCrop();
            }
        });
    }

    private void AddCrop() {

        EditText edtCrop = getView().findViewById(R.id.edtCropName);
        TextView tid = getView().findViewById(R.id.textTID);
        EditText edtBeginHarvest = getView().findViewById(R.id.edtBeginHarvest);
        EditText edtHarvestPeriod = getView().findViewById(R.id.edtHarvestPeriod);
        EditText edtYield = getView().findViewById(R.id.edtYield);

        String cropString = edtCrop.getText().toString().trim();
        String tidString = tid.getText().toString().trim();
        String BeginHarvestString = edtBeginHarvest.getText().toString().trim();
        String HarvestPeriodString = edtHarvestPeriod.getText().toString().trim();
        String YieldString = edtYield.getText().toString().trim();

        if (cropString.isEmpty() || tidString.isEmpty()|| BeginHarvestString.isEmpty() || HarvestPeriodString.isEmpty() || YieldString.isEmpty()) {
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.onrmaIDialog("สวัสดี", "กรุณากรอกข้อมูลให้ครบ");
        }else {
            try {
                Myconstant myconstant = new Myconstant();
                AddCrop addCrop = new AddCrop(getActivity());
                addCrop.execute(cropString,tidString, BeginHarvestString, HarvestPeriodString, YieldString,
                        myconstant.getUrlAddCrop());

                String result = addCrop.get();
                Log.d("crop", "result ==> " + result);
                if (Boolean.parseBoolean(result)) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(), "เพิ่มข้อมูลเรียบร้อย", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //toolbal
    private void createToolbal() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarCrop);
        ((HomeActivity)getActivity()).setSupportActionBar(toolbar);

        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("พืชเพาะปลูก");
        ((HomeActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((HomeActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_crop, container,false);
        return view;
    }
}
