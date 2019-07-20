package th.in.nattawut.plancrop.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.APIUtils;
import th.in.nattawut.plancrop.utility.AddPlantPictuteUpload;
import th.in.nattawut.plancrop.utility.ApiClient;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;
import th.in.nattawut.plancrop.utility.OrderService;
import th.in.nattawut.plancrop.utility.UploadImg;

import static android.app.Activity.RESULT_OK;

public class PlantPictureFragment1 extends Fragment {

    Bitmap bitmap;
    ImageView imageView,imvGallery;
    Button btnUpload;
    private static  final int IMAGE = 100;
    OrderService orderService;
    EditText imgTitle;

    //Button selctDate;
    ImageView selctDate;
    TextView date;
    DatePickerDialog dataPickerDialog;
    Calendar calendar;


    private String NoString,DatepictureString,DescriptionString,result;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        orderService = APIUtils.getService();

        imageView = getView().findViewById(R.id.imagePhoto);
        imvGallery = getView().findViewById(R.id.imvGallery);
        btnUpload = getView().findViewById(R.id.btnUpload);
        //imgTitle = getView().findViewById(R.id.edtNo);

        imvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
                addPlantPicture();
            }
        });

        //DataPickerDialog
        DataPickerDialog();

        selectPlant();

    }

    private void addPlantPicture() {
        Button button = getView().findViewById(R.id.btnUpload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadValueToServer();
            }
        });
    }

    private void uploadValueToServer() {
        TextView textPlantNo = getView().findViewById(R.id.textPlantNo);
        TextView textmyDate = getView().findViewById(R.id.textViewDatePicture);
        EditText edtDescription = getView().findViewById(R.id.edtDescription);

        NoString = textPlantNo.getText().toString().trim();
        DatepictureString = textmyDate.getText().toString().trim();
        DescriptionString = edtDescription.getText().toString().trim();


        if (NoString.isEmpty() ||DatepictureString.isEmpty() || DescriptionString.isEmpty()) {
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.onrmaIDialog("สวัสดี", "กรุณากรอกข้อมูล");
        } else {
            try {
                Myconstant myconstant = new Myconstant();
                AddPlantPictuteUpload addPlantPictuteUpload = new AddPlantPictuteUpload(getActivity());
                addPlantPictuteUpload.execute(NoString,DatepictureString,DescriptionString,
                        myconstant.getUrlAddPlantPicture());

                result = addPlantPictuteUpload.get();
                Log.d("PlantPicture", "result ==>" + result);
                if (Boolean.parseBoolean(result)) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(), "เพิ่มข้อมูลเรียบร้อย",Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void selectPlant() {
        if (android.os.Build.VERSION.SDK_INT > 9) { //setup policy เเพื่อมือถือที่มีประปฏิบัติการสูงกว่านีจะไม่สามารถconnectกับโปรโตรคอลได้
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final Spinner spin = getView().findViewById(R.id.spPlantNo);
        try {

            Myconstant myconstant = new Myconstant();
            GetData getData = new GetData(getActivity());
            getData.execute(myconstant.getUrlPlant());

            String jsonString = getData.get();
            Log.d("5/Jan spPlantNo", "JSON ==>" + jsonString);
            JSONArray data = new JSONArray(jsonString);

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            for (int i = 0; i < data.length(); i++) {
                JSONObject c = data.getJSONObject(i);

                map = new HashMap<String, String>();
                map.put("no", c.getString("no"));
                map.put("crop", c.getString("crop"));
                MyArrList.add(map);
            }
            SimpleAdapter sAdap;sAdap = new SimpleAdapter(getActivity(), MyArrList, R.layout.spinner_plant,
                    new String[]{"no", "crop"}, new int[]{R.id.textPlantNo, R.id.textPlantCrop});
            spin.setAdapter(sAdap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void DataPickerDialog() {
        date = getActivity().findViewById(R.id.textViewDatePicture);
        selctDate = getActivity().findViewById(R.id.imageViewDatePicture);
        selctDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar  = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                dataPickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int y, int m, int d) {
                                //date.setText(d + "/" + (m + 1) + "/" + y);
                                date.setText(y + "/" + (m + 1) + "/" + d);
                            }
                        },day,month,year);
                dataPickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dataPickerDialog.show();
            }
        });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE);
    }

    private String convertToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== IMAGE && resultCode==RESULT_OK && data!=null)
        {
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {
        String image = convertToString();
        String name = imgTitle.getText().toString();
        OrderService orderService = ApiClient.getApiClient().create(OrderService.class);
        Call<UploadImg> call = orderService.uploadImage(name,image);
        call.enqueue(new Callback<UploadImg>() {
            @Override
            public void onResponse(Call<UploadImg> call, Response<UploadImg> response) {
                UploadImg uploadImg = response.body();
                Log.d("Server Respons",""+uploadImg.getResponse());
            }

            @Override
            public void onFailure(Call<UploadImg> call, Throwable t) {
                Log.d("Server Response",""+t.toString());
            }
        });
    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_plantpicture, container, false);
        return view;

    }
}
