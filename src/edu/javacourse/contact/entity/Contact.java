package edu.javacourse.contact.entity;

/**
 * Project: CM2
 * User: vars
 * Date: 08/02/15
 * Time: 20:41
 * Created with IntelliJ IDEA.
 */
public class Contact {
    private Long contactID;
    private String surname;
    private String givenName;
    private String email;
    private String phone;

    public Long getContactID() {
        return contactID;
    }

    public void setContactID(Long contactID) {
        this.contactID = contactID;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactID=" + contactID +
                ", surname='" + surname + '\'' +
                ", givenName='" + givenName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }


}
