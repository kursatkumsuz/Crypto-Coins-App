package com.kursatdrhistoryapp.cryptocoins.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kursatdrhistoryapp.cryptocoins.R
import com.kursatdrhistoryapp.cryptocoins.adapter.RecyclerViewAdapter
import com.kursatdrhistoryapp.cryptocoins.databinding.FragmentFeedBinding
import com.kursatdrhistoryapp.cryptocoins.model.CoinModel
import com.kursatdrhistoryapp.cryptocoins.viewmodel.FeedViewModel


class FeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var viewModel: FeedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        viewModel.loadData()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.coinList.observe(viewLifecycleOwner, Observer { coin ->
            coin?.let {
                adapter = RecyclerViewAdapter(coin)
                binding.recyclerView.adapter = adapter
            }
        })
    }

}