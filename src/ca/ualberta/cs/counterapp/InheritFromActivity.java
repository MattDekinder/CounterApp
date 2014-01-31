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
		/*clears file,then writes each CounterModel delineated by \n 
		 * to file FILENAME. Modified from LonelyTwitter 
		 * Author:Joshua Campbell 2014-01-24*/
		Gson gson = new Gson();
		
		try {
		FileOutputStream fos = openFileOutput(FILENAME,
				Context.MODE_PRIVATE);
		for (int i=0; i<dataList.size(); i++){
		
			String jsonString = gson.toJson(dataList.get(i)) + "\n"; 
			fos.write(jsonString.getBytes());
			
		}
		fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	protected ArrayList<CounterModel> loadFromFile() {
		/*obtains counter models from file FILENAME. 
		 * Modified from LonelyTwitter Author:Joshua Campbell 2014-01-24 */
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cml;
	}
	
	protected ArrayList<CounterModel> sortCounterList(ArrayList<CounterModel> dataList) {
		/*Sorts ArrayList of counter models by count in descending order.
		 * Returns Sorted ArrayList<CounterModel> */
	Collections.sort(dataList, new Comparator<CounterModel>()
			{
				@Override
				public int compare(CounterModel lhs, CounterModel rhs)
				{
					return rhs.getCount() - lhs.getCount();
				}
			});
		return dataList;
	}
}
