package com.example.music_app4b

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION) {

    //Datos de la base de datos

    companion object {
        //Informacion de la BD
        private const val DATABASE_NAME = "musica.db"
        private const val DATABASE_VERSION = 2

        //Informacion de las tablasd en la DB

        private const val TABLE_USUARIOS = "usuarios"
        private const val COL_ID = "id"
        private const val COL_NAME = "name"
        private const val COL_EMAIL = "email"
        private const val COL_PASS = "pass"

    }
    //

    //METODO ONCREATE

    override fun onCreate(db: SQLiteDatabase?) {
        //Crear tabla

        val crearTablaUsuarios = """
        CREATE TABLE $TABLE_USUARIOS(
        $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
        $COL_NAME TEXT NOT NULL,
        $COL_EMAIL TEXT NOT NULL,
        $COL_PASS TEXT NOT NULL        
        )
        
        """.trimIndent()

        //Crear tabla de usuarios

        db?.execSQL(crearTablaUsuarios)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        onCreate(db)
    }

    //Metodo para registrar usuario

    fun registrarUsuario(nombre : String, email : String, pass : String) : Boolean{

        //Crear variable para escribir en la base de datos

        val db = writableDatabase

        //Insertar kis datos

        val datos = ContentValues().apply {
            put(COL_NAME, nombre)
            put(COL_EMAIL, email)
            put(COL_PASS, pass)
        }

        //Ejecutar insercion de datos

        val result = db.insert(TABLE_USUARIOS, null, datos)

        //Cerrar conexion de la db

        db.close()

        //Regresar el valor obtenido de la insercion (Verdadero o Falso)

        return result != -1L



    }

    //Metodo para iniciar sesion (Consulta)

    fun iniciarSesion(email: String, password : String) : Boolean {

        //Variable que lee la base de datos

        val db = readableDatabase

        //Crear consulta

        val query_sql = """
            
            SELECT * FROM TABLE_USUARIOS
            WHERE $COL_EMAIL = ? AND $COL_PASS = ?
            
        """.trimIndent()


        //cREAR un CURSOR QUE PERMITE recorrer todos los registros

        val cursor = db.rawQuery(
            query_sql, arrayOf(email, password)
        )

        //Verificar cuantos resultados encuentra el cursor
        val usuarioEncontrado = cursor.count > 0

        cursor.close()
        db.close()

        return usuarioEncontrado
    }



}
    
