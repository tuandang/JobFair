package com.example.jobfairapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.example.jobfairapp.DBAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends Activity {
	Uri uri;
	public int tValue;

    
	public void applyWeb(View vew)
	{
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW); // display data to user
		
		Bundle extras = getIntent().getExtras();
        tValue = extras.getInt("index"); 

        
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
        
        //---get a contact---
        db.open();
        Cursor
         c = db.getContact(tValue);
        if (c.moveToFirst())        
    		uri = Uri.parse(c.getString(3));
      
        else
            Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
        db.close();


		intent.setData(uri);
		startActivity(intent);		
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.about);

        Bundle extras = getIntent().getExtras();
        tValue = extras.getInt("index"); 

        
        try {        	
        	String destPath = "/data/data/" + getPackageName() + "/databases/MyDB";
        	File f = new File(destPath);  


        	
        	//if (!f.exists()) {     


			    CopyDB( getBaseContext().getAssets().open("newdb"), 
					new FileOutputStream(destPath));
        	//}
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        DBAdapter db = new DBAdapter (this);

        TextView text = (TextView) findViewById(R.id.textView1);

        
        //---get a contact---
        db.open();
        Cursor
         c = db.getContact(tValue);
        if (c.moveToFirst())        
        {
        	text.setText (c.getString(1) + ": " + c.getString(2));

        }
        else
            Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();

        
        

	    
        db.close();



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
        Log.d ("a", "fffff2");
        inputStream.close();
        outputStream.close();
    }
    


	

}
