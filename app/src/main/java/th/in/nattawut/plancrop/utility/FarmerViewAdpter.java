package th.in.nattawut.plancrop.utility;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import th.in.nattawut.plancrop.R;

public class FarmerViewAdpter extends ArrayAdapter<Farmer> {

    private Context context;
    private List<Farmer> farmers;

    public FarmerViewAdpter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Farmer> objects) {
        super(context, resource, objects);
        this.context = context;
        this.farmers = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.frm_farmerandroid_view,parent,false);

        EditText edtusername = view.findViewById(R.id.edtusername);
        EditText edtpassword = view.findViewById(R.id.edtpassword);
        EditText name = view.findViewById(R.id.edtname);
        EditText edtid = view.findViewById(R.id.edtid);
        EditText edtaddress = view.findViewById(R.id.edtaddress);

//        Spinner spProvinceFarmer = view.findViewById(R.id.spProvinceFarmer);
//        Spinner spAmphurFarmer = view.findViewById(R.id.spAmphurFarmer);
//        Spinner spDistriceFarmer = view.findViewById(R.id.spDistriceFarmer);
//        Spinner spVillag = view.findViewById(R.id.spVillag);

        EditText edtphone = view.findViewById(R.id.edtphone);
        EditText edtemail = view.findViewById(R.id.edtemail);
        EditText add1 = view.findViewById(R.id.add1);

        edtusername.setText(farmers.get(position).getUserid());
        edtpassword.setText(farmers.get(position).getPwd());
        name.setText(farmers.get(position).getName());
        edtid.setText(farmers.get(position).getId());
        edtaddress.setText(farmers.get(position).getAddress());

        //spProvinceFarmer.setText(farmers.get(position).getAddress());
        //spAmphurFarmer.setText(farmers.get(position).getName());
        //spDistriceFarmer.setText(farmers.get(position).getSid());
        //spVillag.setText(farmers.get(position).getVid());

        edtphone.setText(farmers.get(position).getTel());
        edtemail.setText(farmers.get(position).getEmail());
        add1.setText(farmers.get(position).getArea());


        return view;
    }

}