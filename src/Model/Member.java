package Model;

import java.io.Serializable;


/**
 * It's a class that represents a member of a club
 */
public class Member implements Serializable
{

  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String email;

  /**
   * This is a constructor. It is used to create an object of the class.
   */
  public Member(String firstName, String lastName, String phoneNumber,
      String email)
  {

    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  //Getting and setting names

  public String getFirstName()
  {
    return firstName;
  }

  /**
   * This function returns the last name of the person.
   *
   * @return The last name of the person.
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * This function sets the firstName variable to the value of the firstName parameter.
   *
   * @param firstName The first name of the user.
   */
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * This function sets the last name of the person.
   *
   * @param lastName The last name of the user.
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
/**
 * This function returns the first name and last name of the person.
 *
 * @return The firstName and lastName variables are being returned.
 */
public String getFullName(){
    return firstName + " " + lastName;
}
  /**
   * This function sets the phone number of the person
   *
   * @param phoneNumber The phone number to send the message to.
   */
  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  /**
   * This function sets the email of the user.
   *
   * @param email The email address of the user.
   */
  public void setEmail(String email)
  {
    this.email = email;
  }

  //Getting and setting phoneNumber
  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  //Getting and setting emails
  public String getEmail()
  {
    return email;
  }


  /**
   * The toString() function returns a string representation of the object
   *
   * @return The firstName and lastName variables are being returned.
   */
  public String toString()
  {
    return firstName + " " + lastName;
  }

  //Comparing with equals
  /**
   * If the object is not null and is of the same class as this object, then return true if the first name, last name,
   * phone number, and email are equal
   *
   * @param obj The object to compare to.
   * @return The hashcode of the object.
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Member other = (Member) obj;
    return firstName.equals(other.firstName) && lastName.equals(other.lastName)
        && phoneNumber.equals(other.phoneNumber) && email.equals(other.email);
  }

  /**
   * This function returns true if the string passed in as a parameter is contained in the string that is returned by the
   * toString() function
   *
   * @param name The name of the parameter to check for.
   * @return A boolean value.
   */
  public boolean contains(String name)
  {
        return toString().contains(name);
  }





}
