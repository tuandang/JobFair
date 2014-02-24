package com.example.jobfairapp;

import java.io.File;
import android.view.MotionEvent;
import android.widget.AdapterView.OnItemLongClickListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;




import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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

public class MainActivity extends Activity {
	
	Uri uri;

    // List view
    private ListView lv;
     
    // Listview Adapter
    ArrayAdapter<String> adapter;
     
    // Search EditText
    EditText inputSearch;
     
     
    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
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


        String products[] = {db.getContact(1).getString(1), db.getContact(2).getString(1),
				 db.getContact(3).getString(1), db.getContact(4).getString(1),
				 db.getContact(5).getString(1), db.getContact(6).getString(1),
				 db.getContact(7).getString(1), db.getContact(8).getString(1),
				 db.getContact(9).getString(1), db.getContact(10).getString(1),
				 db.getContact(11).getString(1), db.getContact(12).getString(1),
				 db.getContact(13).getString(1), db.getContact(14).getString(1),
				 db.getContact(15).getString(1), db.getContact(16).getString(1),
				 db.getContact(17).getString(1), db.getContact(18).getString(1),
				 db.getContact(19).getString(1), db.getContact(20).getString(1),
				 db.getContact(21).getString(1), db.getContact(22).getString(1),
				 db.getContact(23).getString(1), db.getContact(24).getString(1),
				 db.getContact(25).getString(1), db.getContact(26).getString(1),
				 db.getContact(27).getString(1), db.getContact(28).getString(1),
				 db.getContact(29).getString(1), db.getContact(30).getString(1),
				 db.getContact(31).getString(1), db.getContact(32).getString(1),
				 db.getContact(33).getString(1), db.getContact(34).getString(1),
				 db.getContact(35).getString(1), db.getContact(36).getString(1),
				 db.getContact(37).getString(1), db.getContact(38).getString(1),
				 db.getContact(39).getString(1), db.getContact(40).getString(1),
				 db.getContact(41).getString(1), db.getContact(42).getString(1),
				 db.getContact(43).getString(1), db.getContact(44).getString(1),
				 db.getContact(45).getString(1), db.getContact(46).getString(1),
				 db.getContact(47).getString(1), db.getContact(48).getString(1),
				 db.getContact(49).getString(1), db.getContact(50).getString(1),
				 db.getContact(51).getString(1), db.getContact(52).getString(1),
				 db.getContact(53).getString(1), db.getContact(54).getString(1),
				 db.getContact(55).getString(1), db.getContact(56).getString(1),
				 db.getContact(57).getString(1), db.getContact(58).getString(1),
				 db.getContact(59).getString(1), db.getContact(60).getString(1),
				 db.getContact(61).getString(1), db.getContact(62).getString(1),
				 db.getContact(63).getString(1), db.getContact(64).getString(1),
				 db.getContact(65).getString(1), db.getContact(66).getString(1),
				 db.getContact(67).getString(1), db.getContact(68).getString(1),
				 db.getContact(69).getString(1), db.getContact(70).getString(1),
				 db.getContact(71).getString(1), db.getContact(72).getString(1),
				 db.getContact(73).getString(1), db.getContact(74).getString(1),
				 db.getContact(75).getString(1), db.getContact(76).getString(1),
				 db.getContact(77).getString(1), db.getContact(78).getString(1),
				 db.getContact(79).getString(1), db.getContact(80).getString(1),

};
	    db.close();
        
        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        inputSearch.setInputType(InputType.TYPE_NULL);
        inputSearch.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
            inputSearch.setInputType(InputType.TYPE_CLASS_TEXT);
            inputSearch.onTouchEvent(event); // call native handler
            return true; // consume touch even
            } 
        });
        adapter = new ArrayAdapter<String>(this, R.layout.activity_main, R.id.product_name, products);
        lv.setAdapter(adapter);
        
	    lv.setOnItemLongClickListener(new OnItemLongClickListener() {

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
	          			
	          			String item = lv.getItemAtPosition(position).toString();

	          	        long id = db.insertFavoriteContact(item);   

	          			
	          		    db.close();
	        			Toast.makeText(MainActivity.this, "company saved to Favorites", Toast.LENGTH_LONG).show();

	          		    
	          		    

	          		}
	          	}); 

	          	alert.show();
		        return true;
		      }
		    });
	    
	    lv.setOnItemClickListener(new ListView.OnItemClickListener() {

		      @Override
		      public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
		  		Intent intent = new Intent (MainActivity.this, AboutActivity.class);
				intent.putExtra("index", position+1);

				startActivity (intent);
		      }
		    });

        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {
             
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                MainActivity.this.adapter.getFilter().filter(cs);   
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }
             
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub                          
            }
        });

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

			startActivity(intent);
		}
		
		if(item.getItemId() == R.id.action_settings)
		{
			//Toast.makeText(this, "Take your own notes", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, NotesActivity.class);
			startActivity(intent);
		}

		if(item.getItemId() == R.id.menu_googledoc)
		{
			
			Intent intent = new Intent(this, GoogleDocsActivity.class);
			startActivity(intent);
		}

		


		return super.onOptionsItemSelected(item);
	}


}
