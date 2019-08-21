package th.in.nattawut.plancrop.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import th.in.nattawut.plancrop.AdminActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.APIUtils;
import th.in.nattawut.plancrop.utility.DeletePlant;
import th.in.nattawut.plancrop.utility.EditPlant;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.Myconstant;
import th.in.nattawut.plancrop.utility.OrderService;
import th.in.nattawut.plancrop.utility.Plant;
import th.in.nattawut.plancrop.utility.PlantActivity;
import th.in.nattawut.plancrop.utility.PlantAdpter1;

public class PlantViewFragment1 extends Fragment {

    ListView listView;
    OrderService orderService;
    List<Plant> list = new ArrayList<Plant>();

    ImageView selctDate;
    TextView date;
    DatePickerDialog dataPickerDialog;
    Calendar calendar;

    SwipeRefreshLayout mSwipeRefreshLayout;

    View view;

//    private ArrayList<String> arrProvince = new ArrayList<>();
//    private ArrayList<String> arrProvinceID = new ArrayList<>();
//
//    private ArrayList<String> arrAmphur = new ArrayList<>();
//    private ArrayList<String> arrAmphurID = new ArrayList<>();
//
//    private ArrayList<String> arrSid = new ArrayList<>();
//    private ArrayList<String> arrSidID = new ArrayList<>();
//
//    private ArrayList<String> arrVid = new ArrayList<>();
//    private ArrayList<String> arrVidID = new ArrayList<>();
//
//    private ArrayAdapter<String> adpProvince,adpAmphur,adpSid,adpVid;
//    private Spinner spProvince,spAmphur, spSubDistrice,spVillag;
//    private int rubIDprovince;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CreateToolbal();

        swiRefreshLayou();

        PlantController();


