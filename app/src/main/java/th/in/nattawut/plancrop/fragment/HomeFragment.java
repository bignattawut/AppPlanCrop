package th.in.nattawut.plancrop.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import th.in.nattawut.plancrop.MainActivity;
import th.in.nattawut.plancrop.R;

public class HomeFragment extends Fragment {

    TextView scrollingText;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Register Controller
        registerController();

        //createToolbar();

//        TextView textView = getView().findViewById(R.id.textLogin);
//        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translate);
//        textView.startAnimation(animation);

        scrollingText = getView().findViewById(R.id.textLogin);
        scrollingText.setSelected(true);


    }



    private void registerController() {
        ImageButton imageView = getView().findViewById(R.id.ImageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkStatus();
            }
        });
    }


    private void checkStatus() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("กรุณาเลือกการทำงาน");
        builder.setMessage("ต้องการเข้าสู่แอพพลิเคชัน ?");
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("เข้าสู่ระบบ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new MainFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        builder.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_main1, container, false);
        return view;
    }
}
