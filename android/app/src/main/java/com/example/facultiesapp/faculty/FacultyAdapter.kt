package com.example.facultiesapp.faculty

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.facultiesapp.R
import com.example.facultiesapp.models.Faculty

class FacultyAdapter (
    private val onFacultyClick: (Faculty) -> Unit,
    private val onEditFacultyClick: (Faculty) -> Unit,
    private val onDeleteFacultyClick: (Faculty) -> Unit
) : RecyclerView.Adapter<FacultyAdapter.ViewHolder>(){

    var items: List<Faculty> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.element_faculty, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)

        fun bind(faculty: Faculty) {
            tvName.text = faculty.faculty_name
            itemView.setOnClickListener {
                onFacultyClick(faculty)
            }
            itemView.setOnLongClickListener {
                showDialog(itemView.context, faculty)
                true
            }
        }

        private fun showDialog(context: Context, faculty: Faculty) {
            val items =
                arrayOf(
                    "Изменить",
                    "Удалить"
                )
            AlertDialog.Builder(context)
                .setItems(items) { _, item ->
                    when (item) {
                        0 -> {
                            onEditFacultyClick(faculty)
                        }
                        1 -> {
                            onDeleteFacultyClick(faculty)
                        }
                    }
                }
                .create()
                .show()
        }
    }
}