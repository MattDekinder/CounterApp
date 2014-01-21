package ca.ualberta.cs.counterapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class CounterActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_counter);

	
		FrameLayout layout = (FrameLayout) findViewById(R.id.sixsixsix);
		
		Button button = (Button) findViewById(R.id.dummy_button); 
		
		button.setOnClickListener(new OnClickListener()
		
		{
			
			@Override
			public void onClick(View v)
			{
//				Button nbutton = new Button(null);
//				nbutton.setText("test");
				Intent i = new Intent(getApplicationContext(), NewButtonActivity.class);
				startActivity(i);
				
			}
		});
		
	}
}
