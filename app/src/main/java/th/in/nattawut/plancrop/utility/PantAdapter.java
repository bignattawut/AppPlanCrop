package th.in.nattawut.plancrop.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import th.in.nattawut.plancrop.R;

public class PantAdapter extends BaseAdapter {

    private Context context;
    private String[] planStrings, typeStrings, areStrings, dateStrings;

    public PantAdapter(Context context,
                       String[] planStrings,
                       String[] typeStrings,
                       String[] areStrings,
                       String[] dateStrings){
        this.context = context;
        this.planStrings = planStrings;
        this.typeStrings = typeStrings;
        this.areStrings = areStrings;
        this.dateStrings = dateStrings;

    }
    @Override
    public int getCount() {
        return planStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.frm_plan_view, parent, false);

        TextView planTextView = view.findViewById(R.id.texPlan);
        TextView typeTextView = view.findViewById(R.id.textTypePlan);
        TextView areTextView = view.findViewById(R.id.textArePlant);
        TextView dataTextView = view.findViewById(R.id.textDataplant);

        planTextView.setText(planStrings[position]);
        typeTextView.setText(typeStrings[position]);
        areTextView.setText(areStrings[position]);
        dataTextView.setText(dateStrings[position]);

        return view;
    }
}
