package com.example.facultiesapp.department

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.facultiesapp.R
import com.example.facultiesapp.models.Department

class DepartmentAdapter (
    private val onEditClick: (Department) -> Unit,
    private val onDeleteClick: (Department) -> Unit,
    private val onDepartmentClick: (Department) -> Unit,
):  RecyclerView.Adapter<DepartmentAdapter.ViewHolder>() {
    var items: List<Department> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.element_department, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDepartment = itemView.findViewById<TextView>(R.id.tvDepartment)
        private val tvDepartmentTitle =
            itemView.findViewById<TextView>(R.id.tvDepartmentTitle)
        private val tvOpenInfo = itemView.findViewById<TextView>(R.id.tvOpenInfo)
        private val tvAdditionalInfo = itemView.findViewById<TextView>(R.id.tvAdditionalInfo)

        fun bind(department: Department) {
            tvDepartment.text = department.department_name

           /* itemView.setOnLongClickListener {
                Toast.makeText(itemView.context,
                    "Сумма: ${tour.summa}",
                    Toast.LENGTH_LONG
                ).show()
                true
            }*/

            tvDepartmentTitle.setOnLongClickListener {
                onDepartmentClick(department)
                true
            }

            tvOpenInfo.setOnClickListener {
                onEditClick(department)
            }

            tvAdditionalInfo.setOnClickListener {
                showDialog(itemView.context, department)
                true
            }
        }

        private fun showDialog(context: Context, department: Department) {

            AlertDialog.Builder(context)
                .setTitle("Удаление")
                .setMessage("Вы действительно хотите удалить кафедру?")
                .setPositiveButton("Да")
                {
                        _, _ -> onDeleteClick(department)
                }
                .setNegativeButton("Нет", null)
                .setCancelable(true)
                .create()
                .show()

        }
    }
}