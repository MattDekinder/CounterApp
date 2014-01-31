package ca.ualberta.cs.counterapp;


import java.util.ArrayList;

import com.google.gson.Gson;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StatsActivity extends InheritFromActivity
{
	Gson gson = new Gson();
	CounterModel cm;
	ArrayList<CounterModel> dataList;
//	Gson gson = new Gson();
	 
	ArrayList<String> statList = new ArrayList<String>();
	String[] StringList = {"Count By Month","Count By Week","Count By Day", "Count By Hour"}; 

	int listIndex = 0;
	int dataListIndex;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
//		Intent intent = getIntent();
//		String jsonString = intent.getExtras().getString("cModel");
//		final CounterModel cm = gson.fromJson(jsonString, CounterModel.class);
		dataList = loadFromFile();
		for (dataListIndex=0; dataListIndex<dataList.size(); dataListIndex++){
			if (dataList.get(dataListIndex).getGenerateStatsFlag() == 1){
				cm = dataList.get(dataListIndex);
				break;
			}
		}
		//reset this flag first; if the program terminates before this step then it will break
		cm.setGenerateStatsFlag(0);
		saveInFile(dataList);
		
		EditText rename_field= (EditText) findViewById(R.id.rename_text);
		rename_field.setText(cm.name);
		
		TextView tv = new TextView(getApplicationContext());
		tv=(TextView) findViewById(R.id.statistics_table);
		
		statList = cm.countPerHour();
		
		for (int i=0; i<statList.size(); i++){
			tv.append(statList.get(i));
		}
		
		Button cycleButton = (Button) findViewById(R.id.cycle_button);
		cycleButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
			((Button)findViewById(R.id.cycle_button)).setText(StringList[listIndex]);
			listIndex++;
			if (listIndex==4){
				listIndex=0;
			}
			if (listIndex==0){
				statList = cm.countPerHour();
			}
			if (listIndex==1){
				statList = cm.countPerMonth();
			}
			if (listIndex==2){
				statList = cm.countPerWeek();
			}
			if (listIndex==3){
				statList = cm.countPerDay();
			}
			TextView tv = new TextView(getApplicationContext());
			tv=(TextView) findViewById(R.id.statistics_table);
			tv.setText("");
			for (int i=0; i<statList.size(); i++){
				tv.append(statList.get(i));
			}
			
			}
		});
		

		
		Button renameButton = (Button) findViewById(R.id.rename);
		renameButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				EditText rename_field= (EditText) findViewById(R.id.rename_text);
				cm.setName(rename_field.getText().toString());
				saveInFile(dataList);
				rename_field.setText("");
			}
		});
		
		Button deleteButton = (Button) findViewById(R.id.delete);
		deleteButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				dataList.remove(dataListIndex);
				saveInFile(dataList);
				finish();
			}
		});
	}

//TODO: add counter removal and renaming 
}
