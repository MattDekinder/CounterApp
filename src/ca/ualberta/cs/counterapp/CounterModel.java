package ca.ualberta.cs.counterapp;

import java.util.ArrayList;
import java.util.Calendar;

public class CounterModel{
	protected int count=0;
	protected String name;
	protected String newName;
	protected ArrayList<Calendar> dateList = new ArrayList<Calendar>();
	//the next three variables are used to identify particular objects between activities
    //for deletion and renaming by setting 1 in the correct field.
	protected int generateStatsFlag =0;
	protected int markedDeleteFlag = 0;
	protected int markedRenameFlag =0;
	
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
		dateList.add(time);
	}
	
	public String getNewName()
	{
	
		return newName;
	}
	
	public void setNewName(String newName)
	{
	
		this.newName = newName;
	}
	
	public int getGenerateStatsFlag()
	{
	
		return generateStatsFlag;
	}
	
	public void setGenerateStatsFlag(int generateStatsFlag)
	{
	
		this.generateStatsFlag = generateStatsFlag;
	}
	
	public int getMarkedDeleteFlag()
	{
	
		return markedDeleteFlag;
	}
	
	public void setMarkedDeleteFlag(int markedDeleteFlag)
	{
	
		this.markedDeleteFlag = markedDeleteFlag;
	}
	
	public int getMarkedRenameFlag()
	{
	
		return markedRenameFlag;
	}
	
	public void setMarkedRenameFlag(int markedRenameFlag)
	{
	
		this.markedRenameFlag = markedRenameFlag;
	}
	
	public ArrayList<String> countPerHour(){
		int hour;
		int prevHour = 0;
		int month =0;
		int prevMonth=0;
		int year =0;
		int prevYear=0;
		int day=0;
		int prevDay=0;
		if (dateList.size()>0) {
			prevHour=dateList.get(0).get(Calendar.HOUR_OF_DAY);
			prevDay = dateList.get(0).get(Calendar.DAY_OF_MONTH);
			prevMonth =dateList.get(0).get(Calendar.MONTH);
			prevYear = dateList.get(0).get(Calendar.YEAR);
		}
		int count=0;
		String date;
		ArrayList<String> list= new ArrayList<String>();
		MonthConvert mcv = new MonthConvert();

		for (int i=0; i<dateList.size(); i++){
			hour = dateList.get(i).get(Calendar.HOUR_OF_DAY);
			day = dateList.get(i).get(Calendar.DAY_OF_MONTH);
			month =dateList.get(i).get(Calendar.MONTH);
			year = dateList.get(i).get(Calendar.YEAR);
			count++;
			
			if (prevHour != hour || prevDay != day || month != prevMonth || year != prevYear ) {
				date = mcv.getMonth(dateList.get(i-1).get(Calendar.MONTH));
				list.add(date+" "+Integer.toString(day)+" "+Integer.toString(prevHour)+":00 "+Integer.toString(prevYear) + "-- "+Integer.toString(count-1)+"\n");
				count = 1;
			}
			if (i==dateList.size()-1) {
				date = mcv.getMonth(dateList.get(i).get(Calendar.MONTH));
				list.add(date+" "+Integer.toString(day)+" "+Integer.toString(hour)+":00 "+Integer.toString(prevYear) + "-- "+Integer.toString(count)+"\n");
			}
			prevHour=hour;
			prevMonth = month;
			prevYear = year;
			prevDay = day;
			
		}
		return list;
	}
	
	public ArrayList<String> countPerDay(){
		int month =0;
		int prevMonth=0;
		int year =0;
		int prevYear=0;
		int day=0;
		int prevDay=0;
		if (dateList.size()>0) {
			prevDay = dateList.get(0).get(Calendar.DAY_OF_MONTH);
			prevMonth =dateList.get(0).get(Calendar.MONTH);
			prevYear = dateList.get(0).get(Calendar.YEAR);
		}
		int count=0;
		String date;
		ArrayList<String> list= new ArrayList<String>();
		MonthConvert mcv = new MonthConvert();

		for (int i=0; i<dateList.size(); i++){
			day = dateList.get(i).get(Calendar.DAY_OF_MONTH);
			month =dateList.get(i).get(Calendar.MONTH);
			year = dateList.get(i).get(Calendar.YEAR);
			count++;
			
			if (prevDay != day || month != prevMonth || year != prevYear ) {
				date = mcv.getMonth(dateList.get(i-1).get(Calendar.MONTH));
				list.add(date+" "+Integer.toString(day)+" "+":00 "+Integer.toString(prevYear) + "-- "+Integer.toString(count-1)+"\n");
				count = 1;
			}
			if (i==dateList.size()-1) {
				date = mcv.getMonth(dateList.get(i).get(Calendar.MONTH));
				list.add(date+" "+Integer.toString(day)+" "+":00 "+Integer.toString(prevYear) + "-- "+Integer.toString(count)+"\n");
			}

			prevMonth = month;
			prevYear = year;
			prevDay = day;
			
		}
		return list;
	}
	
	public ArrayList<String> countPerMonth(){
		int month =0;
		int prevMonth=0;
		int year =0;
		int prevYear=0;
		if (dateList.size()>0) {
			prevMonth =dateList.get(0).get(Calendar.MONTH);
			prevYear = dateList.get(0).get(Calendar.YEAR);
		}
		int count=0;
		String date;
		ArrayList<String> list= new ArrayList<String>();
		MonthConvert mcv = new MonthConvert();

		for (int i=0; i<dateList.size(); i++){
			month =dateList.get(i).get(Calendar.MONTH);
			year = dateList.get(i).get(Calendar.YEAR);
			count++;
			
			if (month != prevMonth || year != prevYear ) {
				date = mcv.getMonth(dateList.get(i-1).get(Calendar.MONTH));
				list.add(date+" "+":00 "+Integer.toString(prevYear) + "-- "+Integer.toString(count-1)+"\n");
				count = 1;
			}
			if (i==dateList.size()-1) {
				date = mcv.getMonth(dateList.get(i).get(Calendar.MONTH));
				list.add(date+" "+":00 "+Integer.toString(prevYear) + "-- "+Integer.toString(count)+"\n");
			}

			prevMonth = month;
			prevYear = year;
			
		}
		return list;
	}
	
	public ArrayList<String> countPerWeek(){
		int month =0;
		int prevMonth=0;
		int year =0;
		int prevYear=0;
		int week=0;
		int prevWeek=0;
		if (dateList.size()>0) {
			prevWeek = dateList.get(0).getFirstDayOfWeek();
			prevMonth =dateList.get(0).get(Calendar.MONTH);
			prevYear = dateList.get(0).get(Calendar.YEAR);
		}
		int count=0;
		String date;
		ArrayList<String> list= new ArrayList<String>();
		MonthConvert mcv = new MonthConvert();

		for (int i=0; i<dateList.size(); i++){
			week = dateList.get(i).getFirstDayOfWeek();
			month =dateList.get(i).get(Calendar.MONTH);
			year = dateList.get(i).get(Calendar.YEAR);
			count++;
			
			if (prevWeek != week || month != prevMonth || year != prevYear ) {
				date = mcv.getMonth(dateList.get(i-1).get(Calendar.MONTH));
				list.add(date+" "+Integer.toString(week)+" "+":00 "+Integer.toString(prevYear) + "-- "+Integer.toString(count-1)+"\n");
				count = 1;
			}
			if (i==dateList.size()-1) {
				date = mcv.getMonth(dateList.get(i).get(Calendar.MONTH));
				list.add(date+" "+Integer.toString(week)+" "+":00 "+Integer.toString(prevYear) + "-- "+Integer.toString(count)+"\n");
			}

			prevMonth = month;
			prevYear = year;
			prevWeek = week;
			
		}
		return list;
	}

	
}
