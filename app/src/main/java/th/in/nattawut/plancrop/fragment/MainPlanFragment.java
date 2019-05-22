package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.MyPagerAdapter;

public class MainPlanFragment extends Fragment {


    ImageView imageView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabDrawable = {R.drawable.ic_action_tabplan,R.drawable.ic_action_tabplant,R.drawable.ic_action_tabplantpic};

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*
        //PlanView Controller
        planViewController();

        imageView = getView().findViewById(R.id.imvplan_add);
        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.anim);
        imageView.startAnimation(animation);
    }
    private void planViewController() {
        FloatingActionButton floatingActionButton = getView().findViewById(R.id.floatingActionButton5);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
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

        // Create TabLayout
        createTabLayout();

        // Create ViewPager
        createViewPager();
    }

    private void createViewPager() {
        viewPager = getView().findViewById(R.id.viewPager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(
                getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(myPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /*private void createTabLayout() {
        tabLayout = getView().findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

    }*/


    private void createTabLayout() {
        tabLayout = getView().findViewById(R.id.tabLayout);
        String[] strings = new String[]{"วางแผน", "เพาะปลูก", "กิจกรรม"};


        for (int i=0; i<tabDrawable.length; i+=1) {
            tabLayout.addTab(tabLayout.newTab().setText(strings[i]));


        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_tablayout_home, container, false);
        return view;
    }
}
