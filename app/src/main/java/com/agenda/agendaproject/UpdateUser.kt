package com.agenda.agendaproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.agenda.agendaproject.dao.UsuarioDao
import com.agenda.agendaproject.databinding.ActivityUpdateUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateUser : AppCompatActivity() {

    //Variáveis
    private lateinit var binding: ActivityUpdateUserBinding
    private lateinit var usuarioDao: UsuarioDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nomeRecuperado = intent.extras?.getString("nome")
        val sobrenomeRecuperado = intent.extras?.getString("sobrenome")
        val idadeRecuperada = intent.extras?.getString("idade")
        val celularRecuperado = intent.extras?.getString("celular")
        val emailRecuperado = intent.extras?.getString("email")
        val uid = intent.extras!!.getInt("uid")

        binding.editName.setText(nomeRecuperado)
        binding.editSurname.setText(sobrenomeRecuperado)
        binding.editAge.setText(idadeRecuperada)
        binding.editMobileNumber.setText(celularRecuperado)
        binding.editEmail.setText(emailRecuperado)

        binding.btnUpdateUser.setOnClickListener {
            binding.btnUpdateUser.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch {

                    val nome = binding.editName.text.toString()
                    val sobrenome = binding.editSurname.text.toString()
                    val idade = binding.editAge.text.toString()
                    val celular = binding.editMobileNumber.text.toString()
                    val email = binding.editEmail.text.toString()
                    val mensagem: Boolean

                    if (nome.isEmpty() || sobrenome.isEmpty() || idade.isEmpty() || celular.isEmpty() || email.isEmpty()) {
                        mensagem = false
                    } else {
                        mensagem = true
                        atualizarContato(uid, nome, sobrenome, idade, celular, email)
                    }

                    withContext(Dispatchers.Main) {
                        if (mensagem) {
                            Toast.makeText(
                                this@UpdateUser,
                                "Sucesso ao atualizar o usuário",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        } else {
                            Toast.makeText(
                                this@UpdateUser,
                                "Preencha todos os campos!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

        }

    }

    //Função atualizarContato
    private fun atualizarContato( uid: Int, nome: String, sobrenome: String, idade: String, celular: String, email: String) {
        usuarioDao = AppDatabase.getInstance(this).usuarioDao()
        usuarioDao.atualizar(uid, nome, sobrenome, idade, celular, email)
    }
}