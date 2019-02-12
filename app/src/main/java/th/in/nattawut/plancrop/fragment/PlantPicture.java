/*package th.in.nattawut.plancrop.fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import th.in.nattawut.plancrop.HomeActivity;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.AddPlantPictuteUpload;
import th.in.nattawut.plancrop.utility.MyAlert;
import th.in.nattawut.plancrop.utility.Myconstant;

public class PlantPicture extends Fragment {

    private Uri uri;
    private ImageView imageView;
    private boolean photoABoolean = true;
    private ProgressDialog progressDialog;

    //Button selctDate;
    ImageView selctDate;
    TextView date;
    DatePickerDialog dataPickerDialog;
    Calendar calendar;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //RegisterController
        registerController();

        //CreateToolbar
        createToolbar();

        //ImageView Contontroller
        ImageViewContontroller();


        //DataPickerDialog
        date = getActivity().findViewById(R.id.myDatepicture);
        selctDate = getActivity().findViewById(R.id.imageViewDatepicture);
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
                                date.setText(d + "/" + (m + 1) + "/" + y);
                            }
                        },day,month,year);
                dataPickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dataPickerDialog.show();
            }
        });
    }
    private void registerController() {
        Button button = getView().findViewById(R.id.btnPlantPicture);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadValueToServer();
            }
        });
    }

    private void ImageViewContontroller() {
        imageView = getView().findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,
                        "Please Choos Image"), 1);
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


    private void uploadValueToServer() {
        MyAlert myAlert = new MyAlert(getActivity());
        //TextView date = getView().findViewById(R.id.myDatepicture);
        EditText descriptionText = getView().findViewById(R.id.edtDescription);

        //String dateString = date.getText().toString().trim();
        String descriptionString = descriptionText.getText().toString().trim();

        //เซ็ครูป
        if (photoABoolean) {
            myAlert.onrmaIDialog("Choose Avate",
                    "Please Choose Avate");
        } else if (descriptionString.isEmpty()) {
            myAlert.onrmaIDialog("have space",
                    "please Fill Every Blank");
        }else {
            //Upload Photo To Server
            UploadPhoth();

            //Upload Value to Mysql
            UploadValue();
        }
    }//uploadToServer


    private void UploadValue() {

        String photoString = "http://192.168.1.130/android/php/addPlantPicture.php" +
                findPathString().substring(findPathString().lastIndexOf("/"));
        Log.d("1Jan","photoString ==> " + photoString);

        Myconstant myconstant = new Myconstant();
        MyAlert myAlert= new MyAlert(getActivity());

        try {

            AddPlantPictuteUpload addPlantPictuteUpload = new AddPlantPictuteUpload(getActivity());


            if (Boolean.parseBoolean(addPlantPictuteUpload.get())) {
                Toast.makeText(getActivity(),"บันทึกเรียบร้อย",
                        Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
            }else {
                myAlert.onrmaIDialog("Cannont PlantPic",
                        "อีดครั้ง");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void UploadPhoth(){
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("UploadPhoth");
        progressDialog.setMessage("Pleae Wit Few Miuns...");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        FTPClient ftpClient = new FTPClient();
        Myconstant myconstant = new Myconstant();
        try {

            ftpClient.connect(myconstant.getHostnametp(),myconstant.getPortAnInt());
            ftpClient.login(myconstant.getUerFtp(),myconstant.getPassFtp());


            ftpClient.setType(FTPClient.TYPE_BINARY);
            ftpClient.changeDirectory("No");
            ftpClient.upload(findFild(), new MyFTPDataTransferListener());

        } catch (Exception e) {
            e.printStackTrace();
            try {
                ftpClient.disconnect(true);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private File findFild() {
        String pathPhoto = findPathString();
        File file = new File(pathPhoto);

        return file;
    }

    private String findPathString() {
        String pathPhoto = null;
        String[] strings = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri,strings,
                null,null,null);

        if (cursor != null) {
            cursor.moveToFirst();
            int i = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            pathPhoto = cursor.getString(i);
        }else {
            pathPhoto = uri.getPath();
        }
        return pathPhoto;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {

            uri = data.getData();
            try {
                Bitmap bitmap;
                bitmap = BitmapFactory
                        .decodeStream(getActivity()
                                .getContentResolver()
                                .openInputStream(uri));

                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap,
                        800,600,true);
                imageView.setImageBitmap(bitmap1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_plantpicture, container, false);
        return view;
    }
    public class MyFTPDataTransferListener implements FTPDataTransferListener {

        @Override
        public void started() {
            progressDialog.show();
        }

        @Override
        public void transferred(int i) {

        }

        @Override
        public void completed() {
            progressDialog.dismiss();
        }

        @Override
        public void aborted() {

        }

        @Override
        public void failed() {

        }
    }//MyFTPData
}
*/