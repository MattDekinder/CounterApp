package ca.ualberta.cs.counterapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NewButtonActivity extends Activity
{
	CounterModel cm;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_button);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_button, menu);
		return true;
	}

}
