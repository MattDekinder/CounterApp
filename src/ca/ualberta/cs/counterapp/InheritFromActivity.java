package ca.ualberta.cs.counterapp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;

public class InheritFromActivity extends Activity
{
	private static final String FILENAME = "file.sav";
	
	protected void saveInFile(ArrayList<CounterModel> dataList) {
		//clears file and write in each CounterModel delineated by \n
		Gson gson = new Gson();
		Collections.sort(dataList, new Comparator<CounterModel>()
		{
			@Override
			public int compare(CounterModel lhs, CounterModel rhs)
			{
				return rhs.getCount() - lhs.getCount();
			}
		});
		
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
	
	protected ArrayList<CounterModel> loadFromFile() {
		Gson gson = new Gson();
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
