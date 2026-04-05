package com.glmreader.android.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glmreader.android.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.widget.Toolbar
import android.widget.TextView
import com.glmreader.android.data.entity.ProjectEntity
import com.glmreader.android.ui.viewmodel.ProjectListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Экран списка проектов. Первый экран приложения.
 */
class ProjectListActivity : AppCompatActivity() {

    private val viewModel: ProjectListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvEmpty: TextView
    private lateinit var adapter: ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_list)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerView)
        tvEmpty = findViewById(R.id.tvEmpty)
        val fabAddProject: FloatingActionButton = findViewById(R.id.fabAddProject)

        // Адаптер
        adapter = ProjectAdapter(mutableListOf()) { project ->
            openProject(project)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Наблюдаем за проектами
        lifecycleScope.launch {
            viewModel.projects.collectLatest { projects ->
                adapter.updateData(projects)
                tvEmpty.visibility = if (projects.isEmpty()) View.VISIBLE else View.GONE
            }
        }

        // FAB — создать проект
        fabAddProject.setOnClickListener {
            showCreateProjectDialog()
        }
    }

    private fun showCreateProjectDialog() {
        val editText = EditText(this).apply {
            hint = "Название проекта"
        }

        AlertDialog.Builder(this)
            .setTitle("Новый проект")
            .setView(editText)
            .setPositiveButton("Создать") { _, _ ->
                val name = editText.text.toString().trim()
                if (name.isNotEmpty()) {
                    viewModel.createProject(name)
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

    private fun openProject(project: ProjectEntity) {
        // Переход к списку измерений с projectId
        val intent = Intent(this, MeasurementListActivity::class.java).apply {
            putExtra("project_uuid", project.uuid)
            putExtra("project_name", project.name)
        }
        startActivity(intent)
    }
}
