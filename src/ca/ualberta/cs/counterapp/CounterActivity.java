
//much code taken and modified from LonelyTwitter Author:Joshua Campbell 2014-01-24

package ca.ualberta.cs.counterapp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;


import android.app.Activity;
import android.content.Context;
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


public class CounterActivity extends Activity
{
	private static final String FILENAME = "file.sav";
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
				//REMOVE ME_____________________________________________________----------------------------------
				cm.countPerHour();
				//____________________________________________________________________------------------------------------------
				saveInFile(dataList);
				adapter.notifyDataSetChanged();
			}
		});
		
		vi.setOnItemLongClickListener(new OnItemLongClickListener(){
			
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
				CounterModel cm = (CounterModel) vi.getItemAtPosition(position);
				
				Intent i = new Intent(getApplicationContext(), NewButtonActivity.class);
				String jsonString = gson.toJson(cm);
				i.putExtra("cModel", jsonString);
				
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
		adapter = new ListAdapter(this, dataList);
		vi.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
	}
	
	
	private void saveInFile(ArrayList<CounterModel> dataList) {
		//clears file and write in each CounterModel delineated by \n
		try {
		FileOutputStream fos = openFileOutput(FILENAME,
				Context.MODE_PRIVATE);
		for (int i=0; i<dataList.size(); i++){
		
			String jsonString = gson.toJson(dataList.get(i)) + "\n"; 
			fos.write(jsonString.getBytes());
			
		}
		fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private ArrayList<CounterModel> loadFromFile() {
		ArrayList<CounterModel> cml = new ArrayList<CounterModel>();

		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			while (line != null) {
				cml.add(gson.fromJson(line, CounterModel.class));
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cml;
	}
}
