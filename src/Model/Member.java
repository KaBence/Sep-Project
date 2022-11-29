package Model;

public class Member {


    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String selection;


    public Member(String firstName, String lastName, String phoneNumber, String email, String selection)
    {

        this.firstName = firstName;
        this.lastName= lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.selection = selection;

    }

    //Getting and setting names


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSelection() {
        return selection;
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

    public void setSelection(String selection) {
        this.selection = selection;
    }

    //Getting and setting phoneNumber
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber()
    {
        this.phoneNumber = phoneNumber;
    }

    //Getting and setting emails
    public String getEmail()
    {
        return email;
    }

    public void setEmail()
    {
        this.email = email;
    }

    //The toString method
    public String toString()
    {
        return firstName + " " + lastName + " " +phoneNumber +" " + email + " " + selection;
    }

    //Comparing with equals
    public boolean equals(Object obj)
    {
        if(obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Member other = (Member) obj;
        return firstName.equals(other.firstName) && lastName.equals(other.lastName) && phoneNumber.equals(other.phoneNumber) &&email.equals(other.email) && selection.equals(selection);
    }

    public Member copy()
    {
        return new Member(firstName,lastName, phoneNumber, email, selection);

        //Member copyMember = new Member(name, phoneNumber, email);
        // return copyMember;
    }









}
