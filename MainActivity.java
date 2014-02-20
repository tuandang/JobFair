package com.example.jobfairapp;

import java.io.File;
import android.widget.AdapterView.OnItemLongClickListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	
	Uri uri;
	EditText editext;
    String[] databaseArray = new String[100];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
        
        try {        	
        	String destPath = "/data/data/" + getPackageName() + "/databases/MyDB";
        	File f = new File(destPath);  

			CopyDB( getBaseContext().getAssets().open("newdb"), 
			  new FileOutputStream(destPath));

		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        DBAdapter db = new DBAdapter (this);
        

        db.open();

		db.getContact(1).getString(1);
		
	
		String[] rowNames = {db.getContact(1).getString(1), 
				db.getContact(2).getString(1), db.getContact(3).getString(1)};

		
	    db.close();
	    


		ArrayAdapter <String> adapter = new ArrayAdapter<String>
		(this, R.layout.activity_main, R.id.label, rowNames);
		setListAdapter(adapter);		
		final ListView list = getListView();
	    list.setOnItemLongClickListener(new OnItemLongClickListener() {

	      @Override
	      public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
	    	  AlertDialog.Builder alert = new AlertDialog.Builder( MainActivity.this );
          	alert.setTitle( "Save this company?" );
   
          	
          	alert.setPositiveButton("No", new DialogInterface.OnClickListener() {
          		@Override
          		public void onClick( DialogInterface dialog, int whichButton ) {

          			
          		}
          	});
          	alert.setNegativeButton( "Yes", new DialogInterface.OnClickListener() {
          		@Override
          		public void onClick( DialogInterface dialog, int whichButton ) {
          	        DBAdapter db = new DBAdapter (MainActivity.this);

          			db.open();
          			
          			String item = list.getItemAtPosition(position).toString();

          	        long id = db.insertFavoriteContact(item);   

          			
          		    db.close();
        			Toast.makeText(MainActivity.this, "company saved to Favorites", Toast.LENGTH_LONG).show();

          		    
          		    

          		}
          	}); 

          	alert.show();
	        return true;
	      }
	    });
		

	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		Intent intent = new Intent (this, AboutActivity.class);
		intent.putExtra("index", position+1);
		//intent.putExtra ("database", databaseArray);

		startActivity (intent);

	}
	

	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if(item.getItemId() == R.id.menu_about)
		{
			//.makeText(this, "List of favorite companies", Toast.LENGTH_LONG).show();Toast
			Intent intent = new Intent(this, FavoritesActivity.class);
			intent.putExtra ("database", databaseArray);
			Log.d ("WHAT IS DATABASE BEFORE", "" + databaseArray.length);
			startActivity(intent);
		}
		
		if(item.getItemId() == R.id.action_settings)
		{
			//Toast.makeText(this, "Take your own notes", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, NotesActivity.class);
			startActivity(intent);
		}
		
		if(item.getItemId() == R.id.aboutApp)
		{
			//Toast.makeText(this, "Take your own notes", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, VersionActivity.class);
			startActivity(intent);
		}
		


		return super.onOptionsItemSelected(item);
	}
	/* copy stream */
    public void CopyDB(InputStream inputStream, OutputStream outputStream) 
    throws IOException {
        //---copy 1K bytes at a time---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }

        inputStream.close();
        outputStream.close();
    }

}
