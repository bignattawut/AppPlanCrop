package th.in.nattawut.plancrop.utility;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import th.in.nattawut.plancrop.R;

public class PlantResultSiteAdpter extends ArrayAdapter<PlantResultSite> {


    private Context context;
    private List<PlantResultSite> plantResultSites;

    public PlantResultSiteAdpter(@NonNull Context context, @LayoutRes int resource, @NonNull List<PlantResultSite> objects) {
        super(context, resource, objects);
        this.context = context;
        this.plantResultSites = objects;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.frm_plantresultmap_view,parent,false);



        TextView mid = view.findViewById(R.id.mid);
        TextView vid = view.findViewById(R.id.vidThai);
        TextView tel = view.findViewById(R.id.tel);
        TextView crop = view.findViewById(R.id.crop);
        TextView yield = view.findViewById(R.id.yield);

        mid.setText(plantResultSites.get(position).getName());
        vid.setText(plantResultSites.get(position).getThai());
        tel.setText(plantResultSites.get(position).getTel());
        crop.setText(plantResultSites.get(position).getCrop());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String s1 = decimalFormat.format(plantResultSites.get(position).getYield());
        yield.setText(s1);




        return view;
    }
}