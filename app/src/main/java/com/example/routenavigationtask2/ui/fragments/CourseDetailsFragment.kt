package com.example.routenavigationtask2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.routenavigationtask2.databinding.FragmentCourseDetailsBinding
import com.example.routenavigationtask2.models.Course

import com.example.routenavigationtask2.util.Constants

class CourseDetailsFragment : Fragment() {
    private lateinit var mCourse: Course
    private lateinit var mBinding : FragmentCourseDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mCourse = it.getSerializable(Constants.COURSE_ARG) as Course
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentCourseDetailsBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
    }

    private fun bindUI(){
        mBinding.imageView.setImageResource(mCourse.image)
        mBinding.courseDescription.text = mCourse.description
    }
}