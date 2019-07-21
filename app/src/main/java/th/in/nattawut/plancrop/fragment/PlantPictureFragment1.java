package th.in.nattawut.plancrop.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import th.in.nattawut.plancrop.R;
import th.in.nattawut.plancrop.utility.APIUtils;
import th.in.nattawut.plancrop.utility.OrderService;

import static android.app.Activity.RESULT_OK;

public class PlantPictureFragment1 extends Fragment {

    private static final int PERMISSION_WRITE_STORAGE = 10;
    Button btnUpload;
    private Uri uri;
    ImageView photoImageView,imageView;
    private static  final int IMAGE = 100;
    OrderService orderService;
    String mediaPath;

    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private String NoString,DatepictureString,DescriptionString,result;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //เช็คPermission ถ้าสูงกว่า6
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[] {
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, PERMISSION_WRITE_STORAGE);
        }

        orderService = APIUtils.getService();
        photoImageView = getView().findViewById(R.id.imagePhoto);
        //str1 = getView().findViewById(R.id.filename1);

        imageView = getView().findViewById(R.id.imvGallery);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 0);
            }
        });
        btnUpload = getView().findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                File file = new File(mediaPath);
//                RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
//                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
//                ResponseBody filename = ResponseBody.create(MediaType.parse("text/plain"),file.getName());
//
//                Call<FileInfo> call = orderService.uploadFile1(fileToUpload,filename);
//                call.enqueue(new Callback<FileInfo>() {
//                    @Override
//                    public void onResponse(Call<FileInfo> call, Response<FileInfo> response) {
//                        if (response.isSuccessful()) {
//                            Toast.makeText(getActivity(),"อัปโหลอดรูปภาพสำเร็จ",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<FileInfo> call, Throwable t) {
//                        Toast.makeText(getActivity(),"ERROR: " + t.getMessage(),Toast.LENGTH_SHORT).show();
//
//                    }
//                });
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(getActivity(),"ีunable to choose image",Toast.LENGTH_SHORT).show();
                return;
            }
            Uri imageUri = data.getData();
            mediaPath = getPathFromUri(imageUri);
        }
    }

    private String getPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getActivity(),uri,projection,null,null,null);
        Cursor cursor = loader.loadInBackground();
        int colume = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colume);
        cursor.close();
        return result;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_plantpicture, container, false);
        return view;

    }
}
