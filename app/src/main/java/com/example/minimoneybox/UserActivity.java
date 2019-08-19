package com.example.minimoneybox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.minimoneybox.Model.Accounts;
import com.example.minimoneybox.Model.Product;
import com.example.minimoneybox.Model.ProductResponses;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private TextView tv;
    private String token;
    private String username;
    private ApiCall apiCall;

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        tv=findViewById(R.id.textView4);
        token=getIntent().getExtras().getString("TOKEN");
        username=getIntent().getExtras().getString("NAME");
        tv.setText("Welcome "+username);
        listView=findViewById(R.id.listView);
        final String fullHead="Bearer "+token;
        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request=chain.request().newBuilder()
                        .addHeader("AppId","3a97b932a9d449c981b595")
                        .addHeader("Content-Type","application/json")
                        .addHeader("appVersion","5.10.0")
                        .addHeader("apiVersion","3.0.0")
                        .addHeader("Authorization",fullHead).build();
                return chain.proceed(request);
            }
        }).build();
        Retrofit rf=new Retrofit.Builder()
                .baseUrl("https://api-test01.moneyboxapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        apiCall=rf.create(ApiCall.class);
        getStuff();
    }

    private void getStuff() {

        Call<Accounts> call=apiCall.getAccount();
        call.enqueue(new Callback<Accounts>() {
            @Override
            public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                if(!response.isSuccessful()){

                }
                else{
                    ArrayList<ProductResponses> productResponses=response.body().getProductResponses();
                    displayeveryting(productResponses);
                }
            }

            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {

            }
        });
    }

    private void displayeveryting(final ArrayList<ProductResponses> productResponses) {
        ListViewAdapter listViewAdapter=new ListViewAdapter(UserActivity.this,productResponses);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ProductResponses temp= productResponses.get(position);
                Intent intent=new Intent(UserActivity.this,Indivisual_Account.class);
                intent.putExtra("ACCOUNTNAME",temp.getProduct().getName());
                intent.putExtra("PLANVALUE",temp.getPlanValue());
                intent.putExtra("MONEYBOX",temp.getMoneybox());
                intent.putExtra("PROID",temp.getId());
                intent.putExtra("TOKEN",token);
                startActivity(intent);
            }
        });
    }

}
