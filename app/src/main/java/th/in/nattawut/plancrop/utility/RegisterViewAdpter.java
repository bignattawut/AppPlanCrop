package th.in.nattawut.plancrop.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import th.in.nattawut.plancrop.R;

public class RegisterViewAdpter extends BaseAdapter {

    private Context context;
    private String[] userString,passwordString,nameString,idString,addressString,phonString,emailString;

    public RegisterViewAdpter(Context context,
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
        View view = layoutInflater.inflate(R.layout.frm_register_view,parent,false);

        TextView username = view.findViewById(R.id.textUsername);
        TextView password = view.findViewById(R.id.textPassword);
        TextView name = view.findViewById(R.id.textName);
        TextView id = view.findViewById(R.id.textID);
        TextView address = view.findViewById(R.id.textAddress);
        TextView phon = view.findViewById(R.id.textPhone);
        TextView email = view.findViewById(R.id.textEmail);

        username.setText(userString[position]);
        password.setText(passwordString[position]);
        name.setText(nameString[position]);
        id.setText(idString[position]);
        address.setText(addressString[position]);
        phon.setText(phonString[position]);
        email.setText(emailString[position]);

        return view;
    }
}
