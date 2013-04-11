package il.ac.huji.todolist;

import java.util.Date;

public class Todo implements ITodoItem{
	
	public Todo(String title, Date dueDate) {
		this._title = title;
		this._dueDate = dueDate;
	}
	
	private String _title;
	private Date _dueDate;
	
	public String toString() {
		return _title;
	}

	@Override
	public String getTitle() {
		return _title;
	}

	@Override
	public Date getDueDate() {
		return _dueDate;
	}
	
}
