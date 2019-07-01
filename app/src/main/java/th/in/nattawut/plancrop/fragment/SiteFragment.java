package th.in.nattawut.plancrop.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.AddCrop;
import th.in.nattawut.plancrop.utility.AddSite;
import th.in.nattawut.plancrop.utility.MyAlertCrop;
import th.in.nattawut.plancrop.utility.Myconstant;


public class SiteFragment extends Fragment implements LocationListener {

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    TextView txtLat;
    TextView txtLong;

    private String midString,nameString,vidString,latString,longString;

    TextView siteMidName,siteMid;


    @SuppressLint("MissingPermission")
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //setUpTexeShowMid
        setUpTexeShowMid();

        setUpTexeShowVid();

        gpsSetUp();

        siteController();


    }
    private void siteController() {
        Button button = getView().findViewById(R.id.btnSite);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSite();
            }
        });
    }

    private void addSite() {
        TextView txtMid = getView().findViewById(R.id.siteMid);
        TextView txtVid = getView().findViewById(R.id.vidSiteSpinner);
        TextView txtLat = getView().findViewById(R.id.txtLat);
        TextView txtLong = getView().findViewById(R.id.txtLong);


        midString = txtMid.getText().toString().trim();
        vidString = txtVid.getText().toString().trim();
        latString = txtLat.getText().toString().trim();
        longString = txtLong.getText().toString().trim();

        MyAlertCrop myAlertCrop = new MyAlertCrop(getActivity());
        if (nameString.isEmpty()) {
            myAlertCrop.onrmaIDialog("โปรดกรอก", "กรุณากรอกชื่อเกษตร");
        }else if (vidString.isEmpty()) {
            myAlertCrop.onrmaIDialog("โปรดกรอก", "กรุณากรอกที่ตั้งแปลง");
        }else if (latString.isEmpty()) {
            myAlertCrop.onrmaIDialog("โปรดกรอก", "กรุณากรอกละติจูล");
        }else if (longString.isEmpty()) {
            myAlertCrop.onrmaIDialog("โปรดกรอก", "กรุณากรอกลองจิจูล");
        }else {
            comfirmUpload();
        }
    }

    private void comfirmUpload() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("ข้อมูลแปลงเพาะปลูก");
        builder.setMessage("ชื่อเกษตรกร = " + nameString + "\n"
                + "ที่ตั้งแปลงเพาะปลูก = " + vidString + "\n"
                + "ละติจูด = " + latString + "\n"
                + "ลองจิจูด = " + longString);
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {//ปุ่มที่1
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }); //
        builder.setPositiveButton("เพิ่ม", new DialogInterface.OnClickListener() {//ปุ่มที่2
            @Override
            public void onClick(DialogInterface dialog, int which) {
                uploadToServer();
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void uploadToServer(){
        try {
            Myconstant myconstant = new Myconstant();
            AddSite addSite = new AddSite(getActivity());
            addSite.execute(midString,vidString, latString, longString,
                    myconstant.getUrladdSite());

            String result = addSite.get();
            Log.d("crop", "result ==> " + result);
            if (Boolean.parseBoolean(result)) {
                getActivity().getSupportFragmentManager().popBackStack();
            } else {
                Toast.makeText(getActivity(), "เพิ่มข้อมูลเรียบร้อย", Toast.LENGTH_LONG).show();
//                getActivity()
//                        .getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.contentHomeFragment, new CropViewFragment())
//                        .addToBackStack(null)
//                        .commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpTexeShowMid() {
        TextView siteMidName = getView().findViewById(R.id.siteMidName);
        TextView siteMid = getView().findViewById(R.id.siteMid);

        nameString = getActivity().getIntent().getExtras().getString("name");
        siteMidName.setText(nameString);

        midString = getActivity().getIntent().getExtras().getString("mid");
        siteMid.setText(midString);

    }

    private void setUpTexeShowVid() {
        TextView siteVid = getView().findViewById(R.id.vidSiteSpinner);
        String strTextShowVid = getActivity().getIntent().getExtras().getString("vid");
        siteVid.setText(strTextShowVid);
    }

    private void gpsSetUp() {
        txtLat = getView().findViewById(R.id.txtLat);
        txtLong = getView().findViewById(R.id.txtLong);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_site, container, false);
        return view;
    }

    @Override
    public void onLocationChanged(Location location) {
        txtLat.setText("" + location.getLatitude());
        txtLong.setText("" + location.getLongitude());

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
