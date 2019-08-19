package com.example.minimoneybox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.minimoneybox.Model.AddTen;
import com.example.minimoneybox.Model.MoneyBox;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Indivisual_Account extends AppCompatActivity {
    private TextView accName;
    private TextView priceValue;
    private TextView moneyBox;
    private int productID;
    private int amount;
    private Button button;
    private ApiCall apiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indivisual__account);
        String text="";
        text+="Account Name:"+getIntent().getExtras().getString("ACCOUNTNAME");
        text+= getIntent().getExtras().getInt("PROID")+" ";
        accName=findViewById(R.id.name);
        priceValue=findViewById(R.id.PriceValue);
        moneyBox=findViewById(R.id.MoneyBox);
        button=findViewById(R.id.button);
        accName.setText(text);
        priceValue.setText("Price Value :"+getIntent().getExtras().getFloat("PLANVALUE"));
        moneyBox.setText("Money Box:"+getIntent().getExtras().getFloat("MONEYBOX"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTen();
            }
        });
    }

    private void addTen() {
        final String fullHead="Bearer "+getIntent().getExtras().getString("TOKEN");
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
        AddTen addTen1=new AddTen(10,getIntent().getExtras().getInt("PROID"));
        Call<MoneyBox> call=apiCall.addMO(addTen1);
        call.enqueue(new Callback<MoneyBox>() {
            @Override
            public void onResponse(Call<MoneyBox> call, Response<MoneyBox> response) {
                if(response.isSuccessful()){
                    moneyBox.setText("Money Box:"+response.body().getMoneybox());
                }
            }

            @Override
            public void onFailure(Call<MoneyBox> call, Throwable t) {

            }
        });
    }
}
