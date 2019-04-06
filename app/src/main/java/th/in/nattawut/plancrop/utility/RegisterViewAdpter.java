package th.in.nattawut.plancrop.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import th.in.nattawut.plancrop.R;

//สืบทอดมาจาก BaseAdapter

public class RegisterViewAdpter extends BaseAdapter {

    private Context context;
    private String[] userString,passwordString,nameString,idString,addressString,vidString,sidString,phonString,emailString;

    public RegisterViewAdpter(Context context,
                              String[] userString,
                              String[] passwordString,
                              String[] nameString,
                              String[] idString,
                              String[] addressString,
                              String[] vidString,
                              String[] sidString,
                              String[] phonString,
                              String[] emailString) {
        this.context = context;
        this.userString = userString;
        this.passwordString = passwordString;
        this.nameString = nameString;
        this.idString = idString;
        this.addressString = addressString;
        this.vidString = vidString;
        this.sidString = sidString;
        this.phonString = phonString;
        this.emailString = emailString;
    }

    @Override
    public int getCount() {
        return 1;
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
        View view = layoutInflater.inflate(R.layout.frm_register_view1,parent,false);

        TextView username = view.findViewById(R.id.textUsername);
        TextView password = view.findViewById(R.id.textPassword);
        TextView name = view.findViewById(R.id.textName);
        TextView id = view.findViewById(R.id.textID);
        TextView address = view.findViewById(R.id.textAddress);
        TextView vid = view.findViewById(R.id.textVid);
        TextView sid = view.findViewById(R.id.textSid);
        TextView phon = view.findViewById(R.id.textPhone);
        TextView email = view.findViewById(R.id.textEmail);

        username.setText(userString[position]);
        password.setText(passwordString[position]);
        name.setText(nameString[position]);
        id.setText(idString[position]);
        address.setText(addressString[position]);
        vid.setText(vidString[position]);
        sid.setText(sidString[position]);
        phon.setText(phonString[position]);
        email.setText(emailString[position]);

        /*View view = layoutInflater.inflate(R.layout.edit_register,parent,false);
        EditText username = view.findViewById(R.id.EditEdtUsername);
        EditText password = view.findViewById(R.id.EditEdtPassword);
        EditText name = view.findViewById(R.id.EditEdtName);
        EditText id = view.findViewById(R.id.EditEdtId);
        EditText address = view.findViewById(R.id.EditEdtAddress);
        TextView vid = view.findViewById(R.id.textVid);
        TextView sid = view.findViewById(R.id.textSid);
        EditText phon = view.findViewById(R.id.EditEdtPhone);
        EditText email = view.findViewById(R.id.EditEdtEmail);

        username.setText(userString[position]);
        password.setText(passwordString[position]);
        name.setText(nameString[position]);
        id.setText(idString[position]);
        address.setText(addressString[position]);
        vid.setText(vidString[position]);
        sid.setText(sidString[position]);
        phon.setText(phonString[position]);
        email.setText(emailString[position]);*/
        return view;
    }

}
