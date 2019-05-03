package th.in.nattawut.plancrop.utility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class AddlLogin extends AsyncTask<String, Void, String> {

    private Context context;

    public AddlLogin(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("username", strings[0])
                    .add("passwd",strings[1])
                    .build();
            Request.Builder builder = new Request.Builder();
            //Request request = builder.url(strings[2])
            Request request = builder.url("http://192.168.1.118/android/php/memberlogin.php")
                    .post(requestBody).build();
            Response response=okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}

