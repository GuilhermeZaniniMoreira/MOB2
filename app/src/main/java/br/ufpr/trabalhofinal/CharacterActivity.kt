package br.ufpr.trabalhofinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import br.ufpr.trabalhofinal.models.Character
import br.ufpr.trabalhofinal.network.ApiClient
import br.ufpr.trabalhofinal.network.CharactersApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        val etCharacterId = findViewById<EditText>(R.id.etCharacterId)
        val btnFetchCharacter = findViewById<Button>(R.id.btnFetchCharacter)
        val tvCharacterResult = findViewById<TextView>(R.id.tvCharacterResult)

        btnFetchCharacter.setOnClickListener {
            val characterId = etCharacterId.text.toString()
            if (characterId.isNotEmpty()) {
                fetchCharacterById(characterId, tvCharacterResult)
            } else {
                tvCharacterResult.text = "Por favor, insira um ID válido."
            }
        }
    }

    private fun fetchCharacterById(characterId: String, resultView: TextView) {
        val api = ApiClient.retrofit.create(CharactersApi::class.java)
        api.getCharacterById(characterId).enqueue(object : Callback<List<Character>> {
            override fun onResponse(call: Call<List<Character>>, response: Response<List<Character>>) {
                if (response.isSuccessful) {
                    val characters = response.body()
                    val character = characters?.firstOrNull()

                    if (character != null) {
                        val intent = Intent(this@CharacterActivity, CharacterDetailsActivity::class.java)
                        intent.putExtra("character", character)
                        startActivity(intent)
                    } else {
                        resultView.text = "Personagem não encontrado."
                    }
                } else {
                    resultView.text = "Erro ao buscar personagem. Código: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                resultView.text = "Erro: ${t.message}"
            }
        })
    }
}
