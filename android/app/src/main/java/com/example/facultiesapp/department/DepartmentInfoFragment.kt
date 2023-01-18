package com.example.facultiesapp.department

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.facultiesapp.MainFragment
import com.example.facultiesapp.R
import com.example.facultiesapp.databinding.FragmentDepartmentInfoEditBinding
import com.example.facultiesapp.getInitParams
import com.example.facultiesapp.models.Department
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class DepartmentInfoFragment: MainFragment() {
    private lateinit var binding: FragmentDepartmentInfoEditBinding
    private lateinit var viewModel: DepartmentInfoViewModel
    private val initParams: Department_Info_Fragment_Init by lazy { getInitParams() }
    var validationOK : Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_department_info_edit,
                container,
                false
            )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { closeFragment() }
        binding.btnSave.setOnClickListener {
            onSaveClick()
        }
        viewModel = ViewModelProvider(this).get(DepartmentInfoViewModel::class.java)
        initParams.id?.let {
            viewModel.getDepartment(it)
        }
        viewModel.departmentLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }
    private fun onSaveClick() {
        with(binding) {


            val department = Department(
                id = 0,
                faculty = initParams.faculty,
                department_name = etNameDepartment.text.toString(),
                year = etYear.text.toString(),
                auditorium = etAuditorium.text.toString(),
                boss = etBoss.text.toString(),
                phone = etPhone.text.toString(),
                email = etEmail.text.toString(),
                employee_count = etEmploy.text.toString(),
                bachelors_count = etBachalors.text.toString(),
                master_count = etMasters.text.toString(),
            )

            validationOK = true
            var pravdat = false

            try {
                val date = LocalDate.parse(dpSaleDate.text.toString())
                pravdat = true
            }
            catch (e: Exception){

            }

            if(etNameTour == null || etNameTour.text.toString() == "" || check(etNameTour.text.toString()) == false){
                etNameTour.error = "Неправильный ввод названия тура"
                validationOK = false
            }

            if(etCountry == null ||  etCountry.text.toString() == "" || check(etCountry.text.toString()) == false) {
                etCountry.error = "Неправильный ввод страны"
                validationOK = false
            }

            if(etKolvo == null || etKolvo.text.toString() == "" || check2(etKolvo.text.toString()) == false) {
                etKolvo.error = "Неправильный ввод количества билетов"
                validationOK = false
            }




            if(etHotelName == null || etHotelName.text.toString() == "" || check(etHotelName.text.toString()) == false) {
                etHotelName.error = "Ошибка ввода названия отеля"
                validationOK = false
            }

            if(etKolvoDays == null || etKolvoDays.text.toString() == "" || check2(etKolvoDays.text.toString()) == false) {
                etKolvoDays.error = "Ошибка ввода количества дней"
                validationOK = false
            }

            if(etSumma == null || etSumma.text.toString() == "" || check2(etSumma.text.toString()) == false) {
                etSumma.error = "Ошибка ввода общей суммы"
                validationOK = false
            }
            if(etRate == null || etRate.text.toString() == "" || check2(etRate.text.toString()) == false) {
                etRate.error = "Ошибка ввода оценки тура"
                validationOK = false
            }

            if(dpSaleDate == null || dpSaleDate.text.toString() == "" || pravdat == false) {
                dpSaleDate.error = "Неправильный формат даты"
                validationOK = false
            }

            if(validationOK) {
                viewModel.saveTour(tour)
                closeFragment()
            }

        }
    }
}