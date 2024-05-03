package com.agenda.agendaproject.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.agenda.agendaproject.model.Usuario

// Dao -> interface de conexão ->> onde criamos o CRUD e as políticas do app

@Dao
interface UsuarioDao {

    //CREATE
    @Insert
    fun inserir(listaUsuarios: MutableList<Usuario>)

    //READ
    @Query("SELECT * FROM tb_usuarios ORDER BY nome ASC")
    fun get(): MutableList<Usuario>

    //UPDATE
    @Query("UPDATE tb_usuarios SET nome = :novoNome, sobrenome = :novoSobrenome, idade = :novaIdade," +
            "celular = :novoCelular, email = :novoEmail WHERE uid = :id")
    fun atualizar(id: Int, novoNome: String, novoSobrenome : String, novaIdade: String, novoCelular: String, novoEmail: String)

    //DELETE
    @Query("DELETE FROM tb_usuarios WHERE uid = :id")
    fun deletar(id: Int)

}