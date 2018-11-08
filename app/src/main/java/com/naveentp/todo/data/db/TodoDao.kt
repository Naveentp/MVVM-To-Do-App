package com.naveentp.todo.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * @author Naveen T P
 * @since 08/11/18
 */

@Dao
interface TodoDao {

    @Insert
    fun saveTodo(todoRecord: TodoRecord)

    @Delete
    fun deleteTodo(todoRecord: TodoRecord)

    @Update
    fun updateTodo(todoRecord: TodoRecord)

    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun getAllTodoList(): LiveData<List<TodoRecord>>
}