package com.example.quikboot.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quikboot.data.models.Theme
import com.example.quikboot.databinding.ThemeItemBinding

class ListAdapter(
    private val themeList: MutableList<Theme>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<LargeNewsViewHolder>() {

    private lateinit var binding: ThemeItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LargeNewsViewHolder {
        binding = ThemeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LargeNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LargeNewsViewHolder, position: Int) {
        holder.bind(themeList[position], itemClickListener)
    }

    override fun getItemCount(): Int = themeList.size

    fun replaceAll(models: List<Theme>) {
        themeList.clear()
        themeList.addAll(models)
        notifyDataSetChanged()
    }

    fun interface OnItemClickListener {
        fun onItemClick(item: Theme)
    }
}

class LargeNewsViewHolder(
    private val binding: ThemeItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(theme: Theme, listener : ListAdapter.OnItemClickListener) {
        binding.theme = theme
        binding.root.setOnClickListener { view: View? ->
            listener.onItemClick(
                theme
            )
        }

    }
}