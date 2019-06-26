package th.in.nattawut.plancrop.fragment;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import th.in.nattawut.plancrop.HomeActivity;
import th.in.nattawut.plancrop.MainActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.AddFarmer;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.MyAlertCrop;
import th.in.nattawut.plancrop.utility.Myconstant;

public class FarmerFragment extends Fragment {

    private ArrayList<String> arrProvince = new ArrayList<>();
    private ArrayList<String> arrProvinceID = new ArrayList<>();

    private ArrayList<String> arrAmphur = new ArrayList<>();
    private ArrayList<String> arrAmphurID = new ArrayList<>();

    private ArrayList<String> arrSid = new ArrayList<>();
    private ArrayList<String> arrSidID = new ArrayList<>();

    private ArrayList<String> arrVid = new ArrayList<>();
    private ArrayList<String> arrVidID = new ArrayList<>();

    private ArrayAdapter<String> adpProvince,adpAmphur,adpSid,adpVid;
    private Spinner spProvince,spAmphur, spSubDistrice,spVillag;
    private int rubIDprovince;

    private String userString, passwordString, idString, nameString, addressString,provinceString,amphurString,subDistriceString,villagString,phonString,emailString,editTextString;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create Toolbar
        CreateToolbal();

        //FarmerController
        farmerController();


