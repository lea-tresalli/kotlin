package com.example.mobileclientconsultation.adapters
import com.example.mobileclientconsultation.R.layout.listepatient;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.compose.ui.layout.Layout
import com.example.mobileclientconsultation.R


class RadioButtonAdapter(context: Context, private val dataList: List<String>) : ArrayAdapter<String>(context, 0, dataList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.listepatient , parent, false)
        val itemText = getItem(position)
        val textView = view.findViewById<TextView>(R.id.radioButton4)

        textView.text = itemText
        return view
    }
}