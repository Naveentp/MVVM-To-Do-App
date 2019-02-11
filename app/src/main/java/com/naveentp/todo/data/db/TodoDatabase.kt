package com.naveentp.todo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @author Naveen T P
 * @since 08/11/18
 */

@Database(entities = [TodoRecord::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        private var INSTANCE: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase? {
            if (INSTANCE == null) {
                synchronized(TodoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context,
                            TodoDatabase::class.java,
                            "todo_db")
                            .build()
                }
            }
            return INSTANCE
        }
    }
}