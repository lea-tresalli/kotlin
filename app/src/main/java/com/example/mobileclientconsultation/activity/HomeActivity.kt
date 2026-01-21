package com.example.mobileclientconsultation.activity

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.mobileclientconsultation.R



class HomeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState : Bundle? ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainfrag)
        val idDoc = intent.getIntExtra("docId", -1)


    }


}