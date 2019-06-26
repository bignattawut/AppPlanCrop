package th.in.nattawut.plancrop.utility;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.Order;

public class OrderAdapter extends ArrayAdapter<Order> {

    private Context context;
    private List<Order> orders;

    public OrderAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Order> objects) {
        super(context, resource, objects);
        this.context = context;
        this.orders = objects;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.frm_order_view,parent,false);


        TextView textSDate = view.findViewById(R.id.textSData);
        TextView textEDate = view.findViewById(R.id.textEDate);
        TextView textCrop =  view.findViewById(R.id.textCrop);
        TextView textQty = view.findViewById(R.id.textQty);

        textSDate.setText(orders.get(position).getSdate());
        textEDate.setText(orders.get(position).getEdate());
        textCrop.setText(orders.get(position).getCrop());
        textQty.setText(orders.get(position).getQty());


        return view;
    }




}
