package and02.almnos2;

import and02.almnos2.models.Alumno;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateForm extends ActionBarActivity {
	
	private static Context context; 
	
	private EditText txtFormName;
	private EditText txtFormLastName;
	private Spinner spinGender;
	
	private Button btnGuardar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_form);
		
		context = this;
		
		txtFormName = (EditText)findViewById(R.id.txtFormName);
		txtFormLastName = (EditText)findViewById(R.id.txtFormLastName);
		spinGender = (Spinner)findViewById(R.id.spinGender);
		btnGuardar = (Button)findViewById(R.id.btnGuardar);
		btnGuardar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				guardarAlumno();
			}
		});
		
	}

	
	private void guardarAlumno() {
		Alumno al = new Alumno(txtFormName.getText().toString(), 
							   txtFormLastName.getText().toString(), 
							   (String)spinGender.getSelectedItem());
		
		Log.d("FORM", al.toString());
		
		Intent intent = new Intent(context, ListActivity.class);
		intent.putExtra("alumno", al);
		startActivity(intent);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_form, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
