package com.glmreader.android.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glmreader.android.R

data class BleDeviceItem(val name: String, val mac: String, val isSaved: Boolean)

class BleDeviceAdapter(
    private val devices: List<BleDeviceItem>,
    private val onDeviceSelected: (BleDeviceItem?) -> Unit
) : RecyclerView.Adapter<BleDeviceAdapter.ViewHolder>() {

    private var selectedPosition = -1
    var selectedDevice: BleDeviceItem? = null
        private set

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvDeviceName)
        val tvMac: TextView = view.findViewById(R.id.tvDeviceMac)
    }

    fun setSelectedPosition(position: Int) {
        val oldPosition = selectedPosition
        selectedPosition = position
        selectedDevice = if (position >= 0 && position < devices.size) devices[position] else null

        notifyItemChanged(oldPosition)
        notifyItemChanged(selectedPosition)

        onDeviceSelected(selectedDevice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ble_device, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val device = devices[position]
        holder.tvName.text = device.name.ifEmpty { "Unknown Device" }
        holder.tvMac.text = device.mac

        // Подсветка выбранного
        holder.itemView.isSelected = position == selectedPosition
        holder.itemView.setBackgroundColor(
            if (position == selectedPosition)
                holder.itemView.context.getColor(android.R.color.holo_blue_light) // Или цвет темы
            else
                android.graphics.Color.TRANSPARENT
        )

        holder.itemView.setOnClickListener {
            setSelectedPosition(position)
        }
    }

    override fun getItemCount() = devices.size
}