        listView = getView().findViewById(R.id.listViewPlant);
        orderService = APIUtils.getService();

//        //จังหวัด
//        spProvince = getView().findViewById(R.id.spProvincePlant);
//        adpProvince = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrProvinceID);
//        spProvince.setAdapter(adpProvince);
//
//        //อำเภอ
//        spAmphur = getView().findViewById(R.id.spAmphurPlant);
//        adpAmphur = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrAmphurID);
//        spAmphur.setAdapter(adpAmphur);
//
//        //ตำบล
//        spSubDistrice = getView().findViewById(R.id.spDistricePlant);
//        adpSid = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrSidID);
//        spSubDistrice.setAdapter(adpSid);

    }
    private void swiRefreshLayou() {
        mSwipeRefreshLayout = getView().findViewById(R.id.swiRefreshLayouPlant);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                add();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }



    private void pdateController() {
        date = view.findViewById(R.id.EditMyDate);
        selctDate = view.findViewById(R.id.EditImageViewDate);
        selctDate.setOnClickListener(new View.OnClickListener() {
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
                                date.setText(y + "/" + (m + 1) + "/" + d);
                            }
                        }, day, month, year);
                dataPickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());//วันที่ปัจจุบัน

                dataPickerDialog.show();
            }
        });
    }

    private void selectcroptype() {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final Spinner spin = view.findViewById(R.id.EditPlantCropSpinner);
        try {
            GetData getData = new GetData(getActivity());
            getData.execute(Myconstant.getUrlCrop);

            String jsonString = getData.get();
            Log.d("5/Jan CropType", "JSON ==>" + jsonString);
            JSONArray data = new JSONArray(jsonString);

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            for (int i = 0; i < data.length(); i++) {
                JSONObject c = data.getJSONObject(i);

                map = new HashMap<String, String>();
                map.put("cid", c.getString("cid"));
                map.put("crop", c.getString("crop"));
                MyArrList.add(map);
            }
            SimpleAdapter sAdap;
            sAdap = new SimpleAdapter(getActivity(), MyArrList, R.layout.spinner_plancrop,
                    new String[]{"cid", "crop"}, new int[]{R.id.textPlanCidSpinner, R.id.textPlanCropSpinner});
            spin.setAdapter(sAdap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void PlantController() {
        Button button = getView().findViewById(R.id.seletePlant);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    private void add() {
        TextView sdate = getView().findViewById(R.id.pdate);
        String sdateString = sdate.getText().toString().trim();

//        spProvince = getView().findViewById(R.id.spProvincePlant);
//        String pidString = spProvince.getSelectedItem().toString().trim();

        selectPlant(sdateString/*,pidString*/);
    }

    private void selectPlant(String sdateString/*,String pidString*/){
        Call<List<Plant>> call = orderService.getPlant(sdateString);
        call.enqueue(new Callback<List<Plant>>() {
            @Override
            public void onResponse(Call<List<Plant>> call, Response<List<Plant>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    listView.setAdapter(new PlantAdpter1(getActivity(),R.layout.frm_plant_view,list));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            deleteorEditPlant(list.get(position).getNo(),list.get(position).getSno(),
                                    list.get(position).getPdate(),list.get(position).getMid(),list.get(position).getName());
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<List<Plant>> call, Throwable t) {

            }
        });

    }

    //alertให้เลือกลบหรือแก้ไข
    private void deleteorEditPlant(final String noStrings,final String sno,final String pdataString,final String midStrings,final String nameStrings) {

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
                deletePlant(noStrings);
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("แก้ไข", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editPlant(noStrings,sno,pdataString,midStrings,nameStrings);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    //alertให้เลือกจะลบรายการหรือไม่
    private void deletePlant(final String noStrings){
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
                DeletePlant(noStrings);
                dialog.dismiss();
            }
        });
        builder.show();

    }

    //ลบรายการประเภทพืชเพาะปลูก
    private void DeletePlant(String noStrings){

        Myconstant myconstant = new Myconstant();
        try {
            DeletePlant deletePlant = new DeletePlant(getActivity());
            deletePlant.execute(noStrings, myconstant.getUrlDeletePlant());

            if (Boolean.parseBoolean(deletePlant.get())) {
            } else {
                Toast.makeText(getActivity(),"ลบการเพาะปลูก",Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editPlant(final String no,final String sno,final String pdataString,final String midStrings,final String nameStrings) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
        builder.setCancelable(false);
        //กำหนดหัวเเรื้อง
        builder.setTitle("วางการเพาะปลูกใหม่");
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.edit_plant, null);

        //pdateController
        pdateController();

        //selectcroptype
        selectcroptype();

        TextView texPlantMid = view.findViewById(R.id.EditTexPlantMid);
        String strTextShowmid = getActivity().getIntent().getExtras().getString("Mid",midStrings);
        texPlantMid.setText(strTextShowmid);

        TextView texPlantName = view.findViewById(R.id.EditTexPlantName);
        String strTextShowName = getActivity().getIntent().getExtras().getString("Name",nameStrings);
        texPlantName.setText(strTextShowName);

        TextView textPDate = view.findViewById(R.id.EditMyDate);
        String newPDate = getActivity().getIntent().getExtras().getString("pdate",pdataString);
        textPDate.setText(newPDate);


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

                TextView EditPlanCropSpinner = view.findViewById(R.id.textPlanCidSpinner);
                TextView EditMyDate = view.findViewById(R.id.EditMyDate);
                EditText EditAddPlant1 = view.findViewById(R.id.EditAddPlant1);
                EditText EditAddPlant2 = view.findViewById(R.id.EditAddPlant2);
                EditText EditAddPlant3 = view.findViewById(R.id.EditAddPlant3);

                String newEditPlanCropSpinner = EditPlanCropSpinner.getText().toString();
                String newEditMyDate = EditMyDate.getText().toString();
                String newEditArea = Float.toString(Float.parseFloat(EditAddPlant1.getText().toString().trim())
                        + (Float.parseFloat(EditAddPlant2.getText().toString().trim()) * 100
                        + Float.parseFloat(EditAddPlant3.getText().toString().trim())) / 400);

                updatePlant(no,sno,newEditMyDate, newEditPlanCropSpinner, newEditArea);
            }
        });
        builder.show();
    }

    private void updatePlant(String no, String sno, String newEditMyDate, String newEditPlanCropSpinner, String newEditArea) {
        Myconstant myconstant = new Myconstant();

        try {
            EditPlant editPlant = new EditPlant(getActivity());
            editPlant.execute(no, sno,
                    newEditMyDate,
                    newEditPlanCropSpinner,
                    newEditArea,
                    myconstant.getUrlEditPlant());

            Log.d("are", "areb ===>" + editPlant);

            if (Boolean.parseBoolean(editPlant.get())) {

            } else {
                Toast.makeText(getActivity(), "แก้ไขข้อมูลสำเร็จ",
                        Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemlinkUrl) {
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentAdminFragment, new PlantFarmerFragment())
                            .addToBackStack(null)
                            .commit();
                    return false;
                }
            });

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.manu_register, menu);

    }

    private void CreateToolbal() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarPlant);
        ((AdminActivity)getActivity()).setSupportActionBar(toolbar);

        ((AdminActivity)getActivity()).getSupportActionBar().setTitle("ข้อมูลเพาะปลูก");
        //((MainActivity)getActivity()).getSupportActionBar().setSubtitle("ddbdbvd");

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

