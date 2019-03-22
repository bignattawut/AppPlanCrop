package th.in.nattawut.plancrop.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.DeleteCrop;
import th.in.nattawut.plancrop.utility.DeleteFammer;
import th.in.nattawut.plancrop.utility.EditCropType;
import th.in.nattawut.plancrop.utility.EditRegister;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.Myconstant;
import th.in.nattawut.plancrop.utility.RegisterViewAdpter;

public class RegisterViewFragment extends Fragment {

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //CreateLisView
        createLisView();

        //Swipe Refresh Layout
        swipeRefreshLayout();
    }
    private void swipeRefreshLayout() {
        mSwipeRefreshLayout = getView().findViewById(R.id.swiRefreshLayouRegister);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Create ListView
                        createLisView();

                    }
                },2);
            }
        });
    }


    private void createLisView() {
        final ListView listView = getView().findViewById(R.id.listViewRegister);
        Myconstant myconstant = new Myconstant();
        String[] columString = myconstant.getComlumRegisterString();

        try {
            GetData getData = new GetData(getActivity());
            getData.execute(myconstant.getUrlGetRegister());

            String jsonString = getData.get();
            Log.d("19jan","JSon register ==> "+ jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            String[] userString = new String[jsonArray.length()];
            String[] passwordString = new String[jsonArray.length()];
            String[] nameString = new String[jsonArray.length()];
            String[] idString = new String[jsonArray.length()];
            String[] addressString = new String[jsonArray.length()];
            String[] vidString = new String[jsonArray.length()];
            String[] sidString = new String[jsonArray.length()];
            String[] phonString = new String[jsonArray.length()];
            String[] emailString = new String[jsonArray.length()];
            final String[] midString = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                userString[i] = jsonObject.getString(columString[1]);
                passwordString[i] = jsonObject.getString(columString[2]);
                nameString[i] = jsonObject.getString(columString[3]);
                idString[i] = jsonObject.getString(columString[4]);
                addressString[i] = jsonObject.getString(columString[5]);
                vidString[i] = jsonObject.getString(columString[6]);
                sidString[i] = jsonObject.getString(columString[7]);
                phonString[i] = jsonObject.getString(columString[8]);
                emailString[i] = jsonObject.getString(columString[9]);
                midString[i] = jsonObject.getString(columString[0]);
            }
            final RegisterViewAdpter registerAdpter = new RegisterViewAdpter(getActivity(),
                    userString, passwordString, nameString, idString, addressString,vidString,sidString, phonString, emailString);
            listView.setAdapter(registerAdpter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    deleteorEditRegister(midString[position]);

                }
            });
            mSwipeRefreshLayout.setRefreshing(false);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    //alertให้เลือกลบหรือแก้ไข
    private void deleteorEditRegister(final String midString) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_drawerplan);
        builder.setTitle("ลบ หรือ แก้ไข");
        builder.setMessage("กรุณาเลือก ลบ หรือ แก้ไข ?");
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("ลบ" ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteRegister(midString);
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("แก้ไข", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editRegister(midString);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    //alertให้เลือกจะลบรายการหรือไม่
    private void deleteRegister(final String midString){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("ต้องการลบรายการนี้หรือไม่?");
        builder.setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editDeleteRegister(midString);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    //แก้ไขประเทพืชเพาะปลูก
    private void editRegister(final String midString){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("ข้อมูลส่วนตัว");

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.edit_register1, null);
        builder.setView(view);

        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("แก้ไข", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText editText = view.findViewById(R.id.EditEdtUsername);
                String newUsername = editText.getText().toString();

                EditText EditEdtPassword = view.findViewById(R.id.EditEdtPassword);
                String newPassword = EditEdtPassword.getText().toString();


                EditText EditEdtName = view.findViewById(R.id.EditEdtName);
                String newName = EditEdtName.getText().toString();

                EditText EditEdtId = view.findViewById(R.id.EditEdtId);
                String newID = EditEdtId.getText().toString();

                EditText EditEdtAddress = view.findViewById(R.id.EditEdtAddress);
                String newAddress = EditEdtAddress.getText().toString();

                EditText EditEdtPhone = view.findViewById(R.id.EditEdtPhone);
                String newPhone = EditEdtPhone.getText().toString();

                EditText EditEdtEmail = view.findViewById(R.id.EditEdtEmail);
                String newEmail = EditEdtEmail.getText().toString();

                //if (newUsername.isEmpty() || newPassword.isEmpty() || newName.isEmpty() || newID.isEmpty() || newAddress.isEmpty() || newPhone.isEmpty() || newEmail.isEmpty()) {

                //}
                updateRegister(midString,newUsername,newPassword,newName,newID,newAddress,newPhone,newEmail);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    //updateข้อมูลประเภทพืชเพาะปลูก
    private void updateRegister(String midString, String newUsername ,String newPassword, String newName, String newID, String newAddress, String newPhone, String newEmail){
    //private void updateRegister(String midString,String newName){

        Myconstant myconstant = new Myconstant();

        try {
            EditRegister editRegister = new EditRegister(getActivity());
             editRegister.execute(midString,newUsername,newPassword,newName,newID,newAddress,newPhone,newEmail,
            //editRegister.execute(midString,newName,

                    myconstant.getUrlEditRegister());

            if (Boolean.parseBoolean(editRegister.get())) {

            }else {
                Toast.makeText(getActivity(),"แก้ไขข้อมูลสำเร็จ",
                        Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ลบรายการประเภทพืชเพาะปลูก
    private void editDeleteRegister(String midString){

        Myconstant myconstant = new Myconstant();
        try {
            DeleteFammer deleteFammer = new DeleteFammer(getActivity());
            deleteFammer.execute(midString, myconstant.getUrlDeleteFammer());

            if (Boolean.parseBoolean(deleteFammer.get())) {
                createLisView();
            } else {
                Toast.makeText(getActivity(),"ลบรายการพืชเพาะปลูก",Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_view_register,container, false);
        return view;
    }
}

