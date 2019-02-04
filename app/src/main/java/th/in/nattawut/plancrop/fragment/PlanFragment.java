package th.in.nattawut.plancrop.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;
import th.in.nattawut.plancrop.HomeActivity;
import th.in.nattawut.plancrop.MainActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.AddCrop;
import th.in.nattawut.plancrop.utility.AddPlan;
import th.in.nattawut.plancrop.utility.CropAdapter;
import th.in.nattawut.plancrop.utility.MidAdpter;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;

public class PlanFragment extends Fragment {

    private AsyncHttpClient client;
    private Spinner midSpinner,cropSpinner;

    //Button selctDate;
    ImageView selctDate;
    TextView date;
    DatePickerDialog dataPickerDialog;
    Calendar calendar;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create Toolbal
        CreateToolbal();

        //CropController
        cropController();

        client = new AsyncHttpClient();
        midSpinner = getActivity().findViewById(R.id.midSpinner);
        midSpinner();
        cropSpinner = getActivity().findViewById(R.id.plancropspinner);
        cropSpinner();

        date = getActivity().findViewById(R.id.myDate);
        selctDate = getActivity().findViewById(R.id.imageViewDate);
        selctDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar  = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                dataPickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int y, int m, int d) {
                                date.setText(d + "/" + (m + 1) + "/" + y);
                            }
                        },day,month,year);
                dataPickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dataPickerDialog.show();
            }
        });
    }
    private void cropController() {
        Button button = getView().findViewById(R.id.btnPlan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCrop();
            }
        });
    }

    private void AddCrop() {
        Spinner midSpinner = getView().findViewById(R.id.midSpinner);
        Spinner cropSpinner = getView().findViewById(R.id.plancropspinner);

        //EditText addplan1 = getView().findViewById(R.id.addplan1);
        //EditText addplan2 = getView().findViewById(R.id.addplan2);
       // EditText addplan3 = getView().findViewById(R.id.addplan3);

        String midString = midSpinner.getSelectedItem().toString().trim();
        String cropString = cropSpinner.getSelectedItem().toString().trim();
        //String addplan1String = addplan1.getText().toString().trim();
        //String addplan2String = addplan2.getText().toString().trim();
        //String addplan3String = addplan3.getText().toString().trim();

        if (midString.isEmpty() || cropString.isEmpty()){// addplan1String.isEmpty() || addplan2String.isEmpty() || addplan3String.isEmpty()) {
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.onrmaIDialog("สวัสดี", "กรุณากรอกข้อมูลให้ครบ");
        }else {
            try {
                Myconstant myconstant = new Myconstant();
                AddPlan addPlan = new AddPlan(getActivity());
                addPlan.execute(midString,cropString, //addplan1String, addplan2String, addplan3String,
                        myconstant.getUrladdPlan());

                String result = addPlan.get();
                Log.d("plan", "result ==> " + result);
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


    private void midSpinner() {
        client.post(Myconstant.urlmid, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    midspinner(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    private void midspinner(String respuesta){
        ArrayList<MidAdpter> list = new ArrayList<MidAdpter>();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);
            for (int i=0; i<jsonArray.length(); i++){
                MidAdpter p = new MidAdpter(getActivity(),this, android.R.layout.simple_dropdown_item_1line,list);
                p.setName(jsonArray.getJSONObject(i).getString("name"));
                list.add(p);
            }
            ArrayAdapter<MidAdpter> adapter = new ArrayAdapter<MidAdpter>(this.getActivity(),android.R.layout.simple_spinner_item,list);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            midSpinner.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cropSpinner() {
        client.post(Myconstant.getUrlCrop, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    url(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    private void url(String respuesta){
        ArrayList<CropAdapter> list = new ArrayList<CropAdapter>();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);
            for (int i=0; i<jsonArray.length(); i++){
                CropAdapter p = new CropAdapter(getActivity(),this, android.R.layout.simple_dropdown_item_1line,list);
                p.setCrop(jsonArray.getJSONObject(i).getString("crop"));
                list.add(p);
            }
            ArrayAdapter<CropAdapter> adapter = new ArrayAdapter<CropAdapter>(this.getActivity(),android.R.layout.simple_spinner_item,list);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            cropSpinner.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CreateToolbal() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarPlan);
        ((HomeActivity)getActivity()).setSupportActionBar(toolbar);

        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("วางแผนการเพาะปลูก");
        //((MainActivity)getActivity()).getSupportActionBar().setSubtitle("ddbdbvd");

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
        View view = inflater.inflate(R.layout.frm_plan, container, false);
        return view;
    }
}
