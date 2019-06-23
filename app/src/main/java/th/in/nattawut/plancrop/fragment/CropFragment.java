package th.in.nattawut.plancrop.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import th.in.nattawut.plancrop.utility.MyAlertCrop;
import th.in.nattawut.plancrop.utility.Myconstant;

public class CropFragment extends Fragment {

    private String cropString,tidString, BeginHarvestString, HarvestPeriodString, YieldString,cropTypeString;
    TextView textCropType;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create Toolbal
        createToolbal();

        //cropTypeSpinner
        cropTypeSpinner();

        //CropController
        cropController();


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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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

        TextView textCropType = getView().findViewById(R.id.textCropType);
        cropTypeString = textCropType.getText().toString().trim();

        cropString = edtCrop.getText().toString().trim();
        tidString = tid.getText().toString().trim();
        BeginHarvestString = edtBeginHarvest.getText().toString().trim();
        HarvestPeriodString = edtHarvestPeriod.getText().toString().trim();
        YieldString = edtYield.getText().toString().trim();

        MyAlertCrop myAlertCrop = new MyAlertCrop(getActivity());
        if (cropString.isEmpty()|| tidString.isEmpty()) {
            myAlertCrop.onrmaIDialog("โปรดกรอก", "กรุณากรอกชื่อพืช");
        }else if (BeginHarvestString.isEmpty()) {
            myAlertCrop.onrmaIDialog("โปรดกรอก", "กรุณากรอกจำนวนวันเก็บเกี่ยว");
        }else if (HarvestPeriodString.isEmpty()) {
            myAlertCrop.onrmaIDialog("โปรดกรอก", "กรุณากรอกระยะเวลาที่เก็บเกี่ยว");
        }else if (YieldString.isEmpty()) {
            myAlertCrop.onrmaIDialog("โปรดกรอก", "กรุณากรอกผลผลิต");
        }else {
            comfirmUpload();
        }
    }

    private void comfirmUpload() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("ข้อมูลพืชเพาะปลูก");
        builder.setMessage("ชื่อพืช = " + cropString + "\n"
                + "ชื่อประเภทพืช = " + cropTypeString + "\n"
                + "จำนวนวันเก็บเกี่ยว = " + BeginHarvestString + "\n"
                + "ระยะเพาะเก็บเกี่ยว = " + HarvestPeriodString + "\n"
                + "ผลผลิต = " + YieldString);
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {//ปุ่มที่1
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }); //
        builder.setPositiveButton("เพิ่ม", new DialogInterface.OnClickListener() {//ปุ่มที่2
            @Override
            public void onClick(DialogInterface dialog, int which) {
                uploadToServer();
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void uploadToServer(){
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
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new CropViewFragment())
                        .addToBackStack(null)
                        .commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
