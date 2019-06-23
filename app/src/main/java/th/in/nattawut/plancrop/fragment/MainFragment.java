package th.in.nattawut.plancrop.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

import th.in.nattawut.plancrop.AdminActivity;
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
                MyAlert myAlert = new MyAlert(getActivity());


                if (userString.isEmpty() || passwordString.isEmpty()) {
                    myAlert.onrmaIDialog("สวัสดี", "กรุณากรอกชื่อผู้ใช้หรือรหัสผ่าน");
                }else {
                    try {
                        Myconstant myconstant = new Myconstant();
                        AddlLogin addlLogin = new AddlLogin(getActivity());
                        addlLogin.execute(userString, myconstant.getUrlGetUser());
                        String jsonString = addlLogin.get();
                        Log.d("1/may", "JSON ==>" + jsonString);


                        if (jsonString.equals("null")) {
                            myAlert.onrmaIDialog("ชื่อผู้ใช้งานไม่ถูกต้อง", "ไม่มี " + userString + "ชื่อผู้ใช้นี้");
                        } else {

                            JSONArray jsonArray = new JSONArray(jsonString);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            if (passwordString.equals(jsonObject.getString("pwd"))) {
                                Toast.makeText(getActivity(), "Welcome " + jsonObject.getString("name"), Toast.LENGTH_SHORT).show();

                                String nameuser = null, miduser = null, vid = null;
                                nameuser = jsonObject.getString("name");
                                miduser = jsonObject.getString("mid");
                                vid = jsonObject.getString("vid");

                                //ฝัง MID ในแอพ
                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(myconstant.getNameFileSharePreference(),Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("mid",miduser);
                                editor.putString("name",nameuser);
                                editor.putString("vid",vid);

                                Intent intent = new Intent(getActivity(), HomeActivity.class);
                                intent.putExtra("name",nameuser);
                                intent.putExtra("mid",miduser);
                                intent.putExtra("vid",vid);
                                startActivity(intent);
                                getActivity().finish();//คำสั่งปิดแอป
                            }else {
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
        View view = inflater.inflate(R.layout.frm_login1, container, false);
        return view;
    }
}
//}else {
//        Myconstant myconstant = new Myconstant();
//        Boolean b = true;
//        String truePass = null, nameuser = null, miduser = null;
//        MyAlert myAlert = new MyAlert(getActivity());
//
//        try {
//        GetData getData = new GetData(getActivity());
//        getData.execute(myconstant.getUrlGetUser());
//
//        String jsonString = getData.get();
//        Log.d("1/Jan", "JSON ==>" + jsonString);
//
//        JSONArray jsonArray = new JSONArray(jsonString);
//        for (int i = 0; i < jsonArray.length(); i += 1) {
//        JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//        if (userString.equals(jsonObject.getString("UserID"))) {
//        b = false;
//        truePass = jsonObject.getString("PWD");
//        nameuser = jsonObject.getString("Name");
//        miduser = jsonObject.getString("MID");
//        }
//        }
//        if (b) {
//        myAlert.onrmaIDialog("กรุณากรอกข้อมูล", "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง");
//        } else if (passwordString.equals(truePass)) {
//        Toast.makeText(getActivity(), "ยินดีต้อนรับ" + nameuser, Toast.LENGTH_LONG).show();
//
//        //Intent to HomeActivity
//        Intent intent = new Intent(getActivity(),HomeActivity.class);
//        intent.putExtra("Name",nameuser);
//        intent.putExtra("MID",miduser);
//        startActivity(intent);
//        getActivity().finish();
//
//                        /*getActivity().getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.contentMainFragment, new HomeFragment())
//                                .commit();*/
//
//        } else {
//        myAlert.onrmaIDialog("รหัสไม่ถูกต้อง", "กรุณากรอกรหัสผ่านใหม่");
//        }