package th.in.nattawut.plancrop;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import th.in.nattawut.plancrop.fragment.CropFragment;
import th.in.nattawut.plancrop.fragment.CropTypeFragment;
import th.in.nattawut.plancrop.fragment.CropTypeViewFragment;
import th.in.nattawut.plancrop.fragment.CropViewFragment;
import th.in.nattawut.plancrop.fragment.HomeFragment;
import th.in.nattawut.plancrop.fragment.MainPlanFragment;
import th.in.nattawut.plancrop.fragment.PlanFragment;
import th.in.nattawut.plancrop.fragment.PlanViewFragment;
import th.in.nattawut.plancrop.fragment.PlantFragment;
//import th.in.nattawut.plancrop.fragment.PlantPicture;
import th.in.nattawut.plancrop.fragment.AdminFrament;
import th.in.nattawut.plancrop.fragment.PlantPictureFragment;
import th.in.nattawut.plancrop.fragment.RegisterFragment;
import th.in.nattawut.plancrop.fragment.RegisterViewFragment;
import th.in.nattawut.plancrop.fragment.SiteFragment;
import th.in.nattawut.plancrop.utility.DrawerAdapter;
import th.in.nattawut.plancrop.utility.Myconstant;

public class HomeActivity extends AppCompatActivity {

    private String nameString;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        createToolbar();

        receiveValueFromMain();

        //Add Fragment to Activity
        addFragment(savedInstanceState);

        //Create ListView
        createListView();


    }

    private void createListView() {
        ListView listView = findViewById(R.id.listViewDrawer);
        Myconstant myconstant = new Myconstant();

        DrawerAdapter drawerAdapter = new DrawerAdapter(HomeActivity.this,
                myconstant.getIconInts(),myconstant.getTitleStrings());

        listView.setAdapter(drawerAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        setTitle("ข้อมูลส่วนตัว");
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentHomeFragment, new RegisterViewFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 1:
                        setTitle("วางแผนเพาะปลูก");
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentHomeFragment, new PlanFragment())
                                .addToBackStack(null)
                                .commit();
                        drawerLayout.closeDrawers();
                        break;
                    case 2:
                        setTitle("ปฏิทินการเพาะปลูก");
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentHomeFragment, new PlanViewFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 3:
                        setTitle("บันทึกการเพาะปลูก");
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentHomeFragment, new PlantPictureFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 4:
                        setTitle("admin");
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentHomeFragment, new AdminFrament())
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 5:
                        Intent intent = new Intent(getApplication(),MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;

                }
                drawerLayout.closeDrawers();
            }
        });
    }
    private void addFragment(Bundle savedInstanceState){
        if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentHomeFragment, new MainPlanFragment())
                    //.replace(R.id.contentHomeFragment, new HomeFragment())
                    .commit();
        }
    }

    private void receiveValueFromMain() {
        nameString = getIntent().getStringExtra("Name");
        Log.d("1Jan","nameString ==> " + nameString);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    private void createToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarHone);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("เกษตร&Plan");
        //getSupportActionBar().setLogo(R.drawable.ic_action_exit);
        //getSupportActionBar().setSubtitle("Login by" + nameString);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this,
                drawerLayout, R.string.open, R.string.close);

    }
}

