package com.example.quikboot.ui.listFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quikboot.R
import com.example.quikboot.data.datasources.ApiService
import com.example.quikboot.data.repositories.GameRepository
import com.example.quikboot.databinding.FragmentListBinding
import com.example.quikboot.utils.ListAdapter


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var listViewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)

        val retrofitService = ApiService.getInstance()
        val gameRepository = GameRepository(retrofitService)

        listViewModel = ViewModelProvider(this, ListViewModel.Factory(gameRepository))[ListViewModel::class.java]
        listViewModel.getGameInfo()


        listViewModel.gameInfo.observe(viewLifecycleOwner) {
            if (it != null && it.error == null) {
                val itemClickListener = ListAdapter.OnItemClickListener { theme ->
                    val bundle = Bundle()
                     bundle.putSerializable("theme",theme)
                    findNavController().navigate(R.id.action_ListFragment_to_GameFragment,bundle)
                }
                val personAdapter = ListAdapter(it.theme, itemClickListener)
                binding.rvPerson.layoutManager = LinearLayoutManager(activity)
                binding.rvPerson.adapter = personAdapter
            } else {
                Toast.makeText(requireContext(),"La reponse JSON contient des erreurs",Toast.LENGTH_LONG).show()
            }
        }

        binding.lifecycleOwner = this
        binding.viewmodel = listViewModel
        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}