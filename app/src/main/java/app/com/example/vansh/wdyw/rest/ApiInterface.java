package app.com.example.vansh.wdyw.rest;

import com.vansh.resellerprofit.model.LoginResponse;
import com.vansh.resellerprofit.model.SignUpRequest;
import com.vansh.resellerprofit.model.SoldRequest;
import com.vansh.resellerprofit.model.SoldViewResponse;
import com.vansh.resellerprofit.model.StockRequest;
import com.vansh.resellerprofit.model.StockResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;


public interface ApiInterface {
    @POST("/signup")
    Call<SignUpRequest> SignUp(@Body SignUpRequest signUpRequest);

    @GET("/login")
    Call<LoginResponse> getResponse(@QueryMap Map<String, String> params);

    @POST("/stock")
    Call<StockRequest> Stock(@Body StockRequest stockRequest);

    @GET("/stock")
    Call<StockResponse> stockResponse(@QueryMap Map<String, String> params);

     @POST("/sold")
    Call<SoldRequest> Sold(@Body SoldRequest soldRequest);


    @GET("/sold")
    Call<SoldViewResponse> soldViewResonse(@QueryMap Map<String, String> params);



}