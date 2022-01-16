package com.kursatdrhistoryapp.cryptocoins.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kursatdrhistoryapp.cryptocoins.R
import com.kursatdrhistoryapp.cryptocoins.adapter.RVAdapter
import com.kursatdrhistoryapp.cryptocoins.databinding.ActivityMainBinding
import com.kursatdrhistoryapp.cryptocoins.model.CoinModel
import com.kursatdrhistoryapp.cryptocoins.service.CoinAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var coinList : ArrayList<CoinModel>? = null
    private var adapter : RVAdapter? = null
    private lateinit var displayList : ArrayList<CoinModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        displayList = ArrayList<CoinModel>()

        //RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        loadData()
        searchCoin()

    }

    private fun loadData()
    {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(CoinAPI::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<CoinModel>>
        {
            override fun onResponse(
                call: Call<List<CoinModel>>,
                response: Response<List<CoinModel>>
            ) {
                if(response.isSuccessful)
                {
                    response.body()?.let {

                        coinList = ArrayList(it)

                        coinList?.let {

                            displayList.addAll(it)
                            adapter = RVAdapter(displayList)
                            binding.recyclerView.adapter = adapter
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<CoinModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun searchCoin()
    {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isNotEmpty())
                {
                    displayList.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    coinList?.forEach {
                        if(it.currency.toLowerCase(Locale.getDefault()).contains(search))
                        {
                            displayList.add(it)
                        }
                    }
                    binding.recyclerView.adapter!!.notifyDataSetChanged()
                }  else
                {
                    displayList.clear()
                    loadData()
                    binding.recyclerView.adapter!!.notifyDataSetChanged()
                }
                return true
            }
        })
    }
}