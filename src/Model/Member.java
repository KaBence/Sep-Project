package Model;

import java.io.Serializable;

public class Member implements Serializable {


    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;



    public Member(String firstName, String lastName, String phoneNumber, String email)
    {

        this.firstName = firstName;
        this.lastName= lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;


    }

    //Getting and setting names


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
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


    //The toString method
    public String toString()
    {
        return firstName + " " + lastName;
    }

    //Comparing with equals
    public boolean equals(Object obj)
    {
        if(obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Member other = (Member) obj;
        return firstName.equals(other.firstName) && lastName.equals(other.lastName) && phoneNumber.equals(other.phoneNumber) &&email.equals(other.email);
    }

    public Member copy()
    {
        return new Member(firstName,lastName, phoneNumber, email);

        //Member copyMember = new Member(name, phoneNumber, email);
        // return copyMember;
    }










}
