package ca.ualberta.cs.counterapp;

import java.util.ArrayList;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class NewButtonActivity extends Activity
{
	CounterModel cm;
	Gson gson = new Gson();
	TextView tv = new TextView(this);
	ArrayList<String> statList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_button);
		Intent intent = getIntent();
		String jsonString = intent.getExtras().getString("cModel");
		CounterModel cm = gson.fromJson(jsonString, CounterModel.class);
		statList = cm.countPerHour();
		tv=(TextView) findViewById(R.id.statistics_table);
		for (int i=0; i<statList.size(); i++){
			tv.append(statList.get(i));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_button, menu);
		return true;
	}
//TODO: add counter removal and renaming 
}
