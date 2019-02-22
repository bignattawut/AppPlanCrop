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

        //Site Controller
        siteController();
    }
    /*
    private void cropController() {
        ImageView imageView = getView().findViewById(R.id.imageViewSite);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiteFragment cropFragment = new SiteFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.contentHomeFragment,cropFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }*/
    private void  siteController() {
        ImageView imageView = getView().findViewById(R.id.imageViewSite);
        imageView.setOnClickListener(new View.OnClickListener() {
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

    private void cropController() {
        ImageView imageView = getView().findViewById(R.id.imageViewCrop);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new CropFragment())
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
                        .replace(R.id.contentHomeFragment, new CropTypeFragment())
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

