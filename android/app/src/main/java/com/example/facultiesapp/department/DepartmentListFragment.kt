package com.example.facultiesapp.department

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.facultiesapp.MainFragment
import com.example.facultiesapp.R
import com.example.facultiesapp.databinding.FragmentDepartmentListBinding
import com.example.facultiesapp.getInitParams
import com.example.facultiesapp.models.Department
import com.example.facultiesapp.provideInitParams
import java.time.LocalDate
import java.time.temporal.TemporalQueries.localDate
import java.util.*

class DepartmentListFragment: MainFragment() {
    private lateinit var binding: FragmentDepartmentListBinding
    private lateinit var viewModel: DepartmentListViewModel
    var validationOK : Boolean = true

    private val adapter by lazy {
        DepartmentAdapter(
            onEditClick = { department ->
                navigator?.navigateToDepartmentCreateANDEdit(
                    department.faculty,
                    department.id
                )
            },
            onDeleteClick = { department ->
                viewModel.onDeleteClick(department)
            },
            onDepartmentClick = {
                sortByName()
                showMessageByToast("Сортировка по названию кафедры")
            }
        )
    }
    private fun sortByName() {
        adapter.items = adapter.items.sortedBy { it.department_name }
    }
    private val initParams: Department_List_Fragment_Init by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_department_list, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvDepartments.adapter = adapter
        binding.toolbar.setNavigationOnClickListener { super.closeFragment() }
        binding.tvTitle.text = initParams.facultyName
        binding.btnAdd.setOnClickListener {
            navigator?.navigateToDepartmentCreateANDEdit(initParams.facultyId)
        }
        viewModel = ViewModelProvider(this).get(DepartmentListViewModel::class.java)
        viewModel.getDepartments(initParams.facultyId)
        viewModel.departmentsLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }
    private fun updateUI(departmentFulls: List<Department>) {
        adapter.items = departmentFulls.sortedByDescending { it.department_name }
    }

    companion object {
        fun newInstance(tourListFragmentInitParams: Department_List_Fragment_Init): DepartmentListFragment =
            DepartmentListFragment().provideInitParams(tourListFragmentInitParams) as DepartmentListFragment
    }
}