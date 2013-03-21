package il.ac.huji.todolist;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

public class AddNewTodoItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_todo_item);
		Button okButton = 
        		(Button)findViewById(R.id.btnOK);
		okButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        		Intent result = new Intent();
        		EditText edtNewItem = (EditText)findViewById(R.id.edtNewItem);
        		result.putExtra("title", edtNewItem.getText().toString());
        		DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker);
        		@SuppressWarnings("deprecation")
				Date date = new Date(datePicker.getYear()-1900, datePicker.getMonth(), datePicker.getDayOfMonth());
        		result.putExtra("dueDate", date);
        		setResult(RESULT_OK, result);
        		finish();
            }
        });	 
		Button cancelButton = 
        		(Button)findViewById(R.id.btnCancel);
		cancelButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        		Intent result = new Intent();
        		setResult(RESULT_CANCELED, result);
        		finish();
            }
        });	 
	}
	
}
