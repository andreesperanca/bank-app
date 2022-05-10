package com.voltaire.bankapp.data.rest

import com.voltaire.bankapp.models.Movimentacao
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("movimentacoes/{id}")
    suspend fun findBankStatement(@Path("id") accountId : Int) : List<Movimentacao>
}