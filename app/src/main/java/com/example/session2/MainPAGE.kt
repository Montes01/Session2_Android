package com.example.session2

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.session2.Constants.BASE_URL
import com.example.session2.ItemsRecycler.ItemAdapter
import com.example.session2.Models.BasicArticle
import com.example.session2.Models.User
import com.example.session2.Services.Services
import retrofit2.Call
import retrofit2.Callback
import com.example.session2.Models.Response as RES
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainPAGE: AppCompatActivity() {
    var adapter = ItemAdapter(listOf())
    lateinit var ItemList:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        Toast.makeText(this, "Bienvenido ${intent.extras?.getString("username")} usted es ${intent.extras?.getBoolean("gender")}", Toast.LENGTH_LONG).show()
        InitComponents()
    }

    private fun InitComponents(){
        ItemList = findViewById(R.id.ArticleList)
        ItemList.adapter = this.adapter
        ItemList.layoutManager = LinearLayoutManager(this)
        intent.extras?.getInt("ID")?.let { GetItems(it) }
    }

    private fun GetItems(ID:Int){
        var service = getRetrofit().create(Services::class.java).getArticles(ID)
        service.enqueue(object: Callback<RES<List<BasicArticle>>>{
            override fun onResponse( call: Call<RES<List<BasicArticle>>>, response: Response<RES<List<BasicArticle>>>) {
                if(response.isSuccessful){
                    adapter.list = response.body()?.data!!
                    adapter.notifyDataSetChanged()
                    Log.d("items", "$ID ${response.body()?.data}")
                } else {
                    Log.d("Hubo un error al cargar los datos", response.toString())
                }
            }

            override fun onFailure(
                call: Call<RES<List<BasicArticle>>>,
                t: Throwable
            ) {
                Toast.makeText(ItemList.context, "Error al cargar los datos", Toast.LENGTH_SHORT)
            }

        })



    }

    private fun getRetrofit() = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
}