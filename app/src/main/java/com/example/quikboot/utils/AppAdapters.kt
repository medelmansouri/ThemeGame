package com.example.quikboot.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quikboot.data.models.Answer
import com.example.quikboot.data.models.Theme

@BindingAdapter("app:themes")
    fun setFragmentList(
        view: RecyclerView, themeList: List<Theme>
    ) {
        if (view.adapter is ListAdapter && themeList != null) {
            (view.adapter as ListAdapter?)?.replaceAll(themeList)
        }
    }

@BindingAdapter("app:answers")
fun setAnswersList(
    view: RecyclerView, answerList: List<Answer>
) {
    if (view.adapter is AnswersAdapter && answerList != null) {
        (view.adapter as AnswersAdapter?)?.replaceAll(answerList)
    }
}