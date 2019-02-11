package th.in.nattawut.plancrop.fragment;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import th.in.nattawut.plancrop.HomeActivity;
import th.in.nattawut.plancrop.MainActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.AddCrop;
import th.in.nattawut.plancrop.utility.AddPlan;
import th.in.nattawut.plancrop.utility.CropAdapter;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.MidAdpter;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;

public class PlanFragment extends Fragment {
    //Button selctDate;
    ImageView selctDate;
    TextView date;
    DatePickerDialog dataPickerDialog;
    Calendar calendar;

    //ล็อคอิน
    private TextView texPlanLogin;
    private String strTexeShowMid;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create Toolbal
        CreateToolbal();

        //CropController
        cropController();

        //Pdate Controller
        pdateController();

        //PlanFarmerSpinner
        planFarmerSpinner();

        //PlanMidSpinner
        //planMidSpinner();

        //setUpTexeShowMid
        setUpTexeShowMid();

    }
    private void setUpTexeShowMid(){
        TextView texPlanLogin = getView().findViewById(R.id.texPlanLogin);
        TextView texPlanMid = getView().findViewById(R.id.texPlanMid);

        String strTextShow = getActivity().getIntent().getExtras().getString("Name");
        texPlanLogin.setText(strTextShow);

        String strTextShowmid = getActivity().getIntent().getExtras().getString("MID");
        texPlanMid.setText(strTextShowmid);




        //TextView texPlanLogin = getView().findViewById(R.id.texPlanLogin);
        //String strTextShow = getActivity().getIntent().getExtras().getString("Name");
        //String strTextShowMid = getActivity().getIntent().getExtras().getString("Mid");
        //texPlanLogin.setText(strTexeShowMid);
    }
/*
    private void planMidSpinner() {
        //setup policy เเพื่อมือถือที่มีประปฏิบัติการสูงกว่านีจะไม่สามารถconnectกับโปรโตรคอลได้
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final Spinner spin = getView().findViewById(R.id.midSpinner);
        try {
            GetData getData = new GetData(getActivity());
            getData.execute(Myconstant.getUrlmid);

            String jsonString = getData.get();
            Log.d("5/Jan PlanMidSpinner", "JSON ==>" + jsonString);
            JSONArray data = new JSONArray(jsonString);

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            for(int i = 0; i < data.length(); i++){
                JSONObject c = data.getJSONObject(i);

                map = new HashMap<String, String>();
                map.put("mid", c.getString("mid"));
                map.put("name", c.getString("name"));
                MyArrList.add(map);
            }
            SimpleAdapter sAdap;
            sAdap = new SimpleAdapter(getActivity(), MyArrList, R.layout.spinner_planmid,
                    new String[] {"mid", "name"}, new int[] {R.id.textCidmid, R.id.textMid});
            spin.setAdapter(sAdap);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View selectedItemView, int position, long id) {

                }
                public void onNothingSelected(AdapterView<?> arg0) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
    private void planFarmerSpinner() {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final Spinner spin = getView().findViewById(R.id.plancropspinner);
        try {
            GetData getData = new GetData(getActivity());
            getData.execute(Myconstant.getUrlCrop);

            String jsonString = getData.get();
            Log.d("5/Jan PlanCropSpinner", "JSON ==>" + jsonString);
            JSONArray data = new JSONArray(jsonString);

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            for(int i = 0; i < data.length(); i++){
                JSONObject c = data.getJSONObject(i);

                map = new HashMap<String, String>();
                map.put("cid", c.getString("cid"));
                map.put("crop", c.getString("crop"));
                MyArrList.add(map);
            }
            SimpleAdapter sAdap;
            sAdap = new SimpleAdapter(getActivity(), MyArrList, R.layout.spinner_plancrop,
                    new String[] {"cid", "crop"}, new int[] {R.id.textPlanCidSpinner, R.id.textPlanCropSpinner});
            spin.setAdapter(sAdap);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View selectedItemView, int position, long id) {

                }
                public void onNothingSelected(AdapterView<?> arg0) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pdateController() {
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
                                //date.setText(d + "/" + (m + 1) + "/" + y);
                                date.setText(y + "/" + (m + 1) + "/" + d);
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
        //TextView textCidmid = getView().findViewById(R.id.textCidmid);
        TextView textCidmid = getView().findViewById(R.id.texPlanMid);
        TextView textPlanCidSpinner = getView().findViewById(R.id.textPlanCidSpinner);
        TextView textmyDate = getView().findViewById(R.id.myDate);
        EditText editText = getView().findViewById(R.id.addplan1);

        //Spinner midSpinner = getView().findViewById(R.id.midSpinner);
        //Spinner cropSpinner = getView().findViewById(R.id.plancropspinner);

        String cidmidString = textCidmid.getText().toString().trim();
        String cidNameString = textPlanCidSpinner.getText().toString().trim();
        String myDataString = textmyDate.getText().toString().trim();
        String editTextString = editText.getText().toString().trim();
        //String midString = midSpinner.getSelectedItem().toString().trim();
        //String cropString = cropSpinner.getSelectedItem().toString().trim();


        if (cidmidString.isEmpty() || myDataString.isEmpty() || cidNameString.isEmpty() || editTextString.isEmpty()) {
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.onrmaIDialog("สวัสดี", "กรุณากรอกข้อมูลให้ครบ");
        }else {
            try {
                Myconstant myconstant = new Myconstant();
                AddPlan addPlan = new AddPlan(getActivity());
                addPlan.execute(cidmidString,myDataString,cidNameString,editTextString,
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


    /*private void midSpinner() {
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
    }*/

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
