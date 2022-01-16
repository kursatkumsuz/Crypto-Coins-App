package com.kursatdrhistoryapp.cryptocoins.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kursatdrhistoryapp.cryptocoins.R
import com.kursatdrhistoryapp.cryptocoins.databinding.RowRecyclerviewBinding
import com.kursatdrhistoryapp.cryptocoins.model.CoinModel

class RVAdapter (private val coinList : ArrayList<CoinModel>) : RecyclerView.Adapter<RVAdapter.RowHolder>() {


    class RowHolder (val binding: RowRecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {

        val binding = RowRecyclerviewBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return RowHolder(binding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        holder.binding.nameText.text = coinList.get(position).currency
        holder.binding.priceText.text = "$"+ coinList.get(position).price

    }

    override fun getItemCount(): Int {
        return coinList.size
    }
}