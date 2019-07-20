package th.in.nattawut.plancrop.fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.AddPlantPictuteUpload;
import th.in.nattawut.plancrop.utility.GetData;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;
import th.in.nattawut.plancrop.utility.RequestHandler;

import static android.app.Activity.RESULT_OK;

public class PlantPictureFragment extends Fragment {

    private ImageView photoImageView;
    private Uri uri;

    //Button selctDate;
    ImageView selctDate;
    TextView date;
    DatePickerDialog dataPickerDialog;
    Calendar calendar;

    ////////
    private static final String[] Camara = {"เลือกรูปจากคลัง","ถ่ายรูป"};

    public static final String UPLOAD_URL = "http://192.168.1.144/android/php/upload.php";
    public static final String UPLOAD_KEY = "URL";
    private int PICK_IMAGE_REQUEST = 1;

    private ImageView buttonChoose;
    private Button buttonUpload;


    private ImageView imageView;
    private Bitmap bitmap;
    private Uri filePath;
    ///////

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //CreateToolbar
        //createToolbar();

        //imageViewPhoto
        //photoImageView = getView().findViewById(R.id.imagePhoto);

        //Gallery Controller
        //galleryController();

        //Camera Controller
        //cameraController();

        //DataPickerDialog
        DataPickerDialog();

        //AddPlantPicture
//        addPlantPicture();

        //uploadimage
        uploadimage();
        imageView = getView().findViewById(R.id.imagePhoto);

    }
    private void uploadimage(){
        buttonChoose = getView().findViewById(R.id.imvGallery);
        buttonUpload = getView().findViewById(R.id.btnUpload);
        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == buttonChoose) {
                    showFileChooser();
                }
            }
        });
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == buttonUpload){
                    if(filePath!=null) {
                        uploadImage();
                    } else {
                        MyAlert myAlert = new MyAlert(getActivity());
                        myAlert.onrmaIDialog("สวัสดี", "กรุณาเพิ่มรูปภาพกิจกรรมเพาะปลูก");
                        //Toast.makeText(getActivity(),"Select Image",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String> {
            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "กำลังอัพโหลดรูปภาพ", "กรุณารอสักครู่.....",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);
                data.put("name",getFileName(filePath));

                String result = rh.postRequest(UPLOAD_URL,data);
                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

    String getFileName(Uri uri){
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

//    private void addPlantPicture() {
//        Button button = getView().findViewById(R.id.btnPlantPicture);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadValueToServer();
//               // uploadImage();
//            }
//        });
//    }


    private void uploadValueToServer() {
        //EditText edtNo = getView().findViewById(R.id.edtNo);
        TextView textmyDate = getView().findViewById(R.id.textViewDatePicture);
        EditText edtDescription = getView().findViewById(R.id.edtDescription);

        //String NoString = edtNo.getText().toString().trim();
        String DatepictureString = textmyDate.getText().toString().trim();
        String DescriptionString = edtDescription.getText().toString().trim();


        if (/*NoString.isEmpty() ||*/ DatepictureString.isEmpty() || DescriptionString.isEmpty()) {
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.onrmaIDialog("สวัสดี", "กรุณากรอกข้อมูล");
        } else {
            try {
                Myconstant myconstant = new Myconstant();
                AddPlantPictuteUpload addPlantPictuteUpload = new AddPlantPictuteUpload(getActivity());
                addPlantPictuteUpload.execute(/*NoString,*/DatepictureString,DescriptionString,
                        myconstant.getUrlAddPlantPicture());

                String result = addPlantPictuteUpload.get();
                Log.d("PlantPicture", "result ==>" + result);
                if (Boolean.parseBoolean(result)) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast .makeText(getActivity(), "เพิ่มข้อมูลเรียบร้อย",Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
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


    /*
    //ถ่ายรูปกล้อง
    private void cameraController() {
        ImageView imageView = getView().findViewById(R.id.imvCamera);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pathFlieString = Environment.getExternalStorageDirectory() + "/" + "Camera";
                Log.d("13Feb62","pathFlieString ==> " + pathFlieString);

                File file = new File(pathFlieString);
                if (!file.exists()) {
                    file.mkdirs();
                }
                Random random = new Random();
                int i = random.nextInt(1000);
                File cameraFlie1 = new File(file, "big" +Integer.toString(i) + ".jpg");

                uri = Uri.fromFile(cameraFlie1);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent,2);

            }
        });
    }


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

    private void showImage() {
        try {
            Bitmap bitmap = BitmapFactory
                    .decodeStream(getActivity()
                            .getContentResolver().openInputStream(uri));
            Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap,
                    800,600,false);
            photoImageView.setImageBitmap(bitmap1);

        } catch (Exception e) {
            Log.d("13Feb62","showImage ==>" + e.toString());
        }
    }

    //เลือกรูปจาก gallery
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

    */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_plantpicture, container, false);
        return view;

    }
}

