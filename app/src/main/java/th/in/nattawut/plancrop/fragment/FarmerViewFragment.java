package th.in.nattawut.plancrop.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.EditFarmerandroid;
import th.in.nattawut.plancrop.utility.GetDataWhereOneColumn;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;

public class FarmerViewFragment extends Fragment {

    Myconstant myconstant = new Myconstant();
    private String idRecord;
    private boolean aBoolean = true;

    public FarmerViewFragment() {

    }

    private ArrayList<String> arrProvince = new ArrayList<>();
    private ArrayList<String> arrProvinceID = new ArrayList<>();

    private ArrayList<String> arrAmphur = new ArrayList<>();
    private ArrayList<String> arrAmphurID = new ArrayList<>();

    private ArrayList<String> arrSid = new ArrayList<>();
    private ArrayList<String> arrSidID = new ArrayList<>();

    private ArrayList<String> arrVid = new ArrayList<>();
    private ArrayList<String> arrVidID = new ArrayList<>();

    private ArrayAdapter<String> adpProvince, adpAmphur, adpSid, adpVid;
    private Spinner spProvince, spAmphur, spSubDistrice, spVillag;
    private int rubIDprovince;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showView();

        editController();

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
        spVillag = getView().findViewById(R.id.spVillagFarmer);
        adpVid = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrVid);
        spVillag.setAdapter(adpVid);

    }

    @Override
    public void onStart() {
        super.onStart();
        new DataProvince().execute();
        new DataAmphur().execute("1");
        new DataSubDistrict().execute("1", "1");
        new DataVillag().execute("1", "1", "1");
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
                Response response = client.newCall(request).execute();
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

                    Log.d("5/Jan getUrlVid", "JSON ==>" + result);
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

    private void editController() {
        Button button = getView().findViewById(R.id.btnEditFarmer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadValueToServer();
            }
        });
    }

    private void showView() {
        try {

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(myconstant.getNameFileSharePreference(), Context.MODE_PRIVATE);
            idRecord = sharedPreferences.getString("mid", "");

            GetDataWhereOneColumn getDataWhereOneColumn = new GetDataWhereOneColumn(getActivity());
            getDataWhereOneColumn.execute("mid", idRecord, myconstant.getSelectfarmerandroid());

            String result = getDataWhereOneColumn.get();
            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            TextView textViewmid = getView().findViewById(R.id.midtext);
            textViewmid.setText(jsonObject.getString("mid"));

            TextView edtusername = getView().findViewById(R.id.edtusername);
            edtusername.setText(jsonObject.getString("userid"));

            TextView edtpassword = getView().findViewById(R.id.edtpassword);
            edtpassword.setText(jsonObject.getString("pwd"));

            TextView edtid = getView().findViewById(R.id.edtid);
            edtid.setText(jsonObject.getString("id"));

            TextView edtname = getView().findViewById(R.id.edtname);
            edtname.setText(jsonObject.getString("name"));

            TextView edtaddress = getView().findViewById(R.id.edtaddress);
            edtaddress.setText(jsonObject.getString("address"));


//            TextView spProvinceFarmer = getView().findViewById(R.id.spProvince);
//            spProvinceFarmer.setText(jsonObject.getString("thai"));
//
//            TextView spAmphurFarmer = getView().findViewById(R.id.spAmphur);
//            spAmphurFarmer.setText(jsonObject.getString("thai"));
//
//            TextView spDistriceFarmer = getView().findViewById(R.id.spDistrice);
//            spDistriceFarmer.setText(jsonObject.getString("thai"));
//
//            TextView spVillag = getView().findViewById(R.id.spVillag);
//            spVillag.setText(jsonObject.getString("thai"));

            TextView edtphone = getView().findViewById(R.id.edtphone);
            edtphone.setText(jsonObject.getString("tel"));

            TextView edtemail = getView().findViewById(R.id.edtemail);
            edtemail.setText(jsonObject.getString("email"));

//            TextView add1 = getView().findViewById(R.id.add1);
//            add1.setText(jsonObject.getString("area"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void uploadValueToServer() {
        TextView midtext = getView().findViewById(R.id.midtext);
        EditText edtusername = getView().findViewById(R.id.edtusername);
        EditText EditEdtPassword = getView().findViewById(R.id.edtpassword);
        EditText EditEdtId = getView().findViewById(R.id.edtid);
        EditText EditEdtName = getView().findViewById(R.id.edtname);
        EditText EditEdtAddress = getView().findViewById(R.id.edtaddress);
        Spinner province = getView().findViewById(R.id.spProvinceFarmer);
        Spinner amphur = getView().findViewById(R.id.spAmphurFarmer);
        Spinner subDistrice = getView().findViewById(R.id.spDistriceFarmer);
        Spinner villag = getView().findViewById(R.id.spVillagFarmer);
        EditText EditEdtPhone = getView().findViewById(R.id.edtphone);
        EditText EditEdtEmail = getView().findViewById(R.id.edtemail);
        EditText plan1 = getView().findViewById(R.id.add1);
        EditText plan2 = getView().findViewById(R.id.add2);
        EditText plan3 = getView().findViewById(R.id.add3);


        String newmid = midtext.getText().toString();
        String newusername = edtusername.getText().toString();
        String newPassword = EditEdtPassword.getText().toString();
        String newID = EditEdtId.getText().toString();
        String newName = EditEdtName.getText().toString();
        String newAddress = EditEdtAddress.getText().toString();
        String newprovince = province.getSelectedItem().toString().trim();
        String newamphur = amphur.getSelectedItem().toString().trim();
        String newsubDistrice = subDistrice.getSelectedItem().toString().trim();
        String newvillag = villag.getSelectedItem().toString().trim();
        String newPhone = EditEdtPhone.getText().toString();
        String newEmail = EditEdtEmail.getText().toString();
        String editTextString = Float.toString(Float.parseFloat(plan1.getText().toString().trim())
                + (Float.parseFloat(plan2.getText().toString().trim()) * 100 + Float.parseFloat(plan3.getText().toString().trim())) / 400);

        MyAlert myAlert = new MyAlert(getActivity());//การสร้างออปเจ็ค
        // check Spqce  การหาช่องว่าง
        if (newmid.isEmpty() || newusername.isEmpty() || newPassword.isEmpty() || newID.isEmpty() || newName.isEmpty() || newAddress.isEmpty()
                || newprovince.isEmpty() || newamphur.isEmpty() || newsubDistrice.isEmpty() || newvillag.isEmpty()
                || newPhone.isEmpty() || newEmail.isEmpty() || editTextString.isEmpty()) {

            myAlert.onrmaIDialog("มีช่องว่าง", "กรุณาเติมทุกช่องว่าง");
        } else {
            try {
                Myconstant myconstant = new Myconstant();
                EditFarmerandroid editFarmerandroid = new EditFarmerandroid(getActivity());
                editFarmerandroid.execute(newmid, newusername, newPassword, newID, newName, newAddress, newprovince, newamphur, newsubDistrice, newvillag,
                        newPhone, newEmail, editTextString, myconstant.getUrlEditFarmerAndroid());

                if (Boolean.parseBoolean(editFarmerandroid.get())) {
                } else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentHomeFragment,new MainPlanFragment())
                            .commit();
                    Toast.makeText(getActivity(), "แก้ไขข้อมูลสำเร็จ",
                            Toast.LENGTH_SHORT).show();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frm_farmerandroid_view, container, false);
    }
}
