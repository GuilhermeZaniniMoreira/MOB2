package br.ufpr.trabalhofinal.network

import br.ufpr.trabalhofinal.models.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {
    // Busca um personagem específico pelo ID
    @GET("api/character/{id}")
    fun getCharacterById(@Path("id") id: String): Call<List<Character>>

    // Busca todos os personagens
    @GET("api/characters")
    fun getCharacters(): Call<List<Character>>

    // Busca todos os professores
    @GET("api/characters/staff")
    fun getProfessors(): Call<List<Character>>

    // Busca estudantes de uma casa específica
    @GET("api/characters/house/{house}")
    fun getStudentsByHouse(@Path("house") house: String): Call<List<Character>>
}
