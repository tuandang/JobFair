
package com.example.jobfairapp;

import android.app.Activity;
import android.os.Bundle;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.jobfairapp.Reminders;



import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.database.Cursor;

                   
import android.content.DialogInterface;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class FavoritesActivity extends Activity  {


    DBAdapter db = new DBAdapter(this); 
	public int cValue;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);
        
	        DBAdapter db = new DBAdapter (FavoritesActivity.this);
	        TextView text = (TextView) findViewById(R.id.textView1);



	        	
	        String str = "";	
			db.open();
			int index=1; int flag = 1;
			while (flag==1)
			{	

		       Cursor
		         c = db.getFavoriteContact(index);
		        if (c.moveToFirst()){        
		            str = str + "\n" + c.getString(1);
		            index++;
		        }
		        else{ 
		        	flag = 0;
		            Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
		        }    
			}    

			
		    db.close();
		    
		    text.setText (str);

    

    }
    
    public void DisplayContact(Cursor c)
    {
        Toast.makeText(this, 
                "id: " + c.getString(0) + "\n" +
                "Name: " + c.getString(1) + "\n",
                Toast.LENGTH_LONG).show();        
    } 


}

