package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.EditCropType;
import th.in.nattawut.plancrop.utility.EditRegister;
import th.in.nattawut.plancrop.utility.Myconstant;

public class UserFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //setUpTexeShowMid
        setUpTexeShowMid();

    }
    private void setUpTexeShowMid(){
        TextView texPlanLogin = getView().findViewById(R.id.EditEdtUsername);
        TextView texPlanMid = getView().findViewById(R.id.EditEdtUsernameID);

        String strTextShow = getActivity().getIntent().getExtras().getString("Name");
        texPlanLogin.setText(strTextShow);

        String strTextShowmid = getActivity().getIntent().getExtras().getString("MID");
        texPlanMid.setText(strTextShowmid);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_register, container, false);
        return view;
    }
}
