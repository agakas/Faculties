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
import com.example.facultiesapp.provideInitParams
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

            val regex1 = "^(?!\\s)[-а-яА-Я ]*(?<!\\s)\$".toRegex()
            if(!regex1.matches(etNameDepartment.text.toString())){
                etNameDepartment.error = "Введите название кафедры"
                validationOK = false
            }

            val regex2 = "^[0-9]+\$".toRegex()
            if(!regex2.matches(etYear.text.toString())||etYear.text.toString().toInt() > 2023 || etYear.text.toString().toInt() < 1){
                etYear.error = "Введите правильный год"
                validationOK = false
            }

            val regex3 = "^[0-9]+\$".toRegex()
            if(!regex3.matches(etAuditorium.text.toString())){
                etAuditorium.error = "Введите номер аудитории (только цифры)"
                validationOK = false
            }

            val regex4 = "^(?!\\s)[-а-яА-Я ]*(?<!\\s)\$".toRegex()
            if(!regex4.matches(etBoss.text.toString())){
                etBoss.error = "Введите ФИО заведующего"
                validationOK = false
            }

            val regex5 = "^([8]{1}[0-9]{10})?\$".toRegex()
            if(!regex5.matches(etPhone.text.toString())){
                etPhone.error = "Телефон должен начинаться с 8 и состоять из 11 цифр"
                validationOK = false
            }

            val regex6 = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$".toRegex()
            if(!regex6.matches(etEmail.text.toString())){
                etEmail.error = "Неправильный формат email"
                validationOK = false
            }

            val regex7 =  "^[0-9]+\$".toRegex()
            if(!regex7.matches(etEmploy.text.toString())||etEmploy.text.toString().toInt() < 0 || etEmploy.text.toString().length > 4){
                etEmploy.error = "Введите кол-во сотрудников до 1000"
                validationOK = false
            }
            if(!regex7.matches(etBachalors.text.toString())||etBachalors.text.toString().toInt() < 0 || etBachalors.text.toString().length > 4){
                etBachalors.error = "Введите кол-во бакалавров до 1000"
                validationOK = false
            }
            if(!regex7.matches(etMasters.text.toString())||etMasters.text.toString().toInt() < 0 || etMasters.text.toString().length > 4){
                etMasters.error = "Введите кол-во магистров до 1000"
                validationOK = false
            }

            if(validationOK) {
                viewModel.saveDepartment(department)
                closeFragment()
            }
        }
    }
    private fun check(text:String):Boolean {

        val alphabet = arrayListOf<String>(
            "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
            "0","1","2","3","4","5","6","7","8","9","!","?","_", "."
        )
        var index = 0
        for (alphabet in alphabet) {
            if (text.contains(alphabet[index])) {
                return false
            }
        }
        return true
    }
    private fun updateUI(department: Department?) {
        department?.let {
            with(binding) {
                etNameDepartment.setText(department.department_name)
                etYear.setText(department.year)
                etAuditorium.setText(department.auditorium)
                etBoss.setText(department.boss)
                etPhone.setText(department.phone)
                etEmail.setText(department.email)
                etEmploy.setText(department.employee_count)
                etBachalors.setText(department.bachelors_count)
                etMasters.setText(department.master_count)
            }
        }
    }
    companion object {
        fun newInstance(initParams: Department_Info_Fragment_Init) =
            DepartmentInfoFragment().provideInitParams(initParams) as DepartmentInfoFragment
    }
}