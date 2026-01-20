package com.example.mobileclientconsultation.activity

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.mobileclientconsultation.R

import com.example.mobileclientconsultation.fragment.ButtonChange
import com.example.mobileclientconsultation.fragment.Filter

import com.example.mobileclientconsultation.fragment.listeConsultation


class HomeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState : Bundle? ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home)
        val idDoc = intent.getIntExtra("docId", -1)

        setSupportActionBar(findViewById(R.id.toolbar))
        val fragment1 = listeConsultation().apply{
            arguments = Bundle().apply { putInt("docId", idDoc) }
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragmentList,fragment1).commit()

        val fragment2 = ButtonChange().apply{}
        supportFragmentManager.beginTransaction().replace(R.id.buttonChange,fragment2).commit()

        val fragment3 = Filter().apply{
            arguments = Bundle().apply { putInt("docId", idDoc) }
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragmentFilter,fragment3).commit()
    }


}