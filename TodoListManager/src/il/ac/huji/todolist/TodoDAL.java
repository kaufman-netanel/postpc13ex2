package il.ac.huji.todolist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

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
	    Parse.initialize(context, 
	    		context.getResources().getString(R.string.parseApplication), 
	    		context.getResources().getString(R.string.clientKey));
	}
	
	public boolean insert(ITodoItem todoItem) {
	    try {
	    	ContentValues task = createRow(todoItem);
		    Boolean status = _db.insert("tasks", null, task) != -1;
		    
		    ParseObject testObject = new ParseObject("todo");
		    testObject.put("title", todoItem.getTitle());
		    if (todoItem.getDueDate()!=null) {
		    	testObject.put("due", todoItem.getDueDate().getTime());
		    }
		    testObject.saveInBackground();
		    
			return status;
	    } catch (Exception e){
	    	return false;
	    }
	}

	private ContentValues createRow(ITodoItem todoItem) {
		ContentValues task = new ContentValues();
	    task.put("title", todoItem.getTitle());
	    if (todoItem.getDueDate()!=null) {
	    	task.put("due", todoItem.getDueDate().getTime());
	    }
		return task;
	}
	
	public boolean update(ITodoItem todoItem) {
		try {
		    ContentValues task = createRow(todoItem);
		    String namePrefix = todoItem.getTitle();
		    Boolean status = _db.update("tasks", task, "title=?",new String[] { namePrefix }) != 0;
		    
		    final ITodoItem todoItemEx = todoItem;
		    GetCallback cb = new GetCallback() {
			      public void done(ParseObject obj, ParseException e) {
				        if (e == null) {
				        		obj.put("title", todoItemEx.getTitle());
				        		long due = todoItemEx.getDueDate() == null ? null :
				        			todoItemEx.getDueDate().getTime();
				        		obj.put("due", due);
				        	    obj.saveInBackground();
				        	}
				        }
				    };
		    
		    ParseQuery query = new ParseQuery("todo");
		    query.whereEqualTo("title", todoItem.getTitle());
		    query.getFirstInBackground(cb);

			return status;
	    } catch (Exception e){
	    	return false;
	    }
	}
	 
	public boolean delete(ITodoItem todoItem) {
		try {
		    String namePrefix = todoItem.getTitle();
		    Boolean status = _db.delete("tasks", "title=?",new String[] { namePrefix }) != 0;
		   
		    final ITodoItem todoItemEx = todoItem;
		    GetCallback cb = new GetCallback() {
			      public void done(ParseObject obj, ParseException e) {
				        if (e == null) {
				        		obj.deleteInBackground();
				        	}
				        }
				    };
		    
		    ParseQuery query = new ParseQuery("todo");
		    query.whereEqualTo("title", todoItem.getTitle());
		    query.getFirstInBackground(cb);

		    return status; 
	    } catch (Exception e){
	    	return false;
	    }
	}
	 
	 public List<ITodoItem> all() {
		 List<ITodoItem> tasks = new ArrayList<ITodoItem>();
		 Cursor cursor = allCursor();
		 if (cursor.moveToFirst()) {
			 do {
			    String title = cursor.getString(0);
			    long t = cursor.getLong(1);
			    Date due = cursor.getLong(1)==0 ? null : new Date(cursor.getLong(1));
			    tasks.add(new Todo(title, due));
			 } while (cursor.moveToNext());
		 }
		 return tasks;  
	 }
	 
	 public Cursor allCursor() {
		 return _db.query("tasks", new String[] { "title", "due" }, null, null, null, null, null);
	 }
	 
}