package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.MyPagerAdapter;
import th.in.nattawut.plancrop.utility.PlantPicViewFragment2;
import th.in.nattawut.plancrop.utility.ScrollHandler;

public class MainPlanFragment extends Fragment {


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Add Plan
        addPlan();

        addPlant();

        addPlanPic();

        addOrder();

    }
    private void addPlan() {
        CardView cardViewPlanHome = getView().findViewById(R.id.CardViewPlanHome);
        cardViewPlanHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlanFarmerViewFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
    private void addPlant() {
        CardView cardViewPlanHome = getView().findViewById(R.id.CardViewPlantHome);
        cardViewPlanHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlantFarmerViewFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
    private void addPlanPic() {
        CardView cardViewPlanHome = getView().findViewById(R.id.CardViewPicHome);
        cardViewPlanHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlantPicViewFragment2())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
    private void addOrder() {
        CardView cardViewPlanHome = getView().findViewById(R.id.CardViewOrderHome);
        cardViewPlanHome.setOnClickListener(new View.OnClickListener() {
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
        View view = inflater.inflate(R.layout.frm_tablayout_home, container, false);
        return view;
    }
}
