package th.in.nattawut.plancrop.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

import th.in.nattawut.plancrop.MainActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.AddNewUserUpload;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;

public class RegisterFragment extends Fragment {

    private ArrayList<String> arrProvince = new ArrayList<>();
    private ArrayList<String> arrProvinceID = new ArrayList<>();

    private ArrayList<String> arrAmphur = new ArrayList<>();
    private ArrayList<String> arrAmphurID = new ArrayList<>();

    private ArrayList<String> arrVid = new ArrayList<>();
    private ArrayList<String> arrVidID = new ArrayList<>();

    private ArrayAdapter<String> adpProvince,adpAmphur,adpVid;
    private Spinner spProvince,spAmphur, spDistrice;
    private int rubIDprovince;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create Toolbar
        createToolbar();

        //RegisterController
        registerController();

        //จังหวัด
        spProvince = getView().findViewById(R.id.spProvince);
        adpProvince = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrProvince);
        spProvince.setAdapter(adpProvince);

        //อำเภอ
        spAmphur = getView().findViewById(R.id.spAmphur);
        adpAmphur = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrAmphur);
        spAmphur.setAdapter(adpAmphur);

        //หมู่บ้าน
        spDistrice = getView().findViewById(R.id.spDistrice);
        adpVid = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrVid);
        spDistrice.setAdapter(adpVid);

    }
    @Override
    public void onStart() {
        super.onStart();
        new DataProvince().execute();
        new DataAmphur().execute("1");
        new DataDistrict().execute("1","1");
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
                        arrVid.clear();
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
                        /*new DataDistrict().execute(listamphur.get(position), String.valueOf(rubIDprovince));
                        rubIDprovince = Integer.parseInt(listamphurid.get(position));
                       arrVid.clear();*/
                       new DataDistrict().execute(listamphur.get(position));
                        rubIDprovince = Integer.parseInt(listamphurid.get(position));
                        arrVid.clear();

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //new DataDistrict().execute(listamphur.get(0), String.valueOf(rubIDprovince));
        }
    }
    private class DataDistrict extends AsyncTask<String, Void, String> {

        String result;
        private ArrayList<String> listavid;
        private ArrayList<String> listavidid;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            listavid = new ArrayList<>();
            listavidid = new ArrayList<>();
            //Toast.makeText(getActivity(), "ting", Toast.LENGTH_LONG).show();

        }
        @Override
        protected String doInBackground(String... strings) {
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("did", strings[0])
                    //.add("pid", strings[1])
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
                    listavidid.add(jsonObject.getString("sid"));
                    listavid.add(jsonObject.getString("thai"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        /*@Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            arrVid.addAll(listavid);
            arrVidID.addAll(listavidid);
            adpVid.notifyDataSetChanged();

            spDistrice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (spDistrice.getSelectedItem() != null) {
                        //arrVid.clear();

                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }*/

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            arrVidID.addAll(listavidid);
            arrVid.addAll(listavid);
            adpVid.notifyDataSetChanged();
        }
    }

    private void registerController() {
        Button button = getView().findViewById(R.id.btnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadValueToSever();
            }
        });
    }

    /*
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            if (item.getItemId() == R.id.itemupload) {

                uploadValueToSever();
                return true;

            }


            return super.onOptionsItemSelected(item);
        }*/

    private void uploadValueToSever() {

        EditText username = getView().findViewById(R.id.edtusername);
        EditText password = getView().findViewById(R.id.edtpassword);
        EditText id = getView().findViewById(R.id.edtid);
        EditText name = getView().findViewById(R.id.edtname);
        EditText address = getView().findViewById(R.id.edtaddress);
        EditText phon = getView().findViewById(R.id.edtphone);
        EditText email = getView().findViewById(R.id.edtemail);
        Spinner province = getView().findViewById(R.id.spProvince);
        Spinner amphur = getView().findViewById(R.id.spAmphur);

        String userString = username.getText().toString().trim();
        String passwordString = password.getText().toString().trim();
        String nameString = name.getText().toString().trim();
        String idString = id.getText().toString().trim();
        String addressString = address.getText().toString().trim();
        String phonString = phon.getText().toString().trim();
        String emailString = email.getText().toString().trim();
        String provinceString = province.getSelectedItem().toString().trim();
        String amphurString = amphur.getSelectedItem().toString().trim();


        if (userString.isEmpty() || passwordString.isEmpty() || idString.isEmpty() || nameString.isEmpty()  || addressString.isEmpty() || phonString.isEmpty() || emailString.isEmpty() || provinceString.isEmpty() || amphurString.isEmpty()) {

            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.onrmaIDialog("สวัสดี", "*กรุณากรอกข้อมูลให้ครบ");
        } else {

            try {
                Myconstant myconstant = new Myconstant();
                AddNewUserUpload addNewUserUpload = new AddNewUserUpload(getActivity());
                addNewUserUpload.execute(userString, passwordString,  idString,nameString, addressString, phonString, emailString, provinceString, amphurString,
                        myconstant.getUrlRegister());

                String result = addNewUserUpload.get();
                Log.d("1jan", "result ==>" + result);

                if (Boolean.parseBoolean(result)) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(), "สมัครสมาชิกเรียบร้อย", Toast.LENGTH_LONG).show();
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentMainFragment, new MainFragment())
                            .addToBackStack(null)
                            .commit();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.manu_register, menu);
    }*/

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("สมัครสมาชิก");
        //((MainActivity)getActivity()).getSupportActionBar().setSubtitle("ddbdbvd");

        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        View view = inflater.inflate(R.layout.frm_register, container, false);
        return view;
    }
}


