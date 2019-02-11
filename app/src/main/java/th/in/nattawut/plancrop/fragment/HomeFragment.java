package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import th.in.nattawut.plancrop.R;

public class HomeFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Plan Controller
        planController();

        //Picture Controller
        pictureController();

        //Plant Controller
        plantController();

        //Register Controller
        registerController();

        //PlanView Controller
        planViewController();

    }

    private void planViewController() {
        ImageView imageView = getView().findViewById(R.id.imageViewListPlan);
        imageView.setOnClickListener(new View.OnClickListener() {
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

    private void registerController() {
        ImageView imageView = getView().findViewById(R.id.imageViewMember);
        imageView.setOnClickListener(new View.OnClickListener() {
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

    private void plantController() {
        ImageView imageView = getView().findViewById(R.id.imageViewPlant);
        imageView.setOnClickListener(new View.OnClickListener() {
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
    }

    private void pictureController() {
        ImageView imageView = getView().findViewById(R.id.imageViewPlantPicture);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlantPicture())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
    private void planController(){
        ImageView imageView = getView().findViewById(R.id.imageViewPlan);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlanFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_main, container, false);
        return view;
    }
}
