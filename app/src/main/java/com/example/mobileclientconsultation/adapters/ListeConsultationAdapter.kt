package com.example.mobileclientconsultation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import android.widget.TextView
import com.example.mobileclientconsultation.R
import com.example.mobileclientconsultation.entity.Consultation

class ListeConsultationAdapter (context: Context, private val dataList: List<Consultation>) : ArrayAdapter<Consultation>(context, 0, dataList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.consultation , parent, false)
        val itemText = getItem(position)
        val idDoc = view.findViewById<TextView>(R.id.idtext)
        val date = view.findViewById<TextView>(R.id.datetext)
        val time = view.findViewById<TextView>(R.id.timetext)
        val namePatient = view.findViewById<TextView>(R.id.nameText)
        val firstNamePatient = view.findViewById<TextView>(R.id.firstNameText)
        val reason = view.findViewById<TextView>(R.id.reason)

        view.setOnClickListener {
            val popMenu = PopupMenu(context, view)
            popMenu.menuInflater.inflate(R.menu.popupmenu, popMenu.menu)
            popMenu.show()
        }
        idDoc.text =(itemText?.idDoctor).toString()
        date.text = (itemText?.date).toString()
        time.text = (itemText?.hour).toString()
        namePatient.text = itemText?.Patient?.lastName
        firstNamePatient.text = itemText?.Patient?.firstName
        reason.text = itemText?.reason

        return view
    }
}