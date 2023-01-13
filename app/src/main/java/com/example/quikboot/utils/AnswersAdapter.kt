package com.example.quikboot.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.quikboot.data.models.Answer
import com.example.quikboot.data.models.Theme
import com.example.quikboot.databinding.AnswerItemBinding


class AnswersAdapter(
    private val answerList: MutableList<Answer>) : RecyclerView.Adapter<AnswerViewHolder>() {

    private lateinit var binding: AnswerItemBinding
    var selectedPosition = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        binding = AnswerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnswerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(answerList[position])

        holder.binding.radioButton.setChecked(position
                == selectedPosition);

        binding.radioButton.setOnCheckedChangeListener(
            CompoundButton.OnCheckedChangeListener { rButton, b ->
                answerList[position].isChecked = b
                if (b) {
                    selectedPosition = holder.adapterPosition
                    notifyDataSetChanged()
                }
            })
    }

    override fun getItemCount(): Int = answerList.size

    fun replaceAll(models: List<Answer>) {
        answerList.clear()
        answerList.addAll(models)
        notifyDataSetChanged()
    }

  fun getUserAnswers() : MutableList<Answer> {
      return answerList
  }

}

class AnswerViewHolder(
     val binding: AnswerItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(answer: Answer) {
        binding.answer = answer
    }
}