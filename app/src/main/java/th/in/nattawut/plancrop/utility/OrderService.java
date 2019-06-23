package th.in.nattawut.plancrop.utility;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import th.in.nattawut.plancrop.utility.Order;

public interface OrderService {

    @GET("selectorder.php")
    Call<List<Order>> getOrder(@Query("mid") String mid);

    @GET("selectplanfarmerandroid.php")
    Call<List<PlanFarmer>> getPlanFarmer(@Query("mid") String mid);
}
