package com.glmreader.android.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glmreader.android.R
import com.glmreader.android.data.entity.ProjectEntity

class ProjectAdapter(
    private val projects: MutableList<ProjectEntity>,
    private val onProjectClick: (ProjectEntity) -> Unit
) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProjectName: TextView = view.findViewById(R.id.tvProjectName)
        val tvProjectDescription: TextView = view.findViewById(R.id.tvProjectDescription)
        val tvMeasurementCount: TextView = view.findViewById(R.id.tvMeasurementCount)
    }

    fun updateData(newData: List<ProjectEntity>) {
        projects.clear()
        projects.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_project, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projects[position]

        holder.tvProjectName.text = project.name
        holder.tvProjectDescription.text = project.description.ifEmpty { "Нет описания" }
        holder.tvMeasurementCount.text = "Нажмите для открытия"

        holder.itemView.setOnClickListener {
            onProjectClick(project)
        }
    }

    override fun getItemCount() = projects.size
}
