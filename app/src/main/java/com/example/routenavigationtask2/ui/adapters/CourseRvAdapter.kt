package com.example.routenavigationtask2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.routenavigationtask2.databinding.CourseCardItemBinding
import com.example.routenavigationtask2.models.Course

class CourseRvAdapter(
    val onCourseClick : (Course) -> Unit
) : RecyclerView.Adapter<CourseRvAdapter.CourseViewHolder>() {

    val diffUtil = object : DiffUtil.ItemCallback<Course>(){
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }
    }
    val asyncListDiffer = AsyncListDiffer(this,diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(
            CourseCardItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount() = asyncListDiffer.currentList.size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = asyncListDiffer.currentList[position]
        holder.bind(course)
    }

    inner class CourseViewHolder(private val mBinding : CourseCardItemBinding) : ViewHolder(mBinding.root){
        fun bind(course : Course){
            mBinding.courseImage.setImageResource(course.image)
            mBinding.courseButton.text = course.name
            mBinding.courseButton.setOnClickListener {
                onCourseClick(course)
            }
        }
    }
}