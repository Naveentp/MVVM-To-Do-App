package com.naveentp.todo.ui.todoList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naveentp.todo.R
import com.naveentp.todo.data.db.TodoRecord
import kotlinx.android.synthetic.main.todo_item.view.*

/**
 * @author Naveen T P
 * @since 08/11/18
 */
class TodoListAdapter(todoEvents: TodoEvents) : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    private var todoList: List<TodoRecord> = arrayListOf()
    private val listener: TodoEvents = todoEvents

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = todoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(todoList[position], listener)
    }

    fun setAllTodoItems(todoItems: List<TodoRecord>) {
        this.todoList = todoItems
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todo: TodoRecord, listener: TodoEvents) {
            itemView.tv_item_title.text = todo.title
            itemView.tv_item_content.text = todo.content

            itemView.iv_item_delete.setOnClickListener {
                listener.onDeleteClicked(todo)
            }

            itemView.setOnClickListener {
                listener.onViewClicked(todo)
            }
        }
    }


    /**
     * RecycleView touch event callbacks
     * */
    interface TodoEvents {
        fun onDeleteClicked(todoRecord: TodoRecord)
        fun onViewClicked(todoRecord: TodoRecord)
    }
}
