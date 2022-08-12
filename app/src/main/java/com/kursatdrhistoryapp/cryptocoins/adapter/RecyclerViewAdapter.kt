package com.kursatdrhistoryapp.cryptocoins.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.kursatdrhistoryapp.cryptocoins.databinding.RowRecyclerviewBinding
import com.kursatdrhistoryapp.cryptocoins.fragment.FeedFragmentDirections
import com.kursatdrhistoryapp.cryptocoins.model.CoinModel

class RecyclerViewAdapter (private val coinList : List<CoinModel>) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {


    class RowHolder (val binding: RowRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {

        val binding = RowRecyclerviewBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return RowHolder(binding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        val anim = AnimationUtils.loadAnimation(holder.itemView.context , android.R.anim.fade_in)
        holder.itemView.startAnimation(anim)

        holder.binding.nameText.text = coinList[position].currency
        holder.binding.priceText.text = coinList[position].price

        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToPopUpFragment(coinList[position].primaryKey)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return coinList.size
    }
}