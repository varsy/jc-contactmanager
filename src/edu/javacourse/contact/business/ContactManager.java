package edu.javacourse.contact.business;

import edu.javacourse.contact.dao.ContactDAO;
import edu.javacourse.contact.dao.ContactDAOFactory;
import edu.javacourse.contact.entity.Contact;
import edu.javacourse.contact.exception.DAOException;

/**
 * Project: CM2
 * User: vars
 * Date: 08/02/15
 * Time: 20:57
 * Created with IntelliJ IDEA.
 */
public class ContactManager {

    private ContactDAO dao;

    public ContactManager() throws DAOException {
        try {
            dao = ContactDAOFactory.getDAO();
        } catch (DAOException e) {
            e.printStackTrace(System.err);
        }
    }

    public Long addContact(Contact contact) {
        Long id = dao.addContact(contact);
        return id;
    }

    public void deleteContact(Long id) {
        dao.deleteContact(id);
    }

    public void listContacts() {
        dao.listContacts();
    }

    public Contact getContact(Long id) {
        return dao.getContact(id);
    }

    public void editContact(Long id, Contact contact) {
        dao.editContact(id, contact);
    }
}
