package com.example.mobileclientconsultation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import com.example.mobileclientconsultation.R

import com.example.mobileclientconsultation.databinding.ConsultationBinding
import com.example.mobileclientconsultation.entity.kConsultation


class ListeConsultationAdapter (context: Context, private val dataList: MutableList<kConsultation>, private val onDelete: (kConsultation?) -> Unit,
                                private val onUpdate: (kConsultation?) -> Unit) : ArrayAdapter<kConsultation>(context, 0, dataList){
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
        val consultation = getItem(position)
        consultation?.let{
            binding.idtext.text = it.id.toString()
            binding.datetext.text = it.date.toString()
            binding.timetext.text = it.hour.toString()
            binding.firstnamepatient.text = it.patient?.firstName
            binding.nompatient.text = it.patient?.lastName
            binding.reason.text = it.reason

        }
        view.setOnClickListener{
            view ->
            val popupMenu = PopupMenu(context, view)
            popupMenu.menuInflater.inflate(R.menu.popupmenu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                    monItem ->
                when(monItem.itemId){
                    R.id.deletemenu -> {
                        onDelete(consultation)
                    }
                    R.id.updatemenu -> {
                        onUpdate(consultation)
                    }

                }
                true


            }

            popupMenu.show()
        }





        return view
    }


    fun update(newList: List<kConsultation>){
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }
}