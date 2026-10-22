package com.example.music_app4b.model

//Archivo para recibir la respuesta de confirmacion del API

data class ITunesResponse(

    val resultCount : Int,
    val results : List<Album>

)
