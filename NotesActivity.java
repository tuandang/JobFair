package com.example.jobfairapp;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.graphics.drawable.BitmapDrawable;




public class NotesActivity extends Activity  {

	ImageView imageView;
	
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);
        imageView = (ImageView) findViewById (R.id.imageView1);

    }
    
    public void doButton2 (View view)
    {
        DBAdapter db = new DBAdapter(this); 
        db.open();

        if ((db.getImage (1))!=null)
        {	
            Log.d ("hererree", "yeaaa0");
          Bitmap bitmap2 = db.getImage(1);
          Log.d ("hererree", "yeaaa1");
          imageView.setImageBitmap (bitmap2);
        }
        db.close();
    }
    
    private final int CAMERA_REQUEST_CODE = 1;
    public void doButton (View view)
    {
    	Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
    	
    	startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }
    
  
    
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
    	// if coming right from the camera
		if(requestCode == CAMERA_REQUEST_CODE)
		{
			// if user keeps the photo
			if(resultCode == RESULT_OK)
			{
				if(data!=null){
					 DBAdapter db = new DBAdapter(this); 

				        db.open();
				        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
				        
					Bitmap bitmap = data.getParcelableExtra("data");

					bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);   
			        byte[] photo = baos.toByteArray(); 
			        Log.d ("CHECKTHIS", "" +photo.length);
			        db.insertUserDetails("tuan","one", "pass", photo,"visibility");
					imageView.setImageBitmap(bitmap);
			        db.close();
				}
			}
			return;
		}
		super.onActivityResult(requestCode, resultCode, data);
    }
    
	


   

}
