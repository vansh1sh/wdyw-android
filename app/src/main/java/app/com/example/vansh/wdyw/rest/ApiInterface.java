package app.com.example.vansh.wdyw.rest;


import java.util.Map;

import app.com.example.vansh.wdyw.model.BLoginRequest;
import app.com.example.vansh.wdyw.model.BLoginResponse;
import app.com.example.vansh.wdyw.model.BSignupRequest;
import app.com.example.vansh.wdyw.model.LSignupRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;


public interface ApiInterface {
    @POST("/v1/customer")
    Call<BSignupRequest> BSignUp(@Body BSignupRequest bsignUpRequest);
    @POST("/v1/customer/login")
    Call<BLoginResponse> B_LOGIN_REQUEST_CALL(@Body BLoginRequest bLoginRequest);
    @POST("/v1/lender")
    Call<LSignupRequest> L_SIGNUP_REQUEST_CALL(@Body LSignupRequest lSignupRequest);




}