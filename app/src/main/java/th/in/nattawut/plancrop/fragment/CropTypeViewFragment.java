package th.in.nattawut.plancrop.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.CropTypeViewAapter;
import th.in.nattawut.plancrop.utility.DeleteCropType;
import th.in.nattawut.plancrop.utility.EditCropType;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.Myconstant;

public class CropTypeViewFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create ListView
        createListView();
    }

    private void createListView() {
        ListView listView = getView().findViewById(R.id.listViewCropType);
        Myconstant myconstant = new Myconstant();
        String[] columnStrings = myconstant.getColumnCropTypeString();

        try{
            GetData getData = new GetData(getActivity());
            getData.execute(myconstant.getUrlselectcroptype());

            String jsonString = getData.get();
            Log.d("4กุมภาพันธ์","Json CropType ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);
            final String[] tidString = new String[jsonArray.length()];
            final String[] cropTypeString = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                tidString[i] = jsonObject.getString(columnStrings[0]);
                cropTypeString[i] = jsonObject.getString(columnStrings[1]);
            }
            CropTypeViewAapter cropTypeViewAapter = new CropTypeViewAapter(getActivity(),tidString,cropTypeString);
            listView.setAdapter(cropTypeViewAapter);

            //edit
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    deleteorEditCropType(tidString[position],cropTypeString[position]);
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void deleteorEditCropType(final String tidString, final String cropTypeString ) {

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
                deleteCropType(tidString);
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("แก้ไข", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editCropType(tidString,cropTypeString);
                dialog.dismiss();
            }
        });
        builder.show();
    }
    private void editCropType(final String tidString, final String cropTypeString){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("กำหนดชื่อประเภทใหม่");
        builder.setMessage("ประเภทพืช ==> " + cropTypeString );

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.edit_croptype, null);
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
                EditText editText = view.findViewById(R.id.edtEditCropType);
                String newCropType = editText.getText().toString();
                if (newCropType.isEmpty()) {
                    newCropType = "0";
                }
                updateCropType(tidString,newCropType);

                dialog.dismiss();
            }
        });
        builder.show();
    }
    private void updateCropType(String tidString, String newCropType){

        Myconstant myconstant = new Myconstant();

        try {
            EditCropType editCropType = new EditCropType(getActivity());
            editCropType.execute(tidString,newCropType,
                    myconstant.getUrlEditCropType());

            if (Boolean.parseBoolean(editCropType.get())) {
                createListView();
            }else {
                Toast.makeText(getActivity(),"แก้ไขข้อมูลสำเร็จ",
                        Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void deleteCropType(String tidString){

        Myconstant myconstant = new Myconstant();
        try {
            DeleteCropType deleteCropType = new DeleteCropType(getActivity());
            deleteCropType.execute(tidString, myconstant.getUrlDeleteCropType());

            if (Boolean.parseBoolean(deleteCropType.get())) {
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
        View view = inflater.inflate(R.layout.frm_view_croptype,container, false);
        return view;
    }
}
