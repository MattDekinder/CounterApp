
//much code taken and modified from LonelyTwitter Author:Joshua Campbell 2014-01-24

package ca.ualberta.cs.counterapp;


import java.util.ArrayList;

import com.google.gson.Gson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class CounterActivity extends InheritFromActivity
{
	ListAdapter adapter;
	ArrayList<CounterModel> dataList;
	ListView vi;
	Gson gson = new Gson();
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		
		dataList = new ArrayList<CounterModel>();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counter);
		Button statsButton = (Button) findViewById(R.id.stats);
		final EditText field= (EditText) findViewById(R.id.edit_text);
		
		
		vi = (ListView) findViewById(R.id.counter_list);
		
		vi.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id)
			{
				CounterModel cm = (CounterModel) vi.getItemAtPosition(position);
				cm.incCount();
				saveInFile(dataList);
				adapter.notifyDataSetChanged();
			}
		});
		
		vi.setOnItemLongClickListener(new OnItemLongClickListener(){
			
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
				CounterModel cm = (CounterModel) vi.getItemAtPosition(position);
				
				Intent i = new Intent(getApplicationContext(), StatsActivity.class);
				cm.setGenerateStatsFlag(1); //this distinguishes it to the other activity.
				saveInFile(dataList);
//				String jsonString = gson.toJson(cm);
//				i.putExtra("cModel", jsonString);
				
				startActivity(i);
				return true;
			}
		});
		
		statsButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				String name= field.getText().toString();
				CounterModel cm = new CounterModel();
				cm.setName(name);
				dataList.add(cm);
				saveInFile(dataList);
				field.setText("");
				adapter.notifyDataSetChanged();
			}
		});
		
		vi.setLongClickable(true);
		
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
	
		dataList= loadFromFile();
		dataList = sortCounterList(dataList);
		adapter = new ListAdapter(this, dataList);
		vi.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
	}
	
	


	@Override
	protected void onStop()
	{
		super.onStop();
	}
}
