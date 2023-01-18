package com.example.facultiesapp.faculty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.facultiesapp.MainFragment
import com.example.facultiesapp.R
import com.example.facultiesapp.databinding.FragmentFacultyInfoEditBinding
import com.example.facultiesapp.getInitParams
import com.example.facultiesapp.models.Faculty
import com.example.facultiesapp.provideInitParams

class FacultyInfoFragment: MainFragment() {
    private lateinit var binding: FragmentFacultyInfoEditBinding
    private lateinit var viewModel: FacultyInfoViewModel
    private val initParams: Faculty_Info_Fragment_Init by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_faculty_info_edit,
                container,
                false
            )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { closeFragment() }
        binding.btnSave.setOnClickListener {
            binding.etName.clearFocus()
            val name = binding.etName.text.toString()

            if (name.isBlank() || check(name)==false) {
                showMessageByToast("Введите название")
                return@setOnClickListener
            }
            viewModel.saveFaculty(Faculty(faculty_name = name, id = 0))
            closeFragment()
        }

        viewModel = ViewModelProvider(this).get(FacultyInfoViewModel::class.java)
        initParams.id?.let {
            viewModel.getFaculty(it)
        }
        viewModel.facultyLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }
    private fun updateUI(faculty: Faculty?) {
        faculty?.let {
            binding.etName.setText(faculty.faculty_name)
        }
    }
    companion object {
        fun newInstance(initParams: Faculty_Info_Fragment_Init) =
            FacultyInfoFragment().provideInitParams(initParams) as FacultyInfoFragment
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
}