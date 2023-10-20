package com.example.session2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.session2.Models.User
import com.example.session2.Models.Response as RES
import com.example.session2.Models.UserLogin
import com.example.session2.Services.Services
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val BASE_URL = "http://10.0.2.2:5170/"
    lateinit var LoginButton: Button
    lateinit var password: EditText
    lateinit var username:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        InitComponents()
    }

    private fun InitComponents(){
        LoginButton = findViewById(R.id.LoginButton)
        password = findViewById(R.id.Password)
        username = findViewById(R.id.Username)
        LoginButton.setOnClickListener{
            Login(username.text.toString(), password.text.toString())
        }

    }

    private fun Login(username:String, password:String){



        var service = GetRetrofit().create(Services::class.java).login(UserLogin(username, password))
        service.enqueue(object: Callback<RES<User>> {
            override fun onResponse(call: Call<RES<User>>, response: Response<RES<User>>) {
                if (response.isSuccessful) {
                    val mainPage = Intent(LoginButton.context, MainPAGE::class.java)
                    mainPage.putExtra("username", response.body()?.data?.username)
                    mainPage.putExtra("gender", response.body()?.data?.gender)
                    mainPage.putExtra("ID", response.body()?.data?.id)
                    startActivity(mainPage)
                    Log.d("This is the user", "${response.body()?.data}")
                } else {
                    Log.d("Fallo", "")
                }
            }

            override fun onFailure(call: Call<RES<User>>, t: Throwable) {
                Log.d("Failed", t.message.toString())
            }
        })
    }


    private fun GetRetrofit() = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()).build()

}