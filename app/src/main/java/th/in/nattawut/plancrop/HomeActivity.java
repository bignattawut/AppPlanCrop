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
import android.widget.ListView;

import th.in.nattawut.plancrop.fragment.CropFragment;
import th.in.nattawut.plancrop.fragment.CropTypeFragment;
import th.in.nattawut.plancrop.fragment.CropTypeViewFragment;
import th.in.nattawut.plancrop.fragment.CropViewFragment;
import th.in.nattawut.plancrop.fragment.HomeFragment;
import th.in.nattawut.plancrop.fragment.PlanFragment;
import th.in.nattawut.plancrop.fragment.PlantFragment;
//import th.in.nattawut.plancrop.fragment.PlantPicture;
import th.in.nattawut.plancrop.fragment.AdminFrament;
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
                        PlanFragment planFragment =new PlanFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.contentHomeFragment,planFragment);

                        fragmentTransaction.addToBackStack(null).commit();
                        /*
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentHomeFragment, new PlanFragment())
                                .addToBackStack(null)
                                .commit();
                        drawerLayout.closeDrawers();*/
                        break;
                    case 1:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentHomeFragment, new SiteFragment())
                                .addToBackStack(null)
                                .commit();

                        break;
                    /*case 2:
                        getSupportFragmentManager()
                                .beginTransaction()
                                //.add(R.id.contentHomeFragment, new PlantFragment())
                                .replace(R.id.contentHomeFragment, new CropViewFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 3:
                        getSupportFragmentManager()
                                .beginTransaction()
                                //.add(R.id.contentHomeFragment, new CropFragment())
                                .replace(R.id.contentHomeFragment, new CropFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 4:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentHomeFragment, new CropTypeFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 5:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentHomeFragment, new CropTypeViewFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                        */
                    case 2:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentHomeFragment, new AdminFrament())
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 3:
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
                    //.add(R.id.contentHomeFragment, new HomeFragment())
                    .replace(R.id.contentHomeFragment, new HomeFragment())
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
        getSupportActionBar().setTitle("เกษตร&Plan");
        getSupportActionBar().setSubtitle(nameString);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this,
                drawerLayout, R.string.open, R.string.close);

    }
    /*
    private void createToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarHone);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("เกษตร&Plan");
        getSupportActionBar().setSubtitle(nameString);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this,
                drawerLayout, R.string.open, R.string.close);
    }*/

}

