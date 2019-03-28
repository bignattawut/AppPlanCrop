package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import th.in.nattawut.plancrop.R;

public class AdminFrament extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //CropType Controller
        cropTypeController();

        //Crop Controller
        cropController();

        //PlanPicture Controller
        planPictureController();

    }

    private void cropController() {
        ImageView imageView = getView().findViewById(R.id.imageViewCrop);
        imageView.setOnClickListener(new View.OnClickListener() {
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
        ImageView imageView = getView().findViewById(R.id.imageViewCropTypr);
        imageView.setOnClickListener(new View.OnClickListener() {
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
        ImageView imageView = getView().findViewById(R.id.imageViewSite);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlantPictureFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin, container, false);
        return view;
    }
}

