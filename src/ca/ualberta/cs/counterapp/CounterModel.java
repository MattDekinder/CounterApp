package ca.ualberta.cs.counterapp;

import java.util.ArrayList;
import java.util.Date;

public class CounterModel{
	protected Date timestamp;
	protected int count=0;
	protected String name;
	protected ArrayList<Date> dateList = new ArrayList<Date>();
	
	public ArrayList<Date> getTimestamp(){
	
		return dateList;
	}
	public String getName(){
		return name;
	}
	public void setName(String text){
		this.name = text;
	}
	
	public void setTimestamp(Date timestamp){
		this.timestamp = timestamp;
		dateList.add(timestamp);
	}
	
	public int getCount(){
	
		return count;
	}
	
	public void setCount(int count){
		this.count = count;
	}
	
	public void incCount(){
		this.count++;
	}
	//TODO: insert methods to obtain stats.
	
}
