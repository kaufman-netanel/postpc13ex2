package il.ac.huji.todolist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TodoDAL {
	
	private SqlLiteHelper _sqlLiteHelper;
	private SQLiteDatabase _db;
	
	public TodoDAL(Context context) 
	{  
	    _sqlLiteHelper = new SqlLiteHelper(context);
	    _db = _sqlLiteHelper.getWritableDatabase();
	}
	
	public boolean insert(ITodoItem todoItem) {
	    ContentValues task = createRow(todoItem);
	    long status = _db.insert("tasks", null, task);
		return status != -1;
	}

	private ContentValues createRow(ITodoItem todoItem) {
		ContentValues task = new ContentValues();
	    task.put("title", todoItem.getTitle());
	    task.put("due", todoItem.getDueDate().getTime());
		return task;
	}
	
	public boolean update(ITodoItem todoItem) {
	    ContentValues task = createRow(todoItem);
	    String namePrefix = todoItem.getTitle();
	    int status = _db.update("tasks", task, "title='?'",new String[] { namePrefix });
	    return status != 0;
	}
	 
	public boolean delete(ITodoItem todoItem) {
	    String namePrefix = todoItem.getTitle();
	    int status = _db.delete("tasks", "title=?",new String[] { namePrefix });
	    return status != 0;
	}
	 
	 public List<ITodoItem> all() {
		 List<ITodoItem> tasks = new ArrayList<ITodoItem>();
		 Cursor cursor = allCursor();
		 if (cursor.moveToFirst()) {
			 do {
			    String title = cursor.getString(0);
			    Date due = new Date(cursor.getLong(1));
			    tasks.add(new Todo(title, due));
			 } while (cursor.moveToNext());
		 }
		 return tasks;  
	 }
	 
	 public Cursor allCursor() {
		 return _db.query("tasks", new String[] { "title", "due" }, null, null, null, null, null);
	 }
	 
}