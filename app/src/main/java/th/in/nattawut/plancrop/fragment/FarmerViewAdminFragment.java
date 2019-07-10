package th.in.nattawut.plancrop.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import th.in.nattawut.plancrop.AdminActivity;
import th.in.nattawut.plancrop.HomeActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.DeleteFammer;
import th.in.nattawut.plancrop.utility.EditFarmer;
import th.in.nattawut.plancrop.utility.FarmerViewAdminAdpter;
import th.in.nattawut.plancrop.utility.GetDataWhereRegister;
import th.in.nattawut.plancrop.utility.Myconstant;

public class FarmerViewAdminFragment extends Fragment {

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create Toolbal
        CreateToolbal();

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
                        createLisView();

                    }
                },2);
            }
        });
    }

    private void createLisView() {
        ListView listView = getView().findViewById(R.id.listViewFarmer);
        Myconstant myconstant = new Myconstant();
        String[] columString = myconstant.getComlumFarmerString1();

        try {

            GetDataWhereRegister getDataWhereOneColumn = new GetDataWhereRegister(getActivity());
            getDataWhereOneColumn.execute("mid",myconstant.getUrlselectFarmer());

            String jsonString = getDataWhereOneColumn.get();
            Log.d("19jan","JSon Farmer ==> "+ jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);
            final String[] midString = new String[jsonArray.length()];
            final String[] useridString = new String[jsonArray.length()];
            final String[] pwdString = new String[jsonArray.length()];
            final String[] idString = new String[jsonArray.length()];
            final String[] nameString = new String[jsonArray.length()];
            final String[] addressString = new String[jsonArray.length()];
            final String[] pidString = new String[jsonArray.length()];
            final String[] didString = new String[jsonArray.length()];
            final String[] sidString = new String[jsonArray.length()];
            final String[] vidString = new String[jsonArray.length()];
            final String[] telString = new String[jsonArray.length()];
            final String[] emailString = new String[jsonArray.length()];
            final String[] areaString = new String[jsonArray.length()];


            for (int i=0; i<jsonArray.length(); i+=1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                midString[i] = jsonObject.getString(columString[0]);
                useridString[i] = jsonObject.getString(columString[1]);
                pwdString[i] = jsonObject.getString(columString[2]);
                idString[i] = jsonObject.getString(columString[3]);
                nameString[i] = jsonObject.getString(columString[4]);
                addressString[i] = jsonObject.getString(columString[5]);
                pidString[i] = jsonObject.getString(columString[6]);
                didString[i] = jsonObject.getString(columString[7]);
                vidString[i] = jsonObject.getString(columString[8]);
                sidString[i] = jsonObject.getString(columString[9]);
                telString[i] = jsonObject.getString(columString[10]);
                emailString[i] = jsonObject.getString(columString[11]);
                areaString[i] = jsonObject.getString(columString[12]);

            }
            final FarmerViewAdminAdpter farmerViewTestAdpter = new FarmerViewAdminAdpter(getActivity(),
                    midString,useridString,pwdString,idString,nameString,addressString,pidString,didString,vidString,sidString,telString,emailString,areaString);
            listView.setAdapter(farmerViewTestAdpter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    deleteorEditFarmer(midString[position],useridString[position],pwdString[position],idString[position],nameString[position],addressString[position],
                            pidString[position],didString[position],vidString[position],sidString[position],telString[position],emailString[position],areaString[position]);
                }
            });

            mSwipeRefreshLayout.setRefreshing(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteorEditFarmer(final String midString,final String useridString,final String pwdString,final String idString,final String nameString,final String addressString,
                                    final String pidString,final String didString,final String vidString,final String sidString,final String telString,final String emailString,final String areaString) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogTheme);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.farmer);
        builder.setTitle("ข้อมูลเกษตรกร");
        builder.setMessage("กรุณาเลือก ลบ หรือ ดูข้อมูล ?");
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("ลบ" ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteFarmer(midString);
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("ดูข้อมูล", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editFarmer(midString,useridString,pwdString,idString,nameString,addressString,pidString,didString,vidString,sidString,telString,emailString,areaString);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void editFarmer(final String midString, String useridString, String pwdString, String idString, String nameString,
                            String addressString, final String pidString,final String didString, String vidString, String sidString, String telString, String emailString, String areaString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("ข้อมูลส่วนตัว");

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.edit_farmer, null);


        EditText EditEdtPassword = view.findViewById(R.id.Editedtpassword);
        String newPassword = getActivity().getIntent().getExtras().getString("pwd",pwdString);
        EditEdtPassword.setText(newPassword);

        EditText EditEdtId = view.findViewById(R.id.Editedtid);
        String newId = getActivity().getIntent().getExtras().getString("id",idString);
        EditEdtId.setText(newId);

        EditText EditEdtName = view.findViewById(R.id.Editedtname);
        String newName = getActivity().getIntent().getExtras().getString("Name",nameString);
        EditEdtName.setText(newName);

        EditText EditEdtAddress = view.findViewById(R.id.Editedtaddress);
        String newAddress = getActivity().getIntent().getExtras().getString("address",addressString);
        EditEdtAddress.setText(newAddress);

        EditText EditEdtPhone = view.findViewById(R.id.Editedtphone);
        String newPhone = getActivity().getIntent().getExtras().getString("tel",telString);
        EditEdtPhone.setText(newPhone);

        EditText EditEdtEmail = view.findViewById(R.id.Editedtemail);
        String newEmail = getActivity().getIntent().getExtras().getString("email",emailString);
        EditEdtEmail.setText(newEmail);

        TextView EditspProvinceFarmer = view.findViewById(R.id.EditspProvinceFarmer);
        String newPid = getActivity().getIntent().getExtras().getString("pid",pidString);
        EditspProvinceFarmer.setText(newPid);

        TextView EditspAmphurFarmer = view.findViewById(R.id.EditspAmphurFarmer);
        String newDid = getActivity().getIntent().getExtras().getString("thai",didString);
        EditspAmphurFarmer.setText(newDid);

        TextView DistriceFarmer = view.findViewById(R.id.EditspDistriceFarmer);
        String newSid = getActivity().getIntent().getExtras().getString("thai",sidString);
        DistriceFarmer.setText(newSid);

        TextView EditTextVillag = view.findViewById(R.id.EditspVillag);
        String newVillag = getActivity().getIntent().getExtras().getString("thai",vidString);
        EditTextVillag.setText(newVillag);


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

                EditText EditEdtPassword = view.findViewById(R.id.Editedtpassword);
                String newPassword = EditEdtPassword.getText().toString();

                EditText EditEdtId = view.findViewById(R.id.Editedtid);
                String newID = EditEdtId.getText().toString();

                EditText EditEdtName = view.findViewById(R.id.Editedtname);
                String newName = EditEdtName.getText().toString();

                EditText EditEdtAddress = view.findViewById(R.id.Editedtaddress);
                String newAddress = EditEdtAddress.getText().toString();

                EditText EditEdtPhone = view.findViewById(R.id.Editedtphone);
                String newPhone = EditEdtPhone.getText().toString();

                EditText EditEdtEmail = view.findViewById(R.id.Editedtemail);
                String newEmail = EditEdtEmail.getText().toString();

                EditText plan1 = view.findViewById(R.id.Editadd1);
                EditText plan2 = view.findViewById(R.id.Editadd2);
                EditText plan3 = view.findViewById(R.id.Editadd3);

                String newArea = Float.toString(Float.parseFloat(plan1.getText().toString().trim())
                        +(Float.parseFloat(plan2.getText().toString().trim())*100+Float.parseFloat(plan3.getText().toString().trim()))/400);

                updateFarmer(midString,newPassword,newID,newName,newAddress,newPhone,newEmail,newArea);
                dialog.dismiss();

            }
        });
        builder.show();
    }

    //updateเกษตรกร
    private void updateFarmer(String midString, String newPassword, String newID, String newName, String newAddress, String newPhone, String newEmail,String newArea) {
        Myconstant myconstant = new Myconstant();
        try {
            EditFarmer editFarmer = new EditFarmer(getActivity());
            editFarmer.execute(midString,newPassword,newID,newName,newAddress,newPhone,newEmail,newArea,
                    myconstant.getUrlEditFarmer());

            if (Boolean.parseBoolean(editFarmer.get())) {
            }else {
                Toast.makeText(getActivity(),"แก้ไขข้อมูลสำเร็จ",
                        Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //alertให้เลือกจะลบรายการหรือไม่
    private void deleteFarmer(final String midString){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogTheme);
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
                editDeleteFarmer(midString);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    //ลบรายการประเภทพืชเพาะปลูก
    private void editDeleteFarmer(String midString){

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemAddUser) {
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentAdminFragment, new FarmerFragment())
                            .addToBackStack(null)
                            .commit();
                    return false;
                }
            });

            // uploadValueToSever();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.manu_uploaduser, menu);

    }

    private void CreateToolbal() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarFarmerView);
        ((AdminActivity)getActivity()).setSupportActionBar(toolbar);

        ((AdminActivity)getActivity()).getSupportActionBar().setTitle("ข้อมูลเกษตรกร");

        ((AdminActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AdminActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        View view = inflater.inflate(R.layout.frm_view_farmer,container, false);
        return view;
    }
}