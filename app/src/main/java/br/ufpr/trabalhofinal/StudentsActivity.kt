package br.ufpr.trabalhofinal

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import br.ufpr.trabalhofinal.models.Character
import br.ufpr.trabalhofinal.network.ApiClient
import br.ufpr.trabalhofinal.network.CharactersApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)

        val rgHouses = findViewById<RadioGroup>(R.id.rgHouses)
        val btnFetchStudents = findViewById<Button>(R.id.btnFetchStudents)
        val tvStudentsList = findViewById<TextView>(R.id.tvStudentsList)

        // Define a casa padrão como Gryffindor ao abrir a tela
        rgHouses.check(R.id.rbGryffindor)
        fetchStudentsByHouse("Gryffindor", tvStudentsList)

        // Limpa a lista sempre que um novo botão de rádio é selecionado
        rgHouses.setOnCheckedChangeListener { _, checkedId ->
            tvStudentsList.text = "" // Limpa a lista de estudantes
        }

        btnFetchStudents.setOnClickListener {
            val selectedHouse = when (rgHouses.checkedRadioButtonId) {
                R.id.rbGryffindor -> "Gryffindor"
                R.id.rbSlytherin -> "Slytherin"
                R.id.rbHufflepuff -> "Hufflepuff"
                R.id.rbRavenclaw -> "Ravenclaw"
                else -> null
            }

            if (selectedHouse != null) {
                fetchStudentsByHouse(selectedHouse, tvStudentsList)
            } else {
                tvStudentsList.text = "Por favor, selecione uma casa."
            }
        }
    }

    private fun fetchStudentsByHouse(house: String, resultView: TextView) {
        val api = ApiClient.retrofit.create(CharactersApi::class.java)
        resultView.text = "Carregando..." // Mostra mensagem de carregamento
        api.getStudentsByHouse(house).enqueue(object : Callback<List<Character>> {
            override fun onResponse(
                call: Call<List<Character>>,
                response: Response<List<Character>>
            ) {
                if (response.isSuccessful) {
                    val students = response.body()?.joinToString("\n") { it.name } ?: "Nenhum estudante encontrado."
                    resultView.text = students
                } else {
                    resultView.text = "Erro ao buscar estudantes. Código: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                resultView.text = "Erro: ${t.message}"
            }
        })
    }
}
