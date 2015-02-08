package edu.javacourse.contact;

import edu.javacourse.contact.business.ContactManager;
import edu.javacourse.contact.entity.Contact;
import edu.javacourse.contact.exception.DAOException;

/**
 * Project: CM2
 * User: vars
 * Date: 08/02/15
 * Time: 21:47
 * Created with IntelliJ IDEA.
 */
public class ContactTester {
    public static void main(String[] args) throws DAOException {
        ContactManager cm = new ContactManager();

        Contact contact1 = new Contact();
        contact1.setGivenName("Andrey");
        contact1.setSurname("Sizov");
        contact1.setEmail("a@b.com");
        contact1.setPhone("1234567");

        cm.addContact(contact1);
        cm.addContact(contact1);

        cm.listContacts();

        System.out.println("Getting contact: " + cm.getContact(contact1.getContactID()));

        cm.deleteContact(contact1.getContactID());

        cm.listContacts();





    }
}
