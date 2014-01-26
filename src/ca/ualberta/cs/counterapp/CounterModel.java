package ca.ualberta.cs.counterapp;

import java.util.ArrayList;
import java.util.Calendar;

public class CounterModel{
	protected Calendar timestamp;
	protected int count=0;
	protected String name;
	protected ArrayList<Calendar> dateList = new ArrayList<Calendar>();
	
	public ArrayList<Calendar> getTimestamp(){
	
		return dateList;
	}
	public String getName(){
		return name;
	}
	public void setName(String text){
		this.name = text;
	}
	
	public int getCount(){
	
		return count;
	}
	
	public void setCount(int count){
		this.count = count;
	}
	
	public void incCount(){
		count++;
		Calendar time = Calendar.getInstance();
		timestamp = time;
		dateList.add(timestamp);
	}
	
	public ArrayList<String> countPerHour(){
		int num;
		int lastNum=-1;
		int count=0;
		String date;
		ArrayList<String> list= new ArrayList<String>();
		MonthConvert mcv = new MonthConvert();
	
		for (int i=0; i<dateList.size(); i++){
			num = dateList.get(i).get(Calendar.HOUR_OF_DAY);
			count++;
			if (num != lastNum){
				date = mcv.getMonth(dateList.get(i).get(Calendar.MONTH));
				list.add(date+" "+Integer.toString(num)+":00 -- "+Integer.toString(count));
				count=0;
			}
			lastNum=num;
			
		}
		return list;
	}
	
	//TODO: insert methods to obtain stats.
	
}
