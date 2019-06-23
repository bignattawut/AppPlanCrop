package th.in.nattawut.plancrop.utility;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import th.in.nattawut.plancrop.R;



public class PlanFarmerAdapter extends ArrayAdapter<PlanFarmer> {

    private Context context;
    private List<PlanFarmer> planFarmers;

    public PlanFarmerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<PlanFarmer> objects) {
        super(context, resource, objects);
        this.context = context;
        this.planFarmers = objects;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.frm_planfarmer_view, parent, false);


        //TextView textPlanFarmerNo = view.findViewById(R.id.textPlanFarmerNo);
        TextView textPlanFarmer = view.findViewById(R.id.textPlanFarmer);
        TextView textPlanFarmerCid = view.findViewById(R.id.textPlanFarmerCid);
        TextView textPlanFarmerCrop = view.findViewById(R.id.textPlanFarmerCrop);
        TextView textPlanArea = view.findViewById(R.id.textPlanArea);


        //textPlanFarmerNo.setText(planFarmers.get(position).getNo());
        textPlanFarmer.setText(planFarmers.get(position).getPdate());
        textPlanFarmerCid.setText(planFarmers.get(position).getCid());
        textPlanFarmerCrop.setText(planFarmers.get(position).getCrop());
        textPlanArea.setText(planFarmers.get(position).getArea());



        return view;
    }
}
