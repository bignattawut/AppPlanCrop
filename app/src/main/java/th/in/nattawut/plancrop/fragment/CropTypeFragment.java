package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import th.in.nattawut.plancrop.AdminActivity;
import th.in.nattawut.plancrop.HomeActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.AddCropType;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;

public class CropTypeFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create Toolbal
        createToolbal();

        //CropTypeController
        croptypeController();

    }


    private void croptypeController() {
        Button button = getView().findViewById(R.id.btnCropType);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcroptype();
            }
        });
    }

    private void addcroptype() {
        EditText edtCroptype = getView().findViewById(R.id.edtCroptype);
        String croptypeString = edtCroptype.getText().toString().trim();

        if (croptypeString.isEmpty()){
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.onrmaIDialog("สวัสดี", "กรุณากรอกข้อมูล");
        }else {
            try {
                Myconstant myconstant = new Myconstant();
                AddCropType addCropType = new AddCropType(getActivity());
                addCropType.execute(croptypeString,
                        myconstant.getUrlAddCropType());

                String result = addCropType.get();
                Log.d("CropType", "result ==> " + result);
                if (Boolean.parseBoolean(result)) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(), "เพิ่มข้อมูลเรียบร้อย", Toast.LENGTH_LONG).show();
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentAdminFragment, new CropTypeViewFragment())
                            .addToBackStack(null)
                            .commit();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    //toolbal
    private void createToolbal() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarCropType);
        ((AdminActivity)getActivity()).setSupportActionBar(toolbar);

        ((AdminActivity)getActivity()).getSupportActionBar().setTitle("ประเภทพืชเพาะปลูก");
        ((AdminActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AdminActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_croptype, container,false);
        return view;
    }
}
