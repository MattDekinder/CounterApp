package ca.ualberta.cs.counterapp;

import java.util.ArrayList;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

public class NewButtonActivity extends Activity
{
	CounterModel cm;
	Gson gson = new Gson();
	
	ArrayList<String> statList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_button);
		Intent intent = getIntent();
		String jsonString = intent.getExtras().getString("cModel");
		CounterModel cm = gson.fromJson(jsonString, CounterModel.class);
		TextView tv = new TextView(getApplicationContext());
		statList = cm.countPerWeek();
		tv=(TextView) findViewById(R.id.statistics_table);
		for (int i=0; i<statList.size(); i++){
			tv.append(statList.get(i));
		}
	}

//TODO: add counter removal and renaming 
}
