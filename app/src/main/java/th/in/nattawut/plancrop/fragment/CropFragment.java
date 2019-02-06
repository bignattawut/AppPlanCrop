package th.in.nattawut.plancrop.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.*;
import org.json.JSONArray;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;
import th.in.nattawut.plancrop.HomeActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.AddCrop;
import th.in.nattawut.plancrop.utility.CropAdapter;
import th.in.nattawut.plancrop.utility.CropTypeAdpter;
import th.in.nattawut.plancrop.utility.CropTypeAdpter;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;

public class CropFragment extends Fragment {

    private AsyncHttpClient client;
    private Spinner cropSpinner,cropTypeSpinner;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create Toolbal
        createToolbal();

        client = new AsyncHttpClient();


        //ประเภทพืชเพาะปลูก
        cropTypeSpinner = getActivity().findViewById(R.id.cropTypeSpinner);
        cropTypeSpinner();

        //CropController
        cropController();
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
        //Spinner cropSpinner = getView().findViewById(R.id.cropSpinner);
        EditText edtCrop = getView().findViewById(R.id.edtCropName);
        Spinner cropTypeSpinner = getView().findViewById(R.id.cropTypeSpinner);
        EditText edtBeginHarvest = getView().findViewById(R.id.edtBeginHarvest);
        EditText edtHarvestPeriod = getView().findViewById(R.id.edtHarvestPeriod);
        EditText edtYield = getView().findViewById(R.id.edtYield);

        //String cropString = cropSpinner.getSelectedItem().toString().trim();
        String cropString = edtCrop.getText().toString().trim();
        String cropTyperString = cropTypeSpinner.getSelectedItem().toString().trim();
        String BeginHarvestString = edtBeginHarvest.getText().toString().trim();
        String HarvestPeriodString = edtHarvestPeriod.getText().toString().trim();
        String YieldString = edtYield.getText().toString().trim();

        if (cropString.isEmpty() || cropTyperString.isEmpty() || BeginHarvestString.isEmpty() || HarvestPeriodString.isEmpty() || YieldString.isEmpty()) {
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.onrmaIDialog("สวัสดี", "กรุณากรอกข้อมูลให้ครบ");
        }else {
            try {
                Myconstant myconstant = new Myconstant();
                AddCrop addCrop = new AddCrop(getActivity());
                addCrop.execute(cropString, cropTyperString, BeginHarvestString, HarvestPeriodString, YieldString,
                        myconstant.getUrlAddCrpo());

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

    //ประเภทพืชเพาะปลูก
    private void cropTypeSpinner() {
        client.post(Myconstant.getUrlCropType, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    croptype(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    private void croptype(String respuesta){
        ArrayList<CropTypeAdpter> list = new ArrayList<CropTypeAdpter>();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);
            for (int i=0; i<jsonArray.length(); i++){
                CropTypeAdpter p = new CropTypeAdpter(getActivity(),this, android.R.layout.simple_dropdown_item_1line,list);
                //p.setCroptype(jsonArray.getJSONObject(i).getString("tid"));
                p.setCroptype(jsonArray.getJSONObject(i).getString("croptype"));
                list.add(p);
            }
            ArrayAdapter<CropTypeAdpter> adapter = new ArrayAdapter<CropTypeAdpter>(this.getActivity(),android.R.layout.simple_spinner_item,list);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            cropTypeSpinner.setAdapter(adapter);
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
