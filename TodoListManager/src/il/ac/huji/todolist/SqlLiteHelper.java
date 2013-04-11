package il.ac.huji.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlLiteHelper extends SQLiteOpenHelper {

	public SqlLiteHelper(Context context) {
	    super(context, "todo_db", null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		    db.execSQL("create table tasks ( " +
		  	      "_id integer primary key autoincrement, " +
		  	      "due integer, " +
		  	      "title string);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
