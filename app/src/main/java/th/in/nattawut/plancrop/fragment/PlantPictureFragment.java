package th.in.nattawut.plancrop.fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import th.in.nattawut.plancrop.HomeActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.AddPlantPictuteUpload;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;

public class PlantPictureFragment extends Fragment {

    private ImageView photoImageView;
    private Uri uri;
    private String photoURlString;
    private boolean aBoolean = true;
    private ProgressDialog progressDialog;

    //Button selctDate;
    ImageView selctDate;
    TextView date;
    DatePickerDialog dataPickerDialog;
    Calendar calendar;

    public PlantPictureFragment() {
        //
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //CreateToolbar
        createToolbar();

        //imageViewPhoto
        photoImageView = getView().findViewById(R.id.imagePhoto);

        //Gallery Controller
        galleryController();

        //Camera Controller
        cameraController();

        //DataPickerDialog
        DataPickerDialog();

        //AddPlantPicture
        addPlantPicture();

        //UploadPic
       // UploadPic();

        //UploadImage
        UploadImage();

    }

    private void UploadImage() {

    }

//    private void UploadPic() {
//
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
//                .Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        FTPClient ftpClient = new FTPClient();
//        Myconstant myconstant = new Myconstant();
//        File file = new File();
//
//        try {
//            ftpClient.connect(myconstant.getUrlplantpicture());
//            ftpClient.setType(FTPClient.TYPE_BINARY);
//            ftpClient.changeDirectory("no");
//            ftpClient.upload(file,new MyUploadPic());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                ftpClient.disconnect(true);
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
//    }

    private void addPlantPicture() {
        Button button = getView().findViewById(R.id.btnPlantPicture);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadValueToServer();
            }
        });
    }

    private void uploadValueToServer() {
        ImageView imageViewimagePhoto = getView().findViewById(R.id.imagePhoto);
        //EditText imageViewimagePhoto = getView().findViewById(R.id.e);
        TextView textmyDate = getView().findViewById(R.id.textViewDatePicture);
        EditText edtDescription = getView().findViewById(R.id.edtDescription);

        String ImagePhotoString = imageViewimagePhoto.toString().trim();
        //String ImagePhotoString = imageViewimagePhoto.getText().toString().trim();
        String DatepictureString = textmyDate.getText().toString().trim();
        String DescriptionString = edtDescription.getText().toString().trim();


        if (ImagePhotoString.isEmpty() || DatepictureString.isEmpty() || DescriptionString.isEmpty()) {
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.onrmaIDialog("สวัสดี", "กรุณากรอกข้อมูล");
        } else {
            try {
                Myconstant myconstant = new Myconstant();
                AddPlantPictuteUpload addPlantPictuteUpload = new AddPlantPictuteUpload(getActivity());
                addPlantPictuteUpload.execute(ImagePhotoString,DatepictureString,DescriptionString,
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
                File cameraFlie1 = new File(file, "master" +Integer.toString(i) + ".jpg");

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

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarPlantPicture);
        ((HomeActivity)getActivity()).setSupportActionBar(toolbar);

        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("บันทึกจิกกรรม");
        ((HomeActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((HomeActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }

        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_plantpicture, container, false);
        return view;
    }

   /*public class MyUploadPic implements FTPDataTransferListener{
        @Override
        public void started() {
            Toast.makeText(getActivity(),"Upload Start",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void transferred(int i) {
            Toast.makeText(getActivity(),"Transferred...",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void completed() {
            Toast.makeText(getActivity(),"Upload Complated",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void aborted() {

        }

        @Override
        public void failed() {

        }
    }*/
}

