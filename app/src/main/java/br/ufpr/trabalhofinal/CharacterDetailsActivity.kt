package br.ufpr.trabalhofinal

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import br.ufpr.trabalhofinal.models.Character
import com.bumptech.glide.Glide

class CharacterDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        val tvCharacterName = findViewById<TextView>(R.id.tvCharacterName)
        val ivCharacterImage = findViewById<ImageView>(R.id.ivCharacterImage)
        val tvDetails = findViewById<TextView>(R.id.tvDetails)

        // Recebe o objeto Character
        val character = intent.getParcelableExtra<Character>("character")

        if (character != null) {
            tvCharacterName.text = character.name
            tvDetails.text = """
                Casa: ${character.house ?: "Não especificada"}
                Espécie: ${character.species}
                Gênero: ${character.gender}
                Data de Nascimento: ${character.dateOfBirth ?: "Não especificada"}
                Patrono: ${character.patronus ?: "Não especificado"}
                Ator: ${character.actor}
                Ancestralidade: ${character.ancestry ?: "Não especificada"}
                Cor dos Olhos: ${character.eyeColour ?: "Não especificada"}
                Cor do Cabelo: ${character.hairColour ?: "Não especificada"}
                Varinha: Madeira - ${character.wand?.wood ?: "Não especificada"}, 
                         Núcleo - ${character.wand?.core ?: "Não especificado"}, 
                         Comprimento - ${character.wand?.length ?: "Não especificado"}"
            """.trimIndent()

            // Carrega a imagem usando Glide
            Glide.with(this)
                .load(character.image)
                .into(ivCharacterImage)
        } else {
            tvCharacterName.text = "Personagem não encontrado."
        }
    }
}
