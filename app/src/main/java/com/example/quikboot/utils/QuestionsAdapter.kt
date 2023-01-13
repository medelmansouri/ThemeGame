package com.example.quikboot.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quikboot.data.models.Answer
import com.example.quikboot.data.models.Question
import com.example.quikboot.databinding.QuestionItemBinding

class QuestionsAdapter(
    private val questionList: MutableList<Question>) : RecyclerView.Adapter<QuestionViewHolder>() {

    private lateinit var binding: QuestionItemBinding
    val questionListTemp = questionList.map { it.copy() }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        binding = QuestionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(questionList[position])
        questionListTemp[position].answer = (binding.rvAnswers.adapter as AnswersAdapter).getUserAnswers() as ArrayList<Answer>
    }

    override fun getItemCount(): Int = questionList.size

    fun replaceAll(models: List<Question>) {
        questionList.clear()
        questionList.addAll(models)
        notifyDataSetChanged()
    }

    fun getUserAnswers(): List<Question> {
        return questionListTemp
    }

}

class QuestionViewHolder(
    private val binding: QuestionItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(question: Question) {
        binding.question = question
        binding.rvAnswers.adapter = AnswersAdapter(mutableListOf())
    }
}