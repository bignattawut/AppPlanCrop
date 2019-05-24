package th.in.nattawut.plancrop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuann.floatingactionbuttonexpandable.FloatingActionButtonExpandable;

import th.in.nattawut.plancrop.R;

public class TabPlantFragment extends Fragment {

    FloatingActionButton floatingActionButton;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    //Add Plan
        addPlant();
    }


    private void addPlant() {
        FloatingActionButtonExpandable floatingActionButtonPlant = getView().findViewById(R.id.floatingActionButtonPlant);
        floatingActionButtonPlant.setOnClickListener(new View.OnClickListener() {
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

//    private void addPlant() {
//       floatingActionButton = getView().findViewById(R.id.plantAdd);
//       floatingActionButton.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               getActivity()
//                       .getSupportFragmentManager()
//                       .beginTransaction()
//                       .replace(R.id.contentHomeFragment, new PlantFragment())
//                       .addToBackStack(null)
//                       .commit();
//           }
//       });
//    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_tablayout_plant, container, false);
        return view;
    }
}

