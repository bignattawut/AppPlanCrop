package th.in.nattawut.plancrop.utility;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "http://192.168.1.106/android/php/";

    public static OrderService getService(){
        return RetrofitClient.getClient(API_URL).create(OrderService.class);
    }

}
