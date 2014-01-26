package ca.ualberta.cs.counterapp;


public class MonthConvert
{
	public String getMonth(int code){
		if (code ==1){
			return "Jan";
		}
		if (code ==2){
			return "Feb";
		}
		if (code ==3){
			return "Mar";
		}
		if (code ==4){
			return "Apr";
		}
		if (code ==5){
			return "May";
		}
		if (code ==6){
			return "Jun";
		}
		if (code ==7){
			return "Jul";
		}
		if (code ==8){
			return "Aug";
		}
		if (code ==9){
			return "Sep";
		}
		if (code ==10){
			return "Oct";
		}
		if (code ==11){
			return "Nov";
		}
		if (code ==12){
			return "Dec";
		}
		else{
			return "ERROR";
		}
		
	}

}
