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
                        .replace(R.id.contentHomeFragment, new PlantPicreFragment3())
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
//    ImageView imageView;
//    private TabLayout tabLayout;
//    private ViewPager viewPager;
//    private int[] tabIcons = {
//            R.drawable.ic_control_point_black_24dp,
//            R.drawable.ic_spatab_black_24dp,
//            R.drawable.ic_camera_black_24dp};
//
//
//    private Fragment fragment;
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    fragment = new TabPlanFragment();
//                    loadFragment(fragment);
//                    return true;
//                case R.id.navigation_dashboard:
//                    fragment = new PlanFarmerViewFragment();
//                    loadFragment(fragment);
//                    return true;
//                case R.id.navigation_notifications:
//                    fragment = new PlantPictureViewFragment();
//                    loadFragment(fragment);
//                    return true;
//            }
//            return false;
//        }
//    };

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);

//        BottomNavigationView navigation = getView().findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
//        //Load the HomeFragment when app is loaded
//        fragment = new TabPlanFragment();
//        loadFragment(fragment);
//
//        //Hide the Bottom Navigation when the page is scrolled
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
//        layoutParams.setBehavior(new ScrollHandler());
//
//    }
//    private void loadFragment(Fragment fragment) {
//
//        if (fragment != null) {
//            getActivity().getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.frame_container,fragment)
//                    .commit();
//        }
//    }
//
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.frm_plan_add, container, false);
//        return view;
//    }
//
