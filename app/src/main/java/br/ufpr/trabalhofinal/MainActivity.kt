package br.ufpr.trabalhofinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnCharacter).setOnClickListener {
            startActivity(Intent(this, CharacterActivity::class.java))
        }

        findViewById<Button>(R.id.btnProfessors).setOnClickListener {
            startActivity(Intent(this, ProfessorsActivity::class.java))
        }

        findViewById<Button>(R.id.btnStudents).setOnClickListener {
            startActivity(Intent(this, StudentsActivity::class.java))
        }

        findViewById<Button>(R.id.btnExit).setOnClickListener {
            finishAffinity()
        }
    }
}
