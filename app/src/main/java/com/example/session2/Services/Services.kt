package com.example.session2.Services

import com.example.session2.Models.BasicArticle
import com.example.session2.Models.Response
import com.example.session2.Models.User
import com.example.session2.Models.UserLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Services {
    @POST("User/Login")
    fun login(@Body user: UserLogin): Call<Response<User>>


    @GET("User/Articles")
    fun getArticles(@Query("ID") ID:Int):Call<Response<List<BasicArticle>>>

}