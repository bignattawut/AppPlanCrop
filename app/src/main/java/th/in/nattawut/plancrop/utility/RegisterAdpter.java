package th.in.nattawut.plancrop.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import th.in.nattawut.plancrop.R;

public class RegisterAdpter extends BaseAdapter {

    private Context context;
    private String[] userString,passwordString,nameString,idString,addressString,phonString,emailString;

    public RegisterAdpter(Context context,
                          String[] userString,
                          String[] passwordString,
                          String[] nameString,
                          String[] idString,
                          String[] addressString,
                          String[] phonString,
                          String[] emailString) {
        this.context = context;
        this.userString = userString;
        this.passwordString = passwordString;
        this.nameString = nameString;
        this.idString = idString;
        this.addressString = addressString;
        this.phonString = phonString;
        this.emailString = emailString;
    }

    @Override
    public int getCount() {
        return userString.length;
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
        View view = layoutInflater.inflate(R.layout.frm_register,parent,false);

        EditText username = view.findViewById(R.id.edtusername);
        EditText password = view.findViewById(R.id.edtpassword);
        EditText name = view.findViewById(R.id.edtname);
        EditText id = view.findViewById(R.id.edtid);
        EditText address = view.findViewById(R.id.edtaddress);
        EditText phon = view.findViewById(R.id.edtphone);
        EditText email = view.findViewById(R.id.edtemail);

        /*TextView username = view.findViewById(R.id.textView4);
        TextView password = view.findViewById(R.id.textView5);
        TextView name = view.findViewById(R.id.textView6);
        TextView id = view.findViewById(R.id.textView7);
        TextView address = view.findViewById(R.id.textView8);
        TextView phon = view.findViewById(R.id.textView9);
        TextView email = view.findViewById(R.id.textView10);*/

        username.setText(userString[position]);
        password.setText(userString[position]);
        name.setText(userString[position]);
        id.setText(userString[position]);
        address.setText(userString[position]);
        phon.setText(userString[position]);
        email.setText(userString[position]);

        return view;
    }
}
