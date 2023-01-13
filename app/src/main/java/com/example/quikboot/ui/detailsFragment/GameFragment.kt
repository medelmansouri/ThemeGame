package com.example.quikboot.ui.detailsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quikboot.R
import com.example.quikboot.data.datasources.ApiService
import com.example.quikboot.data.models.Question
import com.example.quikboot.data.models.Theme
import com.example.quikboot.data.repositories.GameRepository
import com.example.quikboot.databinding.FragmentGameBinding
import com.example.quikboot.ui.SharedViewModel
import com.example.quikboot.utils.QuestionsAdapter


class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val theme  = requireArguments().getSerializable("theme") as Theme
        val questionAdapter = QuestionsAdapter(theme.question)
        binding.rvQuestions.layoutManager = LinearLayoutManager(activity)
        binding.rvQuestions.adapter = questionAdapter

        binding.confirmButton.setOnClickListener {
            sharedViewModel.questionListWithAnswers = questionAdapter.getUserAnswers() as MutableList<Question>
            findNavController().navigate(R.id.action_GameFragment_to_ResultFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}