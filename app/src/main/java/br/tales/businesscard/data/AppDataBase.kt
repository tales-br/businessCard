/**VER DOCS ROOM
 *  https://developer.android.com/training/data-storage/room
 */

package br.tales.businesscard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Card::class], version = 1) //caso altere algo (ex.: uma tabela nova) tem que alterar o versionamento
abstract class AppDataBase: RoomDatabase()
{
    abstract fun cardDao(): CardDAO

    companion object
    {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase
        {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "businessCard_DB"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}