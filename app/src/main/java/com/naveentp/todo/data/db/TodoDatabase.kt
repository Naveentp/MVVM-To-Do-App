package com.naveentp.todo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author Naveen T P
 * @since 08/11/18
 */

@Database(entities = [TodoRecord::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

}