package th.in.nattawut.plancrop.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.APIUtils;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.Myconstant;
import th.in.nattawut.plancrop.utility.OrderService;
import th.in.nattawut.plancrop.utility.PlantResult;
import th.in.nattawut.plancrop.utility.PlantResultAdpter;

public class PlantResultViewFragment extends Fragment {

    ListView listView;
    OrderService orderService;
    List<PlantResult> list = new ArrayList<PlantResult>();

    ImageView selctDate;
    TextView date;
    DatePickerDialog dataPickerDialog;
    Calendar calendar;
    SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        listView = getView().findViewById(R.id.listViewPlantResult);
        orderService = APIUtils.getService();


        edateController();

        pdateController();

        SwipeRefreshLayout();

        nameSpinner();

        cropTypeSpinner();

        planFarmerSpinner();
    }

    private void SwipeRefreshLayout() {
        mSwipeRefreshLayout = getView().findViewById(R.id.swiRefreshLayoutPlantResult);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cropController();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void edateController() {
        final TextView sdate = getActivity().findViewById(R.id.sdate);
        ImageView selctDate = getActivity().findViewById(R.id.imageViewDatesdate);
        selctDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                //final Date cha = calendar.getTime();

                dataPickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int y, int m, int d) {
                                //date.setText(y + "/" + (m + 1) + "/" + d);
                                sdate.setText(y + "/" + (m + 1) + "/" + d);
                                //DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.UK);

                            }
                        }, year,month,day );
                //dataPickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dataPickerDialog.show();
            }
        });
    }

    private void pdateController() {
        final TextView edate = getActivity().findViewById(R.id.edate);
        ImageView selctDate = getActivity().findViewById(R.id.imageViewDateedate);
        selctDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                //final Date cha = calendar.getTime();

                dataPickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int y, int m, int d) {
                                //date.setText(y + "/" + (m + 1) + "/" + d);
                                edate.setText(y + "/" + (m + 1) + "/" + d);
                                //DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.UK);

                            }
                        }, year,month,day);
                //dataPickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dataPickerDialog.show();
            }
        });
    }

    private void nameSpinner() {
        if (android.os.Build.VERSION.SDK_INT > 9) { //setup policy เเพื่อมือถือที่มีประปฏิบัติการสูงกว่านีจะไม่สามารถconnectกับโปรโตรคอลได้
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final Spinner spin = getView().findViewById(R.id.midSpinner);

        try {

            Myconstant myconstant = new Myconstant();


            GetData getData = new GetData(getActivity());
            getData.execute(myconstant.getUrlSiteFarmer());

            String jsonString = getData.get();
            Log.d("5/Jan PlanCropSpinner", "JSON ==>" + jsonString);
            JSONArray data = new JSONArray(jsonString);

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            for (int i = 0; i < data.length(); i++) {
                JSONObject c = data.getJSONObject(i);

                map = new HashMap<String, String>();
                map.put("mid", c.getString("mid"));
                map.put("name", c.getString("name"));
                MyArrList.add(map);
            }
            SimpleAdapter sAdap;
            sAdap = new SimpleAdapter(getActivity(), MyArrList, R.layout.spinner_sitename,
                    new String[]{"mid", "name"}, new int[]{R.id.textMId, R.id.textName});
            spin.setAdapter(sAdap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cropTypeSpinner(){
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final Spinner spin = getView().findViewById(R.id.croptypespinner);
        try {
            Myconstant myconstant = new Myconstant();
            GetData getData = new GetData(getActivity());
            getData.execute(myconstant.getUrlCropType());

            String jsonString = getData.get();
            Log.d("5/Jan CropType", "JSON ==>" + jsonString);
            JSONArray data = new JSONArray(jsonString);

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            for(int i = 0; i < data.length(); i++){
                JSONObject c = data.getJSONObject(i);

                map = new HashMap<String, String>();
                map.put("TID", c.getString("TID"));
                map.put("croptype", c.getString("croptype"));
                MyArrList.add(map);
            }
            SimpleAdapter sAdap;
            sAdap = new SimpleAdapter(getActivity(), MyArrList, R.layout.spinner_crop,
                    new String[] {"TID", "croptype"}, new int[] {R.id.textTID, R.id.textCropType});
            spin.setAdapter(sAdap);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//
    private void planFarmerSpinner() {
        if (android.os.Build.VERSION.SDK_INT > 9) { //setup policy เเพื่อมือถือที่มีประปฏิบัติการสูงกว่านีจะไม่สามารถconnectกับโปรโตรคอลได้
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final Spinner spin = getView().findViewById(R.id.crop);
        //final MaterialSpinner spin = getView().findViewById(R.id.crop);
        try {
            Myconstant myconstant = new Myconstant();
            GetData getData = new GetData(getActivity());
            getData.execute(myconstant.getUrlCrop());

            String jsonString = getData.get();
            Log.d("5/Jan PlanCropSpinner", "JSON ==>" + jsonString);
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

    private void cropController() {
        Button button = getView().findViewById(R.id.seletePlantResult);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();

            }
        });
    }

    private void add() {

        TextView sdate = getView().findViewById(R.id.sdate);
        TextView edate = getView().findViewById(R.id.edate);
        TextView nameSpinner = getView().findViewById(R.id.textMId);
        TextView textTypeSpinner = getView().findViewById(R.id.textTID);
        TextView textCropSpinner = getView().findViewById(R.id.textPlanCidSpinner);

        String sdateString = sdate.getText().toString().trim();
        String edateString = edate.getText().toString().trim();
        String nameString = nameSpinner.getText().toString().trim();
        String croptypeString = textTypeSpinner.getText().toString().trim();
        String cropString = textCropSpinner.getText().toString().trim();
        selectPlantReportall(sdateString,edateString,nameString,croptypeString,cropString);
    }

    private void selectPlantReportall(String sdateString, String edateString,String nameString, String croptypeString, String cropString) {
        Call<List<PlantResult>> call = orderService.getPlantResult("81","","",sdateString,edateString,nameString,croptypeString,cropString);
        call.enqueue(new Callback<List<PlantResult>>() {
            @Override
            public void onResponse(Call<List<PlantResult>> call, Response<List<PlantResult>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    listView.setAdapter(new PlantResultAdpter(getActivity(),R.layout.frm_plantresult_view,list));
                }
            }
            @Override
            public void onFailure(Call<List<PlantResult>> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_view_plantresult, container, false);
        return view;
    }
}
