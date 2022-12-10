package Model;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * > MyDate is a class that represents a date and time
 */
public class MyDate implements Serializable {
    private int day;
    private int month;
    private int year;
    private int min;
    private int hour;

    // This is a constructor that takes 3 parameters and sets the day, month and year of the MyDate object.
    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        min = -1;
        hour = -1;
    }

    // A constructor that takes 5 parameters and sets the day, month, year, hour and minute of the MyDate object.
    public MyDate(int day, int month, int year, int hour, int min) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.min = min;
        this.hour = hour;
    }

    // A constructor that takes no parameters.
    public MyDate() {
    }

    ;

    /**
     * This function returns the day of the month.
     *
     * @return The day of the month.
     */
    public int getDay() {
        return day;
    }

    /**
     * It takes a date and time in the form of a string, and returns a MyDate object
     *
     * @param date The date in the format dd/mm/yyyy
     * @param time The time of the event
     * @return A new MyDate object with the given parameters.
     */
    public static MyDate stringToDate(String date, String time) {
        String[] temp = date.split("/");
        int tempDate = Integer.valueOf(temp[0]);
        int tempMonth = Integer.valueOf(temp[1]);
        int tempyear = Integer.valueOf(temp[2]);
        String[] timetemp = time.split(":");
        int tempHour = Integer.valueOf(timetemp[0]);
        int tempMin = Integer.valueOf(timetemp[1]);
        return new MyDate(tempDate, tempMonth, tempyear, tempHour, tempMin);
    }
    /**
     * It takes a string and checks if it's a valid time format
     *
     * @param time The time you want to check.
     * @return A boolean value.
     */
    public static boolean timeFormat(String time) {
        boolean a = false;
        String[] timetemp = time.split(":");
        if (timetemp.length == 2) {
            try {
                int tempHour = Integer.valueOf(timetemp[0]);
                int tempMin = Integer.valueOf(timetemp[1]);
                a = true;
            } catch (NumberFormatException e) {
                return a;
            }
        }
        return a;
    }

    /**
     * This function sets the day of the month to the value of the parameter day.
     *
     * @param day The day of the month. Must be 1-31 and valid for the year and month, otherwise an
     * IllegalArgumentException will be thrown.
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * This function returns the month of the year.
     *
     * @return The month of the date.
     */
    public int getMonth() {
        return month;
    }

    /**
     * This function sets the month of the year.
     *
     * @param month The month of the year (1-12)
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * This function returns the year of the car
     *
     * @return The year of the car.
     */
    public int getYear() {
        return year;
    }

    /**
     * This function sets the year of the car.
     *
     * @param year The year of the movie.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * This function returns the hour of the day.
     *
     * @return The hour of the time object.
     */
    public int getHour() {
        return hour;
    }

    /**
     * This function sets the hour of the clock.
     *
     * @param hour The hour of the day, from 0 to 23.
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * Return the minimum element in the stack.
     *
     * @return The minimum value of the stack.
     */
    public int getMin() {
        return min;
    }

    /**
     * This function sets the minimum value of the range.
     *
     * @param min The minimum number of characters the user must enter.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * It returns a string that is the hour and minute of the time object
     *
     * @return The hour and minute of the time.
     */
    public String getStringTime() {
        return hour + ":" + min;
    }


  /**
   * If the object is not null and is of the same class as this object, then return true if the day, month, year, hour, and
   * minute are all equal, otherwise return false
   *
   * @param obj The object to compare this MyDate against.
   * @return The method returns true if the object is not null and the class is the same and the day, month, year, hour and
   * minute are the same.
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    MyDate other =(MyDate) obj;
    if (hour==-1&&min==-1)return day== other.getDay() && month== other.getMonth() && year== other.getYear();
    return day== other.getDay() && month== other.getMonth() && year== other.getYear() && min== other.getMin() && hour== other.getHour();

    }

    /**
     * If the hour and minute are -1, then return the date, otherwise return the date and time
     *
     * @return The date and time in the format of day/month/year hour:min
     */
    public String toString() {
        if (hour == -1 && min == -1) return day + "/" + month + "/" + year;
        return day + "/" + month + "/" + year + " " + hour + ":" + min;
    }

    /**
     * Create a new MyDate object with the current date.
     *
     * @return A new MyDate object with the current date.
     */
    public static MyDate today() {
        LocalDate current = LocalDate.now();
        return new MyDate(current.getDayOfMonth(), current.getMonthValue(), current.getYear());
    }

  /**
   * This function converts the date object to a LocalDate object
   *
   * @return The method is returning a LocalDate object.
   */
  public  LocalDate convertToLocalDate(){
    LocalDate temp=LocalDate.of(year,month,day);
    return temp;
  }


  /*public boolean isBefore(MyDate date2){
    if ((year<date2.year) || (day<date2.day && (month<date2.month || year<date2.year)) || (day>date2.day && (month<date2.month || year<date2.year)) || (month<date2.month && year==date2.year || month>date2.month && year<date2.year) || (day < date2.day)){
      return true;
    }
    else return false;*/

    /**
     * If the year is less than the year of the other date, return true. If the year is the same, but the month is less,
     * return true. If the year and month are the same, but the day is less, return true. Otherwise, return false
     *
     * @param date2 The date to compare to.
     * @return A boolean value.
     */
    public boolean isBefore(MyDate date2) {
        if (year < date2.year) {
            return true;
        } else if (year == date2.year && month < date2.month) {
            return true;
        } else if (year == date2.year && month == date2.month && day < date2.day) {
            return true;
        } else {
            return false;
        }
    }
}
