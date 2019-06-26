package th.in.nattawut.plancrop.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


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
import th.in.nattawut.plancrop.utility.Myconstant;


public class SiteFragment extends Fragment implements LocationListener {

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    TextView txtLat;
    TextView txtLong;

    private ArrayList<String> arrProvince = new ArrayList<>();
    private ArrayList<String> arrProvinceID = new ArrayList<>();

    private ArrayAdapter<String> adpProvince,adpAmphur,adpSid;
    private Spinner spProvince,spAmphur, spDistrice;


    @SuppressLint("MissingPermission")
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //setUpTexeShowMid
        setUpTexeShowMid();

        txtLat = getView().findViewById(R.id.txtLat);
        txtLong = getView().findViewById(R.id.txtLong);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        //จังหวัด
        spProvince = getView().findViewById(R.id.vidSiteSpinner);
        adpProvince = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrProvince);
        spProvince.setAdapter(adpProvince);

    }

    @Override
    public void onStart() {
        super.onStart();
        new  DataPronvince().execute();
    }
    public class DataPronvince extends AsyncTask<String,Void,String> {

        String result;
        ArrayList<String> listprovice;
        ArrayList<String> listprovinceid;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            listprovice = new ArrayList<>();
            listprovinceid = new ArrayList<>();
        }

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(Myconstant.getUrlVid)
                    .build();

            try {
                Response response = client.newCall(request)
                        .execute();

                result = response.body().string();

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = null;

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    listprovice.add(jsonObject.getString("thai"));
                    listprovinceid.add(jsonObject.getString("vid"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            arrProvince.addAll(listprovice);
            arrProvinceID.addAll(listprovinceid);
            adpProvince.notifyDataSetChanged();
        }
    }

    private void setUpTexeShowMid(){
        TextView siteMidName = getView().findViewById(R.id.siteMidName);
        TextView siteMid = getView().findViewById(R.id.siteMid);

        String strTextShow = getActivity().getIntent().getExtras().getString("name");
        siteMidName.setText(strTextShow);

        String strTextShowmid = getActivity().getIntent().getExtras().getString("mid");
        siteMid.setText(strTextShowmid);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_site, container,false);
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
