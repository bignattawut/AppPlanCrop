package th.in.nattawut.plancrop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.APIUtils;
import th.in.nattawut.plancrop.utility.OrderService;
import th.in.nattawut.plancrop.utility.PlantActivity;
import th.in.nattawut.plancrop.utility.PlantPictureViewAapter1;
import th.in.nattawut.plancrop.utility.Site;

public class PlantPictureViewFragment1 extends Fragment {

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;
    OrderService orderService;
    List<PlantActivity> list = new ArrayList<PlantActivity>();

    EditText imgTitle;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        swipeRefreshLayout();

        imgTitle = getView().findViewById(R.id.no);
        showMid();
    }

    private void swipeRefreshLayout() {
        mSwipeRefreshLayout = getView().findViewById(R.id.swiRefreshLayou);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mSwipeRefreshLayout.setRefreshing(false);

                    }
                }, 2);
            }
        });
    }

    public void showMid() {

        TextView sdate = getView().findViewById(R.id.sdate);
        String sdateString = sdate.getText().toString().trim();
        listView = getView().findViewById(R.id.listViewPlantPicture);
        orderService = APIUtils.getService();
        String no = getActivity().getIntent().getExtras().getString("no");
        createListView(no);
    }
    public void createListView(String no) {
        Call<List<PlantActivity>> call = orderService.plantActivity();
        call.enqueue(new Callback<List<PlantActivity>>() {
            @Override
            public void onResponse(Call<List<PlantActivity>> call, Response<List<PlantActivity>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    listView.setAdapter(new PlantPictureViewAapter1(getActivity(),R.layout.frm_plantpicture_view,list));

                }
            }

            @Override
            public void onFailure(Call<List<PlantActivity>> call, Throwable t) {

            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_view_plantpicture, container, false);
        return view;
    }
}
