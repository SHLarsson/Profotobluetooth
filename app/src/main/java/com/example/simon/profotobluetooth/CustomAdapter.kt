package com.example.simon.profotobluetooth

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CustomAdapter(val deviceList: ArrayList<Device>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
      val v = LayoutInflater.from(p0?.context).inflate(R.layout.list_layout,p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return deviceList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val device: Device = deviceList[p1]

        p0?.textViewName?.text = device.name
        p0?.textViewMacID?.text = device.macID
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewName = itemView.findViewById(R.id.txtName) as TextView
        val textViewMacID = itemView.findViewById(R.id.txtMacID) as TextView
    }
}