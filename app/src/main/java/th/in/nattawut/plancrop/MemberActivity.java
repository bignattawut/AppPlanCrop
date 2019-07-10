package th.in.nattawut.plancrop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import th.in.nattawut.plancrop.fragment.AdminFrament;
import th.in.nattawut.plancrop.fragment.MemberFragment;

public class MemberActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentMemberFragment, new MemberFragment())
                    .commit();
        }
    }
}

