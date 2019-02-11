package th.in.nattawut.plancrop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import th.in.nattawut.plancrop.HomeActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;

public class MainFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       // Register Controller
        registerController();

       //Login Controkker
        loginControkker();


    }// onActivityCreat

    private void loginControkker() {
        Button button = getView().findViewById(R.id.btnlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = getView().findViewById(R.id.edtusername);
                EditText password = getView().findViewById(R.id.edtpassword);

                String userString = username.getText().toString().trim();
                String passwordString = password.getText().toString().trim();

                if (userString.isEmpty() || passwordString.isEmpty()) {

                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.onrmaIDialog("สวัสดี", "กรุณากรอกชื่อผู้ใช้หรือรหัสผ่าน");

                }else {
                    Myconstant myconstant = new Myconstant();
                    Boolean b = true;
                    String truePass = null, nameuser = null, miduser = null;
                    MyAlert myAlert = new MyAlert(getActivity());

                try {
                    GetData getData = new GetData(getActivity());
                    getData.execute(myconstant.getUrlGetUser());

                    String jsonString = getData.get();
                    Log.d("1/Jan", "JSON ==>" + jsonString);

                    JSONArray jsonArray = new JSONArray(jsonString);
                    for (int i = 0; i < jsonArray.length(); i += 1) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        if (userString.equals(jsonObject.getString("UserID"))) {
                            b = false;
                            truePass = jsonObject.getString("PWD");
                            nameuser = jsonObject.getString("Name");
                            miduser = jsonObject.getString("MID");
                        }
                    }
                    if (b) {
                        myAlert.onrmaIDialog("กรุณากรอกข้อมูล", "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง");
                    } else if (passwordString.equals(truePass)) {
                        Toast.makeText(getActivity(), "ยินดีต้อนรับ" + nameuser, Toast.LENGTH_LONG).show();

                        //Intent to HomeActivity
                        Intent intent = new Intent(getActivity(),HomeActivity.class);
                        intent.putExtra("Name",nameuser);
                        intent.putExtra("MID",miduser);
                        startActivity(intent);
                        getActivity().finish();

                        /*getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentMainFragment, new HomeFragment())
                                .commit();*/

                    } else {
                        myAlert.onrmaIDialog("รหัสไม่ถูกต้อง", "กรุณากรอกรหัสผ่านใหม่");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                } //if

            }
        });
    }

    private void registerController() {
        Button button = getView().findViewById(R.id.btnregister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Replace Fragment
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_login, container, false);
        return view;
    }
}
