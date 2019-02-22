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
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.DeleteCropType;
import th.in.nattawut.plancrop.utility.DeletePlan;
import th.in.nattawut.plancrop.utility.GetData;
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
        ListView listView = getView().findViewById(R.id.listViewPlan);
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
            String[] areStrings = new String[jsonArray.length()];
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
    private void deleteorEditCropPlan(final String planStrings, final String midString ) {

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
                //editPlan(NoStrings,planStrings);
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
