package Model;

import java.time.LocalDate;

public class MyDate
{
 private int day;
 private int month;
 private int year;
 public MyDate(int day, int month, int year){
   this.day=day;
   this.month=month;
   this.year=year;
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

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    MyDate other =(MyDate) obj;
    return day== other.day && month== other.month && year== other.year;

  }

  public String toString()
  {
    return "DATE:" + day +"/"+ month + "/"+ year;
  }

  public static MyDate today()
  {
    LocalDate currentDate=LocalDate.now();
    return new MyDate(currentDate.getDayOfMonth(),currentDate.getMonthValue(),currentDate.getYear());
  }
}
