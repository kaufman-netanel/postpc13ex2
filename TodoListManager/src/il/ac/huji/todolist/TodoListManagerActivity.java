package il.ac.huji.todolist;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class TodoListManagerActivity extends Activity {

	private ArrayAdapter<Todo> adapter;
	private List<Todo> todos;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);
        
        todos = new ArrayList<Todo>();
        ListView todoListView = 
        		(ListView)findViewById(R.id.lstTodoItems);
        adapter =   new CustomAdapter(this,	todos);
        todoListView.setAdapter(adapter);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.todo_list_manager, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.menuItemAdd:
        	EditText newTask = (EditText)findViewById(R.id.edtNewItem);
    		adapter.add(new Todo(newTask.getText().toString()));
    		break;
    	case R.id.menuItemDelete:
    		ListView todosListView = (ListView)findViewById(R.id.lstTodoItems);
    		Todo todo = (Todo)todosListView.getSelectedItem();
    		adapter.remove(todo);
    		break;
    	}
    	return true;
    }

    
}
