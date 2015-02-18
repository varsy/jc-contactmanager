package edu.javacourse.contact.dao;

import edu.javacourse.contact.entity.Contact;
import edu.javacourse.contact.filter.ContactFilter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.PropertyResourceBundle;

/**
 * Project: CM2
 * User: vars
 * Date: 18/02/15
 * Time: 00:02
 * Created with IntelliJ IDEA.
 */
public class ContactDAODb implements ContactDAO {

    private static PropertyResourceBundle confProps;
    private static String jdbcUrl;
    private static String jdbcUsername;
    private static String jdbcPassword;

    static {
        try {
            confProps = (PropertyResourceBundle) PropertyResourceBundle.getBundle("conf/ContactDAODb");
            jdbcUrl = "jdbc:postgresql://" + confProps.getObject("contact.dao.db.host") + ":" + confProps.getObject("contact.dao.db.port") + "/" + confProps.getObject("contact.dao.db.database");
            jdbcUsername = (String) confProps.getObject("contact.dao.db.user");
            jdbcPassword = (String) confProps.getObject("contact.dao.db.pass");
        } catch (Exception e) {
            System.out.println("Can't parse properties file.");
            e.printStackTrace(System.err);
            System.exit(1);
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find database driver.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl,jdbcUsername,jdbcPassword);
    }

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
