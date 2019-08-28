package th.in.nattawut.plancrop.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.APIUtils;
import th.in.nattawut.plancrop.utility.OrderService;
import th.in.nattawut.plancrop.utility.PlanResult;
import th.in.nattawut.plancrop.utility.PlanResultAdpter;

public class PlanResultViewFragment extends Fragment {


    ListView listView;
    OrderService orderService;
    List<PlanResult> list = new ArrayList<PlanResult>();

    ImageView selctDate;
    TextView date;
    DatePickerDialog dataPickerDialog;
    Calendar calendar;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = getView().findViewById(R.id.listViewPlanResult);
        orderService = APIUtils.getService();

        pdateController();

        cropController();
    }

    private void pdateController() {
        final TextView edate = getActivity().findViewById(R.id.sdate);
        ImageView selctDate = getActivity().findViewById(R.id.imageViewDatesdate);
        selctDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                //final Date cha = calendar.getTime();

                dataPickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int y, int m, int d) {
                                //date.setText(y + "/" + (m + 1) + "/" + d);
                                edate.setText(y + "/" + (m + 1) + "/" + d);
                                //DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.UK);

                            }
                        },year,month,day);
                //dataPickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dataPickerDialog.show();
            }
        });
    }

    private void cropController() {
        Button button = getView().findViewById(R.id.selete1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();

            }
        });
    }

    private void add() {
        TextView sdate = getView().findViewById(R.id.sdate);
        String sdateString = sdate.getText().toString().trim();

        selectPlanReportall(sdateString);
    }

    private void selectPlanReportall(String sdateString) {
        Call<List<PlanResult>> call = orderService.getPlanReport(sdateString);
        call.enqueue(new Callback<List<PlanResult>>() {
            @Override
            public void onResponse(Call<List<PlanResult>> call, Response<List<PlanResult>> response) {
                list = response.body();
                listView.setAdapter(new PlanResultAdpter(getActivity(),R.layout.frm_planresult_view,list));
            }

            @Override
            public void onFailure(Call<List<PlanResult>> call, Throwable t) {

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_view_planresult, container, false);
        return view;
    }
}
