package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class MyDate implements Serializable
{
 private int day;
 private int month;
 private int year;
 private int min;
 private int hour;

 public MyDate(int day, int month, int year){
   this.day=day;
   this.month=month;
   this.year=year;
 }
 public MyDate(int day, int month, int year, int hour, int min){
   this.day=day;
   this.month=month;
   this.year=year;
   this.min=min;
   this.hour=hour;
 }
 public MyDate(){};

  public int getDay()
  {
    return day;
  }

  public void setDay(int day)
  {
    this.day = day;
  }

  public int getMonth()
  {
    return month;
  }

  public void setMonth(int month)
  {
    this.month = month;
  }

  public int getYear()
  {
    return year;
  }

  public void setYear(int year)
  {
    this.year = year;
  }

  public int getHour()
  {
    return hour;
  }

  public void setHour(int hour)
  {
    this.hour = hour;
  }

  public int getMin()
  {
    return min;
  }

  public void setMin(int min)
  {
    this.min = min;
  }


  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    MyDate other =(MyDate) obj;
    return day== other.day && month== other.month && year== other.year && min== other.min && hour== other.hour;

  }

  public String toString()
  {
    return day +"/"+ month + "/"+ year +" "+hour+":"+min;
  }


  public static MyDate today()
  {
    LocalDate currentDate=LocalDate.now();
    return new MyDate(currentDate.getDayOfMonth(),currentDate.getMonthValue(),currentDate.getYear());

  }
  //for setting the events (time)
public MyDate newDate(){
    return  new MyDate(day,month,year,min,hour);
}
}
