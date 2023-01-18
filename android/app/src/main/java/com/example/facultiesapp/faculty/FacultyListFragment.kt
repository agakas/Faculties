package com.example.facultiesapp.faculty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.facultiesapp.MainFragment
import com.example.facultiesapp.R
import com.example.facultiesapp.databinding.FragmentFacultyListBinding
import com.example.facultiesapp.models.Faculty

class FacultyListFragment: MainFragment() {
    private lateinit var binding: FragmentFacultyListBinding
    private lateinit var viewModel: FacultyListViewModel

    private val adapter by lazy {
        FacultyAdapter(
            onFacultyClick = { faculty ->
                navigator?.navigateToFaculty(faculty)
            },
            onEditFacultyClick = { faculty ->
                navigator?.navigateToFacultyCreateANDEdit(faculty.id)
            },
            onDeleteFacultyClick = { faculty ->
                viewModel.onDeleteFacultyClick(faculty)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_faculty_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFaculties.adapter = adapter
        binding.btnAdd.setOnClickListener { navigator?.navigateToFacultyCreateANDEdit() }
        viewModel = ViewModelProvider(this).get(FacultyListViewModel::class.java)
        viewModel.getFaculties()
        viewModel.facultiesLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }
    private fun updateUI(faculties: List<Faculty>) {
        adapter.items = faculties.sortedBy { it.faculty_name }
    }
    companion object {
        fun newInstance() = FacultyListFragment()
    }
}