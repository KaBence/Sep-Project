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
   * @param email String
   * @param firstName String
   * @param lastName String
   * @param phoneNumber String
   */
  public Member(String firstName, String lastName, String phoneNumber,
      String email)
  {

    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }



  /**
   * This function returns the first name of the person.
   * @return The first name of the person.
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * This function returns the last name of the person.
   * @return The last name of the person.
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * This function sets the firstName of the Member.
   * @param firstName The first name of the user.
   */
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * This function sets the last name of the Member.
   * @param lastName The last name of the user.
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
/**
 * This function returns the first name and last name of the Member
 * @return The firstName and lastName variables are being returned.
 */
public String getFullName(){
    return firstName + " " + lastName;
}
  /**
   * This function sets the phone number of the Member.
   * @param phoneNumber The phone number to set to.
   */
  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  /**
   * This function sets the email of the Member.
   * @param email The email address of the Member.
   */
  public void setEmail(String email)
  {
    this.email = email;
  }


  /**
   * This function returns the phone number of the Member.
   * @return The phone number of the Member.
   */
  public String getPhoneNumber()
  {
    return phoneNumber;
  }


  /**
   * This function returns the email of the Member.
   * @return The email address of the Member.
   */
  public String getEmail()
  {
    return email;
  }


  /**
   * The toString() function returns the firstName and lastName of the Member.
   * @return The firstName and lastName variables are being returned.
   */
  public String toString()
  {
    return firstName + " " + lastName;
  }


  /**
   * If the object is not null and is of the same class as this object, then return true if the first name, last name,
   * phone number, and email are equal
   * @param obj The object to compare to.
   * @return true if obj is equals to the current member otherwise false.
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
   * This function returns true if parameter name contains in the toString value of the Member.
   * @param name The name of the parameter to check for.
   * @return A boolean value.
   */
  public boolean contains(String name)
  {
        return toString().contains(name);
  }





}
