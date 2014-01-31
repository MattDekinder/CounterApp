package ca.ualberta.cs.counterapp;


public class MonthConvert
/*Class to convert Java's Calendar object's numerical
 * representations to strings for printing. */
{
	public String getMonth(int code){
		/*Converts the month portion of Calendar objects */
		if (code ==0){
			return "Jan";
		}
		if (code ==1){
			return "Feb";
		}
		if (code ==2){
			return "Mar";
		}
		if (code ==3){
			return "Apr";
		}
		if (code ==4){
			return "May";
		}
		if (code ==5){
			return "Jun";
		}
		if (code ==6){
			return "Jul";
		}
		if (code ==7){
			return "Aug";
		}
		if (code ==8){
			return "Sep";
		}
		if (code ==9){
			return "Oct";
		}
		if (code ==10){
			return "Nov";
		}
		if (code ==11){
			return "Dec";
		}
		else{
			return "ERROR";
		}
		
	}

}
