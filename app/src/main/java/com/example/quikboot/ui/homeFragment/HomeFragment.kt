package com.example.quikboot.ui.homeFragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quikboot.R
import com.example.quikboot.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonConfirm.setOnClickListener {
            if(binding.etNom.text.isNotEmpty()){
                saveUserName(binding.etNom.text.toString())
                 findNavController().navigate(R.id.action_HomeFragment_to_FirstFragment)
            } else {
                binding.etNom.setError("Veuillez entrer votre nom")
            }
        }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun saveUserName(name: String) {
        val sharedPref: SharedPreferences? =
            activity?.getSharedPreferences("application", Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()
        editor?.putString("user_name", name)
        editor?.apply()
    }

}