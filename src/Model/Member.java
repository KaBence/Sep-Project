package Model;

public class Member {


    private String name;
    private String phoneNumber;
    private String email;

    public Member()
    {
        email = "";
        name = "";
        phoneNumber="";
    }

    public Member(String name, String phoneNumber, String email)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    //Getting and setting names
    public String getName()
    {
        return name;
    }

    public void setName()
    {
        this.name = name;
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
        return name + "" +phoneNumber +"" + email;
    }

    //Comparing with equals
    public boolean equals(Object obj)
    {
        if(obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Member other = (Member) obj;
        return name.equals(other.name) && phoneNumber.equals(other.phoneNumber) &&email.equals(other.email);
    }

    public Member copy()
    {
        return new Member(name, phoneNumber, email);

        //Member copyMember = new Member(name, phoneNumber, email);
        // return copyMember;
    }









}
