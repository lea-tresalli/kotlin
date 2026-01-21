package com.example.mobileclientconsultation.adapters
import com.example.mobileclientconsultation.R.layout.patient;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mobileclientconsultation.R
import com.example.mobileclientconsultation.entity.kConsultation
import com.example.mobileclientconsultation.entity.kPatient


class RadioButtonAdapter(context: Context, private val dataList: MutableList<String>) : ArrayAdapter<String>(context, 0, dataList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.patient , parent, false)
        val itemText = getItem(position)
        val textView = view.findViewById<TextView>(R.id.radioButton4)

        textView.text = itemText
        return view
    }

    fun update(newList: List<String>){
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }
}