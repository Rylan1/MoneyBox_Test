package com.example.minimoneybox;

import com.example.minimoneybox.Model.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiCall {
    @POST("users/login")
    Call<authToken> crPost(@Body Post post);
    @GET("investorproducts")
    Call<Accounts>getAccount();
    @POST("oneoffpayments")
    Call<MoneyBox>addMO(@Body AddTen ten);
}
