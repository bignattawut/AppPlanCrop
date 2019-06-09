package th.in.nattawut.plancrop.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.tuann.floatingactionbuttonexpandable.FloatingActionButtonExpandable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.Cccc;
import th.in.nattawut.plancrop.utility.DeleteCropType;
import th.in.nattawut.plancrop.utility.DeletePlan;
import th.in.nattawut.plancrop.utility.EditPlan;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;
import th.in.nattawut.plancrop.utility.PantAdapter;

public class PlanViewFragment extends Fragment {

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create ListView
        createListView();

        //Swipe Refresh Layout
        swipeRefreshLayout();

        //planViewController
        planViewController();


    }

    private void planViewController() {
        FloatingActionButtonExpandable floatingActionButton = getView().findViewById(R.id.floatingActionButtonViewPlan);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlanFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void swipeRefreshLayout() {
        mSwipeRefreshLayout = getView().findViewById(R.id.swiRefreshLayou);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Create ListView
                        createListView();

                    }
                },2);

            }
        });
    }

    private void createListView() {
        final ListView listView = getView().findViewById(R.id.listViewPlan);
        Myconstant myconstant = new Myconstant();
        String[] columnStrings = myconstant.getColumnPlanString();

        try {

            GetData getData = new GetData(getActivity());
            getData.execute(myconstant.getUrlselectPlan());


            String jsonString = getData.get();
            Log.d("22big","JSON plan ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            final String[] planStrings = new String[jsonArray.length()];
            final String[] midString = new String[jsonArray.length()];
            final String[] typeStrings = new String[jsonArray.length()];
            final String[] cidString = new String[jsonArray.length()];
            final String[] areStrings = new String[jsonArray.length()];
            final String[] dateStrings = new String[jsonArray.length()];
            final String[] NoStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                planStrings[i] = jsonObject.getString(columnStrings[0]);
                midString[i] = jsonObject.getString(columnStrings[1]);
                typeStrings[i] = jsonObject.getString(columnStrings[2]);
                cidString[i] = jsonObject.getString(columnStrings[3]);
                areStrings[i] = jsonObject.getString(columnStrings[4]);
                dateStrings[i] = jsonObject.getString(columnStrings[5]);
            }

            PantAdapter pantAdapter = new PantAdapter(getActivity(),
                    planStrings,midString,typeStrings,cidString, areStrings,dateStrings);
            listView.setAdapter(pantAdapter);

            //edit
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    deleteorEditCropPlan(planStrings[position],midString[position]);
                    //mSwipeRefreshLayout.setRefreshing(false);
                }
            });
            mSwipeRefreshLayout.setRefreshing(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //alertให้เลือกลบหรือแก้ไข
    private void deleteorEditCropPlan(final String planStrings, final String midString) {

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
                deleteCropType(planStrings);
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("แก้ไข", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editPlan(planStrings,midString);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    //alertให้เลือกจะลบรายการหรือไม่
    private void deleteCropType(final String planStrings){
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
                editDeletePlan(planStrings);
                dialog.dismiss();
            }
        });
        builder.show();

    }

    private void editPlan(final String planStrings,final String midString){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        //กำหนดหัวเเรื้อง
        builder.setTitle("วางแผนเพาะปลูกใหม่");
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.edit_plan, null);
        //builder = new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Dialog_Alert);//theme


        TextView texPlanMid = view.findViewById(R.id.EditTextMidPlan);
        String strTextShowmid = getActivity().getIntent().getExtras().getString("MID");
        texPlanMid.setText(strTextShowmid);

        TextView texPlanName = view.findViewById(R.id.EditTexPlanLogin);
        String strTextShowName = getActivity().getIntent().getExtras().getString("Name");
        texPlanName.setText(strTextShowName);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final Spinner spin = view.findViewById(R.id.EditPlanCropSpinner);
        try {
            GetData getData = new GetData(getActivity());
            getData.execute(Myconstant.getUrlCrop);

            String jsonString = getData.get();
            Log.d("5/Jan CropType", "JSON ==>" + jsonString);
            JSONArray data = new JSONArray(jsonString);

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            for(int i = 0; i < data.length(); i++){
                JSONObject c = data.getJSONObject(i);

                map = new HashMap<String, String>();
                map.put("cid", c.getString("cid"));
                map.put("crop", c.getString("crop"));
                MyArrList.add(map);
            }
            SimpleAdapter sAdap;
            sAdap = new SimpleAdapter(getActivity(), MyArrList, R.layout.spinner_plancrop,
                    new String[] {"cid", "crop"}, new int[] {R.id.textPlanCidSpinner, R.id.textPlanCropSpinner});
            spin.setAdapter(sAdap);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> arg0, View selectedItemView, int position, long id) {

                }
                public void onNothingSelected(AdapterView<?> arg0) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        final TextView data = view.findViewById(R.id.EditMyDate);
        ImageView selctData = view.findViewById(R.id.EditImageViewDate);
        selctData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dataPickerDialog = new DatePickerDialog(getActivity(),/*AlertDialog.THEME_DEVICE_DEFAULT_DARK,*///Theme
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int y, int m, int d) {
                                data.setText(y + "/" + (m + 1) + "/" + d);
                            }
                        },day,month,year);
                dataPickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());//วันที่ปัจจุบัน

                dataPickerDialog.show();
            }
        });
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

                TextView EditTextMidPlan = view.findViewById(R.id.EditTextMidPlan);
                TextView EditPlanCropSpinner = view.findViewById(R.id.textPlanCidSpinner);
                TextView EditMyDate = view.findViewById(R.id.EditMyDate);
                EditText EditAddPlan1 = view.findViewById(R.id.EditAddPlan1);
                EditText EditAddPlan2 = view.findViewById(R.id.EditAddPlan2);
                EditText EditAddPlan3 = view.findViewById(R.id.EditAddPlan3);

                String newEditTextMidPlan = EditTextMidPlan.getText().toString();
                String newEditPlanCropSpinner = EditPlanCropSpinner.getText().toString();
                String newEditMyDate = EditMyDate.getText().toString();

                String newEditArea = Float.toString(Float.parseFloat(EditAddPlan1.getText().toString().trim())
                        +(Float.parseFloat(EditAddPlan2.getText().toString().trim()) *100
                        +Float.parseFloat(EditAddPlan3.getText().toString().trim()))/400);

                //Log.d("are","are ===>" + newEditTextMidPlan + newEditMyDate + newEditPlanCropSpinner);

                updatePlan(planStrings,newEditTextMidPlan,
                        newEditMyDate,
                        newEditPlanCropSpinner,
                        newEditArea);

            }
        });
        builder.show();
    }

    private void updatePlan(String planStrings, String newEditTextMidPlan, String newEditMyDate, String newEditPlanCropSpinner,String newEditArea) {
        Myconstant myconstant = new Myconstant();

        Log.d("are","areb ===>" + newEditTextMidPlan + newEditMyDate);
        try {
            EditPlan editPlan = new EditPlan(getActivity());
            editPlan.execute(planStrings,newEditTextMidPlan,
                    newEditMyDate,
                    newEditPlanCropSpinner,
                    newEditArea,
                    myconstant.getUrlEditPlan());

            if (Boolean.parseBoolean(editPlan.get())) {
                createListView();
            } else {
                Toast.makeText(getActivity(),"แก้ไขข้อมูลสำเร็จ",
                        Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ลบรายการประเภทพืชเพาะปลูก
    private void editDeletePlan(String planStrings){

        Myconstant myconstant = new Myconstant();
        try {
            DeletePlan deletePlan = new DeletePlan(getActivity());
            deletePlan.execute(planStrings, myconstant.getUrlDeletePlan());

            if (Boolean.parseBoolean(deletePlan.get())) {
                createListView();
            } else {
                Toast.makeText(getActivity(),"ลบประเภทพืช",Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_view_plan,container, false);
        return view;
    }
}
