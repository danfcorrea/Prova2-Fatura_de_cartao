package br.com.cotemig.daniel.services

import br.com.cotemig.daniel.models.Fatura
import br.com.cotemig.daniel.models.ItensFatura
import retrofit2.Call
import retrofit2.http.GET

interface ServiceFatura {

    @GET("fatura")
    fun getFatura(): Call<Fatura>

}