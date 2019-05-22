package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import th.in.nattawut.plancrop.R;

public class AdminFrament extends Fragment {


    CardView farmer;
    CardView register;
    CardView crop;
    CardView cropType;

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



    }
    /*//layout admin
    private void farmerController() {
        TextView textView = getView().findViewById(R.id.imageViewFarmer);
        textView.setOnClickListener(new View.OnClickListener() {
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
        TextView textView = getView().findViewById(R.id.imageViewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
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
        TextView textView = getView().findViewById(R.id.imageViewCrop);
        textView.setOnClickListener(new View.OnClickListener() {
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
        TextView textView = getView().findViewById(R.id.imageViewCropTypr);
        textView.setOnClickListener(new View.OnClickListener() {
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

    private void planPictureController() {
        TextView textView = getView().findViewById(R.id.imageViewSite);
        textView.setOnClickListener(new View.OnClickListener() {
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
    */



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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin3, container, false);
        return view;
    }
}

