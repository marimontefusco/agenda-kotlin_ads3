package com.agenda.agendaproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agenda.agendaproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Variáveis
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding -> usando viewBinding pra enxergar os objs da interface dao
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Definindo evento de click - btn cadastrar
        binding.btnCreateUser.setOnClickListener {
            val navegarTelaCadastro = Intent(this, CreateUser::class.java) // Intent -> para a tela CreateUser
            startActivity(navegarTelaCadastro)
        }

        binding.btnUpdateUser.setOnClickListener {
            val navegarTelaAtualizacao = Intent(this, UpdateUser::class.java) // Intent -> para a tela UpdateUser
            startActivity(navegarTelaAtualizacao)
        }


    }
}