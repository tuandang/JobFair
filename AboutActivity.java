package com.example.jobfairapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends Activity {
	Uri uri;
	
	public void applyWeb(View vew)
	{
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW); // display data to user
		Uri uri = Uri.parse("http://www.tripadvisor.com/careers/jobs_engineering");
		intent.setData(uri);
		startActivity(intent);		
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    // TODO Auto-generated method stub
	    setContentView(R.layout.about);
	}

}
