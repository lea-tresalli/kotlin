package com.example.mobileclientconsultation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ComponentActivity

import com.example.mobileclientconsultation.R
import com.example.mobileclientconsultation.network.networkConnection
import com.example.mobileclientconsultation.network.networkConnection.contacteServeur
import hepl.faad.Bibliotheque.Reponse_Login
import hepl.faad.Bibliotheque.Requete
import hepl.faad.Bibliotheque.Requete_Login
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState :  Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val loginText = findViewById<EditText>(R.id.logintext)
        val passwordText = findViewById<EditText>(R.id.passwordtext)

        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val login = loginText.text.toString()
            val password = passwordText.text.toString()

            if(login.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Vous n'avez pas remplie les champ", Toast.LENGTH_SHORT).show()

            }
            else{
                val req = Requete_Login(login, password)
                CoroutineScope(Dispatchers.IO).launch{
                            val reponse_Login = contacteServeur(req)
                    withContext(Dispatchers.Main){
                        (reponse_Login as? Reponse_Login)
                            ?.idDoctor?.let {docId ->
                                val intentId = Intent(this@LoginActivity, HomeActivity::class.java)
                                intentId.putExtra("docId", docId)
                                startActivity(intentId)

                                finish()
                            } ?:run{
                                Toast.makeText(this@LoginActivity, "coordonnées invalide", Toast.LENGTH_SHORT).show()
                            }


                    }
                }



            }
        }



    }

}