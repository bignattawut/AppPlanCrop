package th.in.nattawut.plancrop.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import th.in.nattawut.plancrop.HomeActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.AddlLogin;
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

                    try {
                        AddlLogin addlLogin = new AddlLogin(getActivity());
                        addlLogin.execute(userString,passwordString);
                        String jsonString = addlLogin.get();
                        Log.d("1/may", "JSON ==>" + jsonString);

                        if (jsonString.equals("null")) {
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.onrmaIDialog("กรุณากรอกข้อมูล", "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง");
                        } else {
                            JSONArray jsonArray = new JSONArray(jsonString);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            if (passwordString.equals(jsonObject.getString("pwd"))) {
                                Toast.makeText(getActivity(), "Welcome " + jsonObject.getString("Name"), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(),HomeActivity.class);
                                String nameuser = null, miduser = null;
                                nameuser = jsonObject.getString("Name");
                                miduser = jsonObject.getString("MID");
                                intent.putExtra("Name",nameuser);
                                intent.putExtra("MID",miduser);
                                startActivity(intent);
                                getActivity().finish();
                            }else {
                                MyAlert myAlert = new MyAlert(getActivity());
                                myAlert.onrmaIDialog("รหัสผ่าน", "รหัสผ่านไม่ถูกต้อง");
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
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