        //จังหวัด
        spProvince = getView().findViewById(R.id.spProvinceFarmer);
        adpProvince = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrProvince);
        spProvince.setAdapter(adpProvince);

        //อำเภอ
        spAmphur = getView().findViewById(R.id.spAmphurFarmer);
        adpAmphur = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrAmphur);
        spAmphur.setAdapter(adpAmphur);

        //ตำบล
        spSubDistrice = getView().findViewById(R.id.spDistriceFarmer);
        adpSid = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrSid);
        spSubDistrice.setAdapter(adpSid);

        //ตำบล
        spVillag = getView().findViewById(R.id.spVillag);
        adpVid = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrVid);
        spVillag.setAdapter(adpVid);

    }

    @Override
    public void onStart() {
        super.onStart();
        new DataProvince().execute();
        new DataAmphur().execute("1");
        new DataSubDistrict().execute("1","1");
        new DataVillag().execute("1","1","1");
    }

    public class DataProvince extends AsyncTask<String, Void, String> {

        String result;
        ArrayList<String> listprovice;
        ArrayList<String> listprovinceid;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getActivity(), "Connecting", Toast.LENGTH_LONG).show();
            listprovice = new ArrayList<>();
            listprovinceid = new ArrayList<>();
        }
        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(Myconstant.getUrlProvince)
                    .build();


            try {
                Response response = client.newCall(request)
                        .execute();

                result = response.body().string();



            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = null;

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    listprovice.add(jsonObject.getString("thai"));
                    listprovinceid.add(jsonObject.getString("pid"));

                    Log.d("5/Jan getUrlProvince", "JSON ==>" + result);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            arrProvince.addAll(listprovice);
            arrProvinceID.addAll(listprovinceid);
            adpProvince.notifyDataSetChanged();

            spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (spProvince.getSelectedItem() != null) {
                        new DataAmphur().execute(listprovinceid.get(position));
                        rubIDprovince = Integer.parseInt(listprovinceid.get(position));
                        arrAmphur.clear();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

    public class DataAmphur extends AsyncTask<String, Void, String> {

        String result;
        private ArrayList<String> listamphur;
        private ArrayList<String> listamphurid;

        @Override
        protected void onPreExecute() {
            listamphur = new ArrayList<>();
            listamphurid = new ArrayList<>();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("pid", strings[0])
                    .build();
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(Myconstant.getUrlAmphur)
                    .post(requestBody)
                    .build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                result = response.body().string();

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    listamphurid.add(jsonObject.getString("did"));
                    listamphur.add(jsonObject.getString("thai"));

                    Log.d("5/Jan getUrlAmphur", "JSON ==>" + result);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            arrAmphur.addAll(listamphur);
            arrAmphurID.addAll(listamphurid);
            adpAmphur.notifyDataSetChanged();


            spAmphur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (spAmphur.getSelectedItem() != null) {
                        new DataSubDistrict().execute(listamphurid.get(position));//String.valueOf(rubIDprovince)
                        rubIDprovince = Integer.parseInt(listamphurid.get(position));
                        arrSid.clear();
                        //MyAlert myAlert = new MyAlert(getActivity());
                        // myAlert.onrmaIDialog("spAmphur","am");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    private class DataSubDistrict extends AsyncTask<String, Void, String> {

        String result;
        private ArrayList<String> listSid;
        private ArrayList<String> listSidId;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            listSid = new ArrayList<>();
            listSidId = new ArrayList<>();

        }

        @Override
        protected String doInBackground(String... strings) {
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("did", strings[0])
                    .build();
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(Myconstant.getUrlSid)
                    .post(requestBody)
                    .build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                result = response.body().string();

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    listSidId.add(jsonObject.getString("sid"));
                    listSid.add(jsonObject.getString("thai"));

                    Log.d("5/Jan getUrlSid", "JSON ==>" + result);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            arrSid.addAll(listSid);
            arrSidID.addAll(listSidId);
            adpSid.notifyDataSetChanged();

            spSubDistrice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (spSubDistrice.getSelectedItem() != null) {
                        new DataVillag().execute(listSidId.get(position));
                        rubIDprovince = Integer.parseInt(listSidId.get(position));
                        arrVid.clear();
                        //MyAlert myAlert = new MyAlert(getActivity());
                        //myAlert.onrmaIDialog("spAmphur","am");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

    private class DataVillag extends AsyncTask<String, Void, String> {

        String result;
        private ArrayList<String> listVid;
        private ArrayList<String> listVidId;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            listVid = new ArrayList<>();
            listVidId = new ArrayList<>();

        }

        @Override
        protected String doInBackground(String... strings) {
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("sid", strings[0])
                    .build();
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(Myconstant.getUrlVid)
                    .post(requestBody)
                    .build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                result = response.body().string();

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    listVidId.add(jsonObject.getString("vid"));
                    listVid.add(jsonObject.getString("thai"));

                    Log.d("5/Jan getUrlSid", "JSON ==>" + result);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            arrVid.addAll(listVid);
            arrVidID.addAll(listVidId);
            adpVid.notifyDataSetChanged();

        }
    }

    private void farmerController() {
        Button button = getView().findViewById(R.id.btnFarmer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadValueToSever();
            }
        });
    }

    private void uploadValueToSever() {

        EditText username = getView().findViewById(R.id.edtusername);
        EditText password = getView().findViewById(R.id.edtpassword);
        EditText id = getView().findViewById(R.id.edtid);
        EditText name = getView().findViewById(R.id.edtname);
        EditText address = getView().findViewById(R.id.edtaddress);
        Spinner province = getView().findViewById(R.id.spProvinceFarmer);
        Spinner amphur = getView().findViewById(R.id.spAmphurFarmer);
        Spinner subDistrice = getView().findViewById(R.id.spDistriceFarmer);
        Spinner villag = getView().findViewById(R.id.spVillag);
        EditText phon = getView().findViewById(R.id.edtphone);
        EditText email = getView().findViewById(R.id.edtemail);



        userString = username.getText().toString().trim();
        passwordString = password.getText().toString().trim();
        idString = id.getText().toString().trim();
        nameString = name.getText().toString().trim();
        addressString = address.getText().toString().trim();
        provinceString = province.getSelectedItem().toString().trim();
        amphurString = amphur.getSelectedItem().toString().trim();
        subDistriceString = subDistrice.getSelectedItem().toString().trim();
        villagString = villag.getSelectedItem().toString().trim();
        phonString = phon.getText().toString().trim();
        emailString = email.getText().toString().trim();


        EditText plan1 = getView().findViewById(R.id.add1);
        EditText plan2 = getView().findViewById(R.id.add2);
        EditText plan3 = getView().findViewById(R.id.add3);

        editTextString = Float.toString(Float.parseFloat(plan1.getText().toString().trim())
                +(Float.parseFloat(plan2.getText().toString().trim())*100+Float.parseFloat(plan3.getText().toString().trim()))/400);


        ////
        MyAlertCrop myAlertCrop = new MyAlertCrop(getActivity());
        if (userString.isEmpty()) {
            myAlertCrop.onrmaIDialog("โปรดกรอก", "กรุณากรอกชื่อผู้ใช้งาน");
        } else if (passwordString.isEmpty()) {
            myAlertCrop.onrmaIDialog("โปรดกรอก", "กรุณากรอกรหัสผ่าน");
        }else if (nameString.isEmpty()){
            myAlertCrop.onrmaIDialog("โปรดกรอก", "กรุณากรอกชื่อ-นามสกุล");
        } else if (phonString.isEmpty()) {
            myAlertCrop.onrmaIDialog("โปรดกรอก", "กรุณากรอกเบอร์โทรศํพท์");
        }else if (editTextString.isEmpty()){
            myAlertCrop.onrmaIDialog("โปรดกรอก", "กรุณากรอกพื้นที่เพาะปลูก");
        //}if (idString.isEmpty()|| addressString.isEmpty() || provinceString.isEmpty() || amphurString.isEmpty() || subDistriceString.isEmpty() || villagString.isEmpty() || emailString.isEmpty() ) {

        }else {
            uploadFarmer();
        }
    }

    private void uploadFarmer() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("ข้อมูลเกษตรกร");
        builder.setMessage("ชื่อผู้ใช้งาน = " + userString + "\n"
                + "รหัสผ่าน = " + passwordString + "\n"
                + "ชื่อ-นามสกุล = " + nameString + "\n"
                + "ที่อยู่ = " + addressString + "\n"
                + "จังหวัด = " + provinceString + "\n"
                + "อำเภอ = " + amphurString + "\n"
                + "ตำบล = " + subDistriceString + "\n"
                + "หมู่บ้าน = " + villagString + "\n"
                + "เบอร์โทรศัพท์ = " + phonString + "\n"
                + "อีเมล์ = " + emailString + "\n"
                + "พื้นที่เพาะปลูก = " + editTextString);
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
                AddFarmer addFarmer = new AddFarmer(getActivity());
                addFarmer.execute(userString, passwordString, idString, nameString, addressString,provinceString,amphurString,subDistriceString,villagString,phonString,emailString,editTextString,
                        myconstant.getUrlFarmer());

                String result = addFarmer.get();
                Log.d("1jan", "result ==>" + result);

                if (Boolean.parseBoolean(result)) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(), "ลงทะเบียนเกษตรกรเรียบร้อย", Toast.LENGTH_LONG).show();
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentHomeFragment, new FarmerViewFragment())
                            .addToBackStack(null)
                            .commit();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    private void CreateToolbal() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarFarmer);
        ((HomeActivity)getActivity()).setSupportActionBar(toolbar);

        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("เพิ่มข้อมูลเกษตรกร");
        //((MainActivity)getActivity()).getSupportActionBar().setSubtitle("ddbdbvd");

        ((HomeActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((HomeActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        setHasOptionsMenu(true);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_farmer, container, false);
        return view;
    }
}



