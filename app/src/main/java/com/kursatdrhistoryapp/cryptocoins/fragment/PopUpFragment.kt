package com.kursatdrhistoryapp.cryptocoins.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kursatdrhistoryapp.cryptocoins.databinding.FragmentPopUpBinding
import com.kursatdrhistoryapp.cryptocoins.viewmodel.PopUpViewModel


class PopUpFragment : DialogFragment() {

    private var _binding : FragmentPopUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : PopUpViewModel
    private var key = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentPopUpBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            key = PopUpFragmentArgs.fromBundle(it).key
        }
        viewModel = ViewModelProvider(this)[PopUpViewModel::class.java]
        viewModel.getFromRoom(key)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.selectedCoin.observe(viewLifecycleOwner , Observer {coin ->
            coin?.let {
                binding.nameTextView.text = coin.currency
                binding.priceTextView.text = coin.price
            }
        })
    }

}