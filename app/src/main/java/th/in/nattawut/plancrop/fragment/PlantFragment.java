package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import th.in.nattawut.plancrop.HomeActivity;
import th.in.nattawut.plancrop.R;

public class PlantFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create Toolbal
        createToolbal();
    }

    private void createToolbal() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarPlant);
        ((HomeActivity)getActivity()).setSupportActionBar(toolbar);

        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("แปลงเพาะปลูก");
        ((HomeActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((HomeActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        View view = inflater.inflate(R.layout.frm_plant, container, false);
        return view;
    }
}
