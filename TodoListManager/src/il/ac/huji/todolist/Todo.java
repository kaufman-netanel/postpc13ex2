package il.ac.huji.todolist;

import java.util.Date;

public class Todo {
	
	public Todo(String task, Date date) {
		this.task = task;
		this.date = date;
	}
	
	public String task;
	public Date date;
	
	public String toString() {
		return task;
	}
	
}
