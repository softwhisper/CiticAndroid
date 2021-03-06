package and25.completo.adapters;

import java.util.List;

import and25.completo.R;
import and25.completo.models.Student;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;



public class StudentArrayAdapter extends ArrayAdapter<Student> {
	
	private int resource;
	
	public StudentArrayAdapter(Context context, int textViewResourceId, List<Student> items) {
		super(context, textViewResourceId, items);
		resource = textViewResourceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LinearLayout view;
		
		Student student = getItem(position);
		
	    if (convertView == null) {
	    	view = new LinearLayout(getContext());
	        String inflater = Context.LAYOUT_INFLATER_SERVICE;
	        LayoutInflater li;
	        li = (LayoutInflater)getContext().getSystemService(inflater);
	        li.inflate(resource, view, true); 
	    } else {
	    	view = (LinearLayout) convertView;
	    }
	    
	    TextView txtName = (TextView)view.findViewById(R.id.txtFullname);
	    TextView txtEmail = (TextView)view.findViewById(R.id.txtEmail);
	    TextView txtCity = (TextView)view.findViewById(R.id.txtCity);
		
	    txtName.setText(student.getFullname());
	    txtEmail.setText(student.getEmail());
	    txtCity.setText(student.getCity());	   
	    
		return view;
	}

}
