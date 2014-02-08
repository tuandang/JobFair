package com.example.jobfairapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class NotesActivity extends Activity {
	
	EditText editText;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    // TODO Auto-generated method stub
	    //setContentView(R.layout.notes);
	    editText = new EditText(this);
	    editText.setHint("Add some text here");
	    setContentView(editText);

	}

}
