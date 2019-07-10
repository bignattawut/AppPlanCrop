package th.in.nattawut.plancrop.utility;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {

    @GET("selectorder.php")
    Call<List<Order>> getOrder(@Query("mid") String mid);

    @GET("selectplanfarmerandroid.php")
    Call<List<PlanFarmer>> getPlanFarmer(@Query("mid") String mid);

    @GET("selectplantfarmer.php")
    Call<List<PlantFarmer>> getPlantFarmer(@Query("mid") String mid,@Query("sdate") String sdate);

    Call<List<PlantFarmer>> getsdataFarmer(
            @Query("sdate") String sdate);

    @GET("selectfarmerandroid.php")
    Call<List<Farmer>> getFarmer(@Query("mid") String mid);

//    @GET("selectsite.php")
//    Call<List<Site>> getSite(@Query("mid") String mid);


    @POST("selectsite.php")
    @FormUrlEncoded
    Call<List<Site>> getSite(@Field("mid") String mid);


    @GET("plantreportall.php")
    Call<List<PlantReportall>> getPlantReportall(@Query("sdate") String sdate, @Query("edate") String edate);

    @GET("planresult.php")
    Call<List<PlanResult>> getPlanReport(@Query("pdate") String pdate);

    @GET("plantreport.php")
    Call<List<PlantReport>> getPlantReport(@Query("mid") String mid);

    @GET("plantresult.php")
    Call<List<PlantResult>> getPlantResult(@Query("sdate") String sdate,
                                           @Query("edate") String edate/*,
                                           @Query("sid") String sid,
                                           @Query("did") String did,
                                           @Query("pid") String pid,
                                           @Query("mid") String mid,
                                           @Query("tid") String tid,
                                           @Query("cid") String cid*/);

    @POST("memberlogin.php")
    @FormUrlEncoded
    Call<LoginResponse> getuserLogin(
            @Field("username") String username,
            @Field("passwd") String passwd
    );

}
