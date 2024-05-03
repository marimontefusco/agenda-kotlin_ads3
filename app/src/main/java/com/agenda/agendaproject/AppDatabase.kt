package com.agenda.agendaproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.agenda.agendaproject.dao.UsuarioDao
import com.agenda.agendaproject.model.Usuario

@Database(entities = [Usuario::class], version = 1) //versão do aplicativo
abstract class AppDatabase : RoomDatabase() { //extends da RoomDatabase

    abstract fun usuarioDao(): UsuarioDao //extends da UsuarioDao
    //abstrata pq é onde vamos chamar os inserts -> abstração dos dados

    // Criando o DB //
    companion object {
        private const val DATABASE_NAME = "DB_AGENDA"

        // Instancias
        @Volatile
        private var INSTANCE: AppDatabase? = null //instanciando -> herda do AppDatabase
                                                  // ? -> null -> pode retornar nulos

        // fun -> sincronizar o BD com a Instancia através do Dao //
        fun getInstance(context: Context) : AppDatabase { //extends da AppDatabase
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME //quem tá me trazendo todos os contextos?
                ).build()

                INSTANCE = instance //Instanciando a variável instance
                instance
            }
        } //puxa o q o usuario digita na tela atraves do viewById

    }

}

//AppDatabase -> classe que faz a configuração do BD