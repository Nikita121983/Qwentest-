package com.glmreader.android.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.glmreader.android.R
import com.glmreader.android.data.entity.MeasurementEntity
import com.glmreader.android.protocol.BlePacketParser
import com.glmreader.android.protocol.InclinoLogic
import java.text.SimpleDateFormat
import java.util.*

class MeasurementAdapter(
    private val measurements: MutableList<MeasurementEntity>,
    private val onDelete: (String) -> Unit,
    private val onSelectionChanged: ((Set<String>) -> Unit)? = null
) : RecyclerView.Adapter<MeasurementAdapter.ViewHolder>() {

    private val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale("ru"))
    private val selectionMode = mutableSetOf<String>()
    private var isSelectionMode = false

    /** Получить текущий список измерений (для экспорта) */
    fun getMeasurements(): List<MeasurementEntity> = measurements.toList()

    fun updateData(newData: List<MeasurementEntity>) {
        measurements.clear()
        measurements.addAll(newData)
        notifyDataSetChanged()
    }

    fun isSelectionMode(): Boolean = isSelectionMode

    fun getSelectedUuids(): Set<String> = selectionMode.toSet()

    fun clearSelection() {
        selectionMode.clear()
        notifyDataSetChanged()
        onSelectionChanged?.invoke(emptySet())
    }

    fun selectAll() {
        selectionMode.clear()
        selectionMode.addAll(measurements.map { it.uuid })
        notifyDataSetChanged()
        onSelectionChanged?.invoke(selectionMode.toSet())
    }

    fun deleteSelected() {
        selectionMode.forEach { uuid ->
            onDelete(uuid)
        }
        clearSelection()
    }

    fun toggleSelectionMode(enabled: Boolean) {
        isSelectionMode = enabled
        if (!enabled) {
            selectionMode.clear()
        }
        notifyDataSetChanged()
        onSelectionChanged?.invoke(if (enabled) emptySet() else emptySet())
    }

    private fun toggleSelection(position: Int, holder: ViewHolder) {
        val uuid = measurements[position].uuid
        if (selectionMode.contains(uuid)) {
            selectionMode.remove(uuid)
        } else {
            selectionMode.add(uuid)
        }
        holder.cbSelected.isChecked = selectionMode.contains(uuid)
        onSelectionChanged?.invoke(selectionMode.toSet())
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvDateTime: TextView = view.findViewById(R.id.tvDateTime)
        val ivTypeIcon: ImageView = view.findViewById(R.id.ivTypeIcon)
        val tvValue: TextView = view.findViewById(R.id.tvValue)
        val cbSelected: CheckBox = view.findViewById(R.id.cbSelected)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_measurement, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val m = measurements[position]
        val type = BlePacketParser.MeasurementType.fromDevMode(m.measurementType)
        val inclino = InclinoLogic.determineType(m.angleDeg, m.resultValue)

        // Название замера
        holder.tvName.text = type.displayName

        // Дата и время
        holder.tvDateTime.text = dateFormat.format(Date(m.timestamp))

        // Значение
        holder.tvValue.text = when (m.measurementType) {
            8 -> "${m.angleDeg}°"
            4 -> "%.2f м²".format(m.resultValue)
            7 -> "%.2f м³".format(m.resultValue)
            else -> "%.3f м".format(m.resultValue)
        }

        // Иконка типа
        holder.ivTypeIcon.setImageResource(when (m.measurementType) {
            1 -> R.drawable.ic_distance
            2 -> R.drawable.ic_continuous
            4 -> R.drawable.ic_area
            7 -> R.drawable.ic_volume
            8 -> R.drawable.ic_angle
            10 -> R.drawable.ic_indirect_height
            11 -> R.drawable.ic_indirect_length
            12, 13 -> R.drawable.ic_double_indirect
            else -> R.drawable.ic_distance
        })

        // Selection mode
        holder.cbSelected.visibility = if (isSelectionMode) View.VISIBLE else View.GONE
        holder.cbSelected.isChecked = selectionMode.contains(m.uuid)
        val selectionColor = ContextCompat.getColor(holder.itemView.context, R.color.selection_highlight)
        holder.itemView.setBackgroundColor(
            if (selectionMode.contains(m.uuid)) selectionColor else android.graphics.Color.TRANSPARENT
        )

        // Клик — toggle selection или открыть детали
        holder.itemView.setOnClickListener {
            if (isSelectionMode) {
                toggleSelection(position, holder)
            }
        }

        // Долгое нажатие — включить selection mode
        holder.itemView.setOnLongClickListener {
            if (!isSelectionMode) {
                toggleSelectionMode(true)
                toggleSelection(position, holder)
                true
            } else {
                false
            }
        }
    }

    override fun getItemCount() = measurements.size
}
