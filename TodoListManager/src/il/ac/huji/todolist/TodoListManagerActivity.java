package il.ac.huji.todolist;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class TodoListManagerActivity extends Activity {

	private ArrayAdapter<Todo> adapter;
	private List<Todo> todos;
	private static final int contextDelete=0;
	private static final int contextCall=1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);
        
        todos = new ArrayList<Todo>();
        ListView todoListView = 
        		(ListView)findViewById(R.id.lstTodoItems);
        adapter =   new CustomAdapter(this,	todos);
        todoListView.setAdapter(adapter);
        registerForContextMenu(todoListView);

    }

    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
        super.onCreateContextMenu(menu, v, menuInfo);  
        AdapterContextMenuInfo info =(AdapterContextMenuInfo)menuInfo;
        Todo task = todos.get((int)info.id);
        menu.setHeaderTitle(task.task);  
        menu.add(Menu.NONE, contextDelete, 0, R.string.delete_button);
        if (task.task.startsWith("Call ")) {
            menu.add(Menu.NONE, contextCall, 1, task.task);  
        }
    }  
    @Override  
    public boolean onContextItemSelected(MenuItem item) { 
        AdapterContextMenuInfo info =(AdapterContextMenuInfo)item.getMenuInfo();
        Todo task = todos.get((int)info.id);
    	switch (item.getItemId()) {
    	case contextDelete:
    		adapter.remove(task);
    		break;
    	case contextCall:
    		Toast.makeText(this, task.task, Toast.LENGTH_SHORT).show();
    		break;
    	default:
    		return false;
    	}
    	return true;  
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
