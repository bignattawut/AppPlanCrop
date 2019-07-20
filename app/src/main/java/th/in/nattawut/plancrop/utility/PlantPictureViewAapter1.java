package th.in.nattawut.plancrop.utility;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import th.in.nattawut.plancrop.R;

public class PlantPictureViewAapter1 extends ArrayAdapter<PlantActivity> {

    private Context context;
    private List<PlantActivity> plantActivities;

    public PlantPictureViewAapter1(@NonNull Context context, @LayoutRes int resource, @NonNull List<PlantActivity> objects) {
        super(context, resource, objects);
        this.context = context;
        this.plantActivities = objects;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.frm_plantpicture_view, parent, false);

        TextView textPicNo = view.findViewById(R.id.textPicNo);
        TextView textData = view.findViewById(R.id.textData);
        TextView textDescription = view.findViewById(R.id.textDescription);


        textPicNo.setText(plantActivities.get(position).getPicno());
        textData.setText(plantActivities.get(position).getPdate());
        textDescription.setText(plantActivities.get(position).getDescription());

        return view;
    }
}
