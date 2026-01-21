package com.example.mobileclientconsultation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import android.widget.TextView
import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent
import com.example.mobileclientconsultation.databinding.ConsultationBinding
import com.example.mobileclientconsultation.entity.kConsultation

class ListeConsultationAdapter (context: Context, private val dataList: MutableList<kConsultation>) : ArrayAdapter<kConsultation>(context, 0, dataList){
    private lateinit var binding : ConsultationBinding;
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view : View
        if(convertView == null){
            binding = ConsultationBinding.inflate(LayoutInflater.from(context), parent, false);
            view = binding.root;
            view.tag = binding;
        }
        else{
            view = convertView
            binding = view.tag as ConsultationBinding
        }
/*
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
        namePatient.text = itemText?.patient?.lastName
        firstNamePatient.text = itemText?.patient?.firstName
        reason.text = itemText?.reason*/
        val consultation = getItem(position)
        consultation?.let{
            binding.idtext.text = it.idDoctor.toString()
            binding.datetext.text = it.date.toString()
            binding.timetext.text = it.hour.toString()
            binding.firstnamepatient.text = it.patient?.firstName
            binding.nompatient.text = it.patient?.lastName
            binding.reason.text = it.reason

        }

        return view
    }

    fun update(newList: List<kConsultation>){
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }
}