package com.example.login_basic

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Date

class MainmenuActivity : AppCompatActivity() {

    private lateinit var cerrarsesion: Button
    private lateinit var revisar: Button
    private lateinit var tiempo: TextView
    private lateinit var auth: FirebaseAuth

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainmenu)

        cerrarsesion = findViewById(R.id.button4) // password nuevamente
        revisar = findViewById(R.id.rev)
        tiempo = findViewById(R.id.textView10)

        auth = FirebaseAuth.getInstance()
        var token = intent.getStringExtra("token")
        val expire = intent.getStringExtra("expiracy")

        val dateexpire : Int = expire.toString().toInt()


        cerrarsesion.setOnClickListener{
            auth.signOut()
            Toast.makeText(this, "Cerrando Sesion...", Toast.LENGTH_SHORT).show()
            openmain()

        }

        revisar.setOnClickListener{

            val currentDateTime = LocalDateTime.now()
            // Convert LocalDateTime to Instant
            val instant = currentDateTime.toInstant(ZoneOffset.UTC)
            // Get the seconds since the epoch
            val secondsSinceEpoch = instant.epochSecond
            if (secondsSinceEpoch <= dateexpire){
                Toast.makeText(this, "El token es:" + token.toString(), Toast.LENGTH_SHORT).show()
                val tiempos : String = "El tiempo restante de tu sesion es: \n" + (dateexpire-secondsSinceEpoch).toString() + " segundos"
                tiempo.text = tiempos
            }
            else{
                token  = ""
                Toast.makeText(this, "El token se ha caducado", Toast.LENGTH_SHORT).show()
                openmain()
                auth.signOut()
            }



        }
    }

    private fun openmain() {
        val main = Intent(this, MainActivity::class.java)

        startActivity(main)

    }


}