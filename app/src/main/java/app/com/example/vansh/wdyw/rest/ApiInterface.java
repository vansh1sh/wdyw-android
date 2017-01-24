package app.com.example.vansh.wdyw.rest;


import java.util.Date;
import java.util.List;
import java.util.Map;

import app.com.example.vansh.wdyw.model.BLoginRequest;
import app.com.example.vansh.wdyw.model.BLoginResponse;
import app.com.example.vansh.wdyw.model.BSignupRequest;
import app.com.example.vansh.wdyw.model.BloanResponse;
import app.com.example.vansh.wdyw.model.LLoginRequest;
import app.com.example.vansh.wdyw.model.LLoginResponse;
import app.com.example.vansh.wdyw.model.LSignupRequest;
import app.com.example.vansh.wdyw.model.LenderData;
import app.com.example.vansh.wdyw.model.LenderDetails;
import app.com.example.vansh.wdyw.model.LenderProfileGet;
import app.com.example.vansh.wdyw.model.LenderProfileResponse;
import app.com.example.vansh.wdyw.model.LloanIdRequest;
import app.com.example.vansh.wdyw.model.LoanPostRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import static android.R.attr.id;


public interface ApiInterface {
    @POST("/v1/customer")
    Call<BSignupRequest> BSignUp(@Body BSignupRequest bsignUpRequest);
    @POST("/v1/customer/login")
    Call<BLoginResponse> B_LOGIN_REQUEST_CALL(@Body BLoginRequest bLoginRequest);
    @POST("/v1/lender/login")
    Call<LLoginResponse> L_LOGIN_REQUEST_CALL(@Body LLoginRequest lLoginResponse);

    @POST("/v1/offer")
    Call<LloanIdRequest> id(@Body LloanIdRequest lloanIdRequest);

    @POST("/v1/lender")
    Call<LSignupRequest> L_SIGNUP_REQUEST_CALL(@Body LSignupRequest lSignupRequest);

    @POST("/v1/loan")
    Call<LoanPostRequest> LoanPost(@Body LoanPostRequest loanPostRequest);

    @GET("/v1/lender/me")
    Call<LenderProfileGet> profile();




    @GET("/v1/loan")
    Call<BloanResponse> loandetails(
            @Query("city") String city,
            @Query("pageid") String pageid,
            @Query("loanAmt") String loanAmt);


    @GET("/v1/lender")
    Call<LenderData> lenderDetails(
            @Query("city") String city,
            @Query("pageid") String pageid,
            @Query("loanAmt") String loanAmt);





}