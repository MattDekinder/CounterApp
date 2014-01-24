package ca.ualberta.cs.counterapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
//frame layout used for popup window
//import android.widget.FrameLayout;


//create references to the objects defined in the xml files
//add functionality
//add saving



public class CounterActivity extends Activity
{
	private static final String FILENAME = "file.sav";
	ListAdapter adapter;
	ArrayList<CounterModel> dataList;
	ListView vi;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){

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
				adapter.notifyDataSetChanged();
			}
		});
		
		vi.setOnItemLongClickListener(new OnItemLongClickListener(){
			
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
				CounterModel cm = (CounterModel) vi.getItemAtPosition(position);
				
				Intent i = new Intent(getApplicationContext(), NewButtonActivity.class);
//TODO:MUST PASS SERIALIZED OBJECT TO NEXT ACTIVITY TO GENEREATE STATS. OR FIND SOME OTHER METHOD.
//				i.putExtra(counter_model, value)
				
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
				adapter.notifyDataSetChanged();
			}
		});
		
		
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		vi.setLongClickable(true);
		
		dataList = new ArrayList<CounterModel>();
		CounterModel mod1 = new CounterModel();
		CounterModel mod2 = new CounterModel();
		mod1.setCount(5);
		mod1.setName("fsdfdsfds");
		mod2.setCount(57);
		mod2.setName("ffffffff");
		dataList.add(mod1);
		dataList.add(mod2);
		adapter = new ListAdapter(this, dataList);
		vi.setAdapter(adapter);
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		adapter.notifyDataSetChanged();
		
	}

}
