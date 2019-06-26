package th.in.nattawut.plancrop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import th.in.nattawut.plancrop.fragment.MainPlanFragment;
import th.in.nattawut.plancrop.fragment.OrderViewRePortFragment;
import th.in.nattawut.plancrop.fragment.PlanFarmerViewFragment;
import th.in.nattawut.plancrop.fragment.PlanFragment;
import th.in.nattawut.plancrop.fragment.PlantFarmerViewFragment;
import th.in.nattawut.plancrop.fragment.PlantFragment;
//import th.in.nattawut.plancrop.fragment.PlantPicture;
import th.in.nattawut.plancrop.fragment.AdminFrament;
import th.in.nattawut.plancrop.fragment.PlantPictureFragment;
import th.in.nattawut.plancrop.fragment.PlantPictureViewFragment;
import th.in.nattawut.plancrop.fragment.PlantViewFragment;
import th.in.nattawut.plancrop.fragment.RegisterViewFragment;
import th.in.nattawut.plancrop.fragment.TabPlanFragment;
import th.in.nattawut.plancrop.fragment.memberFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String nameString;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    TextView textView;
    LinearLayout linearLayout;
    CardView cardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //createToolbar();

        receiveValueFromMain();

        //Add Fragment to Activity
        addFragment(savedInstanceState);

        //Create ListView
        //createListView();

        ////navigationView
        Toolbar toolbar = findViewById(R.id.toolbarHone);
        //toolbar.setNavigationIcon(R.drawable.ic_action_camera);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //nameString = getIntent().getStringExtra("Name");
        //getSupportActionBar().setSubtitle(nameString);

        //get name nav_header_name
        nameString = getIntent().getStringExtra("name");
        View view = navigationView.getHeaderView(0);
        textView = view.findViewById(R.id.nav_header_name);
        textView.setText(nameString);
        cardView = view.findViewById(R.id.nav_header_id);

        //show name MenuItem
//        MenuItem menuItem = navigationView.getMenu().findItem(R.id.menu_user);
//        menuItem.setTitle(nameString);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        ///////

        plantController();

    }

    private void plantController() {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new RegisterViewFragment())
                        .addToBackStack(null)
                        .commit();
                drawerLayout.closeDrawers();
            }
        });
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_user:
                setTitle("หน้าหลัก");
                //getSupportActionBar().setSubtitle(nameString);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new TabPlanFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.menu_plan:
                setTitle("วางแผนเพาะปลูก");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlanFarmerViewFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.munu_Plant:
                setTitle("การเพาะปลูก");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlantFarmerViewFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.munu_PlantPicture:
                setTitle("บันทึกการเพาะปลูก");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlantPictureFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.munu_produce:
                setTitle("ผลผลิต");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new PlantFragment())
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.menu_want:
                setTitle("แจ้งความต้องการ");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new OrderViewRePortFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.menu_admin:
                setTitle("ผู้ดูแลระบบ");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new AdminFrament())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.menu_member:
                setTitle("สมาชิก");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentHomeFragment, new memberFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.menu_exit:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT);
                builder.setCancelable(false);
                builder.setTitle("ต้องการออกจากระบบใช่หรือไม่?");
                builder.setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplication(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        dialog.dismiss();

                    }
                });
                builder.show();
                break;

        }
        //drawerLayout.closeDrawer(GravityCompat.START);
        drawerLayout.closeDrawers();
        return true;
    }


    private void addFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentHomeFragment, new MainPlanFragment())
                    //.replace(R.id.contentHomeFragment, new HomeFragment())
                    .commit();
        }
    }


    private void receiveValueFromMain() {
        nameString = getIntent().getStringExtra("name");
        Log.d("1Jan", "nameString ==> " + nameString);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_select,menu);//Show layout
        menuInflater.inflate(R.menu.manu_exit,menu);
        menuInflater.inflate(R.menu.manu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.itemlinkUrl:
//                Intent intent = new Intent(Intent.ACTION_VIEW);
////                intent.setData(Uri.parse("https://www.moc.go.th/index.php/rice-iframe-4.html"));
////                startActivity(intent);
////                finish();

            //Show layout
//            case R.id.itemSelect:
//                final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
//                builder1.setCancelable(false);
//                View view = getLayoutInflater().inflate(R.layout.member,null);
//                CardView cardView = view.findViewById(R.id.CardViewRegister);
//                cardView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.contentHomeFragment, new OrderViewRePortFragment())
//                                .addToBackStack(null)
//                                .commit();
//                    }
//                });
//                builder1.setView(view);
//                AlertDialog alertDialog = builder1.create();
//                alertDialog.show();;
//                break;
            case R.id.itemSingOut:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT);
                builder.setCancelable(false);
                builder.setTitle("ต้องการออกจากระบบใช่หรือไม่?");
                builder.setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplication(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        dialog.dismiss();

                    }
                });
                builder.show();
                break;
            case R.id.option:
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
            }
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
    }*/

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /*
    private void createToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarHone);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("เกษตร&Plan");
        //getSupportActionBar().setLogo(R.drawable.ic_action_exit);
        nameString = getIntent().getStringExtra("Name");
        getSupportActionBar().setSubtitle(nameString);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar.setNavigationIcon(R.drawable.ic_action_back);

        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this,
                drawerLayout, R.string.open, R.string.close);

    }*/
}

