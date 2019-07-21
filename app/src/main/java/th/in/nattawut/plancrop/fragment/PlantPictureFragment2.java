package th.in.nattawut.plancrop.fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
import th.in.nattawut.plancrop.utility.ServerResponse;
import th.in.nattawut.plancrop.utility.UploadImg;

public class PlantPictureFragment2 extends Fragment {

    private ImageView photoImageView;
    private Uri uri;
    Button btnUpload;
    OrderService orderService;
    EditText imgTitle;
    Bitmap bitmap;

    //Button selctDate;
    ImageView selctDate;
    TextView date;
    DatePickerDialog dataPickerDialog;
    Calendar calendar;

    private String NoString,DatepictureString,DescriptionString,result;
    ProgressDialog progressDialog;
    String mediaPath, mediaPath1;

    private static final int IMAGE_CAPTURE_CODE = 100;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //imageViewPhoto
        photoImageView = getView().findViewById(R.id.imagePhoto);
        //imgTitle = getView().findViewById(R.id.edtNo);

        //Gallery Controller
        galleryController();

        //Camera Controller
        //cameraController();

        //DataPickerDialog
        DataPickerDialog();

        //selectPlant
        selectPlant();

        btnUpload = getView().findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
                uploadValueToServer();

            }
        });
        orderService = APIUtils.getService();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Uploading...");
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

    private void uploadImage() {
        String image = convertToString();
        String name = imgTitle.getText().toString();

//        progressDialog.show();
//        File file = new File(mediaPath);
//
//        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
//        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
//        ResponseBody body = ResponseBody.create(MediaType.parse("text/plain"),file.getName());
//
//        OrderService orderService = ApiClient.getApiClient().create(OrderService.class);
//        Call<ServerResponse> call = orderService.uploadFile(fileToUpload,body);
//        call.enqueue(new Callback<ServerResponse>() {
//            @Override
//            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
//                ServerResponse serverResponse = response.body();
//                if (serverResponse != null) {
//                    if (serverResponse.isSuccess()) {
//                        Toast.makeText(getActivity(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getActivity(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    assert serverResponse != null;
//                    Log.v("Response", serverResponse.toString());
//                }
//                progressDialog.dismiss();
//            }
//            @Override
//            public void onFailure(Call<ServerResponse> call, Throwable t) {
//
//            }
//        });
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

//    private void cameraController() {
//        ImageView imageView = getView().findViewById(R.id.imvCamera);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String pathFlieString = Environment.getExternalStorageDirectory() + "/" + "Camera";
//                Log.d("13Feb62","pathFlieString ==> " + pathFlieString);
//
//                File file = new File(pathFlieString);
//                if (!file.exists()) {
//                    file.mkdirs();
//                }
//                Random random = new Random();
//                int i = random.nextInt(1000);
//                File cameraFlie1 = new File(file, "master" +Integer.toString(i) + ".jpg");
//
//                uri = Uri.fromFile(cameraFlie1);
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//                startActivityForResult(intent,2);
//
//            }
//        });
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode){
                case 1:
                    uri = data.getData();
                    showImage();
                    break;
                case 2:
                    showImage();
                    break;

            }
        }
    }

    private String convertToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }

    private void showImage() {
        try {
            bitmap = BitmapFactory
                    .decodeStream(getActivity()
                            .getContentResolver().openInputStream(uri));
            Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap,
                    800,600,false);
            photoImageView.setImageBitmap(bitmap1);

        } catch (Exception e) {
            Log.d("13Feb62","showImage ==>" + e.toString());
        }
    }

    private void galleryController() {
        ImageView imageView = getView().findViewById(R.id.imvGallery);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
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
