package il.ac.huji.todolist;

import java.util.List;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Todo> {
	public CustomAdapter(
			TodoListManagerActivity activity,
			List<Todo> todoList) {
		super(activity, android.R.layout.simple_list_item_1, todoList);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Todo todo = getItem(position);
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.todo_list_row, null);
		TextView txtTaskTitle = (TextView)view.findViewById(R.id.txtTaskTitle);
		txtTaskTitle.setText(todo.task);
		txtTaskTitle.setTextColor( position % 2 == 0 ? Color.RED : Color.BLUE);
		return view;
	}
}