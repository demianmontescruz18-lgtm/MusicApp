package com.example.music_app4b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {

    //Se declaran los elementos
    private lateinit var etUser : EditText
    private lateinit var etEmail : EditText
    private lateinit var etPass : EditText
    private lateinit var btnRegistrar : Button

    //Crear instancia de DatabaseHelper
    private lateinit var dbHelper: DatabaseHelper

    //Metodo principal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        //Inicializar los objetos de los componentes
        etUser = findViewById(R.id.etUser)
        etEmail = findViewById(R.id.etEmail)
        etPass = findViewById(R.id.etPass)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        //Inicializar la base de datos en un objeto
        dbHelper = DatabaseHelper(this)

        //Evento para registrar al usuario
        btnRegistrar.setOnClickListener {
            //Registrar al usuario
            registrarUsuario()
        }


        val tvIniciarSesion = findViewById<TextView>(R.id.tvIniciarSesion)

        tvIniciarSesion.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

    }

    //Metodo por aqui

    private fun registrarUsuario(){
        //Variables para guardar los textos
        val nombre = etUser.text.toString().trim()
        val correo = etEmail.text.toString().trim()
        val pass = etPass.text.toString().trim()

        if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        //Variable que guardar el resultado del registro y llamar

        val registro = dbHelper.registrarUsuario(nombre, correo , pass)

        //validar que el resultado de la insercion sea veredadero o falso
        if (registro){
            //Si el registro fue correcto
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            //Cierra la pantalla actual y regresa a la pantalla anterior
            finish()
        }else{
            Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
        }
    }


}