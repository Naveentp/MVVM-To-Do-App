package com.naveentp.todo.ui.todoList

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.naveentp.todo.R
import com.naveentp.todo.data.db.TodoRecord
import com.naveentp.todo.ui.createTodo.CreateTodoActivity
import com.naveentp.todo.utils.Constants
import kotlinx.android.synthetic.main.activity_todo_list.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class TodoListActivity : AppCompatActivity(), TodoListAdapter.TodoEvents {

    //Koin provides TodoViewModel dependency
    private val todoViewModel: TodoViewModel by viewModel()

    private lateinit var searchView: SearchView
    private lateinit var todoAdapter: TodoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        //Setting up RecyclerView
        rv_todo_list.layoutManager = LinearLayoutManager(this)
        todoAdapter = TodoListAdapter(this)
        rv_todo_list.adapter = todoAdapter


        //Setting up LiveData
        todoViewModel.getAllTodoList().observe(this, Observer {
            todoAdapter.setAllTodoItems(it)
        })

        //FAB click listener
        fab_new_todo.setOnClickListener {
            resetSearchView()
            val intent = Intent(this@TodoListActivity, CreateTodoActivity::class.java)
            startActivityForResult(intent, Constants.INTENT_CREATE_TODO)
        }
    }


    /**
     * RecyclerView Item callbacks
     * */
    //Callback when user clicks on Delete note
    override fun onDeleteClicked(todoRecord: TodoRecord) {
        todoViewModel.deleteTodo(todoRecord)
    }

    //Callback when user clicks on view note
    override fun onViewClicked(todoRecord: TodoRecord) {
        resetSearchView()
        val intent = Intent(this@TodoListActivity, CreateTodoActivity::class.java)
        intent.putExtra(Constants.INTENT_OBJECT, todoRecord)
        startActivityForResult(intent, Constants.INTENT_UPDATE_TODO)
    }


    /**
     * Activity result callback
     * Triggers when Save button clicked from @CreateTodoActivity
     * */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val todoRecord = data?.getParcelableExtra<TodoRecord>(Constants.INTENT_OBJECT)!!
            when (requestCode) {
                Constants.INTENT_CREATE_TODO -> {
                    todoViewModel.saveTodo(todoRecord)
                }
                Constants.INTENT_UPDATE_TODO -> {
                    todoViewModel.updateTodo(todoRecord)
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu?.findItem(R.id.search_todo)
                ?.actionView as SearchView
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                todoAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                todoAdapter.filter.filter(newText)
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.search_todo -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        resetSearchView()
        super.onBackPressed()
    }

    private fun resetSearchView() {
        if (!searchView.isIconified) {
            searchView.isIconified = true
            return
        }
    }
}