//    @Override
//    public void onStart() {
//        super.onStart();
//        new DataProvince().execute();
//        new DataAmphur().execute("1");
//        new DataSubDistrict().execute("1","1");
//    }
//
//    public class DataProvince extends AsyncTask<String, Void, String> {
//
//        String result;
//        ArrayList<String> listprovice;
//        ArrayList<String> listprovinceid;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Toast.makeText(getActivity(), "Connecting", Toast.LENGTH_LONG).show();
//            listprovice = new ArrayList<>();
//            listprovinceid = new ArrayList<>();
//        }
//        @Override
//        protected String doInBackground(String... params) {
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .url(Myconstant.getUrlProvince)
//                    .build();
//
//
//            try {
//                com.squareup.okhttp.Response response = client.newCall(request)
//                        .execute();
//
//                result = response.body().string();
//
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            try {
//                JSONArray jsonArray = new JSONArray(result);
//                JSONObject jsonObject = null;
//
//
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    jsonObject = jsonArray.getJSONObject(i);
//                    listprovice.add(jsonObject.getString("thai"));
//                    listprovinceid.add(jsonObject.getString("pid"));
//
//                    Log.d("5/Jan getUrlProvince", "JSON ==>" + result);
//
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            arrProvince.addAll(listprovice);
//            arrProvinceID.addAll(listprovinceid);
//            adpProvince.notifyDataSetChanged();
//
//            spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    if (spProvince.getSelectedItem() != null) {
//                        new DataAmphur().execute(listprovinceid.get(position));
//                        rubIDprovince = Integer.parseInt(listprovinceid.get(position));
//                        arrAmphur.clear();
//                    }
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            });
//
//        }
//    }
//
//    public class DataAmphur extends AsyncTask<String, Void, String> {
//
//        String result;
//        private ArrayList<String> listamphur;
//        private ArrayList<String> listamphurid;
//
//        @Override
//        protected void onPreExecute() {
//            listamphur = new ArrayList<>();
//            listamphurid = new ArrayList<>();
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            RequestBody requestBody = new FormEncodingBuilder()
//                    .add("pid", strings[0])
//                    .build();
//            OkHttpClient okHttpClient = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .url(Myconstant.getUrlAmphur)
//                    .post(requestBody)
//                    .build();
//            try {
//                com.squareup.okhttp.Response response = okHttpClient.newCall(request).execute();
//                result = response.body().string();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            try {
//                JSONArray jsonArray = new JSONArray(result);
//                JSONObject jsonObject = null;
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    jsonObject = jsonArray.getJSONObject(i);
//                    listamphurid.add(jsonObject.getString("did"));
//                    listamphur.add(jsonObject.getString("thai"));
//
//                    Log.d("5/Jan getUrlAmphur", "JSON ==>" + result);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            arrAmphur.addAll(listamphur);
//            arrAmphurID.addAll(listamphurid);
//            adpAmphur.notifyDataSetChanged();
//
//
//            spAmphur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    if (spAmphur.getSelectedItem() != null) {
//                        new DataSubDistrict().execute(listamphurid.get(position));//String.valueOf(rubIDprovince)
//                        rubIDprovince = Integer.parseInt(listamphurid.get(position));
//                        arrSid.clear();
//                        //MyAlert myAlert = new MyAlert(getActivity());
//                        // myAlert.onrmaIDialog("spAmphur","am");
//                    }
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            });
//        }
//    }
//
//    private class DataSubDistrict extends AsyncTask<String, Void, String> {
//
//        String result;
//        private ArrayList<String> listSid;
//        private ArrayList<String> listSidId;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            listSid = new ArrayList<>();
//            listSidId = new ArrayList<>();
//
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            RequestBody requestBody = new FormEncodingBuilder()
//                    .add("did", strings[0])
//                    .build();
//            OkHttpClient okHttpClient = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .url(Myconstant.getUrlSid)
//                    .post(requestBody)
//                    .build();
//            try {
//                com.squareup.okhttp.Response response = okHttpClient.newCall(request).execute();
//                result = response.body().string();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            try {
//                JSONArray jsonArray = new JSONArray(result);
//                JSONObject jsonObject = null;
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    jsonObject = jsonArray.getJSONObject(i);
//                    listSidId.add(jsonObject.getString("sid"));
//                    listSid.add(jsonObject.getString("thai"));
//
//                    Log.d("5/Jan getUrlSid", "JSON ==>" + result);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            arrSid.addAll(listSid);
//            arrSidID.addAll(listSidId);
//            adpSid.notifyDataSetChanged();
//
//
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_view_plant,container, false);
        return view;
    }
}
