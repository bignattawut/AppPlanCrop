package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.tuann.floatingactionbuttonexpandable.FloatingActionButtonExpandable;

import th.in.nattawut.plancrop.R;

public class TabPlanFragment extends Fragment {

    ImageView imageView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Add Plan
        addPlan();
    }

    private void addPlan() {
        FloatingActionButtonExpandable floatingActionButtonPlan = getView().findViewById(R.id.floatingActionButtonPlan);
        floatingActionButtonPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                getActivity()
//                        .getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.contentHomeFragment, new PlanFragment())
//                        .addToBackStack(null)
//                        .commit();
            }
        });
    }



//    private void addPlan() {
//        FloatingActionButton floatingActionButton = getView().findViewById(R.id.floatingActionButton2);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                /*getActivity()
//                        .getSupportFragmentManager()
//                        .beginTransaction()
//                        .add(R.id.contentHomeFragment, new PlanFragment())
//                        .addToBackStack(null)
//                        .commit();*/
//
//
//                PlanFragment planFragment = new PlanFragment();
//                FragmentTransaction transaction = getActivity()
//                        .getSupportFragmentManager()
//                        .beginTransaction();
//                transaction.replace(R.id.contentHomeFragment,planFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//
//            }
//        });
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_tablayout_plan, container, false);
        return view;
    }
}

