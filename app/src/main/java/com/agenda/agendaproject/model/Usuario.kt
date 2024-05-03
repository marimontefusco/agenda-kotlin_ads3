package com.agenda.agendaproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_usuarios")
data class Usuario (
    @ColumnInfo(name= "nome") val nome: String?, //? -> aceita null
    @ColumnInfo(name = "sobrenome") val sobrenome: String?,
    @ColumnInfo(name = "idade") val idade: String,
    @ColumnInfo(name = "celular") val celular: String?,
    @ColumnInfo(name = "email") val email: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0 // gera o id automaticamente -> dinâmico
}

// Entidade -> representa a tabela
// é uma data class