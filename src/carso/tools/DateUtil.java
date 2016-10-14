package carso.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
public class DateUtil {
	/**
	  * 得到本日 
	  */
	public static String getDay(){
		Calendar cl = Calendar.getInstance();
		cl.setTime(new Date());
		Date startDate = cl.getTime();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String start = dd.format(startDate);
		return start;
	}
	/**
	  * 得到本日0点
	  */
	public static String getDayFirst(){
		Calendar cl = Calendar.getInstance();
		cl.setTime(new Date());
		//cl.add(Calendar.DATE, days);
		Date startDate = cl.getTime();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		String start = dd.format(startDate);
		return start+" 00:00:00";
	}
	 /**
	  * 得到本周第一天
	  */
	  public static String getWeekFirst() {
		  Calendar c = Calendar.getInstance();
		  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		  if (day_of_week == 0)  day_of_week = 7;
		  c.add(Calendar.DATE, -day_of_week + 1);
		  SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		  return dd.format(c.getTime())+" 00:00:00";
	  }
	 /**
	   * 得到本月第一天
	  */
	 public static String getMonthFirst() {
		   Calendar calendar  = Calendar.getInstance();
		   calendar.set( Calendar.DATE,  1 );
		   SimpleDateFormat simpleFormate  =   new  SimpleDateFormat( "yyyy-MM-dd" );
		   return simpleFormate.format(calendar.getTime())+" 00:00:00";
	 }
	 
	 
    /**
	  * 得到upperDay日0点
	  */
	public static String getDayFirst(int upperDay){
		Calendar cl = Calendar.getInstance();
		cl.setTime(new Date());
		cl.add(Calendar.DATE, upperDay);
		Date startDate = cl.getTime();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		String start = dd.format(startDate);
		return start+" 00:00:00";
	}
	 /**
	  * 得到upperWeek周第一天
	  */
	  public static String getWeekFirst(int upperWeek) {
		  Calendar c = Calendar.getInstance();
		  c.add(Calendar.DATE, upperWeek);
		  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		  if (day_of_week == 0)  day_of_week = 7;
		  c.add(Calendar.DATE, -day_of_week + 1);
		  SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		  return dd.format(c.getTime())+" 00:00:00";
	  }
	 /**
	   * 得到upperMonth月第一天
	  */
	 public static String getMonthFirst(int upperMonth) {
		   Calendar calendar  = Calendar.getInstance();
		   calendar.add(Calendar.MONTH, upperMonth);
		   calendar.set( Calendar.DATE,  1 );
		   SimpleDateFormat simpleFormate  =   new  SimpleDateFormat( "yyyy-MM-dd" );
		   return simpleFormate.format(calendar.getTime())+" 00:00:00";
	 }
	 public static void main(String[] args) {
		/* String day=   getDayFirst(-1);
		 System.out.println("  day "+day);
		 
		 String months=   getMonthFirst(-1);
		 System.out.println("  months "+months);*/
		 String week= getWeekFirst(-7);
		 System.out.println(" week="+week);
	}
	 
	 
}







