package com.agenda.agendaproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.agenda.agendaproject.dao.UsuarioDao
import com.agenda.agendaproject.databinding.ActivityCreateUserBinding
import com.agenda.agendaproject.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateUser : AppCompatActivity() {

    private lateinit var binding: ActivityCreateUserBinding
    private lateinit var usuarioDao: UsuarioDao
    private val listaUsuarios: MutableList<Usuario> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root) //configurando o binding

        //Definindo evento de click - btn cadastrar
        binding.btnCreateUser.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {

                // variáveis da model
                val nome      = binding.editName.text.toString()
                val sobrenome = binding.editSurname.text.toString()
                val idade     = binding.editAge.text.toString()
                val celular   = binding.editMobileNumber.text.toString()
                val email     = binding.editEmail.text.toString()
                val mensagem: Boolean

                // verificação de preenchimento - se estiver preenchido - cadastrar
                if (nome.isEmpty() || sobrenome.isEmpty() || idade.isEmpty() || celular.isEmpty() || email.isEmpty()) {
                    mensagem = false
                    //Toast.makeText(this, "Preencher todos os campos!", Toast.LENGTH_SHORT).show()
                } else {
                    mensagem = true
                    cadastrar(nome, sobrenome, idade, celular, email)
                }

                // Coroutines
                withContext(Dispatchers.Main) {
                    if (mensagem){
                        Toast.makeText(applicationContext, "Sucesso ao cadastrar usuário!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else{
                        Toast.makeText(applicationContext, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                        //Toast -> msg de feedback do evento
                    } // Coroutines -> permite a execução de tarefas em segundo plano sem bloquear a linha de execução principal do aplicativo
                }
            }

        }
    }

    private fun cadastrar(nome: String, sobrenome: String, idade: String, celular: String, email: String){
        val usuario = Usuario(nome,sobrenome,idade,celular,email)
        listaUsuarios.add(usuario)
        usuarioDao = AppDatabase.getInstance(this).usuarioDao()
        usuarioDao.inserir(listaUsuarios)
    }

}