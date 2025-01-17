package br.ufpr.trabalhofinal

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import br.ufpr.trabalhofinal.models.Character
import br.ufpr.trabalhofinal.network.ApiClient
import br.ufpr.trabalhofinal.network.CharactersApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfessorsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professors)

        val tvProfessorsList = findViewById<TextView>(R.id.tvProfessorsList)

        fetchProfessors(tvProfessorsList)
    }

    private fun fetchProfessors(resultView: TextView) {
        val api = ApiClient.retrofit.create(CharactersApi::class.java)
        api.getProfessors().enqueue(object : Callback<List<Character>> {
            override fun onResponse(
                call: Call<List<Character>>,
                response: Response<List<Character>>
            ) {
                if (response.isSuccessful) {
                    val professors = response.body()?.joinToString("\n") { it.name } ?: "Nenhum professor encontrado."
                    resultView.text = professors
                } else {
                    resultView.text = "Erro ao buscar professores."
                }
            }

            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                resultView.text = "Erro: ${t.message}"
            }
        })
    }
}
