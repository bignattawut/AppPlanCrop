package th.in.nattawut.plancrop.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import th.in.nattawut.plancrop.R;

public class PlantPictureViewAapter extends BaseAdapter {

    private Context context;
    private String[] SCodeString, imageStrings;

    public PlantPictureViewAapter(Context context,
                                  String[] imageStrings,
                                  String[] SCodeString) {
        this.context = context;
        this.SCodeString = SCodeString;
        this.imageStrings = imageStrings;
    }


    @Override
    public int getCount() {
        return SCodeString.length;
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
        View view = layoutInflater.inflate(R.layout.frm_plantpicture_view, parent, false);

        TextView textPicNo = view.findViewById(R.id.textPicNo);
        ImageView imageView = view.findViewById(R.id.imvPlantPic);


        textPicNo.setText(SCodeString[position]);
//        Picasso.get().load(imageStrings[position]).into(imageView);
//
        Picasso.get()
                .load(imageStrings[position])
                .resize(1280,720) //ย่อขนาดรูป
                .into(imageView);




        return view;
    }
}
