package com.example.quikboot.ui.resultFragment

import android.content.Context
import android.content.Context.MODE_APPEND
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quikboot.R
import com.example.quikboot.data.datasources.ApiService
import com.example.quikboot.data.repositories.GameRepository
import com.example.quikboot.databinding.FragmentResultBinding
import com.example.quikboot.ui.SharedViewModel


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonBack.setOnClickListener {
                findNavController().navigate(R.id.action_ResultFragment_ListFragment)
        }
        binding.tvName.text = getsavedUserName()
        binding.tvScore.text = sharedViewModel.calculateResult()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getsavedUserName() : String? {
        val sharedPref: SharedPreferences? =
            activity?.getSharedPreferences("application", Context.MODE_PRIVATE)
       return sharedPref?.getString("user_name", "")
    }

}