package edu.javacourse.contact.dao;

import edu.javacourse.contact.entity.Contact;
import edu.javacourse.contact.filter.ContactFilter;

import java.util.List;

/**
 * Project: CM2
 * User: vars
 * Date: 08/02/15
 * Time: 21:00
 * Created with IntelliJ IDEA.
 */
public interface ContactDAO {
    public Long addContact(Contact contact);
    public Contact getContact(Long id);
    public void deleteContact(Long id);
    public void editContact(Long id, Contact contact);

    public List<Contact> listContacts();
    public List<Contact> findContact(ContactFilter filter);

}
