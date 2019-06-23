package th.in.nattawut.plancrop.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.AddOrder;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;

public class OrderFragment extends Fragment {

    //Button selctDate;
    //ImageView selctDate;
    TextView txtSData,txtEData;
    DatePickerDialog dataPickerDialog;
    Calendar calendar;
    Button selctDate;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        addCrop();

        sdateController();

        eateController();

        addMember();

        addOrder();

    }

    private void addMember() {
        TextView txtName = getView().findViewById(R.id.txtName);
        TextView txtMid = getView().findViewById(R.id.txtMid);

        String strTextShow = getActivity().getIntent().getExtras().getString("name");
        txtName.setText(strTextShow);

        String strTextShowmid = getActivity().getIntent().getExtras().getString("mid");
        txtMid.setText(strTextShowmid);

    }

    private void addCrop() {
        if (android.os.Build.VERSION.SDK_INT > 9) { //setup policy เเพื่อมือถือที่มีประปฏิบัติการสูงกว่านีจะไม่สามารถconnectกับโปรโตรคอลได้
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final Spinner spin = getView().findViewById(R.id.addCrop);
        try {
            GetData getData = new GetData(getActivity());
            getData.execute(Myconstant.getUrlCrop);

            String jsonString = getData.get();
            Log.d("5/Jan PlanCropSpinner", "JSON ==>" + jsonString);
            JSONArray data = new JSONArray(jsonString);

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            for (int i = 0; i < data.length(); i++) {
                JSONObject c = data.getJSONObject(i);

                map = new HashMap<String, String>();
                map.put("cid", c.getString("cid"));
                map.put("crop", c.getString("crop"));
                MyArrList.add(map);
            }
            SimpleAdapter sAdap;
            sAdap = new SimpleAdapter(getActivity(), MyArrList, R.layout.spinner_plancrop,
                    new String[]{"cid", "crop"}, new int[]{R.id.textPlanCidSpinner, R.id.textPlanCropSpinner});
            spin.setAdapter(sAdap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sdateController() {
        txtSData = getActivity().findViewById(R.id.txtSData);
        selctDate = getActivity().findViewById(R.id.btnSData);
        selctDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                //final Date cha = calendar.getTime();

                dataPickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int y, int m, int d) {
                                //date.setText(y + "/" + (m + 1) + "/" + d);
                                txtSData.setText(y + "/" + (m + 1) + "/" + d);
                                //DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.UK);

                            }
                        }, day, month, year);
                dataPickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dataPickerDialog.show();
            }
        });
    }

    private void eateController() {
        txtEData = getActivity().findViewById(R.id.txtEData);
        selctDate = getActivity().findViewById(R.id.btnEData);
        selctDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                //final Date cha = calendar.getTime();

                dataPickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int y, int m, int d) {
                                //date.setText(y + "/" + (m + 1) + "/" + d);
                                txtEData.setText(y + "/" + (m + 1) + "/" + d);
                                //DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.UK);

                            }
                        }, day, month, year);
                dataPickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dataPickerDialog.show();
            }
        });
    }

    private void addOrder() {
        Button button = getView().findViewById(R.id.btnAddOrder);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order();
            }
        });
    }

    private void order() {
        TextView txtMid = getView().findViewById(R.id.txtMid);
        TextView textCrop = getView().findViewById(R.id.textPlanCidSpinner);
        TextView txtSData = getView().findViewById(R.id.txtSData);
        TextView txtEData = getView().findViewById(R.id.txtEData);
        EditText edtQty = getView().findViewById(R.id.edtQty);

        String addMid = txtMid.getText().toString().trim();
        String addCrop = textCrop.getText().toString().trim();
        String addSData = txtSData.getText().toString().trim();
        String addEData = txtEData.getText().toString().trim();
        String addQty = edtQty.getText().toString().trim();

        if (addMid.isEmpty() ||  addSData.isEmpty() || addEData.isEmpty() || addCrop.isEmpty() || addQty.isEmpty()) {
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.onrmaIDialog("สวัสดี", "กรุณากรอกข้อมูลให้ครบ");
        }else {

            try {
                Myconstant myconstant = new Myconstant();
                AddOrder addOrder = new AddOrder(getActivity());
                addOrder.execute(addMid,addSData,addEData,addCrop,addQty,
                        myconstant.getUrladdorder());

                String result = addOrder.get();
                Log.d("addOrder", "result ==> " + result);
                if (Boolean.parseBoolean(result)) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(), "เพิ่มข้อมูลเรียบร้อย", Toast.LENGTH_LONG).show();
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentHomeFragment, new OrderViewFragment())
                            .addToBackStack(null)
                            .commit();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_add_requirement, container, false);
        return view;
    }
}
