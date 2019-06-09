package th.in.nattawut.plancrop.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tuann.floatingactionbuttonexpandable.FloatingActionButtonExpandable;

import org.json.JSONArray;
import org.json.JSONObject;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.DeletePlan;
import th.in.nattawut.plancrop.utility.DeletePlant;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.Myconstant;
import th.in.nattawut.plancrop.utility.PantAdapter;
import th.in.nattawut.plancrop.utility.PlatAdpter;

public class PlantViewFragment extends Fragment {

    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //planViewController
        plantViewController();

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

    private void plantViewController() {
        FloatingActionButtonExpandable floatingActionButtonViewPlant = getView().findViewById(R.id.floatingActionButtonViewPlant);
        floatingActionButtonViewPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlantFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void createListView() {
        final ListView listView = getView().findViewById(R.id.listViewPlant);
        Myconstant myconstant = new Myconstant();
        String[] columnStrings = myconstant.getColumnPlantString();

        try {

            GetData getData = new GetData(getActivity());
            getData.execute(myconstant.getUrlselectPlant());


            String jsonString = getData.get();
            Log.d("NatTWut","JSON plant ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            final String[] noStrings = new String[jsonArray.length()];
            final String[] pdataString = new String[jsonArray.length()];
            final String[] cidStrings = new String[jsonArray.length()];
            final String[] yieldStrings = new String[jsonArray.length()];
            final String[] cropStrings = new String[jsonArray.length()];
            final String[] areaStrings = new String[jsonArray.length()];
            final String[] midStrings = new String[jsonArray.length()];
            final String[] nameStrings = new String[jsonArray.length()];
            final String[] snoStrings = new String[jsonArray.length()];
            final String[] latStrings = new String[jsonArray.length()];
            final String[] lonStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                noStrings[i] = jsonObject.getString(columnStrings[0]);
                pdataString[i] = jsonObject.getString(columnStrings[1]);
                cidStrings[i] = jsonObject.getString(columnStrings[2]);
                yieldStrings[i] = jsonObject.getString(columnStrings[3]);
                cropStrings[i] = jsonObject.getString(columnStrings[4]);
                areaStrings[i] = jsonObject.getString(columnStrings[5]);
                midStrings[i] = jsonObject.getString(columnStrings[6]);
                nameStrings[i] = jsonObject.getString(columnStrings[7]);
                snoStrings[i] = jsonObject.getString(columnStrings[8]);
                latStrings[i] = jsonObject.getString(columnStrings[9]);
                lonStrings[i] = jsonObject.getString(columnStrings[10]);
            }

            PlatAdpter platAdpter = new PlatAdpter(getActivity(),
                    noStrings,pdataString,cidStrings,yieldStrings,cropStrings,areaStrings,
                    midStrings,nameStrings,snoStrings,latStrings,lonStrings);
            listView.setAdapter(platAdpter);

            //edit
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    deleteorEditPlant(noStrings[position]);
                    //mSwipeRefreshLayout.setRefreshing(false);
                }
            });
            mSwipeRefreshLayout.setRefreshing(false);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //alertให้เลือกลบหรือแก้ไข
    private void deleteorEditPlant(final String noStrings) {

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
                //editPlan(planStrings,midString);
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
                createListView();
            } else {
                Toast.makeText(getActivity(),"ลบการเพาะปลูก",Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_view_plant,container, false);
        return view;
    }
}
