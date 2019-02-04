package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import th.in.nattawut.plancrop.R;

public class HomeFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
/*
        //Plan Controller
        planController();

        //PlanViewController
        planViewController();

        //CropController
        cropController();

        PlantController

        Button btnPlant = getView().findViewById(R.id.btnPlant);
        btnPlant.setOnClickListener(new View.OnClickListener() {
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

    private void cropController() {
        Button btnCrop = getView().findViewById(R.id.btnCrop);
        btnCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment,new CropFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void planViewController() {
        Button buttonplan = getView().findViewById(R.id.btnPlanView);
        buttonplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlanViewFragment())
                        .commit();
            }
        });
    }
    private void planController() {
        Button button = getView().findViewById(R.id.btnPlanmain);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlanFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });*/

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_list_drawer, container, false);
        return view;
    }
}
