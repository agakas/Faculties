package com.example.facultiesapp

import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

open class MainFragment: Fragment() {
    protected open var navigator: Navigator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = context as Navigator
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                closeFragment()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
    open fun closeFragment() {
        (requireActivity() as Navigator).exit(this)
    }

    override fun onDestroy() {
        navigator = null
        super.onDestroy()
    }

    fun showMessageByToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), message, duration).show()
    }
}