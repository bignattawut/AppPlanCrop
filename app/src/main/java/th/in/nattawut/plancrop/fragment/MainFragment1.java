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
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import th.in.nattawut.plancrop.AdminActivity;
import th.in.nattawut.plancrop.HomeActivity;
import th.in.nattawut.plancrop.MemberActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.APIUtils;
import th.in.nattawut.plancrop.utility.LoginResponse;
import th.in.nattawut.plancrop.utility.Myconstant;
import th.in.nattawut.plancrop.utility.OrderService;
import th.in.nattawut.plancrop.utility.RetrofitClient;

public class MainFragment1 extends Fragment {

    private EditText editTextUserId;
    private EditText editTextPassword;
    OrderService orderService;
    private String typeUser;
    private int typeDataInt;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        editTextUserId = getView().findViewById(R.id.edtusername);
        editTextPassword = getView().findViewById(R.id.edtpassword);
        orderService = APIUtils.getService();

        loginControkker();

    }

    private void loginControkker() {
        Button button = getView().findViewById(R.id.btnlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userString = editTextUserId.getText().toString().trim();
                String passwordString = editTextPassword.getText().toString().trim();

                if (userString.isEmpty()) {
                    editTextUserId.setError("ชื้อผู้ใช้งานไม่ถูกต้อง");
                    editTextUserId.requestFocus();
                    return;
                }
                if (passwordString.isEmpty()) {
                    editTextPassword.setError("รหัสผ่านไม่ถูกต้อง");
                    editTextPassword.requestFocus();
                    return;
                }
                if (passwordString.length() < 2) {
                    editTextPassword.setError("รหัสผ่านควรมีความยาวอย่างน้อย 2 ตัว");
                    editTextPassword.requestFocus();
                    return;
                }
                Call<LoginResponse> call = orderService.getuserLogin(userString, passwordString);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse loginResponse = response.body();
                        if (!loginResponse.isError()) {
                            Toast.makeText(getActivity(), loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), loginResponse.getMessage(), Toast.LENGTH_LONG).show();

                        }
//                        if (response.isSuccessful()) {
//                            if (response.body() != null) {
//                                Log.i("onSucces",response.body().toString());
//
//                                String json = response.body().toString();
//                                login(json);
//                            }
//                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.i("onEmptyResponse", "Returned empty response");

                    }
                });


            }
        });
    }



    public void login(String response){
        try {

            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            String nameuser = null, miduser = null, vid = null;
            nameuser = jsonObject.getString("name");
            miduser = jsonObject.getString("mid");
            vid = jsonObject.getString("vid");
            typeUser = jsonObject.getString("type");

            String typeDataString = jsonObject.getString("type").trim();
            typeDataInt = Integer.parseInt(typeDataString);

            //ฝัง MID ในแอพ
            Myconstant myconstant = new Myconstant();
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(myconstant.getNameFileSharePreference(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("mid", miduser);
            editor.putString("name", nameuser);
            editor.putString("vid", vid);
            editor.putString("type", typeUser);
            editor.commit();

            switch (typeDataInt) {
                case 1:
                    Intent admin = new Intent(getActivity(), AdminActivity.class);
                    admin.putExtra("name", nameuser);
                    admin.putExtra("mid", miduser);
                    admin.putExtra("vid", vid);
                    startActivity(admin);
                    getActivity().finish();//คำสั่งปิดแอป
                    break;
                case 2:
                    Intent home = new Intent(getActivity(), HomeActivity.class);
                    home.putExtra("name", nameuser);
                    home.putExtra("mid", miduser);
                    home.putExtra("vid", vid);
                    startActivity(home);
                    getActivity().finish();//คำสั่งปิดแอป
                    break;
                case 3:
                    Intent member = new Intent(getActivity(), MemberActivity.class);
                    member.putExtra("name", nameuser);
                    member.putExtra("mid", miduser);
                    member.putExtra("vid", vid);
                    startActivity(member);
                    break;
            }

//            String nameuser = null, miduser = null, vid = null;
//            Intent home = new Intent(getActivity(), HomeActivity.class);
//            home.putExtra("name", nameuser);
//            home.putExtra("mid", miduser);
//            home.putExtra("vid", vid);
//            startActivity(home);
//            getActivity().finish();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_login1, container, false);
        return view;
    }
}
