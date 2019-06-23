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

public class memberFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ordertController();

        selectOrDerController();

    }
    private void ordertController() {
        CardView Requirement = getView().findViewById(R.id.CardViewAddOrder);
        Requirement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new OrderViewFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
    private void selectOrDerController() {
        CardView Requirement = getView().findViewById(R.id.CardViewSelectOrder);
        Requirement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new OrderViewFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.member, container, false);
        return view;
    }
}
