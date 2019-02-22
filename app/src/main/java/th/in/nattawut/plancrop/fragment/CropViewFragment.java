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
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import th.in.nattawut.plancrop.HomeActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.CropViewAdpter;
import th.in.nattawut.plancrop.utility.DeleteCrop;
import th.in.nattawut.plancrop.utility.DeleteCropType;
import th.in.nattawut.plancrop.utility.EditCrop;
import th.in.nattawut.plancrop.utility.EditCropType;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;

public class CropViewFragment extends Fragment {

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create ListView
        createListView();

        //Create Toolbal
        createToolbal();

        //Swipe Refresh Layout
        swipeRefreshLayout();


    }
    private void swipeRefreshLayout() {
        mSwipeRefreshLayout = getView().findViewById(R.id.swiRefreshLayouCrop);
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
        //final ListView listView = getView().findViewById(R.id.listViewCrop);
        listView = getView().findViewById(R.id.listViewCrop);
        Myconstant myconstant = new Myconstant();
        String[] columnStrings = myconstant.getColumnCropString();
        try{
            GetData getData = new GetData(getActivity());
            getData.execute(myconstant.getUrlselectCrop());

            String jsonString = getData.get();
            Log.d("4กุมภาพันธ์","Json Crop ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            final String[] cidString = new String[jsonArray.length()];
            final String[] cropString = new String[jsonArray.length()];
            final String[] tidString = new String[jsonArray.length()];
            final String[] croptypeString = new String[jsonArray.length()];
            final String[] beginharvestString = new String[jsonArray.length()];
            final String[] harvestperiodString = new String[jsonArray.length()];
            final String[] yield = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                cidString[i] = jsonObject.getString(columnStrings[0]);
                cropString[i] = jsonObject.getString(columnStrings[1]);
                tidString[i] = jsonObject.getString(columnStrings[2]);
                croptypeString[i] = jsonObject.getString(columnStrings[3]);
                beginharvestString[i] = jsonObject.getString(columnStrings[4]);
                harvestperiodString[i] = jsonObject.getString(columnStrings[5]);
                yield[i] = jsonObject.getString(columnStrings[6]);
            }

            CropViewAdpter cropViewAdpter = new CropViewAdpter(getActivity(),
                    cidString,cropString,tidString,croptypeString,beginharvestString,harvestperiodString,yield);
            listView.setAdapter(cropViewAdpter);
            //edit
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    deleteorEditCropType(cidString[position]/*,
                            cropString[position],
                            tidString[position],
                            beginharvestString[position],
                            harvestperiodString[position],
                            yield[position]*/);
                }
            });
            mSwipeRefreshLayout.setRefreshing(false);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //alertให้เลือกลบหรือแก้ไข
    private void deleteorEditCropType(final String cidString/*,
                                      final String cropString,
                                      final String tidString,
                                      final String beginharvestString,
                                      final String harvestperiodString,
                                      final String yield*/) {

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
                deleteCrop(cidString);
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("แก้ไข", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //editCrop(cidString, cropString,tidString,beginharvestString,harvestperiodString,yield);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    //alertให้เลือกจะลบรายการหรือไม่
    private void deleteCrop(final String cidString){
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
                editDeleteCrop(cidString);
                dialog.dismiss();
            }
        });
        builder.show();
    }
    /*
    //แก้ไขพืชเพาะปลูก
    private void editCrop(final String cidString,
                          final String cropString,
                          final String tidString,
                          final String beginharvestString,
                          final String harvestperiodString,
                          final String yield){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("กำหนดชื่อใหม่");

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.edit_crop, null);
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

                //พืช
                EditText edtEditCropName = view.findViewById(R.id.edtEditCropName);
                String newCropName = edtEditCropName.getText().toString();
                //ประเภท
                EditText EditcropType = view.findViewById(R.id.EditcropType);
                String newcropType = EditcropType.getText().toString();
                //เริ่มต้นการเก็บเกี่ยว
                EditText edtEditBeginHarvest = view.findViewById(R.id.edtEditBeginHarvest);
                String newBeginHarvest = edtEditBeginHarvest.getText().toString();
                //ระยะเวลา
                EditText edtEditHarvestPeriod = view.findViewById(R.id.edtEditHarvestPeriod);
                String newHarvestPeriod = edtEditHarvestPeriod.getText().toString();
                //edtEditYield
                EditText edtEditYield = view.findViewById(R.id.edtEditYield);
                String newYield = edtEditYield.getText().toString();

                if (newCropName.isEmpty() || newcropType.isEmpty() || newBeginHarvest.isEmpty() || newHarvestPeriod.isEmpty() || newYield.isEmpty()) {
                    //newCropName = "0";

                }
                updateCrop(cidString,newCropName,
                        cropString,newcropType,
                        tidString,newBeginHarvest,
                        beginharvestString,newHarvestPeriod,
                        harvestperiodString,newYield,
                        yield);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    //updateข้อมูลพืชเพาะปลูก
    private void updateCrop(String cidString, String newCropName,
                            String cropString, String newcropType,
                            String tidString, String newBeginHarvest,
                            String beginharvestString, String newHarvestPeriod,
                            String harvestperiodString, String newYield,
                            String yield){
        Myconstant myconstant = new Myconstant();

        try {
            EditCrop editCrop = new EditCrop(getActivity());
            editCrop.execute(cidString,newCropName,
                    cropString,newcropType,tidString,newBeginHarvest,
                    beginharvestString,newHarvestPeriod,harvestperiodString,newYield,yield,
                    myconstant.getUrlEditCrop());

            if (Boolean.parseBoolean(editCrop.get())) {
                createListView();
            } else {
                Toast.makeText(getActivity(),"แก้ไขข้อมูลสำเร็จ",Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    //ลบรายการประเภทพืชเพาะปลูก
    private void editDeleteCrop(String tidString){

        Myconstant myconstant = new Myconstant();
        try {
            DeleteCrop deleteCrop = new DeleteCrop(getActivity());
            deleteCrop.execute(tidString, myconstant.getUrlDeleteCrop());

            if (Boolean.parseBoolean(deleteCrop.get())) {
                createListView();
            } else {
                Toast.makeText(getActivity(),"ลบรายการพืชเพาะปลูก",Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemupload) {
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentHomeFragment, new CropFragment())
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

        inflater.inflate(R.menu.manu_register, menu);

    }

    private void createToolbal() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarCropView);
        ((HomeActivity)getActivity()).setSupportActionBar(toolbar);

        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("แสดงรายการพืช");
        //((MainActivity)getActivity()).getSupportActionBar().setSubtitle("ddbdbvd");

        ((HomeActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((HomeActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        View view = inflater.inflate(R.layout.frm_view_crop,container, false);
        return view;
    }
}

