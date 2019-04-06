package th.in.nattawut.plancrop.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import th.in.nattawut.plancrop.MainActivity;
import th.in.nattawut.plancrop.R;

public class HomeFragment extends Fragment {


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Register Controller
        registerController();

        //createToolbar();

        TextView textView = getView().findViewById(R.id.textLogin);
        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translate);
        textView.startAnimation(animation);

    }
    /*
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            if (item.getItemId() == R.id.itemlogin) {
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        getActivity()
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentMainFragment, new MainFragment())
                                .addToBackStack(null)
                                .commit();
                        return false;
                    }
                });

                return true;
            }
            return super.onOptionsItemSelected(item);
        }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_login, menu);
    }
        private void createToolbar() {
            Toolbar toolbar = getView().findViewById(R.id.toolbarmain);
            ((MainActivity) getActivity()).setSupportActionBar(toolbar);

            ((MainActivity) getActivity()).getSupportActionBar().setTitle("Plan & เกษตร");
            //((MainActivity)getActivity()).getSupportActionBar().setSubtitle("ddbdbvd");

            ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentMainFragment, new MainFragment())
                            .addToBackStack(null)
                            .commit();

                }
            });
            setHasOptionsMenu(true);

        }*/

    private void registerController() {
        ImageView imageView = getView().findViewById(R.id.ImageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new MainFragment())
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
