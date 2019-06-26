package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import th.in.nattawut.plancrop.R;

public class AdminFrament extends Fragment {


    CardView farmer;
    CardView register;
    CardView crop;
    CardView cropType;
    CardView plan;
    CardView plant;
    CardView Site;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //CropType Controller
        cropTypeController();

        //Crop Controller
        cropController();

        //Register Controller
        RegisterController();

        //Farmer Controller
        farmerController();

        //Plan Controller
        planController();

        //Plant Controller
        plantController();

        //Site Controller
        siteController();

        //Order Controller
        orderController();


    }


    private void farmerController() {
        farmer = getView().findViewById(R.id.imageViewFarmer);
        farmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new FarmerViewFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void RegisterController() {
        register = getView().findViewById(R.id.imageViewRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new RegisterViewFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


    private void cropController() {
        crop = getView().findViewById(R.id.imageViewCrop);
        crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new CropViewFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void cropTypeController() {
        cropType = getView().findViewById(R.id.imageViewCropTypr);
        cropType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new CropTypeViewFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void planController() {
        plan = getView().findViewById(R.id.imageViewPlan);
        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlanViewFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void plantController() {
        plant = getView().findViewById(R.id.imageViewPlant);
        plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlantViewFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void siteController() {
        Site = getView().findViewById(R.id.imageViewSite);
        Site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new SiteFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
    private void orderController() {
        Site = getView().findViewById(R.id.imageViewOrder);
        Site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new OrderViewRePortFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin2, container, false);
        return view;
    }
}

