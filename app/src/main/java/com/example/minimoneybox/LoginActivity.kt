package com.example.minimoneybox

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import java.util.regex.Pattern
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.widget.TextView
import com.example.minimoneybox.Model.AllData
import com.example.minimoneybox.Model.Post
import com.example.minimoneybox.Model.authToken
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    lateinit var btn_sign_in : Button
    lateinit var til_email : TextInputLayout
    lateinit var et_email : EditText
    lateinit var til_password : TextInputLayout
    lateinit var et_password : EditText
    lateinit var til_name : TextInputLayout
    lateinit var et_name : EditText
    lateinit var pigAnimation : LottieAnimationView
    lateinit var apicall:ApiCall
    lateinit var tempdata: AllData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupViews()
        val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("AppId", "3a97b932a9d449c981b595")
                .addHeader("Content-Type", "application/json")
                .addHeader("appVersion", "5.10.0")
                .addHeader("apiVersion", "3.0.0").build()
            chain.proceed(request)
        }.build()
        val retrofit=Retrofit.Builder().baseUrl("https://api-test01.moneyboxapp.com/")
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
        apicall = retrofit.create(ApiCall::class.java)

    }

    override fun onStart() {
        super.onStart()
        setupAnimation()
    }

    private fun setupViews() {
        btn_sign_in = findViewById(R.id.btn_sign_in)
        til_email = findViewById(R.id.til_email)
        et_email = findViewById(R.id.et_email)
        til_password = findViewById(R.id.til_password)
        et_password = findViewById(R.id.et_password)
        til_name = findViewById(R.id.til_name)
        et_name = findViewById(R.id.et_name)
        pigAnimation = findViewById(R.id.animation)

        btn_sign_in.setOnClickListener {
            if (allFieldsValid()) {
                Toast.makeText(this, R.string.input_valid, Toast.LENGTH_LONG).show()
            }
        }


    }

    private fun allFieldsValid() : Boolean {
        var allValid = false
        var score = 0
        if (Pattern.matches(EMAIL_REGEX, et_email.text.toString())) {
            score++

        } else {
            til_email.error = getString(R.string.email_address_error)

        }

        if (Pattern.matches(PASSWORD_REGEX, et_password.text.toString())) {

            score++
        } else {
            til_password.error = getString(R.string.password_error)
        }
        if (Pattern.matches(NAME_REGEX, et_name.text.toString())||et_name.text.toString().isEmpty()) {

            score++
        } else {
                til_name.error = getString(R.string.full_name_error)
        }
        if(score==3){
            allValid = true
            createPost(et_email.text.toString(),et_password.text.toString())
            tempdata = AllData(
                et_email.text.toString(),
                et_password.text.toString(),
                et_name.text.toString()
            )
        }
        return allValid
    }

    private fun setupAnimation() {
        pigAnimation.setMinAndMaxFrame(firstAnim.first, firstAnim.second)
        pigAnimation.playAnimation()
        pigAnimation.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                pigAnimation.setMinAndMaxFrame(secondAnim.first, secondAnim.second)
                pigAnimation.playAnimation()
            }
        })

    }

    companion object {
        val EMAIL_REGEX = "[^@]+@[^.]+\\..+"
        val NAME_REGEX = "[a-zA-Z]{6,30}"
        val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[A-Z]).{10,50}$"
        val firstAnim = 0 to 109
        val secondAnim = 131 to 158
    }

    private fun createPost( email:String, password:String) {
        val post = Post(email, password, "ANYTHING")
        val call:Call<authToken> = apicall.crPost(post)
        call.enqueue(object : Callback<authToken> {
            override fun onResponse(call: Call<authToken>, response: Response<authToken>) {
                if (!response.isSuccessful) {
                }
                else{
                tempdata.setTolken(response.body()!!.session.bearerToken)
                val intent=Intent(this@LoginActivity,UserActivity::class.java)
                    intent.putExtra("TOKEN",tempdata.getTolken())
                    intent.putExtra("NAME",tempdata.getUsername())
                startActivity(intent)
                }
            }

            override fun onFailure(call: Call<authToken>, t: Throwable) {

            }
        })

    }
}
