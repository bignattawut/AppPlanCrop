package th.in.nattawut.plancrop.utility;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import th.in.nattawut.plancrop.R;

public class FarmerViewAdpter extends BaseAdapter {

    private Context context;
    private String[] nameString,vidString,phonString,emailString;

    public FarmerViewAdpter(Context context,
                              String[] nameString,
                              String[] vidString,
                              String[] phonString,
                              String[] emailString) {
        this.context = context;
        this.nameString = nameString;
        this.vidString = vidString;
        this.phonString = phonString;
        this.emailString = emailString;

    }


    @Override
    public int getCount() {
        return nameString.length;
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
        View view = layoutInflater.inflate(R.layout.frm_farmer_view,parent,false);

        TextView name = view.findViewById(R.id.textNamef);
        TextView vid = view.findViewById(R.id.textVidf);
        TextView phon = view.findViewById(R.id.textPhonef);
        TextView email = view.findViewById(R.id.textemailf);


        name.setText(nameString[position]);
        vid.setText(vidString[position]);
        phon.setText(phonString[position]);
        email.setText(emailString[position]);

        return view;
    }

}