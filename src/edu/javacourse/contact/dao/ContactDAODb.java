package edu.javacourse.contact.dao;

import edu.javacourse.contact.entity.Contact;
import edu.javacourse.contact.filter.ContactFilter;

import java.util.List;

/**
 * Project: CM2
 * User: vars
 * Date: 18/02/15
 * Time: 00:02
 * Created with IntelliJ IDEA.
 */
public class ContactDAODb implements ContactDAO {
    @Override
    public Long addContact(Contact contact) {
        return null;
    }

    @Override
    public Contact getContact(Long id) {
        return null;
    }

    @Override
    public void deleteContact(Long id) {

    }

    @Override
    public void editContact(Long id, Contact contact) {

    }

    @Override
    public List<Contact> listContacts() {
        return null;
    }

    @Override
    public List<Contact> findContact(ContactFilter filter) {
        return null;
    }
}
