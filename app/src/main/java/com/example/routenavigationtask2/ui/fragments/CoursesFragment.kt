package com.example.routenavigationtask2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.routenavigationtask2.R
import com.example.routenavigationtask2.databinding.FragmentCoursesBinding
import com.example.routenavigationtask2.models.courses
import com.example.routenavigationtask2.ui.adapters.CourseRvAdapter
import com.example.routenavigationtask2.util.Constants

class CoursesFragment : Fragment() {
    private lateinit var mBinding : FragmentCoursesBinding
    private val mCoursesRVAdapter by lazy {
        CourseRvAdapter(
            onCourseClick = {course ->
                val bundle = Bundle().apply { putSerializable(Constants.COURSE_ARG , course) }
                findNavController().navigate(R.id.action_coursesFragment_to_courseDetailsFragment,args = bundle)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentCoursesBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpCoursesRV()
        populateCoursesInRV()
    }

    private fun setUpCoursesRV(){
        mBinding.coursesRv.apply {
            adapter = mCoursesRVAdapter
            layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        }
    }

    private fun populateCoursesInRV(){
        mCoursesRVAdapter.asyncListDiffer.submitList(courses)
    }
}