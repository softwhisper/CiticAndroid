package curso.and10;

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import curso.and10.R;

public class MainActivity extends Activity {
	
	Button btnCreate;
	Button btnUpdate;
	Button btnRead;
	EditText txtNombre;
	EditText txtTelefono;
	TextView txtTodos;
	
	DataBaseHandler db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		db = new DataBaseHandler(this);
		
		bindUI();
		setListeners();
	}
	
	private void bindUI() {
		btnCreate = (Button)findViewById(R.id.btnCrear);
		btnUpdate = (Button)findViewById(R.id.btnActualizar);
		btnRead = (Button)findViewById(R.id.btnLeer);
		
		txtNombre = (EditText)findViewById(R.id.txtNombre);
		txtTelefono = (EditText)findViewById(R.id.txtTelefono);
		txtTodos = (TextView)findViewById(R.id.txtTodos);
	}
	
	private void setListeners() {
		btnCreate.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				createContacts();
			}
		});
		btnUpdate.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				updateContacts();
			}
		});		
		btnRead.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				readContacts();
			}
		});		
		
		((Button)findViewById(R.id.btnLeerTodos)).setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				readAll();
			}
		});
	}
	
	private void createContacts() {
		db.addContact(new Contact(txtNombre.getText().toString(), txtTelefono.getText().toString()));
		txtNombre.setText("");
		txtTelefono.setText("");
	}

	private void readContacts() {
		Contact c = db.getContact(1);
		txtNombre.setText("#" + c.getID() + " " + c.getName());
		txtTelefono.setText(c.getPhoneNumber());
	}	
	
	private void updateContacts() {		
		Contact c = db.getContact(1);
		c.setName(txtNombre.getText().toString());
		c.setPhoneNumber(txtTelefono.getText().toString());
		int ok = db.updateContact(c);
		if (ok == 1) {
			txtTelefono.setText("OK");			
		} else {
			txtTelefono.setText("Algo ha pasado");
		}
	}

	private void readAll() {		
		txtTodos.setText("");
		
		for (Contact c : db.getAllContacts()) {
			String line = "#" + c.getID() + " " + c.getName() + "\n";
			txtTodos.append(line);
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
