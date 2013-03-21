package il.ac.huji.todolist;

import java.util.GregorianCalendar;

public class Todo {
	
	public Todo(String task, GregorianCalendar date) {
		this.task = task;
		this.date = date;
	}
	
	public String task;
	public GregorianCalendar date;
	
	public String toString() {
		return task;
	}
	
}
