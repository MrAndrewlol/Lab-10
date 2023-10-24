package com.example.login_basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class registraractivity : AppCompatActivity() {

    lateinit var textinputusuario: EditText
    private lateinit var repass: EditText
    private lateinit var textinputpass : EditText
    lateinit var clickregist: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrar)
        repass = findViewById(R.id.editTextTextPassword2) // password nuevamente
        textinputusuario = findViewById(R.id.editTextText3) //Usuario
        textinputpass = findViewById(R.id.editTextTextPassword3)
        clickregist = findViewById(R.id.button3) //Iniciar Sesion
        auth = Firebase.auth

        clickregist.setOnClickListener{
            register()
        }

    }

    private fun openregistraract() {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun register(){

        val email = textinputusuario.text.toString()
        val pass =  textinputpass.text.toString()
        val confirm = repass.text.toString()

        if (email.isBlank() || pass.isBlank() || confirm.isBlank()) {
            Toast.makeText(this, "No hay contrasena puesta", Toast.LENGTH_SHORT).show()
            return
        }
        else{
            if (pass != confirm) {
                Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show()
                return
            }
            else {
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Toast.makeText(this, "Ingresados Correctamente", Toast.LENGTH_SHORT).show()
                        openregistraract()
                        finish()
                    }
                    else{
                        Toast.makeText(this, "El registro fallo", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }



    }



}