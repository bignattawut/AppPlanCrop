package th.in.nattawut.plancrop.fragment;

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
import th.in.nattawut.plancrop.MainActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.AddNewUserUpload;
import th.in.nattawut.plancrop.utility.Amphur;
import th.in.nattawut.plancrop.utility.Devs;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;

public class RegisterFragment extends Fragment {

    private AsyncHttpClient client;
    private Spinner sponerde;

    private Spinner spdistrict;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create Toolbar
        createToolbar();

        //RegisterController
        registerController();

        client = new AsyncHttpClient();
        sponerde = getActivity().findViewById(R.id.spProvince);
        spdistrict = getActivity().findViewById(R.id.spAmphur);

        //Spinner Province
        province();

        //Spinner Amphur
        Amphur();
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
        EditText name = getView().findViewById(R.id.edtname);
        EditText id = getView().findViewById(R.id.edtid);
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


        if (userString.isEmpty() || passwordString.isEmpty() || nameString.isEmpty() || idString.isEmpty() || addressString.isEmpty() || phonString.isEmpty() || emailString.isEmpty() || passwordString.isEmpty() || amphurString.isEmpty()) {

            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.onrmaIDialog("สวัสดี", "กรุณากรอกข้อมูล");
        } else {

            try {
                Myconstant myconstant = new Myconstant();
                AddNewUserUpload addNewUserUpload = new AddNewUserUpload(getActivity());
                addNewUserUpload.execute(userString, passwordString, nameString, idString, addressString, phonString, emailString,provinceString,amphurString,
                        myconstant.getUrlRegister());

                String result = addNewUserUpload.get();
                Log.d("1jan", "result ==>" + result);

                if (Boolean.parseBoolean(result)) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(), "สมัครสมาชิกเรียบร้อย", Toast.LENGTH_LONG).show();
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

    private void province() {
        //String u = "http://192.168.1.30/android/php/selectprovince.php";
        client.post(Myconstant.getUrlProvince, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    c(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    private void c(String respuesta) {
        ArrayList<Devs> lista = new ArrayList<Devs>();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);
            for (int i = 0; i < jsonArray.length(); i++) {
                Devs p = new Devs(getActivity(), this, android.R.layout.simple_dropdown_item_1line, lista);
                p.setThai(jsonArray.getJSONObject(i).getString("thai"));
                p.setPid(jsonArray.getJSONObject(i).getInt("pid"));
                lista.add(p);
            }
            ArrayAdapter<Devs> adapter = new ArrayAdapter<Devs>(this.getActivity(), android.R.layout.simple_spinner_item, lista);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            sponerde.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Amphur() {
        client.post(Myconstant.getUrlProvince, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    b(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    private void b(String respu){
        ArrayList<Amphur> lis = new ArrayList<Amphur>();
        try {
            JSONArray json = new JSONArray(respu);
            for (int i =0; i<json.length(); i++){
                Amphur a = new Amphur(getActivity(),this,android.R.layout.simple_dropdown_item_1line, lis);
                a.setThai(json.getJSONObject(i).getString("thai"));
                //a.setDid(json.getJSONObject(i).getInt("did"));
                lis.add(a);
            }
            ArrayAdapter<Amphur> amphurAdapter = new ArrayAdapter<Amphur>(this.getActivity(), android.R.layout.simple_spinner_item, lis);
            amphurAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spdistrict.setAdapter(amphurAdapter);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}

