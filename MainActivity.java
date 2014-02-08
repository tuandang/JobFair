package com.example.jobfairapp;



import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	
	Uri uri;
	EditText editext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		String[] rowNames = 
		{"Abalta Tech", "Academia.edu", "Achieve Internet", "Ad-Juster", "Akamai Tech", 
		"Altera Corporation", "Amazon", "Apple", "Applied Medical", "Arista Networks", 
		 "Aruba Networks", "Autofusion", "Axure Software", "Baxter", "Beckman Coulter",
		 "Bionomics Consulting", "Blue Sky Scientific", "Bluebeam", "Boeing", "BrightScope", 
		 "Broadcom", "BussinessOnline", "CareFusion", "CBS Interative", "Chartboost", 
		 "Chevron", "Cisco", "CliniComp", "Contact Singapore", "County of LA, Dept of Public Works", 
		 "Coupa Software", "CRB", "Cubic Corp", "DR Systems", "Encore Capital Group", 
		 "Epig Tech", "Ericsson", "Facebook", "FactSet Research Systems", "FindTheBest.com", 
		 "Gelber Group", "General Atomics", "generationE Tech", "Google", "HP", "HGST", 
		 "HUGHES", "Hulu", "Illumina", "Informatica", "InnovaSystems International", 
		 "Integrity Application", "Intel", "Intellisis", "Intuit", "Kaiser Permanente",
		 "Lab Support", "Lab126", "Laserfiche", "Leidos", "Life Tech", "Linear Tech",
		 "LinkedIn", "LiveOps", "LSI Corporation", "MadCap Software", "Microsoft",
		 "Mitchell International", "MobilityWare", "Moebius Solutions", "Mosys", "National Instruments", 
		 "NOAA Commission Officer Corps", "Northrop Grumman", "Ooyala", "Opera Solutions", 
		 "OSIsoft", "PG&E", "Panasonic Avionics", "Peace Corps", "Pearson", 
		 "Peregrine Semiconductor", "Provide Commerce", "Raytheon", "Readyforce", "RocketFuel",
		 "Room 5", "Salesforce.com", "Samsung", "SDSU Research Foundation", "Seagate Tech", 
		 "Sears Holdings Corp", "Sense4Baby", "ServiceNow", "Shazam Entertainment", 
		 "Simpson Gumpertz & Heger", "SoftHQ", "Solar Turbines", "Space and Naval Warfare Systems Center SD", 
		 "SupplyFrame", "Tandem Diabetes Care", "TEKERP LLC", "Teradata Corp", "Thales Avionics", 
		 "The Control Group", "The Scripps Research Institute", "Theranos", "Triage Consulting Group", 
		 "TripAdvisor", "Turn", "Twitter", "U.S. Marine Corps", "Uber Tech", "United Tech", 
		 "UTC Aerospace Systems", "Veeva Systems", "Verimatrix", "ViaSat", "VisiSeek", 
		 "VMware", "Volcano Corporation", "Walt Disney Imagineering", "Webroot", "Western Digital", 
		 "WestPac Wealth", "Workday", "XIFIN", "Yahoo!", "Yelp", "ZestFinance", "Zynga"
		
		
		
		
		};
		ArrayAdapter <String> adapter = new ArrayAdapter<String>
		(this, android.R.layout.simple_list_item_1, rowNames);
		setListAdapter(adapter);			
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		for (int i = 0; i < 15; i++)
		{
			doBrowser1();
			break;
		}
	
	}

	private void doBrowser1(){
		Intent intent = new Intent(this, AboutActivity.class);
		startActivity(intent);
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
			//Toast.makeText(this, "List of favorite companies", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, FavoritesActivity.class);
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
	

}
