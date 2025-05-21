package com.assignment.little_lemon

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity
data class MenuItem2(
    @PrimaryKey
    val id:Int,
    val title:String,
    val description:String,
    val price:String,
    val image:String,
    val category:String
)

@Dao
interface MenuDao {

    @Insert
    suspend fun insertMenuItem(menuItem: MenuItem2):Unit

    @Query("Select * from MenuItem2")
    fun getItems(): LiveData<List<MenuItem2>>

    @Query("Delete from menuitem2")
    suspend fun deleteAll()
}

@Database(entities = [MenuItem2::class], version = 1, exportSchema = false)
abstract class MenuDatabase:RoomDatabase() {

    abstract fun getMenuDao():MenuDao

    companion object {
        private var INSTANCE:MenuDatabase?=null
        fun getInstance(applicationContext: Context):MenuDatabase= synchronized(this) {
            if(INSTANCE==null) {
                INSTANCE= Room
                    .databaseBuilder(applicationContext,MenuDatabase::class.java,"menu_db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

    }

}