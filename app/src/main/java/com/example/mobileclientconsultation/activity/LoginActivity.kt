package com.example.mobileclientconsultation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

import androidx.core.os.LocaleListCompat

import com.example.mobileclientconsultation.R

import com.example.mobileclientconsultation.network.networkConnection.contacteServeur
import hepl.faad.Bibliotheque.Reponse_Login

import hepl.faad.Bibliotheque.Requete_Login

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState :  Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val loginText = findViewById<EditText>(R.id.logintext)

        val passwordText = findViewById<EditText>(R.id.passwordtext)

        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        val buttonLangFr = findViewById<Button>(R.id.buttonLanguageFr)
        val buttonLangEn = findViewById<Button>(R.id.buttonLanguageEn)


        buttonLogin.setOnClickListener {
            val login = loginText.text.toString()
            val password = passwordText.text.toString()

            if(login.isEmpty() || password.isEmpty()){
                Toast.makeText(this, R.string.mesMissing, Toast.LENGTH_SHORT).show()

            }
            else{
                val req = Requete_Login(login, password)
                CoroutineScope(Dispatchers.IO).launch{
                            val reponse_Login = contacteServeur(req)
                    withContext(Dispatchers.Main){

                        (reponse_Login as? Reponse_Login)
                            ?.getIdDoctor()?.let {docId ->
                                val intentId = Intent(this@LoginActivity, HomeActivity::class.java)

                                intentId.putExtra("docId", docId)
                                startActivity(intentId)

                                finish()
                            } ?:run{
                                Toast.makeText(this@LoginActivity, getString(R.string.mesCoord), Toast.LENGTH_SHORT).show()
                            }


                    }
                }



            }
        }
        buttonLangFr.setOnClickListener() {
            val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("fr")
            AppCompatDelegate.setApplicationLocales(appLocale)
        }

        buttonLangEn.setOnClickListener {
            val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("en")
            AppCompatDelegate.setApplicationLocales(appLocale)
        }





    }

}